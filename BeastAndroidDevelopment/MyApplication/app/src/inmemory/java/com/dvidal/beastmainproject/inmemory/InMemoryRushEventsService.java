package com.dvidal.beastmainproject.inmemory;

import com.dvidal.beastmainproject.enties.RushEvent;
import com.dvidal.beastmainproject.infrastructure.BeastApplication;
import com.dvidal.beastmainproject.services.RushEventsService;
import com.squareup.otto.Subscribe;

import java.util.ArrayList;

/**
 * Created by diegovidal on 14/10/17.
 */

public class InMemoryRushEventsService extends BaseInMemory {

    public InMemoryRushEventsService(BeastApplication application) {
        super(application);
    }

    @Subscribe
    public void getCommunitySearchEvents(RushEventsService.SearchRushEventsCommunityRequest request){

        RushEventsService.SearchRushEventsCommunityResponse response = new RushEventsService.SearchRushEventsCommunityResponse();
        response.communityRushEvents = new ArrayList<>();

        response.communityRushEvents.add(new RushEvent(
                1,
                "Rush Community Event 1",
                "9/04/2016",
                "8:00pm",
                "Mu 202",
                2.2,
                2.2,
                true,
                "This is were the description of the event goes!"
        ));

        bus.post(response);
    }

    @Subscribe
    public void getSocialRushEvents(RushEventsService.SearchRushEventSocialRequest request){

        RushEventsService.SearchRushEventSocialResponse response = new RushEventsService.SearchRushEventSocialResponse();
        response.socialEvents = new ArrayList<>();

        response.socialEvents.add(new RushEvent(
                1,
                "Rush Social Event 1",
                "9/06/2016",
                "8:00pm",
                "Jack in the Box, 721 S Mill Ave, Tempe, AZ 85281, USA",
                33.422613,
                -111.939648,
                false,
                "This is were the description of the event goes!"
        ));

        bus.post(response);
    }
}
