package com.example.nurislam.gisproj;


import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Nurislam on 11.11.2016.
 */

public class WeatherService {
    WeatherAPI weatherAPI;
    public static final String KEY = "26cb4382fc8ccfb6b3ee50348176885c";

    public static WeatherAPI getAPI(){
        if(weatherAPI == null){
            Retrofit retrofit  = new Retrofit.Builder()
                    .baseUrl("http://api.openweathermap.org/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
            weatherAPI = retrofit.create(WeatherAPI.class);
        }
        return weatherAPI;
    }


    public static MyWeather getCityWeatherByName(String name) throws Exception{
        Response<MyWeather> response = WeatherService.getAPI().getWeatherByName(name, KEY).execute();
        return response.body();
    }
}
