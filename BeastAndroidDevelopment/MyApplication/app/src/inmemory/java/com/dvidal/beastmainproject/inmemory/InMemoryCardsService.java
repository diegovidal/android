package com.dvidal.beastmainproject.inmemory;

import com.dvidal.beastmainproject.enties.EventCard;
import com.dvidal.beastmainproject.infrastructure.BeastApplication;
import com.dvidal.beastmainproject.services.EventCardServices;
import com.squareup.otto.Subscribe;

import java.util.ArrayList;

/**
 * Created by diegovidal on 15/05/17.
 */

public class InMemoryCardsService extends BaseInMemory {

    public InMemoryCardsService(BeastApplication application) {
        super(application);
    }

    @Subscribe
    public void getCommunityServiceEvents(EventCardServices.SearchCommunityServiceCardsRequest request){

        EventCardServices.SearchCommunityServiceCardsResponse response = new EventCardServices.SearchCommunityServiceCardsResponse();
        response.communityServiceCards = new ArrayList<>();

        response.communityServiceCards.add(new EventCard(
                1,
                "Community Event 1",
                "Community Event 1's description",
                "http://www.gravatar.com/avatar/" + 1 + "?d=identicon",
                false,
                "null"
        ));

        response.communityServiceCards.add(new EventCard(
                2,
                "Community Event 2",
                "Community Event 2's description",
                "http://www.gravatar.com/avatar/" + 2 + "?d=identicon",
                false,
                "null"
        ));

        bus.post(response);
    }

    @Subscribe
    public void getBrotherhoodEvents(EventCardServices.SearchBrotherhoodCardsRequest request){

        EventCardServices.SearchBrotherhoodCardsResponse response = new EventCardServices.SearchBrotherhoodCardsResponse();
        response.brotherhoodCards = new ArrayList<>();

        response.brotherhoodCards.add(new EventCard(
                3,
                "Brotherhood Event 3",
                "Brotherhood Event 3's description",
                "http://www.gravatar.com/avatar/" + 3 + "?d=identicon",
                false,
                "null"
        ));

        response.brotherhoodCards.add(new EventCard(
                4,
                "Brotherhood Event 4",
                "Brotherhood Event 4's description",
                "http://www.gravatar.com/avatar/" + 4 + "?d=identicon",
                true,
                "null"
        ));

        bus.post(response);
    }

    @Subscribe
    public void getSocialEvents(EventCardServices.SearchSocialCardsRequest request){

        EventCardServices.SearchSocialCardsResponse response = new EventCardServices.SearchSocialCardsResponse();
        response.socialCards = new ArrayList<>();

        response.socialCards.add(new EventCard(
                5,
                "Social Event 5",
                "Social Event 5's description",
                "http://www.gravatar.com/avatar/" + 5 + "?d=identicon",
                false,
                "null"
        ));

        response.socialCards.add(new EventCard(
                6,
                "Social Event 6",
                "Social Event 6's description",
                "http://www.gravatar.com/avatar/" + 6 + "?d=identicon",
                true,
                "null"
        ));

        bus.post(response);
    }
}
