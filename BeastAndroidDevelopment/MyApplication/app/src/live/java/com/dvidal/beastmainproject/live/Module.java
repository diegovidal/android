package com.dvidal.beastmainproject.live;

import com.dvidal.beastmainproject.infrastructure.BeastApplication;

/**
 * Created by diegovidal on 30/10/17.
 */

public class Module {

    public static void Register(BeastApplication application){

        new LiveBrotherService(application);
        new LiveCardService(application);
        new LivePictureService(application);
        new LiveRushService(application);
    }
}
