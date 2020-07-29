package com.example.test.ui;

import com.example.test.Utils;
import com.example.test.api.ListApi;
import com.example.test.model.ModelList;
import com.example.test.model.ModelVotes;
import com.example.test.model.SchedulerProvider;
import com.example.test.utils.ChannelBuilder;

import java.io.IOException;
import java.io.InputStream;
import java.lang.ref.WeakReference;
import java.util.List;

import WC.TestResMsg;
import WC.TestSendMsg;
import WC.WellConnectedGrpc;
import io.grpc.ManagedChannel;
import io.grpc.android.AndroidChannelBuilder;
import io.grpc.stub.StreamObserver;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.disposables.Disposable;

public class MyListPresenter {

    WeakReference<MainActivity> view;
    private final ListApi api;
    final SchedulerProvider schedulerProvider;
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

    public void testService(final InputStream certificate) {
        final ManagedChannel channel  = ChannelBuilder.buildTls("wcapi.demeco.com.ar", 5001, certificate, "wcapi.demeco.com.ar");
        try {
            certificate.close();
        } catch (final IOException e) {
            e.printStackTrace();
        }
        final WellConnectedGrpc.WellConnectedStub stub = WellConnectedGrpc.newStub(channel);
        final TestSendMsg request = TestSendMsg.newBuilder().setNombre("nb").setCategoria("asd").build();
        stub.test(request, new StreamObserver<TestResMsg>() {
            @Override
            public void onNext(TestResMsg value) {
                Observable.just(value)
                        .subscribeOn(schedulerProvider.ui())
                        .observeOn(schedulerProvider.ui())
                        .subscribe(message -> view.get().showMessage(message.getResponse()), this::onError);
            }

            @Override
            public void onError(final Throwable t) {
                onLoadError(t);
            }

            @Override
            public void onCompleted() {

            }
        });
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

    void onLoadError(final Throwable t) {
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
