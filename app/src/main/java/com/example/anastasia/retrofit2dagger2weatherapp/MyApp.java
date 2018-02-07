package com.example.anastasia.retrofit2dagger2weatherapp;

import android.app.Application;

import com.example.anastasia.retrofit2dagger2weatherapp.components.BasicComponent;
import com.example.anastasia.retrofit2dagger2weatherapp.components.DaggerBasicComponent;
import com.example.anastasia.retrofit2dagger2weatherapp.modules.AppModule;

/**
 * Created by anastasia on 2/5/18.
 */

public class MyApp extends Application {
    private static MyApp app;
    private BasicComponent basicComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        app = this;
        basicComponent = DaggerBasicComponent.builder()
                .appModule(new AppModule(getApplicationContext()))
                .build();
    }

    public static MyApp app() {return app;}

    public BasicComponent basicComponent() {return basicComponent;}
}
