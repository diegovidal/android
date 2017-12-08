package com.dvidal.beastweather.services;

import com.dvidal.beastweather.model.WeatherListModel;

import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by diegovidal on 06/12/2017.
 */

public interface WeatherWebServices {

    @GET("/data/2.5/forecast")
    Observable<WeatherListModel> getWeather(

            @Query("id") String id,
            @Query("units") String units,
            @Query("cnt") String cnt,
            @Query("APPID") String apiKey
    );
}
