package com.dvidal.beastweather.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by diegovidal on 06/12/2017.
 */

public class WeatherDetails {

    @SerializedName("main")
    String basicWeatherDescription;

    @SerializedName("description")
    String detailedWeatherDescription;

    @SerializedName("icon")
    String weatherIcon;

    public WeatherDetails() {
    }

    public String getBasicWeatherDescription() {
        return basicWeatherDescription;
    }

    public String getDetailedWeatherDescription() {
        return detailedWeatherDescription;
    }

    public String getWeatherIcon() {
        return weatherIcon;
    }
}
