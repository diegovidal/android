package com.dvidal.beastmainproject.inmemory;

import com.dvidal.beastmainproject.enties.Brother;
import com.dvidal.beastmainproject.infrastructure.BeastApplication;
import com.dvidal.beastmainproject.services.BrotherServices;
import com.squareup.otto.Subscribe;

import java.util.ArrayList;

/**
 * Created by diegovidal on 21/02/17.
 */

public class InMemoryBrotherService extends BaseInMemory {

    public InMemoryBrotherService(BeastApplication application) {
        super(application);
    }

    @Subscribe
    public void getBrothers(BrotherServices.SearchBrotherRequest request){

        BrotherServices.SearchBrotherResponse response = new BrotherServices.SearchBrotherResponse();
        response.brothers = new ArrayList<>();

        for (int i = 0; i < 32; i++){

            response.brothers.add(new Brother(
                    i,
                    "Brother " + i,
                    "Brother " + i + " joined for this reason",
                    "http://www.gravatar.com/avatar/" + i + "?d=identicon",
                    "Computer Engineering",
                    "Spring 2013",
                    "I love to code my heart out"
            ));
        }

        bus.post(response);
    }
}
