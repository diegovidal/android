package com.dvidal.beastmainproject.enties.firebaseEntities;

/**
 * Created by diegovidal on 07/11/17.
 */

public class RushEventFireBase {

    private String name;
    private String date;
    private String time;
    private String location;
    private double latitude;
    private double longitude;
    private boolean isOnCampus;
    private String description;

    public RushEventFireBase() {
    }

    public String getName() {
        return name;
    }

    public String getDate() {
        return date;
    }

    public String getTime() {
        return time;
    }

    public String getLocation() {
        return location;
    }

    public double getLatitude() {
        return latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public boolean isOnCampus() {
        return isOnCampus;
    }

    public String getDescription() {
        return description;
    }
}
