package com.dvidal.beastweather.infrastructure;
import android.app.Application;
import com.google.firebase.database.FirebaseDatabase;

/**
 * Created by diegovidal on 05/12/2017.
 */

public class BeastWeatherApplication extends Application {

    public static final String FIREBASE_DATABASE_URL = "data/";

    public static final String BASE_WEATHER_URL = "http://api.openweathermap.org";
    public static final String BASE_WEATHER_API = "f99056e10233d03344713e84028f5691";

    public static final String LOCATION_PREFERENCE = "LOCATION_PREFERENCE";
    public static final String UNIT_PREFERENCE = "UNIT_PREFERENCE";

    public static final String BASE_WEATHER_IMAGE = "http://openweathermap.org/img/w/";

    public static final String APP_ID_PREFERENCE = "APP_ID_PREFERENCE";
    public static final String APP_ID = "APP_ID";

    @Override
    public void onCreate() {
        super.onCreate();

        FirebaseDatabase.getInstance().setPersistenceEnabled(true);
    }
}
