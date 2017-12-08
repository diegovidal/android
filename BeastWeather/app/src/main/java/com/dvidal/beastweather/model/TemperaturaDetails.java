package com.dvidal.beastweather.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by diegovidal on 06/12/2017.
 */

public class TemperaturaDetails {

    @SerializedName("temp_max")
    double temperatureMax;

    @SerializedName("temp_min")
    double temperatureMin;

    public TemperaturaDetails() {
    }

    public double getTemperatureMax() {
        return temperatureMax;
    }

    public double getTemperatureMin() {
        return temperatureMin;
    }
}
