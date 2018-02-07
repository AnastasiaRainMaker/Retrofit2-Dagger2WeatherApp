package com.example.anastasia.retrofit2dagger2weatherapp.ui;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.AutoCompleteTextView;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.anastasia.retrofit2dagger2weatherapp.MyApp;
import com.example.anastasia.retrofit2dagger2weatherapp.R;
import com.example.anastasia.retrofit2dagger2weatherapp.models.Main;
import com.example.anastasia.retrofit2dagger2weatherapp.models.Weather;
import com.example.anastasia.retrofit2dagger2weatherapp.rest.RetrofitApi;
import java.util.List;
import javax.inject.Inject;
import retrofit.Call;
import retrofit.Callback;
import retrofit.Response;
import retrofit.Retrofit;

public class WeatherActivity extends AppCompatActivity {

    @Inject
    Context context;

    @Inject
    RetrofitApi.WeatherApiInterface apiService;

    ImageView image;
    AutoCompleteTextView autoCompleteTextView;
    TextView tempView;
    TextView minView;
    TextView maxView;
    TextView cityView;
    String link;
    int cityId;
    String appId;
    double temp;
    double max;
    double min;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weather);
        MyApp.app().basicComponent().inject(this);
//        apiService = AppModule.provideWeatherApiInterface();
        image = findViewById(R.id.image);
        tempView = findViewById(R.id.temp);
        minView = findViewById(R.id.min);
        maxView =  findViewById(R.id.max);
        cityView = findViewById(R.id.city_name);
        cityId = 524901;
        appId = "012db5f55ecd002e85825976ad49243d";
        link = "https://s-media-cache-ak0.pinimg.com/originals/c1/1e/ea/c11eeac4b497feb04bcc74bf54fdada9.jpg";
        loadImage(link);
        getWeather(cityId, appId);
    }

    public void loadImage(String link){
        Glide.with(context).load(link).into(image);
    }

    public void getWeather(final int cityId, String appId) {
       // startActivity(new Intent(this, WeatherActivityTwo.class));
        Call<Weather> call = apiService.getWeather(cityId, appId);
        call.enqueue(new Callback<Weather>() {
            @Override
            public void onResponse(Response<Weather> response, Retrofit retrofit) {
                if(response.isSuccess()) {
                    //Handle the received weather data here
                    Main main = response.body().getMain();
                    temp = main.getTemp();
                    max = main.getTempMax();
                    min = main.getTempMin();
                    tempView.setText(String.valueOf(temp));
                    maxView.setText(String.valueOf(max));
                    minView.setText(String.valueOf(min));
                    cityView.setText(String.valueOf(cityId));
                } else {
                    Log.e("MainActivity", "Response received but request not successful. Response: " + response.raw());
                }
            }

            @Override
            public void onFailure(Throwable t) {
                Log.e("MainActivity", "Request error!");
            }
        });
    }
}
