package com.dvidal.beastmainproject.live;

import com.dvidal.beastmainproject.enties.EventCard;
import com.dvidal.beastmainproject.enties.EventPicture;
import com.dvidal.beastmainproject.enties.firebaseEntities.EventPictureFireBase;
import com.dvidal.beastmainproject.infrastructure.BeastApplication;
import com.dvidal.beastmainproject.services.EventPhotoService;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.otto.Subscribe;

import java.util.ArrayList;

/**
 * Created by diegovidal on 30/10/17.
 */

public class LivePictureService extends BaseLiveService {

    public LivePictureService(BeastApplication application) {
        super(application);
    }

    @Subscribe
    public void getCommunityPhotos(EventPhotoService.SearchCommunityPhotoRequest request){

        final EventPhotoService.SearchCommunityPhotoResponse response = new EventPhotoService.SearchCommunityPhotoResponse();
        response.communityPhotos = new ArrayList<>();

        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
        DatabaseReference reference = firebaseDatabase.getReference(request.fireBaseUrl);
        reference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                for (DataSnapshot ds : dataSnapshot.getChildren()){

                    EventPictureFireBase eventPictureFireBase = ds.getValue(EventPictureFireBase.class);
                    EventPicture eventPicture = new EventPicture(eventPictureFireBase.getUrl());
                    response.communityPhotos.add(eventPicture);
                }

                bus.post(response);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

//        response.communityPhotos.add(new EventPicture("http://www.gravatar.com/avatar/" + 50 + "?d=identicon"));
//        response.communityPhotos.add(new EventPicture("http://www.gravatar.com/avatar/" + 51 + "?d=identicon"));
//        response.communityPhotos.add(new EventPicture("http://www.gravatar.com/avatar/" + 52 + "?d=identicon"));
//
//
//        int index = 0;
//        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
//
//        for (EventPicture eventPicture : response.communityPhotos) {
//
//            DatabaseReference cardReference = firebaseDatabase.getReference().child("data").child("eventPics").child("community").child(Integer.toString(index));
//            cardReference.child("url").setValue(eventPicture.getPictureUrl());
//            index ++;
//        }
//        bus.post(response);
    }

    @Subscribe
    public void getBrotherHoodPhotos(EventPhotoService.SearchBrotherHoodPhotosRequest request){

        final EventPhotoService.SearchBrotherHoodPhotosResponse response = new EventPhotoService.SearchBrotherHoodPhotosResponse();
        response.brotherHoodPics = new ArrayList<>();

        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
        DatabaseReference reference = firebaseDatabase.getReference(request.fireBaseUrl);
        reference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                for (DataSnapshot ds : dataSnapshot.getChildren()){

                    EventPictureFireBase eventPictureFireBase = ds.getValue(EventPictureFireBase.class);
                    EventPicture eventPicture = new EventPicture(eventPictureFireBase.getUrl());
                    response.brotherHoodPics.add(eventPicture);
                }

                bus.post(response);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

//        response.brotherHoodPics.add(new EventPicture("http://www.gravatar.com/avatar/" + 53 + "?d=identicon"));
//        response.brotherHoodPics.add(new EventPicture("http://www.gravatar.com/avatar/" + 54 + "?d=identicon"));
//        response.brotherHoodPics.add(new EventPicture("http://www.gravatar.com/avatar/" + 55 + "?d=identicon"));
//
//        int index = 0;
//        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
//
//        for (EventPicture eventPicture : response.brotherHoodPics) {
//
//            DatabaseReference cardReference = firebaseDatabase.getReference().child("data").child("eventPics").child("brotherHood").child(Integer.toString(index));
//            cardReference.child("url").setValue(eventPicture.getPictureUrl());
//            index ++;
//        }
//        bus.post(response);
    }

    @Subscribe
    public void getSocialPhotos(EventPhotoService.SearchSocialPhotosRequest request){

        final EventPhotoService.SearchSocialPhotosResponse response = new EventPhotoService.SearchSocialPhotosResponse();
        response.socialPics = new ArrayList<>();

        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
        DatabaseReference reference = firebaseDatabase.getReference(request.fireBaseUrl);
        reference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                for (DataSnapshot ds : dataSnapshot.getChildren()){

                    EventPictureFireBase eventPictureFireBase = ds.getValue(EventPictureFireBase.class);
                    EventPicture eventPicture = new EventPicture(eventPictureFireBase.getUrl());
                    response.socialPics.add(eventPicture);
                }

                bus.post(response);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

//        response.socialPics.add(new EventPicture("http://www.gravatar.com/avatar/" + 56 + "?d=identicon"));
//        response.socialPics.add(new EventPicture("http://www.gravatar.com/avatar/" + 57 + "?d=identicon"));
//        response.socialPics.add(new EventPicture("http://www.gravatar.com/avatar/" + 58 + "?d=identicon"));
//
//        int index = 0;
//        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
//
//        for (EventPicture eventPicture : response.socialPics) {
//
//            DatabaseReference cardReference = firebaseDatabase.getReference().child("data").child("eventPics").child("social").child(Integer.toString(index));
//            cardReference.child("url").setValue(eventPicture.getPictureUrl());
//            index ++;
//        }
//        bus.post(response);
    }
}
