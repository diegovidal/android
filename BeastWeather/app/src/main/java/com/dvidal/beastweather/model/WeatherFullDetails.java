package com.dvidal.beastweather.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by diegovidal on 06/12/2017.
 */

public class WeatherFullDetails {

    double pressure;
    double humidity;
    double speed;

    @SerializedName("main")
    TemperaturaDetails temperaturaDetails;

    @SerializedName("weather")
    List<WeatherDetails> weatherDetails;

    public double getPressure() {
        return pressure;
    }

    public double getHumidity() {
        return humidity;
    }

    public double getSpeed() {
        return speed;
    }

    public TemperaturaDetails getTemperaturaDetails() {
        return temperaturaDetails;
    }

    public List<WeatherDetails> getWeatherDetails() {
        return weatherDetails;
    }
}
