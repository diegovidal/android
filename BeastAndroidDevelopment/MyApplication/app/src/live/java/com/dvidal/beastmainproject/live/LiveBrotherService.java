package com.dvidal.beastmainproject.live;

import com.dvidal.beastmainproject.enties.Brother;
import com.dvidal.beastmainproject.enties.firebaseEntities.BrotherFireBase;
import com.dvidal.beastmainproject.infrastructure.BeastApplication;
import com.dvidal.beastmainproject.services.BrotherServices;
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

public class LiveBrotherService extends BaseLiveService {

    public LiveBrotherService(BeastApplication application) {
        super(application);
    }

    @Subscribe
    public void getBrothers(BrotherServices.SearchBrotherRequest request){

        final BrotherServices.SearchBrotherResponse response = new BrotherServices.SearchBrotherResponse();
        response.brothers = new ArrayList<>();

        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
        DatabaseReference reference = firebaseDatabase.getReference(request.fireBaseUrl);
        reference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                int index = 0;
                for (DataSnapshot ds : dataSnapshot.getChildren()){
                    BrotherFireBase brotherFireBase = ds.getValue(BrotherFireBase.class);

                    Brother brother = new Brother(
                            index,
                            brotherFireBase.getName(),
                            brotherFireBase.getWhy(),
                            brotherFireBase.getPicture(),
                            brotherFireBase.getMajor(),
                            brotherFireBase.getCross(),
                            brotherFireBase.getFact()
                    );

                    response.brothers.add(brother);
                    index ++;
                }

                bus.post(response);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

//        for (int i = 0; i < 32; i++){
//
//            response.brothers.add(new Brother(
//                    i,
//                    "Brother " + i,
//                    "Brother " + i + " joined for this reason",
//                    "http://www.gravatar.com/avatar/" + i + "?d=identicon",
//                    "Computer Engineering",
//                    "Spring 2013",
//                    "I love to code my heart out"
//            ));
//        }
//
//        int index = 0;
//        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
//
//        for (Brother brother : response.brothers){
//
//            DatabaseReference brotherReference = firebaseDatabase.getReference().child("data").child("brothers").child(Integer.toString(index));
//            brotherReference.child("name").setValue(brother.getBrotherName());
//            brotherReference.child("fact").setValue(brother.getBrotherFunFact());
//            brotherReference.child("picture").setValue(brother.getBrotherPicture());
//            brotherReference.child("major").setValue(brother.getBrotherMajor());
//            brotherReference.child("cross").setValue(brother.getBrotherCrossSemester());
//            brotherReference.child("why").setValue(brother.getBrotherWhyJoin());
//            index ++;
//        }
//
//        bus.post(response);
    }
}
