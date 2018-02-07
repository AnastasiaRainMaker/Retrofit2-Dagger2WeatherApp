package com.example.anastasia.retrofit2dagger2weatherapp.modules;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.example.anastasia.retrofit2dagger2weatherapp.rest.RetrofitApi;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import retrofit.GsonConverterFactory;
import retrofit.Retrofit;

/**
 * Created by anastasia on 2/5/18.
 */

@Module
public class AppModule {

    private Context context;

    public AppModule(Context context) {
        this.context = context;
    }

    @Singleton @Provides
    public Context provideContext(){
        return context;
    }

    @Singleton @Provides
    public SharedPreferences provideSharedPreferences(Context context){
        return PreferenceManager.getDefaultSharedPreferences(context);
    }
    @Singleton @Provides
    public RetrofitApi.WeatherApiInterface provideWeatherApiInterface() {
        String baseUrl = "http://api.openweathermap.org";
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        return retrofit.create(RetrofitApi.WeatherApiInterface.class);
    }

}
