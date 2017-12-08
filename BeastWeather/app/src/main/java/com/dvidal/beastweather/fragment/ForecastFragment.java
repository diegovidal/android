package com.dvidal.beastweather.fragment;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.MainThread;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.dvidal.beastweather.R;
import com.dvidal.beastweather.entity.WeatherData;
import com.dvidal.beastweather.infrastructure.BeastWeatherApplication;
import com.dvidal.beastweather.model.WeatherListModel;
import com.dvidal.beastweather.services.WeatherClient;
import com.dvidal.beastweather.view.WeatherAdapter;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import rx.Observer;
import rx.Subscription;
import rx.functions.Func1;
import rx.schedulers.Schedulers;


/**
 * Created by diegovidal on 05/12/2017.
 */

public class ForecastFragment extends Fragment implements WeatherAdapter.WeatherListener {

    @BindView(R.id.fragment_forecast_recyclerView)
    RecyclerView recyclerView;

    private FirebaseDatabase referenceDataBase;
    private DatabaseReference reference;
    private ValueEventListener listener;

    private WeatherAdapter adapter;
    private Subscription mSubscrition;

    public static ForecastFragment newInstance(){

        return new ForecastFragment();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_forecast, container, false);
        ButterKnife.bind(this, rootView);

        return rootView;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        this.referenceDataBase = FirebaseDatabase.getInstance();
        this.reference = referenceDataBase.getReference(BeastWeatherApplication.FIREBASE_DATABASE_URL);

        adapter = new WeatherAdapter(getActivity(), this);
//        adapter.setWeatherDataList(getWeather());

        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setAdapter(adapter);
        getWeather("6320062", "metric");
    }

    @Override
    public void onStart() {
        super.onStart();

        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getActivity());
        SharedPreferences appPreferences = getActivity().getSharedPreferences(BeastWeatherApplication.APP_ID_PREFERENCE, Context.MODE_PRIVATE);

        String appId = appPreferences.getString(BeastWeatherApplication.APP_ID, "");

//        if (appId.isEmpty()){
//
//             this.reference = referenceDataBase.getReference(BeastWeatherApplication.FIREBASE_DATABASE_URL).push();
//            appPreferences.edit().putString(BeastWeatherApplication.APP_ID, this.reference.getKey()).apply();
//        } else {
//
//            this.reference = referenceDataBase.getReference(BeastWeatherApplication.FIREBASE_DATABASE_URL).push();
//        }

        String zipCode = sharedPreferences.getString(BeastWeatherApplication.LOCATION_PREFERENCE, "6320062");
        String units = sharedPreferences.getString(BeastWeatherApplication.UNIT_PREFERENCE, "Metric");

        adapter = new WeatherAdapter(getActivity(), this);
//        adapter.setWeatherDataList(getWeather());
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setAdapter(adapter);
        getWeather(zipCode, units);
    }

    private void getWeather(String id, final String units){

        mSubscrition = WeatherClient.getInstance()
                .getWeather(id, units)
                .subscribeOn(Schedulers.io())
                .map(new Func1<WeatherListModel, List<WeatherData>>() {
                    @Override
                    public List<WeatherData> call(WeatherListModel weatherListModel) {
                        return WeatherClient.getInstance().weatherDataConverter(weatherListModel);
                    }
                })
                .observeOn(Schedulers.io())
                .subscribe(new Observer<List<WeatherData>>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                        listener = WeatherClient.getInstance().readFromFirebase(reference, adapter, getActivity());
                        reference.addValueEventListener(listener);

                        getActivity().runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                Toast.makeText(getContext(), "You are on offline mode", Toast.LENGTH_LONG).show();
                            }
                        });
                    }

                    @Override
                    public void onNext(List<WeatherData> weatherDatas) {

//                        adapter.setWeatherDataList(weatherDatas);
                        listener = WeatherClient.getInstance().readFromFirebase(reference, adapter, getActivity());
                        reference.addValueEventListener(listener);

                        adapter.setMetric(units.equals("Metric"));
                    }
                });
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (mSubscrition != null && mSubscrition.isUnsubscribed()){
            mSubscrition.unsubscribe();
        }

        if (listener != null){
            reference.removeEventListener(listener);
        }
    }

    @Override
    public void weatherClicked(WeatherData weatherData) {

    }
}

//    private List<WeatherData> getWeather(){
//
//        List<WeatherData> weatherDataList = new ArrayList<>();
//        for (int index = 0; index < 7; index++) {
//
//            GregorianCalendar gregorianCalendar = new GregorianCalendar();
//            gregorianCalendar.add(GregorianCalendar.DATE, index);
//            Date date = gregorianCalendar.getTime();
//
//            SimpleDateFormat dateFormat = new SimpleDateFormat("EE MM dd", Locale.getDefault());
//            WeatherData weatherData = new WeatherData(100.0, 80, 25, 36,
//                    "clear", "very clear", dateFormat.format(date));
//            weatherDataList.add(weatherData);
//        }
//
//        return weatherDataList;
//    }