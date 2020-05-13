package com.example.test.ui;

import com.example.test.api.Languages;
import com.example.test.api.ListApi;
import com.example.test.api.MyList;
import com.example.test.model.SchedulerProvider;

import org.junit.Before;
import org.junit.Test;

import java.util.LinkedList;
import java.util.List;

import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.schedulers.Schedulers;

import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class MyPresenterTest {

    private ListApi listApi;
    private MyListPresenter presenter;
    private MainActivity view;

    @Before
    public void setUp() {
       listApi = mock(ListApi.class);
        SchedulerProvider schedulerProvider = mock(SchedulerProvider.class);
        when(schedulerProvider.ui()).thenReturn(Schedulers.trampoline());
        when(schedulerProvider.io()).thenReturn(Schedulers.trampoline());
        view = mock(MainActivity.class);
        presenter = new MyListPresenter(listApi, schedulerProvider);
        presenter.onAttachView(view);
    }

    @Test
    public void givenAResponseWithTitleThenCallViewShowListAndShowTitle() {
        when(listApi.getList()).thenReturn(Observable.just(getList()));

        presenter.getList();

        verify(view).setToolbarTitle("title");
        verify(view).addMoreVotes(anyList());
    }

    @Test
    public void givenOnScrollEventThenCallAddMoreItems() {
        when(listApi.getList()).thenReturn(Observable.just(getList()));
        when(listApi.getMoreVotes()).thenReturn(Observable.just(getLanguagesList()));

        presenter.getList();
        presenter.onScrollEvent(2);

        verify(view, times(2)).addMoreVotes(anyList());
    }

    @Test
    public void givenOnScrollEventTwiceThenCallAddMoreItemsOnes() {
        when(listApi.getList()).thenReturn(Observable.just(getList()));
        when(listApi.getMoreVotes()).thenReturn(Observable.just(getLanguagesList()));

        presenter.getList();
        presenter.onScrollEvent(2);
        presenter.onScrollEvent(2);

        verify(view, times(2)).addMoreVotes(anyList());
    }

    @Test
    public void givenAnEmptyListThenNotCallAddMoreItems() {
        when(listApi.getList()).thenReturn(Observable.just(getList()));
        when(listApi.getMoreVotes()).thenReturn(Observable.just(new LinkedList<>()));

        presenter.getList();
        presenter.onScrollEvent(2);

        verify(view).addMoreVotes(anyList());
    }

    @Test
    public void givenOnLittleScrollEventThenNotCallAddMoreItems() {
        when(listApi.getList()).thenReturn(Observable.just(getList()));
        when(listApi.getMoreVotes()).thenReturn(Observable.just(getLanguagesList()));

        presenter.getList();
        presenter.onScrollEvent(1);

        verify(view).addMoreVotes(anyList());
    }

    @Test
    public void givenAResponseWithoutTitleThenCallViewShowList() {
        when(listApi.getList()).thenReturn(Observable.just(getWithoutTitle()));

        presenter.getList();

        verify(view, never()).setToolbarTitle(anyString());
        verify(view).addMoreVotes(anyList());
    }

    @Test
    public void givenAResponseWithoutListThenCallViewShowTitle() {
        when(listApi.getList()).thenReturn(Observable.just(getWithoutElement()));

        presenter.getList();

        verify(view, never()).addMoreVotes(anyList());
    }

    private MyList getList() {
        return new MyList.Builder()
                .withTitle("title")
                .withDescription("description")
                .withLanguages(getLanguagesList())
                .build();
    }

    private MyList getWithoutTitle() {
        return new MyList.Builder()
                .withDescription("description")
                .withLanguages(getLanguagesList())
                .build();
    }

    private MyList getWithoutElement() {
        return new MyList.Builder()
                .withDescription("description")
                .withLanguages(new LinkedList<>())
                .build();
    }

    private List<Languages> getLanguagesList() {
        List<Languages> votes = new LinkedList<>();
        votes.add(new Languages("asd", 3));
        votes.add(new Languages("asd", 3));
        votes.add(new Languages("asd", 3));
        return votes;
    }
}
