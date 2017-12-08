package com.dvidal.beastweather.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by diegovidal on 06/12/2017.
 */

public class WeatherListModel {

    @SerializedName("list")
    public List<WeatherFullDetails> fullDetailsList;

    public WeatherListModel() {
    }
}
