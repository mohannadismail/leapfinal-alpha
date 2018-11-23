package com.leap_app.leap.Utility;

import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.leap_app.leap.LeapProvider.LeapContract;
import com.leap_app.leap.LeapProvider.LeapProvider;

import java.util.Objects;


public class LeapLatLon {

    public static double[] LeapLat(int leapid) {

        SQLiteDatabase db = LeapProvider.mLeapHelper.getWritableDatabase();

        Cursor c = db.rawQuery("SELECT Place.Latitude FROM Place INNER JOIN Leap_Place ON Place.id = Leap_Place.place_id WHERE Leap_Place.leap_id =?", new String[]{String.valueOf(leapid)});

        if (c != null) {
            c.moveToFirst();
        }

        Log.d("TAGleap", DatabaseUtils.dumpCursorToString(c));
        Log.d("Tagleaplat", "Value:" + c.getCount());

        double[] s = new double[c.getCount()];
        int i = 0;
        while (!c.isAfterLast()) {
            s[i] = (c.getDouble(0));
            i++;
            c.moveToNext();
        }

        return s;
    }

    public static double[] LeapLon(int leapid) {

        SQLiteDatabase db = LeapProvider.mLeapHelper.getWritableDatabase();
        Cursor c = db.rawQuery("SELECT Place.longitude FROM Place INNER JOIN Leap_Place ON Place.id = Leap_place.place_id WHERE Leap_Place.leap_id =?", new String[]{String.valueOf(leapid)});

        if (c != null) {
            c.moveToFirst();
        }

        Log.d("TAGleap1", DatabaseUtils.dumpCursorToString(c));
        Log.d("Tagleaplon", "Value:" + Objects.requireNonNull(c).getCount());

        double[] s = new double[c.getCount()];
        int i = 0;
        while (!c.isAfterLast()) {
            s[i] = (c.getDouble(0));
            i++;
            c.moveToNext();
        }

        Log.d("Sval ", "" + s[0] + s[1] + s[2] + " " + s.length);
        return s;
    }

    public static double[] LeapCenter(double[] lat, double[] lon) {

        double[] x1 = new double[lat.length], y1 = new double[lat.length], z1 = new double[lat.length];

        for (int j = 0; j < lon.length; j++) {
            x1[j] = Math.cos(Math.toRadians(lat[j])) * Math.cos(Math.toRadians(lon[j]));
            y1[j] = Math.cos(Math.toRadians(lat[j])) * Math.sin(Math.toRadians(lon[j]));
            z1[j] = Math.sin(Math.toRadians(lat[j]));
        }

        Log.d("LatJ ", "" + lat.length + lon.length);

        Log.d("X1 ", "" + x1.length);
        Log.d("Y1 ", "" + y1.length);
        Log.d("Z1 ", "" + z1.length);

        double x = 0;
        for (double aX1 : x1) {
            x = x + aX1;
        }
        x = x / x1.length;

        double y = 0;
        for (double aY1 : y1) {
            y = y + aY1;
        }
        y = y / y1.length;

        double z = 0;
        for (double aZ1 : z1) {
            z = z + aZ1;
        }
        z = z / z1.length;

        Log.d("Xval ", "" + x);
        Log.d("Yval ", "" + y);
        Log.d("Zval ", "" + z);

        double leaplon1 = Math.atan2(y, x);
        double hyp = Math.sqrt((x * x) + (y * y));
        double leaplat1 = Math.atan2(z, hyp);

        double leaplon = Math.toDegrees(leaplon1);
        double leaplat = Math.toDegrees(leaplat1);

        Log.d("TAGCenter", " " + leaplat + leaplon);

        return new double[]{leaplat, leaplon};
    }

    public static double[] getLeapLat() {
        SQLiteDatabase db = LeapProvider.mLeapHelper.getReadableDatabase();
        Cursor c = db.query(LeapContract.LeapEntry.Table_Name, new String[]{"latitude"}, null, null, null, null, "id");

        if (c != null) {
            c.moveToFirst();
        }

        Log.d("TAG6", DatabaseUtils.dumpCursorToString(c));
        Log.d("Tagleaplat1", "Value:" + Objects.requireNonNull(c).getCount());

        double[] s = new double[c.getCount()];
        int i = 0;
        while (!c.isAfterLast()) {
            s[i] = (c.getDouble(0));
            i++;
            c.moveToNext();
        }
        c.close();
        return s;
    }

    public static double[] getLeapLon() {
        SQLiteDatabase db = LeapProvider.mLeapHelper.getReadableDatabase();
        Cursor c = db.query(LeapContract.LeapEntry.Table_Name, new String[]{"longitude"}, null, null, null, null, "id");

        if (c != null) {
            c.moveToFirst();
        }

        Log.d("TAG7", DatabaseUtils.dumpCursorToString(c));
        Log.d("Tagleaplon1", "Value:" + Objects.requireNonNull(c).getCount());

        double[] s = new double[c.getCount()];
        int i = 0;
        while (!c.isAfterLast()) {
            s[i] = (c.getDouble(0));
            i++;
            c.moveToNext();
        }
        c.close();
        return s;
    }

}
