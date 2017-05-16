package com.dvidal.beastmainproject.services;

import com.dvidal.beastmainproject.enties.Brother;

import java.util.List;

/**
 * Created by diegovidal on 21/02/17.
 */

public class BrotherServices {

    public  BrotherServices(){

    }

    public static class SearchBrotherRequest{

        public String fireBaseUrl;

        public SearchBrotherRequest(String fireBaseUrl){
            this.fireBaseUrl = fireBaseUrl;
        }
    }

    public static class SearchBrotherResponse{

        public List<Brother> brothers;
    }
}
