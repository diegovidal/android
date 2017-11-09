package com.dvidal.beastmainproject.inmemory;

import com.dvidal.beastmainproject.infrastructure.BeastApplication;

/**
 * Created by diegovidal on 21/02/17.
 */

public class Module {

    public static void Register(BeastApplication application){

        new InMemoryBrotherService(application);
        new InMemoryCardsService(application);
        new InMemoryPictureService(application);
        new InMemoryRushEventsService(application);
    }
}
