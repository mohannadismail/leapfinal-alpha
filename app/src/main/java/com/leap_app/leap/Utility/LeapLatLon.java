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

        Log.d(Constants.TAG_LEAP, DatabaseUtils.dumpCursorToString(c));
        Log.d(Constants.TAG_LEAP_LAT, String.valueOf(c != null ? c.getCount() : 0));

        double[] s = c != null ? new double[c.getCount()] : new double[0];
        int i = 0;
        if (c != null) {
            while (!c.isAfterLast()) {
                s[i] = (c.getDouble(0));
                i++;
                c.moveToNext();
            }
        }

        return s;
    }

    public static double[] LeapLon(int leapid) {

        SQLiteDatabase db = LeapProvider.mLeapHelper.getWritableDatabase();
        Cursor c = db.rawQuery("SELECT Place.longitude FROM Place INNER JOIN Leap_Place ON Place.id = Leap_place.place_id WHERE Leap_Place.leap_id =?", new String[]{String.valueOf(leapid)});

        if (c != null) {
            c.moveToFirst();
        }

        Log.d(Constants.TAG_LEAP, DatabaseUtils.dumpCursorToString(c));
        Log.d(Constants.TAG_LEAP_LAT, String.valueOf(Objects.requireNonNull(c).getCount()));

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

        double[] x1 = new double[lat.length],
                y1 = new double[lat.length],
                z1 = new double[lat.length];

        for (int j = 0; j < lon.length; j++) {
            x1[j] = Math.cos(Math.toRadians(lat[j])) * Math.cos(Math.toRadians(lon[j]));
            y1[j] = Math.cos(Math.toRadians(lat[j])) * Math.sin(Math.toRadians(lon[j]));
            z1[j] = Math.sin(Math.toRadians(lat[j]));
        }

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

        double leaplon1 = Math.atan2(y, x);
        double hyp = Math.sqrt((x * x) + (y * y));
        double leaplat1 = Math.atan2(z, hyp);

        double leaplon = Math.toDegrees(leaplon1);
        double leaplat = Math.toDegrees(leaplat1);

        return new double[]{leaplat, leaplon};
    }

    public static double[] getLeapLat() {
        SQLiteDatabase db = LeapProvider.mLeapHelper.getReadableDatabase();
        Cursor c = db.query(LeapContract.LeapEntry.Table_Name, new String[]{"latitude"}, null, null, null, null, "id");

        if (c != null) {
            c.moveToFirst();
        }

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

        double[] s = new double[0];
        if (c != null) {
            s = new double[c.getCount()];
        }
        int i = 0;
        if (c != null) {
            while (!c.isAfterLast()) {
                s[i] = (c.getDouble(0));
                i++;
                c.moveToNext();
            }
        }
        assert c != null;
        c.close();
        return s;
    }

}
