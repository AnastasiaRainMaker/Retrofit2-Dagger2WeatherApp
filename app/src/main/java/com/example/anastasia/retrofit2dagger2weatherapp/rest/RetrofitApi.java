package com.example.anastasia.retrofit2dagger2weatherapp.rest;

import com.example.anastasia.retrofit2dagger2weatherapp.models.Weather;

import retrofit.Call;
import retrofit.GsonConverterFactory;
import retrofit.Retrofit;
import retrofit.http.GET;
import retrofit.http.Query;

/**
 * Created by anastasia on 2/6/18.
 */

public class RetrofitApi {

//    public static WeatherApiInterface getInstance() {
//        String baseUrl = "http://api.openweathermap.org";
//        Retrofit retrofit = new Retrofit.Builder()
//            .baseUrl(baseUrl)
//            .addConverterFactory(GsonConverterFactory.create())
//            .build();
//    return retrofit.create(WeatherApiInterface.class);
//    }

    //API REST methods
    public interface WeatherApiInterface {
        @GET("/data/2.5/weather?")
        Call<Weather> getWeather(@Query("id") int cityID, @Query("appid") String appId);
    }
}
