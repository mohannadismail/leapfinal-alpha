package com.leap_app.leap.firebase.provider;

import android.app.NotificationManager;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.app.NotificationCompat;
import android.util.Log;

import com.firebase.jobdispatcher.JobService;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.leap_app.leap.R;
import com.leap_app.leap.models.Leap;

public class FirebaseDBScheduler extends JobService {

    private static final String TAG = "SyncService";
    private Leap leap;
    private NotificationCompat.Builder notificationBuilder;

    public FirebaseDBScheduler() {
    }

    @Override
    public boolean onStartJob(com.firebase.jobdispatcher.JobParameters job) {
        Log.e(TAG, "Executing job id: " + job.getTag());
        DatabaseReference dbReference = FirebaseDatabase.getInstance().getReference().child("leap").child("Leap");
        Log.e(TAG, dbReference.getKey());
        dbReference.addValueEventListener(new ValueEventListener() {

            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                Log.e(TAG, "OnDataChanged Called");    //----Never Called


                for (DataSnapshot infoDataSnapshot : dataSnapshot.getChildren()) {
                    leap = infoDataSnapshot.getValue(Leap.class);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                databaseError.toException().printStackTrace();
            }

        });

        notificationBuilder = new NotificationCompat.Builder(getApplicationContext())
                .setSmallIcon(R.drawable.leaplogo)
                .setContentTitle(leap.getName())
                .setContentText(leap.getName());
        NotificationManager mNotificationManager =
                (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);

        mNotificationManager.notify(1, notificationBuilder.build());
        return false;
    }

    @Override
    public boolean onStopJob(com.firebase.jobdispatcher.JobParameters job) {
        Log.e(TAG, "Finished job: " + job.getTag());
        return false;
    }

}