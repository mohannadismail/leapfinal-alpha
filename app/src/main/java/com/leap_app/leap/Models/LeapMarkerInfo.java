package com.leap_app.leap.Models;


import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.leap_app.leap.LeapProvider.LeapContract;
import com.leap_app.leap.LeapProvider.LeapProvider;

/**
 * Created by RamyFRadwan on 26/03/2016.
 */
public class LeapMarkerInfo {
    public static Context context ;
    public String name;
    public String price;
    public String user;
    public String photoId;
    public int leapIddd;
    public double lat,lon;

    public LeapMarkerInfo(double lat, double lon){
        this.lat = lat;
        this.lon = lon;
    }

    public int getLeapID(){
        SQLiteDatabase db = LeapProvider.mLeapHelper.getReadableDatabase();


        Cursor cid = db.query(LeapContract.LeapEntry.Table_Name, new String[]{"id"},  "latitude =" + lat + " AND " + "longitude =" + lon, null, null, null, "id");

        if(cid!=null)
        {
            cid.moveToFirst();
        }

        Log.d("TAG2", DatabaseUtils.dumpCursorToString(cid));
        Log.d("TagName", "Value:" + cid.getCount());

        int[] s = new int[cid.getCount()];
        int i = 0;
        while (!cid.isAfterLast()){
            s[i] = (cid.getInt(0));
            leapIddd = s[i];
            i++;
            cid.moveToNext();
        }
        cid.close();
        return s[0];
    }


    public  String getLeapNameColumn() {

        SQLiteDatabase db = LeapProvider.mLeapHelper.getReadableDatabase();
        Cursor c = db.query(LeapContract.LeapEntry.Table_Name, new String[]{"name"}, "id =" + leapIddd, null, null, null, "id");

        if(c!=null)
        {
            c.moveToFirst();
        }

        Log.d("TAG1", DatabaseUtils.dumpCursorToString(c));
        Log.d("Tagname", "Value:" + c.getCount());

        String s = (c.getString(0));

        c.close();
        return s;
    }

    public String getLeapPriceColumn() {

        SQLiteDatabase db = LeapProvider.mLeapHelper.getReadableDatabase();
        Cursor c = db.query(LeapContract.LeapEntry.Table_Name, new String[]{"price"}, "id =" + leapIddd, null, null, null, "id");

        if(c!=null)
        {
            c.moveToFirst();
        }

        Log.d("TAG1", DatabaseUtils.dumpCursorToString(c));
        Log.d("Tagname", "Value:" + c.getCount());

        String s = (c.getString(0));

        c.close();
        return s;
    }

    public String getLeapUserColumn() {

        SQLiteDatabase db = LeapProvider.mLeapHelper.getReadableDatabase();
        Cursor c = db.rawQuery("SELECT User.name FROM User INNER JOIN Leap ON User.id = Leap.user_id WHERE Leap.id =?", new String[]{String.valueOf(leapIddd)});

        if(c!=null)
        {
            c.moveToFirst();
        }

        Log.d("TAG2", DatabaseUtils.dumpCursorToString(c));
        Log.d("Taguser", "Value:" + c.getCount());

        String s = (c.getString(0));

        c.close();
        return s;
    }
}