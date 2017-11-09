package com.dvidal.beastmainproject.services;

import com.dvidal.beastmainproject.enties.EventPicture;

import java.util.List;

/**
 * Created by diegovidal on 07/09/17.
 */

public class EventPhotoService {

    private EventPhotoService(){

    }

    public static class SearchCommunityPhotoRequest {

        public String fireBaseUrl;

        public SearchCommunityPhotoRequest(String fireBaseUrl){

            this.fireBaseUrl = fireBaseUrl;
        }
    }

    public static class SearchCommunityPhotoResponse{

        public List<EventPicture> communityPhotos;
    }

    public static class SearchBrotherHoodPhotosRequest{

        public String fireBaseUrl;

        public SearchBrotherHoodPhotosRequest(String fireBaseUrl) {
            this.fireBaseUrl = fireBaseUrl;
        }
    }

    public static class SearchBrotherHoodPhotosResponse{

        public List<EventPicture> brotherHoodPics;
    }

    public static class SearchSocialPhotosRequest {

        public String fireBaseUrl;

        public SearchSocialPhotosRequest(String fireBaseUrl) {
            this.fireBaseUrl = fireBaseUrl;
        }
    }

    public static class SearchSocialPhotosResponse{

        public List<EventPicture> socialPics;
    }
}
