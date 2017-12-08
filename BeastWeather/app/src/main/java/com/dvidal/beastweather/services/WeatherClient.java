package com.dvidal.beastweather.services;

import android.content.Context;
import android.widget.Toast;

import com.dvidal.beastweather.entity.WeatherData;
import com.dvidal.beastweather.infrastructure.BeastWeatherApplication;
import com.dvidal.beastweather.model.WeatherDetails;
import com.dvidal.beastweather.model.WeatherFullDetails;
import com.dvidal.beastweather.model.WeatherListModel;
import com.dvidal.beastweather.view.WeatherAdapter;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Observable;

/**
 * Created by diegovidal on 06/12/2017.
 */

public class WeatherClient {

    private static WeatherClient weatherClient;
    private WeatherWebServices weatherWebServices;

    private WeatherClient(){

        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient client  = new OkHttpClient.Builder().addInterceptor(interceptor).build();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BeastWeatherApplication.BASE_WEATHER_URL)
                .client(client)
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();


        weatherWebServices = retrofit.create(WeatherWebServices.class);
    }

    public static WeatherClient getInstance(){

        if (weatherClient == null){
            weatherClient = new WeatherClient();
        }

        return weatherClient;
    }

    public Observable<WeatherListModel> getWeather(final String id, final String units){

        return weatherWebServices.getWeather(id, units,"", BeastWeatherApplication.BASE_WEATHER_API);
    }

    public List<WeatherData> weatherDataConverter(WeatherListModel weatherListModel){

        final List<WeatherData> weatherDataList = new ArrayList<>();
        int position = 0;

        for (WeatherFullDetails weatherFullDetails : weatherListModel.fullDetailsList){

            GregorianCalendar gregorianCalendar = new GregorianCalendar();
            gregorianCalendar.add(GregorianCalendar.DATE, position);
            Date time = gregorianCalendar.getTime();
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("EEE MM dd", Locale.getDefault());

            WeatherData weatherData = new WeatherData(weatherFullDetails.getTemperaturaDetails().getTemperatureMax(),
                    weatherFullDetails.getTemperaturaDetails().getTemperatureMin(), weatherFullDetails.getPressure(),
                    weatherFullDetails.getHumidity(), "", "", simpleDateFormat.format(time), "");

            for (WeatherDetails weatherDetails : weatherFullDetails.getWeatherDetails()){

                weatherData.setWeatherBasic(weatherDetails.getBasicWeatherDescription());
                weatherData.setWeatherDetails(weatherDetails.getDetailedWeatherDescription());
                weatherData.setWeatherIcon(weatherDetails.getWeatherIcon());
            }

            weatherDataList.add(weatherData);
            position ++;
        }

        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
        final DatabaseReference reference = firebaseDatabase.getReference(BeastWeatherApplication.FIREBASE_DATABASE_URL);

        reference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                if (!dataSnapshot.hasChildren()){
                    int index = 0;

                    for (WeatherData weatherData : weatherDataList){
                        reference.child(Integer.toString(index)).setValue(weatherData);
                        index ++;
                    }
                } else {

                    int index = 0;

                    for (WeatherData weatherData : weatherDataList){
                        Map newWeatherData = new HashMap();
                        newWeatherData.put("temperatureMax", weatherData.getTemperatureMax());
                        newWeatherData.put("temperatureMin", weatherData.getTemperatureMin());
                        newWeatherData.put("pressure", weatherData.getPressure());
                        newWeatherData.put("humidity", weatherData.getHumidity());
                        newWeatherData.put("weatherBasic", weatherData.getWeatherBasic());
                        newWeatherData.put("weatherDetails", weatherData.getWeatherDetails());
                        newWeatherData.put("weatherDate", weatherData.getWeatherDate());
                        newWeatherData.put("weatherIcon", weatherData.getWeatherIcon());

                        reference.child(Integer.toString(index)).updateChildren(newWeatherData);
                        index ++;
                    }

                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        return weatherDataList;
    }

    public ValueEventListener readFromFirebase(DatabaseReference reference, final WeatherAdapter adapter, final Context context){

        return reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                List<WeatherData> weatherDataList = new ArrayList<>();
                if (dataSnapshot != null && dataSnapshot.hasChildren()){

                    for (DataSnapshot ds : dataSnapshot.getChildren()){
                        WeatherData weatherData = ds.getValue(WeatherData.class);
                        weatherDataList.add(weatherData);
                    }
                }

                if (weatherDataList.isEmpty()){
                    Toast.makeText(context, "Please get check you internet", Toast.LENGTH_LONG).show();
                }
                adapter.setWeatherDataList(weatherDataList);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }
}
