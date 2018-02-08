package com.example.anastasia.retrofit2dagger2weatherapp.rest;

import com.example.anastasia.retrofit2dagger2weatherapp.models.Weather;

import retrofit.Call;
import retrofit.http.GET;
import retrofit.http.Query;

/**
 * Created by anastasia on 2/6/18.
 */

public class RetrofitApi {
    //API REST methods
    public interface WeatherApiInterface {
        @GET("/data/2.5/weather?")
        Call<Weather> getWeather(@Query("id") int cityID, @Query("appid") String appId);
    }
}
