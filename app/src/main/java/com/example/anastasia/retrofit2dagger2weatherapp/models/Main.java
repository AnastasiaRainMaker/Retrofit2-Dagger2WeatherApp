package com.example.anastasia.retrofit2dagger2weatherapp.models;

/**
 * Created by anastasia on 2/6/18.
 */

public class Main {

    private Double temp;
    private Integer pressure;
    private Integer humidity;
    private Double temp_min;
    private Double temp_max;

    public Double getTemp() {
        return temp;
    }

    public void setTemp(Double temp) {
        this.temp = temp;
    }

    public Integer getPressure() {
        return pressure;
    }

    public void setPressure(Integer pressure) {
        this.pressure = pressure;
    }

    public Integer getHumidity() {
        return humidity;
    }

    public void setHumidity(Integer humidity) {
        this.humidity = humidity;
    }

    public Double getTempMin() {
        return temp_min;
    }

    public void setTempMin(Double temp_min) {
        this.temp_min = temp_min;
    }

    public Double getTempMax() {
        return temp_max;
    }

    public void setTempMax(Double temp_max) {
        this.temp_max = temp_max;
   }

}
