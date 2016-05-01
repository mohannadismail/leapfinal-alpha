package com.leap_app.leap;

import android.content.Context;
import android.support.multidex.MultiDex;
import android.support.multidex.MultiDexApplication;

import com.firebase.client.Firebase;

/**
 * Includes one-time initialization of Firebase related code
 */

public class LeapApplication extends MultiDexApplication {
    @Override
    public void onCreate() {
        super.onCreate();

         /* Initialize Firebase */
        Firebase.setAndroidContext(this);
        /* Enable disk persistence  */
        Firebase.getDefaultConfig().setPersistenceEnabled(true);
    }
    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        MultiDex.install(this);
    }
}