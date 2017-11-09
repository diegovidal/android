package com.dvidal.beastmainproject.enties.firebaseEntities;

/**
 * Created by diegovidal on 07/11/17.
 */

public class EventCardFireBase {

    private String title;
    private String description;
    private String picture;
    private boolean video;
    private String url;

    public EventCardFireBase() {
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public String getPicture() {
        return picture;
    }

    public boolean isVideo() {
        return video;
    }

    public String getUrl() {
        return url;
    }
}
