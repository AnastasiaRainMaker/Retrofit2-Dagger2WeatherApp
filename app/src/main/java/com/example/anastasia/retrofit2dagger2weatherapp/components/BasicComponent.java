package com.example.anastasia.retrofit2dagger2weatherapp.components;

import com.example.anastasia.retrofit2dagger2weatherapp.ui.WeatherActivity;
import com.example.anastasia.retrofit2dagger2weatherapp.modules.AppModule;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by anastasia on 2/5/18.
 */

@Singleton
@Component (modules = {AppModule.class})
public interface BasicComponent {
    void inject(WeatherActivity mainActivity);
}
