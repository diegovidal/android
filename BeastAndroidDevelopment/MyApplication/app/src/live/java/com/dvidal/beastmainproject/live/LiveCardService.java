package com.dvidal.beastmainproject.live;

import com.dvidal.beastmainproject.enties.EventCard;
import com.dvidal.beastmainproject.enties.firebaseEntities.BrotherFireBase;
import com.dvidal.beastmainproject.enties.firebaseEntities.EventCardFireBase;
import com.dvidal.beastmainproject.infrastructure.BeastApplication;
import com.dvidal.beastmainproject.services.EventCardServices;
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

public class LiveCardService extends BaseLiveService {

    public LiveCardService(BeastApplication application) {
        super(application);
    }

    @Subscribe
    public void getCommunityServiceEvents(EventCardServices.SearchCommunityServiceCardsRequest request){

        final EventCardServices.SearchCommunityServiceCardsResponse response = new EventCardServices.SearchCommunityServiceCardsResponse();
        response.communityServiceCards = new ArrayList<>();

        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
        DatabaseReference reference = firebaseDatabase.getReference(request.fireBaseUrl);
        reference.addListenerForSingleValueEvent(new ValueEventListener() {
             @Override
             public void onDataChange(DataSnapshot dataSnapshot) {

                 int index = 1;
                 for (DataSnapshot ds : dataSnapshot.getChildren()) {

                     EventCardFireBase eventCardFireBase = ds.getValue(EventCardFireBase.class);
                     EventCard eventCard = new EventCard(
                             index,
                             eventCardFireBase.getTitle(),
                             eventCardFireBase.getDescription(),
                             eventCardFireBase.getPicture(),
                             eventCardFireBase.isVideo(),
                             eventCardFireBase.getUrl()
                     );

                     response.communityServiceCards.add(eventCard);
                     index ++;
                 }

                 bus.post(response);
             }

             @Override
             public void onCancelled(DatabaseError databaseError) {

             }
         });

//        response.communityServiceCards.add(new EventCard(
//                1,
//                "Community Event 1",
//                "Community Event 1's description",
//                "http://www.gravatar.com/avatar/" + 1 + "?d=identicon",
//                false,
//                "null"
//        ));
//
//        response.communityServiceCards.add(new EventCard(
//                2,
//                "Community Event 2",
//                "Community Event 2's description",
//                "http://www.gravatar.com/avatar/" + 2 + "?d=identicon",
//                true,
//                "-3bMERyIUWo"
//        ));
//
//        int index = 1;
//        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
//
//        for (EventCard eventCard : response.communityServiceCards){
//
//            DatabaseReference cardReference = firebaseDatabase.getReference().child("data").child("eventCards").child("community").child(Integer.toString(index));
//            cardReference.child("title").setValue(eventCard.getEventName());
//            cardReference.child("description").setValue(eventCard.getEventDescription());
//            cardReference.child("picture").setValue(eventCard.getEventImage());
//            cardReference.child("video").setValue(eventCard.isVideo());
//            cardReference.child("url").setValue(eventCard.getYoutubeEnding());
//            index++;
//        }
//
//        bus.post(response);
    }

    @Subscribe
    public void getBrotherhoodEvents(EventCardServices.SearchBrotherhoodCardsRequest request){

        final EventCardServices.SearchBrotherhoodCardsResponse response = new EventCardServices.SearchBrotherhoodCardsResponse();
        response.brotherhoodCards = new ArrayList<>();

        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
        DatabaseReference reference = firebaseDatabase.getReference(request.fireBaseUrl);
        reference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                int index = 3;
                for (DataSnapshot ds : dataSnapshot.getChildren()) {
                    EventCardFireBase eventCardFireBase = ds.getValue(EventCardFireBase.class);

                    EventCard eventCard = new EventCard(
                            index,
                            eventCardFireBase.getTitle(),
                            eventCardFireBase.getDescription(),
                            eventCardFireBase.getPicture(),
                            eventCardFireBase.isVideo(),
                            eventCardFireBase.getUrl()
                    );

                    response.brotherhoodCards.add(eventCard);
                    index ++;
                }

                bus.post(response);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

//        response.brotherhoodCards.add(new EventCard(
//                3,
//                "Brotherhood Event 3",
//                "Brotherhood Event 3's description",
//                "http://www.gravatar.com/avatar/" + 3 + "?d=identicon",
//                false,
//                "null"
//        ));
//
//        response.brotherhoodCards.add(new EventCard(
//                4,
//                "Brotherhood Event 4",
//                "Brotherhood Event 4's description",
//                "http://www.gravatar.com/avatar/" + 4 + "?d=identicon",
//                true,
//                "FGBhQbmPwH8"
//        ));
//
//        int index = 3;
//        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
//
//        for (EventCard eventCard : response.brotherhoodCards) {
//
//            DatabaseReference cardReference = firebaseDatabase.getReference().child("data").child("eventCards").child("brotherHood").child(Integer.toString(index));
//            cardReference.child("title").setValue(eventCard.getEventName());
//            cardReference.child("description").setValue(eventCard.getEventDescription());
//            cardReference.child("picture").setValue(eventCard.getEventImage());
//            cardReference.child("video").setValue(eventCard.isVideo());
//            cardReference.child("url").setValue(eventCard.getYoutubeEnding());
//            index++;
//        }
//        bus.post(response);
    }

    @Subscribe
    public void getSocialEvents(EventCardServices.SearchSocialCardsRequest request){

        final EventCardServices.SearchSocialCardsResponse response = new EventCardServices.SearchSocialCardsResponse();
        response.socialCards = new ArrayList<>();

        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
        DatabaseReference reference = firebaseDatabase.getReference(request.fireBaseUrl);
        reference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                int index = 5;
                for (DataSnapshot ds : dataSnapshot.getChildren()) {
                    EventCardFireBase eventCardFireBase = ds.getValue(EventCardFireBase.class);

                    EventCard eventCard = new EventCard(
                            index,
                            eventCardFireBase.getTitle(),
                            eventCardFireBase.getDescription(),
                            eventCardFireBase.getPicture(),
                            eventCardFireBase.isVideo(),
                            eventCardFireBase.getUrl()
                    );

                    response.socialCards.add(eventCard);
                    index ++;
                }

                bus.post(response);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

//        response.socialCards.add(new EventCard(
//                5,
//                "Social Event 5",
//                "Social Event 5's description",
//                "http://www.gravatar.com/avatar/" + 5 + "?d=identicon",
//                false,
//                "null"
//        ));
//
//        response.socialCards.add(new EventCard(
//                6,
//                "Social Event 6",
//                "Social Event 6's description",
//                "http://www.gravatar.com/avatar/" + 6 + "?d=identicon",
//                true,
//                "gAjR4_CbPpQ"
//        ));
//
//        int index = 5;
//        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
//
//        for (EventCard eventCard : response.socialCards) {
//
//            DatabaseReference cardReference = firebaseDatabase.getReference().child("data").child("eventCards").child("social").child(Integer.toString(index));
//            cardReference.child("title").setValue(eventCard.getEventName());
//            cardReference.child("description").setValue(eventCard.getEventDescription());
//            cardReference.child("picture").setValue(eventCard.getEventImage());
//            cardReference.child("video").setValue(eventCard.isVideo());
//            cardReference.child("url").setValue(eventCard.getYoutubeEnding());
//            index++;
//        }
//        bus.post(response);
    }
}
