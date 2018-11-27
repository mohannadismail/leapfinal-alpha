package com.leap_app.leap.Utility;

import android.util.Log;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.leap_app.leap.Models.Leap;

import java.util.ArrayList;

public class FireDatabase {
    private DatabaseReference mDatabase;
    private ArrayList<Leap> leaps;

    public FireDatabase() {
    }

    public ArrayList<Leap> getLeaps() {
        leaps = new ArrayList<>();
        mDatabase = FirebaseDatabase.getInstance().getReference();

        Log.e("tag", mDatabase.getPath().toString());
        mDatabase.child(Constants.FIREBASE_Leap);

        return leaps;
    }
}
