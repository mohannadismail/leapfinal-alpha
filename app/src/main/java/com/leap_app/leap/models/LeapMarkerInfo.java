package com.leap_app.leap.models;


import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.os.Parcel;
import android.os.Parcelable;
import android.util.Log;

import com.leap_app.leap.leap_provider.LeapContract;
import com.leap_app.leap.leap_provider.LeapProvider;


public class LeapMarkerInfo implements Parcelable {
    private Context context;
    private String name;
    private String price;
    private String user;
    private String photoId;
    private int leapIddd;
    private double lat, lon;

    public LeapMarkerInfo(double lat, double lon) {
        this.lat = lat;
        this.lon = lon;
    }

    private LeapMarkerInfo(Parcel in) {
        name = in.readString();
        price = in.readString();
        user = in.readString();
        photoId = in.readString();
        leapIddd = in.readInt();
        lat = in.readDouble();
        lon = in.readDouble();
    }

    public static final Creator<LeapMarkerInfo> CREATOR = new Creator<LeapMarkerInfo>() {
        @Override
        public LeapMarkerInfo createFromParcel(Parcel in) {
            return new LeapMarkerInfo(in);
        }

        @Override
        public LeapMarkerInfo[] newArray(int size) {
            return new LeapMarkerInfo[size];
        }
    };

    public int getLeapID() {
        SQLiteDatabase db = LeapProvider.mLeapHelper.getReadableDatabase();


        Cursor cid = db.query(LeapContract.LeapEntry.Table_Name, new String[]{"id"}, "latitude =" + lat + " AND " + "longitude =" + lon, null, null, null, "id");

        if (cid != null) {
            cid.moveToFirst();
        }

        Log.d("TAG2", DatabaseUtils.dumpCursorToString(cid));
        Log.d("TagName", "Value:" + cid.getCount());

        int[] s = new int[cid.getCount()];
        int i = 0;
        while (!cid.isAfterLast()) {
            s[i] = (cid.getInt(0));
            leapIddd = s[i];
            i++;
            cid.moveToNext();
        }
        cid.close();
        return s[0];
    }


    public String getLeapNameColumn() {

        SQLiteDatabase db = LeapProvider.mLeapHelper.getReadableDatabase();
        Cursor c = db.query(LeapContract.LeapEntry.Table_Name, new String[]{"name"}, "id =" + leapIddd, null, null, null, "id");

        if (c != null) {
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

        if (c != null) {
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

        if (c != null) {
            c.moveToFirst();
        }

        Log.d("TAG2", DatabaseUtils.dumpCursorToString(c));
        Log.d("Taguser", "Value:" + c.getCount());

        String s = (c.getString(0));

        c.close();
        return s;
    }

    public Context getContext() {
        return context;
    }

    public void setContext(Context context) {
        this.context = context;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getPhotoId() {
        return photoId;
    }

    public void setPhotoId(String photoId) {
        this.photoId = photoId;
    }

    public int getLeapIddd() {
        return leapIddd;
    }

    public void setLeapIddd(int leapIddd) {
        this.leapIddd = leapIddd;
    }

    public double getLat() {
        return lat;
    }

    public void setLat(double lat) {
        this.lat = lat;
    }

    public double getLon() {
        return lon;
    }

    public void setLon(double lon) {
        this.lon = lon;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeString(price);
        dest.writeString(user);
        dest.writeString(photoId);
        dest.writeInt(leapIddd);
        dest.writeDouble(lat);
        dest.writeDouble(lon);
    }
}