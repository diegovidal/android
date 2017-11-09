package com.dvidal.beastmainproject.live;

import com.dvidal.beastmainproject.enties.EventPicture;
import com.dvidal.beastmainproject.enties.RushEvent;
import com.dvidal.beastmainproject.enties.firebaseEntities.EventPictureFireBase;
import com.dvidal.beastmainproject.enties.firebaseEntities.RushEventFireBase;
import com.dvidal.beastmainproject.infrastructure.BeastApplication;
import com.dvidal.beastmainproject.services.RushEventsService;
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

public class LiveRushService extends BaseLiveService {

    public LiveRushService(BeastApplication application) {
        super(application);
    }

    @Subscribe
    public void getCommunitySearchEvents(RushEventsService.SearchRushEventsCommunityRequest request){

        final RushEventsService.SearchRushEventsCommunityResponse response = new RushEventsService.SearchRushEventsCommunityResponse();
        response.communityRushEvents = new ArrayList<>();

        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
        DatabaseReference reference = firebaseDatabase.getReference(request.fireBaseUrl);
        reference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                int index = 0;
                for (DataSnapshot ds : dataSnapshot.getChildren()){

                    RushEventFireBase rushEventFireBase = ds.getValue(RushEventFireBase.class);

                    RushEvent rushEvent = new RushEvent(
                            index,
                            rushEventFireBase.getName(),
                            rushEventFireBase.getDate(),
                            rushEventFireBase.getTime(),
                            rushEventFireBase.getLocation(),
                            rushEventFireBase.getLatitude(),
                            rushEventFireBase.getLongitude(),
                            rushEventFireBase.isOnCampus(),
                            rushEventFireBase.getDescription()
                    );

                    index ++;
                    response.communityRushEvents.add(rushEvent);
                }

                bus.post(response);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

//        response.communityRushEvents.add(new RushEvent(
//                1,
//                "Rush Community Event 1",
//                "9/04/2016",
//                "8:00pm",
//                "Mu 202",
//                2.2,
//                2.2,
//                true,
//                "This is were the description of the event goes!"
//        ));
//
//        int index = 0;
//        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
//
//        for (RushEvent rushEvent : response.communityRushEvents) {
//
//            DatabaseReference rushReference = firebaseDatabase.getReference().child("data").child("rushEvents").child("community").child(Integer.toString(index));
//
//            rushReference.child("name").setValue(rushEvent.getEventName());
//            rushReference.child("date").setValue(rushEvent.getEventDate());
//            rushReference.child("time").setValue(rushEvent.getEventTime());
//            rushReference.child("location").setValue(rushEvent.getEventLocation());
//            rushReference.child("latitude").setValue(rushEvent.getEventLatitude());
//            rushReference.child("longitude").setValue(rushEvent.getEventLongitude());
//            rushReference.child("isOnCampus").setValue(rushEvent.isOnCampus());
//            rushReference.child("description").setValue(rushEvent.getEventDescription());
//
//            index ++;
//        }
//        bus.post(response);
    }

    @Subscribe
    public void getSocialRushEvents(RushEventsService.SearchRushEventSocialRequest request){

        final RushEventsService.SearchRushEventSocialResponse response = new RushEventsService.SearchRushEventSocialResponse();
        response.socialEvents = new ArrayList<>();

        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
        DatabaseReference reference = firebaseDatabase.getReference(request.fireBaseUrl);
        reference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                int index = 0;
                for (DataSnapshot ds : dataSnapshot.getChildren()){

                    RushEventFireBase rushEventFireBase = ds.getValue(RushEventFireBase.class);

                    RushEvent rushEvent = new RushEvent(
                            index,
                            rushEventFireBase.getName(),
                            rushEventFireBase.getDate(),
                            rushEventFireBase.getTime(),
                            rushEventFireBase.getLocation(),
                            rushEventFireBase.getLatitude(),
                            rushEventFireBase.getLongitude(),
                            rushEventFireBase.isOnCampus(),
                            rushEventFireBase.getDescription()
                    );

                    index ++;
                    response.socialEvents.add(rushEvent);
                }

                bus.post(response);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

//        response.socialEvents.add(new RushEvent(
//                1,
//                "Rush Social Event 1",
//                "9/06/2016",
//                "8:00pm",
//                "Jack in the Box, 721 S Mill Ave, Tempe, AZ 85281, USA",
//                33.422613,
//                -111.939648,
//                false,
//                "This is were the description of the event goes!"
//        ));
//
//        int index = 0;
//        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
//
//        for (RushEvent rushEvent : response.socialEvents) {
//
//            DatabaseReference rushReference = firebaseDatabase.getReference().child("data").child("rushEvents").child("social").child(Integer.toString(index));
//
//            rushReference.child("name").setValue(rushEvent.getEventName());
//            rushReference.child("date").setValue(rushEvent.getEventDate());
//            rushReference.child("time").setValue(rushEvent.getEventTime());
//            rushReference.child("location").setValue(rushEvent.getEventLocation());
//            rushReference.child("latitude").setValue(rushEvent.getEventLatitude());
//            rushReference.child("longitude").setValue(rushEvent.getEventLongitude());
//            rushReference.child("isOnCampus").setValue(rushEvent.isOnCampus());
//            rushReference.child("description").setValue(rushEvent.getEventDescription());
//
//            index ++;
//        }
//        bus.post(response);
    }
}
