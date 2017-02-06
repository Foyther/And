package com.example.nurislam.gisproj;

import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by Nurislam on 11.11.2016.
 */

public class WeatherAPI {
    @GET("/data/2.5/group?units=metric&lang=ru")
    Call<MyWeather> getWeatherById(@Query("id") String id, @Query("appid") String apiKey);



    @GET("/data/2.5/find?units=metric&lang=ru")
    Call<MyWeather> getWeatherByName(@Query("q") String name, @Query("appid") String apiKey);
}
