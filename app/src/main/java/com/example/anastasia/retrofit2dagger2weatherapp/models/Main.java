package com.example.anastasia.retrofit2dagger2weatherapp.models;

/**
 * Created by anastasia on 2/6/18.
 */

public class Main {

    private Double temp;
    private Double temp_min;
    private Double temp_max;

    public Main() {
    }

    public Double getTemp() {
        return temp;
    }

    public Double getTempMin() {
        return temp_min;
    }

    public Double getTempMax() {
        return temp_max;
    }

    public void setTemp(Double temp) {
        this.temp = temp;
    }

    public void setTemp_min(Double temp_min) {
        this.temp_min = temp_min;
    }

    public void setTemp_max(Double temp_max) {
        this.temp_max = temp_max;
    }
}
