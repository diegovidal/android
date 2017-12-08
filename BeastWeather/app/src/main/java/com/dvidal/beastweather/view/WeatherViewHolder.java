package com.dvidal.beastweather.view;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.dvidal.beastweather.R;
import com.dvidal.beastweather.entity.WeatherData;
import com.dvidal.beastweather.infrastructure.BeastWeatherApplication;
import com.squareup.picasso.Picasso;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by diegovidal on 05/12/2017.
 */

public class WeatherViewHolder extends RecyclerView.ViewHolder {

    @BindView(R.id.list_weather_item_date)
    TextView weatherDate;

    @BindView(R.id.list_weather_item_imageView)
    ImageView weatherImage;

    @BindView(R.id.list_weather_item_max)
    TextView weatherMax;

    @BindView(R.id.list_weather_item_min)
    TextView weatherMin;

    @BindView(R.id.list_weather_item_weatherDescription)
    TextView weatherDesc;

    public WeatherViewHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }

    public void populate(WeatherData weatherData, int position, Context context, boolean isMetric){

        itemView.setTag(weatherData);
        String date;

        if (position == 0){

            date = "Today";
        } else if (position == 1){

            date = "Tomorrow";
        } else {

            date = weatherData.getWeatherDate();
        }

        weatherDate.setText(date);

        if (isMetric){

            weatherMax.setText(context.getString(R.string.format_temperature, roundWeather(weatherData.getTemperatureMax()), "C"));
            weatherMin.setText(context.getString(R.string.format_temperature, roundWeather(weatherData.getTemperatureMin()), "C"));
        } else {

            weatherMax.setText(context.getString(R.string.format_temperature, roundWeather(weatherData.getTemperatureMax()), "F"));
            weatherMin.setText(context.getString(R.string.format_temperature, roundWeather(weatherData.getTemperatureMin()), "F"));
        }

//        weatherMax.setText(roundWeather(weatherData.getTemperatureMax()));
//        weatherMin.setText(roundWeather(weatherData.getTemperatureMin()));

//        weatherMax.setText(String.valueOf(weatherData.getTemperatureMax()));
//        weatherMin.setText(String.valueOf(weatherData.getTemperatureMin()));

        weatherDesc.setText(weatherData.getWeatherDetails());

        Picasso.with(context).load(BeastWeatherApplication.BASE_WEATHER_IMAGE + weatherData.getWeatherIcon() + ".png")
            .into(weatherImage);
    }

    private String roundWeather(double temperature){

        double roundedWeather = Math.round(temperature);
        return Double.toString(roundedWeather);
    }
}
