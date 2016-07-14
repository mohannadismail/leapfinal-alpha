package com.leap_app.leap.Models;

import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.leap_app.leap.LeapProvider.LeapContract;
import com.leap_app.leap.LeapProvider.LeapProvider;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Psychalafy on 1/3/2016.
 */

 public class Leap {
    public static Context context ;
    public String name;
    public String price;
    public String user;
    public String photoId;
    public int leapId;

    Leap(String name, String price, String user, String photoId, int leapId) {
        this.name = name;
        this.price = price;
        this.user = user;
        this.photoId = photoId;
        this.leapId = leapId;
    }

    public static List<Leap> leaps;

    public static String[] getNameColumn() {

        SQLiteDatabase db = LeapProvider.mLeapHelper.getReadableDatabase();
        Cursor c = db.query(LeapContract.LeapEntry.Table_Name, new String[]{"name"}, null, null, null, null, "id");

        if(c!=null)
        {
            c.moveToFirst();
        }

        Log.d("TAG1", DatabaseUtils.dumpCursorToString(c));
        Log.d("Tagname", "Value:" + c.getCount());

        String[] s = new String[c.getCount()];
        int i = 0;
        while (!c.isAfterLast()){
            s[i] = (c.getString(0));
            i++;
            c.moveToNext();
        }
        c.close();
        return s;
    }

    public static String[] getPriceColumn() {

        SQLiteDatabase db = LeapProvider.mLeapHelper.getReadableDatabase();
        Cursor c = db.query(LeapContract.LeapEntry.Table_Name, new String[]{"price"}, null, null, null, null, "id");

        if(c!=null)
        {
            c.moveToFirst();
        }

        Log.d("TAG2", DatabaseUtils.dumpCursorToString(c));
        Log.d("Tagprice", "Value:" + c.getCount());

        String[] s = new String[c.getCount()];
        int i = 0;
        while (!c.isAfterLast()){
            s[i] = (c.getString(0));
            i++;
            c.moveToNext();
        }
        c.close();
        return s;
    }

    public static String[] getUserColumn() {

        SQLiteDatabase db = LeapProvider.mLeapHelper.getReadableDatabase();
        Cursor c = db.rawQuery("SELECT User.name FROM User INNER JOIN Leap ON User.id = Leap.user_id", null);

        if(c!=null)
        {
            c.moveToFirst();
        }

        Log.d("TAG2", DatabaseUtils.dumpCursorToString(c));
        Log.d("Taguser", "Value:" + c.getCount());

        String[] s = new String[c.getCount()];
        int i = 0;
        while (!c.isAfterLast()){
            s[i] = (c.getString(0));
            i++;
            c.moveToNext();
        }
        c.close();
        return s;
    }

    public static String[] getImageColumn() {
        SQLiteDatabase db = LeapProvider.mLeapHelper.getReadableDatabase();
        Cursor c = db.query(LeapContract.LeapEntry.Table_Name, new String[]{"imageURI"}, null, null, null, null, "id");

        if(c!=null)
        {
            c.moveToFirst();
        }
        Log.d("TAG2", DatabaseUtils.dumpCursorToString(c));
        Log.d("Tagimage", "Value:" + c.getCount());

        String[] s = new String[c.getCount()];
        int i = 0;
        while (!c.isAfterLast()){
            s[i] = (c.getString(0));
            i++;
            c.moveToNext();
        }
        c.close();
        Log.d("TAG4", "Values: " + s[2]);
        return s;
    }

    public static int[] getLeapIdColumn() {

        SQLiteDatabase db = LeapProvider.mLeapHelper.getReadableDatabase();
        Cursor c = db.query(LeapContract.LeapEntry.Table_Name, new String[]{"id"}, null, null, null, null, "id");

        if(c!=null)
        {
            c.moveToFirst();
        }

        Log.d("TAG5", DatabaseUtils.dumpCursorToString(c));
        Log.d("Tagname", "Value:" + c.getCount());

        int[] s = new int[c.getCount()];
        int i = 0;
        while (!c.isAfterLast()){
            s[i] = (c.getInt(0));
            i++;
            c.moveToNext();
        }
        c.close();
        return s;
    }

    public static List<Leap> initializeData() {
        String[] string = getNameColumn();
        String[] string1 = getPriceColumn();
        String[] string2 = getUserColumn();
        String[] image = getImageColumn();
        int[] id = getLeapIdColumn();
        leaps = new ArrayList<>();
        SQLiteDatabase db = LeapProvider.mLeapHelper.getReadableDatabase();
        long numLeapRows = DatabaseUtils.queryNumEntries(db, "Leap");
        Log.d("Tag3", "Value:" + numLeapRows);

        for (int i = 0; i < numLeapRows; i++){

            leaps.add(new Leap(string[i], string1[i], string2[i],image[i], id[i]));

        }

        return leaps;
    }
}
