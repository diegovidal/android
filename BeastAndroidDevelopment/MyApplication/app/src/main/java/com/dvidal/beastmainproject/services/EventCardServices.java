package com.dvidal.beastmainproject.services;

import com.dvidal.beastmainproject.enties.EventCard;

import java.util.List;

/**
 * Created by diegovidal on 15/05/17.
 */

public class EventCardServices {

    public static class SearchCommunityServiceCardsRequest{

        public String fireBaseUrl;

        public SearchCommunityServiceCardsRequest(String fireBaseUrl) {
            this.fireBaseUrl = fireBaseUrl;
        }
    }

    public static class SearchCommunityServiceCardsResponse{

        public List<EventCard> communityServiceCards;
    }

    public static class SearchBrotherhoodCardsRequest{

        public String fireBaseUrl;

        public SearchBrotherhoodCardsRequest(String fireBaseUrl) {
            this.fireBaseUrl = fireBaseUrl;
        }
    }

    public static class SearchBrotherhoodCardsResponse{

        public List<EventCard> brotherhoodCards;
    }

    public static class SearchSocialCardsRequest{

        public String fireBaseUrl;

        public SearchSocialCardsRequest(String fireBaseUrl) {
            this.fireBaseUrl = fireBaseUrl;
        }
    }

    public static class SearchSocialCardsResponse{

        public List<EventCard> socialCards;
    }
}
