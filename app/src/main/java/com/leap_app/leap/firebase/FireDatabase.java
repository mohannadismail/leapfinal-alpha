package com.leap_app.leap.firebase;

import android.support.annotation.NonNull;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.leap_app.leap.Models.LeapBaseInfo;
import com.leap_app.leap.Utility.Constants;

import java.util.ArrayList;
import java.util.HashMap;

public class FireDatabase {
    private DatabaseReference mDatabase;
    private ArrayList<LeapBaseInfo> leapss = new ArrayList<>();
    private ArrayList<HashMap<String, String>> leaps;
    private OnLeapsRetrieved onLeapsRetrieved;
    private String[] leapIDs;

    public FireDatabase(OnLeapsRetrieved onLeapsRetrieved) {
        this.onLeapsRetrieved = onLeapsRetrieved;
    }

    public void getLeaps() {
        mDatabase = FirebaseDatabase.getInstance().getReference(Constants.LEAP);
        try {
            mDatabase.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    leapIDs = new String[(int) dataSnapshot.getChildrenCount()];
                    leaps = new ArrayList<>();
                    for (DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()) {
                        leaps.add((HashMap<String, String>) dataSnapshot1.getValue());
                    }
                    //Getting Keys and Leaps in HashMaps
                    for (int i = 0; i < dataSnapshot.getChildrenCount(); i++) {
                        leapIDs[i] = dataSnapshot.getChildren().iterator().next().getKey();
                    }
                    //Transforming Hashmaps to LeapBaseInfro objects
                    for (int i = 0; i < leaps.size(); i++) {
                        LeapBaseInfo leap = new LeapBaseInfo();
                        leap.setLeapID(leapIDs[i]);
                        leap.setLeapName(leaps.get(i).get(Constants.LEAP_NAME));
                        leap.setLeapDescription(leaps.get(i).get(Constants.LEAP_DESCRIPTION));
                        leap.setDate(leaps.get(i).get(Constants.LEAP_DATE));
                        leap.setLeapPrice(leaps.get(i).get(Constants.LEAP_PRICE));
                        leap.setTime(leaps.get(i).get(Constants.LEAP_TIME));
                        leap.setLeapLocation(leaps.get(i).get(Constants.LEAP_LOCATION));
                        leapss.add(leap);
                    }
                    onLeapsRetrieved.dataRetrieved(leapss);
                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {
                    databaseError.toException().printStackTrace();
                }
            });
        } catch (ArrayIndexOutOfBoundsException e) {
            e.printStackTrace();
        }
    }
}

