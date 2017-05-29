package com.example.nurislam.rxjavahw;

import rx.Observable;

/**
 * Created by Nurislam on 29.05.2017.
 */

public class MyTransformer<T> implements Observable.Transformer<T, T> {
    @Override
    public Observable<T> call(Observable<T> obs) {
        return obs.doOnSubscribe(()->{
            System.out.println("_________________________________________");
        });
    }
}
