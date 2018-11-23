package com.leap_app.leap.Models;

import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.os.Parcel;
import android.os.Parcelable;
import android.util.Log;

import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;
import com.leap_app.leap.LeapProvider.LeapContract;
import com.leap_app.leap.LeapProvider.LeapProvider;
import com.leap_app.leap.Utility.Constants;

/**
 * Place model to set and get place attributes from all over the app.
 */

public class Placeview implements Parcelable{
    private double lat;
    private double lon;
    private int placeId;
    private   String placeName;
    private   String placeCategory;
    private   String placeAddress;
    private   String placeDesc;
    private   int price;
    private   String phone;
    private   String placeID;


    protected Placeview(Parcel in) {
        lat = in.readDouble();
        lon = in.readDouble();
        placeId = in.readInt();
        placeName = in.readString();
        placeCategory = in.readString();
        placeAddress = in.readString();
        placeDesc = in.readString();
        price = in.readInt();
        phone = in.readString();
        placeID = in.readString();
    }

    public static final Creator<Placeview> CREATOR = new Creator<Placeview>() {
        @Override
        public Placeview createFromParcel(Parcel in) {
            return new Placeview(in);
        }

        @Override
        public Placeview[] newArray(int size) {
            return new Placeview[size];
        }
    };

    public   void setPlaceID(String placeID) {
        this.placeID = placeID;
    }

    public   void setPhone(String phone) {
        this.phone = phone;
    }

    public   void setPrice(int price) {
        this.price = price;
    }



    public Placeview(double lat, double lon, String placeName, String placeCategory, String placeAddress, String placeDesc) {
        setLat(lat);
        setLon(lon);
        setPlaceName(placeName);
        setPlaceAddress(placeAddress);
        setPlaceDesc(placeDesc);
        setPlaceCategory(placeCategory);
    }

    public Placeview(double lat, double lon, String placeName, String placeCategory, String placeAddress, String placeDesc, int placeId) {
        setLat(lat);
        setLon(lon);
        setPlaceName(placeName);
        setPlaceAddress(placeAddress);
        setPlaceDesc(placeDesc);
        setPlaceCategory(placeCategory);

        //place key

        setPlaceId(placeId);
    }

    public Placeview(double lat, double lon, String placeName, String placeAddress, int price, String phone, String id) {
        setLat(lat);
        setLon(lon);
        setPlaceName(placeName);
        setPlaceAddress(placeAddress);
        setPhone(phone);
        setPrice(price);


        //place ID

        setPlaceID(id);
    }
    public static   double[] getLat(int leapID)
    {
        SQLiteDatabase db = LeapProvider.mLeapHelper.getReadableDatabase();
        Cursor c = db.rawQuery("SELECT Place.Latitude FROM Place INNER JOIN Leap_Place ON Place.id = Leap_place.place_id WHERE Leap_place.leap_id =?", new String[]{String.valueOf(leapID)});

        if(c!=null)
        {
            c.moveToFirst();
        }

        Log.d("TAG5", DatabaseUtils.dumpCursorToString(c));
        Log.d("Taglat", "Value:" + c.getCount());

        double[] s = new double[c.getCount()];
        int i = 0;
        while (!c.isAfterLast()){
            s[i] = (c.getDouble(0));
            i++;
            c.moveToNext();
        }
        c.close();
        return s;
    }

    public static  double[] getLon(int leapID)
    {
        SQLiteDatabase db = LeapProvider.mLeapHelper.getReadableDatabase();
        Cursor c = db.rawQuery("SELECT Place.longitude FROM Place INNER JOIN Leap_Place ON Place.id = Leap_place.place_id WHERE Leap_place.leap_id =?", new String[]{String.valueOf(leapID)});

        if(c!=null)
        {
            c.moveToFirst();
        }

        Log.d("TAG5", DatabaseUtils.dumpCursorToString(c));
        Log.d("Taglon", "Value:" + c.getCount());

        double[] s = new double[c.getCount()];
        int i = 0;
        while (!c.isAfterLast()){
            s[i] = (c.getDouble(0));
            i++;
            c.moveToNext();
        }
        c.close();
        return s;
    }


    public static  String getPlaceNameColumn(double lat, double lon) {

        SQLiteDatabase db = LeapProvider.mLeapHelper.getReadableDatabase();
        Cursor c = db.query(LeapContract.PlaceEntry.Table_Name, new String[]{"name"}, "latitude =" + lat + " AND " + "longitude =" + lon, null, null, null, "id");

        if(c!=null)
        {
            c.moveToFirst();
        }

        Log.d("TAGP1", DatabaseUtils.dumpCursorToString(c));
        Log.d("TagPname", "Value:" + c.getCount());

        String s = (c.getString(0));

        c.close();
        return s;
    }

    public static  String getPlaceCatColumn(double lat, double lon) {

        SQLiteDatabase db = LeapProvider.mLeapHelper.getReadableDatabase();
        Cursor c = db.query(LeapContract.PlaceEntry.Table_Name, new String[]{"category"}, "latitude =" + lat + " AND " + "longitude =" + lon, null, null, null, "id");

        if(c!=null)
        {
            c.moveToFirst();
        }

        Log.d("TAGP2", DatabaseUtils.dumpCursorToString(c));
        Log.d("TagPCat", "Value:" + c.getCount());

        String s = (c.getString(0));

        c.close();
        return s;
    }

    public static  String getPlaceAddColumn(double lat, double lon) {

        SQLiteDatabase db = LeapProvider.mLeapHelper.getReadableDatabase();
        Cursor c = db.query(LeapContract.PlaceEntry.Table_Name, new String[]{"Address"}, "latitude =" + lat + " AND " + "longitude =" + lon, null, null, null, "id");

        if(c!=null)
        {
            c.moveToFirst();
        }

        Log.d("TAGP3", DatabaseUtils.dumpCursorToString(c));
        Log.d("TagPAdd", "Value:" + c.getCount());

        String s = (c.getString(0));

        c.close();
        return s;
    }

    public static  String getPlaceDescColumn(double lat, double lon) {

        SQLiteDatabase db = LeapProvider.mLeapHelper.getReadableDatabase();
        Cursor c = db.query(LeapContract.PlaceEntry.Table_Name, new String[]{"description"}, "latitude =" + lat + " AND " + "longitude =" + lon, null, null, null, "id");

        if(c!=null)
        {
            c.moveToFirst();
        }

        Log.d("TAGP4", DatabaseUtils.dumpCursorToString(c));
        Log.d("TagPDesc", "Value:" + c.getCount());

        String s = (c.getString(0));

        c.close();
        return s;
    }

    public void setLat(double lat) {
        this.lat = lat;
    }

    public void setPlaceDesc(String placeDesc) {
        this.placeDesc = placeDesc;
    }

    public void setPlaceAddress(String placeAddress) {
        this.placeAddress = placeAddress;
    }

    public void setPlaceCategory(String placeCategory) {
        this.placeCategory = placeCategory;
    }


    public void setPlaceName(String placeName) {
        this.placeName = placeName;
    }

    public int getPlaceId() {

        return placeId;
    }

    public void setPlaceId(int placeId) {
        this.placeId = placeId;
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
        dest.writeDouble(lat);
        dest.writeDouble(lon);
        dest.writeInt(placeId);
        dest.writeString(placeName);
        dest.writeString(placeCategory);
        dest.writeString(placeAddress);
        dest.writeString(placeDesc);
        dest.writeInt(price);
        dest.writeString(phone);
        dest.writeString(placeID);
    }
}
