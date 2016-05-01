package com.leap_app.leap.Models;

import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.leap_app.leap.LeapProvider.LeapContract;
import com.leap_app.leap.LeapProvider.LeapProvider;

/**
 * Created by TOSHIBA on 09-Jan-16.
 */
public class LeapInfo {

    public static Context context ;
    public String leapName;
    public String leapPrice;
//    public String leapAddress;
    public String leapDescription;
    public String userName;
    public int userNoOfLeaps;
    public int mapPhotoId;
    public int userPhotoId;
    public int leapPhotoId;

    LeapInfo(String leapName, String leapPrice, String leapDescription, String userName, int userNoOfLeaps, int mapPhotoId, int userPhotoId, int leapPhotoId) {
        this.leapName = leapName;
        this.leapPrice = leapPrice;
//        this.leapAddress = leapAddress;
        this.leapDescription = leapDescription;
        this.userName = userName;
        this.userNoOfLeaps = userNoOfLeaps;
        this.mapPhotoId = mapPhotoId;
        this.userPhotoId = userPhotoId;
        this.leapPhotoId = leapPhotoId;
    }

    public static String getLeapNameColumn(int leapID) {

        SQLiteDatabase db = LeapProvider.mLeapHelper.getReadableDatabase();
        Cursor c = db.query(LeapContract.LeapEntry.Table_Name, new String[]{"name"}, "id =" + leapID, null, null, null, "id");

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

    public static String getLeapPriceColumn(int leapID) {

        SQLiteDatabase db = LeapProvider.mLeapHelper.getReadableDatabase();
        Cursor c = db.query(LeapContract.LeapEntry.Table_Name, new String[]{"price"}, "id =" + leapID, null, null, null, "id");

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

    public static String getLeapDescColumn(int leapID) {

        SQLiteDatabase db = LeapProvider.mLeapHelper.getReadableDatabase();
        Cursor c = db.query(LeapContract.LeapEntry.Table_Name, new String[]{"description"}, "id =" + leapID, null, null, null, "id");

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

    public static String getLeapUserColumn(int leapID) {

        SQLiteDatabase db = LeapProvider.mLeapHelper.getReadableDatabase();
        Cursor c = db.rawQuery("SELECT User.name FROM User INNER JOIN Leap ON User.id = Leap.user_id WHERE Leap.id =?", new String[]{String.valueOf(leapID)});

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

    public static String getNumOfLeapsColumn(int leapID) {

        SQLiteDatabase db = LeapProvider.mLeapHelper.getReadableDatabase();
        Cursor c = db.rawQuery("SELECT User.leapsNumber FROM User INNER JOIN Leap ON User.id = Leap.user_id WHERE Leap.id =?", new String[]{String.valueOf(leapID)});

        if(c!=null)
        {
            c.moveToFirst();
        }

        Log.d("TAG2", DatabaseUtils.dumpCursorToString(c));
        Log.d("Taguser", "Value:" + c.getCount());

        String s = (c.getString(0) + " leaps");

        c.close();
        return s;
    }

    public static String getImageColumn(int leapID) {
        SQLiteDatabase db = LeapProvider.mLeapHelper.getReadableDatabase();
        Cursor c = db.query(LeapContract.LeapEntry.Table_Name, new String[]{"imageURI"}, "id =" + leapID, null, null, null, "id");

        if(c!=null)
        {
            c.moveToFirst();
        }
        Log.d("TAG2", DatabaseUtils.dumpCursorToString(c));
        Log.d("Tagimage1", "Value:" + c.getCount());

        String s = (c.getString(0));

        c.close();
        return s;
    }

    public static String getUserImageColumn(int leapID) {
        SQLiteDatabase db = LeapProvider.mLeapHelper.getReadableDatabase();
        Cursor c = db.rawQuery("SELECT User.imageUri FROM User INNER JOIN Leap ON User.id = Leap.user_id WHERE Leap.id =?", new String[]{String.valueOf(leapID)});

        if(c!=null)
        {
            c.moveToFirst();
        }
        Log.d("TAG2", DatabaseUtils.dumpCursorToString(c));
        Log.d("Tagimage2", "Value:" + c.getCount());

        String s = (c.getString(0));

        c.close();
        return s;
    }


}
