package com.example.test.api;

import java.util.List;

import io.reactivex.rxjava3.core.Observable;
import retrofit2.http.GET;

public interface ListApi {

    @GET("more_votes")
    Observable<List<Languages>> getMoreVotes();

    @GET("list")
    Observable<MyList> getList();
}
