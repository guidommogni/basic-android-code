package com.example.test.ui;

import com.example.test.api.ListApi;
import com.example.test.model.ModelList;
import com.example.test.model.ModelVotes;
import com.example.test.model.SchedulerProvider;

import java.lang.ref.WeakReference;
import java.util.List;

import io.reactivex.rxjava3.disposables.Disposable;

public class MyListPresenter {

    private WeakReference<MainActivity> view;
    private final ListApi api;
    private final SchedulerProvider schedulerProvider;
    private Disposable disposable;
    private List<ModelVotes> currentList;
    private boolean isLoadingData = false;

    public MyListPresenter(final ListApi api, final SchedulerProvider schedulerProvider) {
        this.api = api;
        this.schedulerProvider = schedulerProvider;
    }

    public void onAttachView(final MainActivity view) {
        this.view = new WeakReference<>(view);
    }

    public void onScrollEvent(final Integer lastVisibleIndex) {
        if (lastVisibleIndex != null && lastVisibleIndex == currentList.size() - 1 && !isLoadingData) {
            isLoadingData = true;
            addMoreItems();
        }
    }

    private void addMoreItems() {
        disposable = api.getMoreVotes()
                .subscribeOn(schedulerProvider.io())
                .observeOn(schedulerProvider.ui())
                .flatMapIterable(list -> list)
                .map(ModelVotes::getFromDto)
                .toList()
                .subscribe(this::addMoreItem, this::onLoadError);
    }

    private void addMoreItem(final List<ModelVotes> votes) {
        if (votes != null && !votes.isEmpty() && isViewAttached()) {
            currentList.addAll(votes);
            view.get().addMoreVotes(votes);
        }
        isLoadingData = false;
    }

    private boolean isViewAttached() {
        return view != null && view.get() != null;
    }

    public void getList() {
        isLoadingData = true;
        disposable = api.getList()
                .subscribeOn(schedulerProvider.io())
                .observeOn(schedulerProvider.ui())
                .map(ModelList::getFromDto)
                .subscribe(this::loadCompleteList, this::onLoadError);
    }

    private void loadCompleteList(final ModelList modelList) {
        if (modelList != null) {
            if (!modelList.getVotes().isEmpty()) {
                currentList = modelList.getVotes();
                loadList(modelList.getVotes());
            }
            if (modelList.getTitle() != null) {
                loadTitle(modelList.getTitle());
            }
        }
        isLoadingData = false;
    }

    private void loadTitle(String title) {
        if (isViewAttached()) {
            view.get().setToolbarTitle(title);
        }
    }

    private void loadList(final List<ModelVotes> votes) {
        if (isViewAttached()) {
            view.get().addMoreVotes(votes);
        }
    }

    private void onLoadError(final Throwable t) {
        if (isViewAttached()) {
            view.get().shwError();
        }
        isLoadingData = false;
    }

    public void onDetachView() {
        if (disposable != null && !disposable.isDisposed()) {
            disposable.dispose();
        }
    }
}
