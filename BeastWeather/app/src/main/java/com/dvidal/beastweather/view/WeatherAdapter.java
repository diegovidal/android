package com.dvidal.beastweather.view;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.dvidal.beastweather.R;
import com.dvidal.beastweather.entity.WeatherData;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by diegovidal on 05/12/2017.
 */

public class WeatherAdapter extends RecyclerView.Adapter<WeatherViewHolder> implements View.OnClickListener {

    private final int VIEW_TYPE_HEADER = 1;
    private final int VIEW_TYPE_BODY = 2;

    //HEADER//
    //BODY//

    private List<WeatherData> weatherDataList;
    private LayoutInflater inflater;
    private Context context;
    private WeatherListener listener;

    private boolean isMetric;

    public WeatherAdapter(Context context, WeatherListener listener) {
        this.context = context;
        this.listener = listener;

        inflater = LayoutInflater.from(context);
        weatherDataList = new ArrayList<>();
        isMetric = true;
    }

    public void setMetric(boolean metric) {
        isMetric = metric;
    }

    public void setWeatherDataList(List<WeatherData> weatherDataList) {

        this.weatherDataList.clear();
        this.weatherDataList.addAll(weatherDataList);
        notifyDataSetChanged();
    }

    @Override
    public int getItemViewType(int position) {

        if (position == 0){

            return  VIEW_TYPE_HEADER;
        }

        position --;
        if (position < weatherDataList.size()){
            return VIEW_TYPE_BODY;
        }

        position -= weatherDataList.size();

        throw new IllegalArgumentException("We are at the end of the list");
    }

    @Override
    public WeatherViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View headerView = inflater.inflate(R.layout.list_weather_today, parent, false);
        View bodyView = inflater.inflate(R.layout.list_weather_item, parent, false);

        if (viewType == VIEW_TYPE_HEADER){

            headerView.setOnClickListener(this);
            return new WeatherViewHolder(headerView);
        } else {

            bodyView.setOnClickListener(this);
            return  new WeatherViewHolder(bodyView);
        }
    }

    @Override
    public void onBindViewHolder(WeatherViewHolder holder, int position) {

        holder.populate(weatherDataList.get(position), position, context, isMetric);
    }

    @Override
    public int getItemCount() {
        return weatherDataList.size();
    }

    @Override
    public void onClick(View view) {

        if (view.getTag() instanceof WeatherData){

            listener.weatherClicked((WeatherData) view.getTag());
        }
    }

    public interface  WeatherListener{

        void weatherClicked(WeatherData weatherData);
    }
}
