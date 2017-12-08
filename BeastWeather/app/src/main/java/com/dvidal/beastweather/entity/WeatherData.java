package com.dvidal.beastweather.entity;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by diegovidal on 05/12/2017.
 */

public class WeatherData {

    private double temperatureMax;
    private double temperatureMin;
    private double pressure;
    private double humidity;

    private String weatherBasic;
    private String weatherDetails;
    private String weatherDate;
    private String weatherIcon;

    public WeatherData(double temperatureMax, double temperatureMin, double pressure, double humidity, String weatherBasic,
                       String weatherDetails, String weatherDate, String weatherIcon) {

        this.temperatureMax = temperatureMax;
        this.temperatureMin = temperatureMin;
        this.pressure = pressure;
        this.humidity = humidity;
        this.weatherBasic = weatherBasic;
        this.weatherDetails = weatherDetails;
        this.weatherDate = weatherDate;
        this.weatherIcon = weatherIcon;
    }

    public WeatherData() {
    }

    //    protected WeatherData(Parcel in) {
//        temperatureMax = in.readDouble();
//        temperatureMin = in.readDouble();
//        pressure = in.readDouble();
//        humidity = in.readDouble();
//        weatherBasic = in.readString();
//        weatherDetails = in.readString();
//        weatherDate = in.readString();
//    }
//
//    public static final Creator<WeatherData> CREATOR = new Creator<WeatherData>() {
//        @Override
//        public WeatherData createFromParcel(Parcel in) {
//            return new WeatherData(in);
//        }
//
//        @Override
//        public WeatherData[] newArray(int size) {
//            return new WeatherData[size];
//        }
//    };
//
//    @Override
//    public int describeContents() {
//        return 0;
//    }
//
//    @Override
//    public void writeToParcel(Parcel parcel, int i) {
//        parcel.writeDouble(temperatureMax);
//        parcel.writeDouble(temperatureMin);
//        parcel.writeDouble(pressure);
//        parcel.writeDouble(humidity);
//        parcel.writeString(weatherBasic);
//        parcel.writeString(weatherDetails);
//        parcel.writeString(weatherDate);
//    }

    public double getTemperatureMax() {
        return temperatureMax;
    }

    public double getTemperatureMin() {
        return temperatureMin;
    }

    public double getPressure() {
        return pressure;
    }

    public double getHumidity() {
        return humidity;
    }

    public String getWeatherBasic() {
        return weatherBasic;
    }

    public String getWeatherDetails() {
        return weatherDetails;
    }

    public String getWeatherDate() {
        return weatherDate;
    }

    public String getWeatherIcon() {
        return weatherIcon;
    }

    public void setWeatherBasic(String weatherBasic) {
        this.weatherBasic = weatherBasic;
    }

    public void setWeatherDetails(String weatherDetails) {
        this.weatherDetails = weatherDetails;
    }

    public void setWeatherIcon(String weatherIcon) {
        this.weatherIcon = weatherIcon;
    }
}
