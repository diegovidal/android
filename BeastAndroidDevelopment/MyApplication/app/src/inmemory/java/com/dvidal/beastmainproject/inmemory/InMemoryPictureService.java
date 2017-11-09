package com.dvidal.beastmainproject.inmemory;

import com.dvidal.beastmainproject.enties.EventPicture;
import com.dvidal.beastmainproject.infrastructure.BeastApplication;
import com.dvidal.beastmainproject.services.EventPhotoService;
import com.squareup.otto.Subscribe;

import java.util.ArrayList;

/**
 * Created by diegovidal on 07/09/17.
 */

public class InMemoryPictureService extends BaseInMemory {

    public InMemoryPictureService(BeastApplication application) {
        super(application);
    }

    @Subscribe
    public void getCommunityPhotos(EventPhotoService.SearchCommunityPhotoRequest request){

        EventPhotoService.SearchCommunityPhotoResponse response = new EventPhotoService.SearchCommunityPhotoResponse();
        response.communityPhotos = new ArrayList<>();

        response.communityPhotos.add(new EventPicture("http://www.gravatar.com/avatar/" + 50 + "?d=identicon"));

        response.communityPhotos.add(new EventPicture("http://www.gravatar.com/avatar/" + 51 + "?d=identicon"));

        response.communityPhotos.add(new EventPicture("http://www.gravatar.com/avatar/" + 52 + "?d=identicon"));

        bus.post(response);
    }

    @Subscribe
    public void getBrotherHoodPhotos(EventPhotoService.SearchBrotherHoodPhotosRequest request){

        EventPhotoService.SearchBrotherHoodPhotosResponse response = new EventPhotoService.SearchBrotherHoodPhotosResponse();
        response.brotherHoodPics = new ArrayList<>();

        response.brotherHoodPics.add(new EventPicture("http://www.gravatar.com/avatar/" + 53 + "?d=identicon"));

        response.brotherHoodPics.add(new EventPicture("http://www.gravatar.com/avatar/" + 54 + "?d=identicon"));

        response.brotherHoodPics.add(new EventPicture("http://www.gravatar.com/avatar/" + 55 + "?d=identicon"));

        bus.post(response);
    }

    @Subscribe
    public void getSocialPhotos(EventPhotoService.SearchSocialPhotosRequest request){

        EventPhotoService.SearchSocialPhotosResponse response = new EventPhotoService.SearchSocialPhotosResponse();
        response.socialPics = new ArrayList<>();

        response.socialPics.add(new EventPicture("http://www.gravatar.com/avatar/" + 56 + "?d=identicon"));

        response.socialPics.add(new EventPicture("http://www.gravatar.com/avatar/" + 57 + "?d=identicon"));

        response.socialPics.add(new EventPicture("http://www.gravatar.com/avatar/" + 58 + "?d=identicon"));

        bus.post(response);
    }
}
