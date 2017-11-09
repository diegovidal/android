package com.dvidal.beastmainproject.services;

import com.dvidal.beastmainproject.enties.RushEvent;

import java.util.List;

/**
 * Created by diegovidal on 14/10/17.
 */

public class RushEventsService {

    private RushEventsService(){

    }

    public static class SearchRushEventsCommunityRequest{

        public String fireBaseUrl;

        public SearchRushEventsCommunityRequest(String fireBaseUrl) {
            this.fireBaseUrl = fireBaseUrl;
        }
    }

    public static class SearchRushEventsCommunityResponse{

        public List<RushEvent> communityRushEvents;
    }

    public static class SearchRushEventSocialRequest{

        public String fireBaseUrl;

        public SearchRushEventSocialRequest(String fireBaseUrl) {
            this.fireBaseUrl = fireBaseUrl;
        }
    }

    public static class SearchRushEventSocialResponse{

        public List<RushEvent> socialEvents;
    }
}
