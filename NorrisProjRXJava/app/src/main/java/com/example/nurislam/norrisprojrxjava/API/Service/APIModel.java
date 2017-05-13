package com.example.nurislam.norrisprojrxjava.API.Service;

import com.example.nurislam.norrisprojrxjava.API.POJO.ChuckNorrisApi;
import com.example.nurislam.norrisprojrxjava.API.POJO.Value;

import retrofit2.Call;
import retrofit2.http.GET;
import rx.Observable;

/**
 * Created by Nurislam on 23.12.2016.
 */

public interface APIModel {
    @GET("jokes/random")
    Observable<ChuckNorrisApi> getChuckNorrisApi();
}
