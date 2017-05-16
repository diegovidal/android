package com.dvidal.beastmainproject.inmemory;

import com.dvidal.beastmainproject.infrastructure.BeastApplication;
import com.squareup.otto.Bus;

/**
 * Created by diegovidal on 21/02/17.
 */

public class BaseInMemory {

    protected final BeastApplication application;
    protected final Bus bus;

    public BaseInMemory(BeastApplication application) {
        this.application = application;
        this.bus = application.getBus();
        bus.register(this);
    }
}
