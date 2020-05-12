package com.example.test.di;

import com.example.test.api.ListApi;
import com.example.test.model.SchedulerProvider;
import com.example.test.ui.MyListPresenter;

import hu.akarnokd.rxjava3.retrofit.RxJava3CallAdapterFactory;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ComponentsLocator {

    private static ListApi listApi;
    private static MyListPresenter presenter;
    private static SchedulerProvider schedulerProvider;

    private static ListApi getListApi() {
        if (listApi == null) {
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl("https://private-0001be-test20799.apiary-mock.com")
                    .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
                    .addConverterFactory(GsonConverterFactory.create())
                    .client(new OkHttpClient.Builder().build())
                    .build();
            listApi = retrofit.create(ListApi.class);
        }
        return listApi;
    }

    private static SchedulerProvider getSchedulerProvider() {
        if (schedulerProvider == null) {
            schedulerProvider = new SchedulerProvider();
        }
        return schedulerProvider;
    }

    public static MyListPresenter getPresenter() {
        return new MyListPresenter(getListApi(), getSchedulerProvider());
    }
}
