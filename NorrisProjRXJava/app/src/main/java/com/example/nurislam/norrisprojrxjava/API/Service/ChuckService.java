package com.example.nurislam.norrisprojrxjava.API.Service;

import android.util.Log;

import com.example.nurislam.norrisprojrxjava.API.POJO.ChuckNorrisApi;
import com.example.nurislam.norrisprojrxjava.API.POJO.Value;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.IOException;

import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Observable;
import rx.Subscriber;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import rx.subjects.BehaviorSubject;

/**
 * Created by Nurislam on 23.12.2016.
 */

public class ChuckService {

    private static Value value;

    private static Observable<ChuckNorrisApi> observableRetrofit;
    private static BehaviorSubject<ChuckNorrisApi> observableModelsList;
    private static Subscription subscription;

    public static void init() {
        RxJavaCallAdapterFactory rxAdapter = RxJavaCallAdapterFactory.createWithScheduler(Schedulers.io());

        Gson gson = new GsonBuilder().create();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://api.icndb.com/")
                .addConverterFactory(GsonConverterFactory.create(gson))
                .addCallAdapterFactory(rxAdapter)
                .build();
        APIModel apiService = retrofit.create(APIModel.class);

        observableRetrofit = apiService.getChuckNorrisApi();
    }

    public static Observable<ChuckNorrisApi> getValue() throws IOException {
        observableModelsList = BehaviorSubject.create();

        if (subscription != null && !subscription.isUnsubscribed()) {
            subscription.unsubscribe();
        }

        init();
        subscription = observableRetrofit.subscribe(new Subscriber<ChuckNorrisApi>() {
            @Override
            public void onCompleted() {
            }

            @Override
            public void onError(Throwable e) {
                observableModelsList.onError(e);
            }

            @Override
            public void onNext(ChuckNorrisApi chuckNorrisApi) {
                observableModelsList.onNext(chuckNorrisApi);
            }
        });
        return observableModelsList;
    }

    public static void saveValue(Value valueSaving){
        value = valueSaving;
    }
}
