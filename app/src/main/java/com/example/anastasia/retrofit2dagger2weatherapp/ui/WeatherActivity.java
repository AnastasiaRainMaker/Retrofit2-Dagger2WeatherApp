package com.example.anastasia.retrofit2dagger2weatherapp.ui;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import com.bumptech.glide.Glide;
import com.example.anastasia.retrofit2dagger2weatherapp.MyApp;
import com.example.anastasia.retrofit2dagger2weatherapp.R;
import com.example.anastasia.retrofit2dagger2weatherapp.models.Main;
import com.example.anastasia.retrofit2dagger2weatherapp.models.Place;
import com.example.anastasia.retrofit2dagger2weatherapp.models.Weather;
import com.example.anastasia.retrofit2dagger2weatherapp.rest.RetrofitApi;
import com.google.gson.Gson;
import org.json.JSONException;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Objects;
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

    private ImageView image;
    private Spinner spinner;
    private TextView tempView;
    private TextView minView;
    private TextView maxView;
    private TextView cityView;
    private String cityName;
    private Integer cityId;
    private String appId;
    double temp;
    double max;
    double min;
    private Place[] placeAr;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weather);
        MyApp.app().basicComponent().inject(this);
        appId = "012db5f55ecd002e85825976ad49243d";
        String link = "https://s-media-cache-ak0.pinimg.com/originals/c1/1e/ea/c11eeac4b497feb04bcc74bf54fdada9.jpg";
        initView();
        loadImage(link);
        try {
            parseJson();
        } catch (JSONException | IOException e) {
            e.printStackTrace();
        }
        setUpSpinner();
    }

    private void initView() {
        image = findViewById(R.id.image);
        tempView = findViewById(R.id.temp);
        tempView.setVisibility(View.GONE);
        minView = findViewById(R.id.min);
        minView.setVisibility(View.GONE);
        maxView =  findViewById(R.id.max);
        maxView.setVisibility(View.GONE);
        cityView = findViewById(R.id.city_name);
        cityView.setVisibility(View.GONE);
        spinner = findViewById(R.id.spinner);
    }

    private void setUpSpinner() {
        final int[] check = {0};
        ArrayList<String> cities = new ArrayList<>();
        if (placeAr != null) {
            for(Place place : placeAr) {
                cities.add(place.getName());
            }
        }
        ArrayAdapter adapter = new ArrayAdapter<>(context, R.layout.spinner_dropdown_item, cities);
        adapter.setDropDownViewResource(R.layout.spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                if (++check[0] > 1) {
                    cityName = spinner.getSelectedItem().toString();
                    getWeather(cityName, appId);
                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

    }
    public void loadImage(String link){
        Glide.with(context).load(link).into(image);
    }

    public void getWeather(final String cityName, String appId) {
        //look for the id of the selected city
        if (placeAr != null) {
            for(Place place : placeAr) {
                if(Objects.equals(place.getName(), cityName)) {
                    cityId = place.getId();
                }
            }
        }
        //perform Retrofit call
        if(cityId != null) {
            Call<Weather> call = apiService.getWeather(cityId, appId);
            call.enqueue(new Callback<Weather>() {
                @Override
                public void onResponse(Response<Weather> response, Retrofit retrofit) {
                    if (response.isSuccess()) {
                        //Handle the received weather data here
                        Main main = response.body().getMain();
                        temp = main.getTemp();
                        max = main.getTempMax();
                        min = main.getTempMin();
                        //updateUI
                        tempView.setVisibility(View.VISIBLE);
                        maxView.setVisibility(View.VISIBLE);
                        minView.setVisibility(View.VISIBLE);
                        cityView.setVisibility(View.VISIBLE);

                        tempView.setText(String.format(context.getResources().getString(R.string.temperature),temp));
                        maxView.setText(String.format(context.getResources().getString(R.string.max_temp),max));
                        minView.setText(String.format(context.getResources().getString(R.string.min_temp),min));
                        cityView.setText(cityName);
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

    public void parseJson() throws JSONException, IOException {
        //Convert JSON to an array of Java objects
        Gson gson = new Gson();
        placeAr = gson.fromJson(getDataFromRes(), Place[].class);
    }

    public String getDataFromRes() throws IOException {
        //get Data from inner JSON
        StringBuilder sb = new StringBuilder();
         try (InputStream is = getResources().openRawResource(R.raw.citylist)) {
            BufferedReader reader = new BufferedReader(new InputStreamReader(is, "UTF-8"));
            String line;
            while ((line = reader.readLine()) != null) {
                sb.append(line);
            }
        }
        return sb.toString();
    }
}
