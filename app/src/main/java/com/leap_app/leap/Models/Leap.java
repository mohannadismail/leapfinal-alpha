package com.leap_app.leap.Models;

import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.os.Parcel;
import android.os.Parcelable;
import android.util.Log;

import com.google.firebase.database.IgnoreExtraProperties;
import com.leap_app.leap.LeapProvider.LeapContract;
import com.leap_app.leap.LeapProvider.LeapProvider;

import java.util.ArrayList;
import java.util.Objects;

@IgnoreExtraProperties
public class Leap implements Parcelable {
    private Context context;
    private String name;
    private String price;
    private String user;
    private String photoId;
    private String leapId;

    private Leap(String name, String price, String user, String photoId, String leapId) {
        this.name = name;
        this.price = price;
        this.user = user;
        this.photoId = photoId;
        this.leapId = leapId;
    }

    private ArrayList<Leap> leaps;

    protected Leap(Parcel in) {
        name = in.readString();
        price = in.readString();
        user = in.readString();
        photoId = in.readString();
        leapId = in.readString();
    }

    public static final Creator<Leap> CREATOR = new Creator<Leap>() {
        @Override
        public Leap createFromParcel(Parcel in) {
            return new Leap(in);
        }

        @Override
        public Leap[] newArray(int size) {
            return new Leap[size];
        }
    };

    public Leap() {
    }

    private static String[] getNameColumn() {

        SQLiteDatabase db = LeapProvider.mLeapHelper.getReadableDatabase();
        Cursor c = db.query(LeapContract.LeapEntry.Table_Name, new String[]{"name"}, null, null, null, null, "id");

        if (c != null) {
            c.moveToFirst();
        }

        Log.d("TAG1", DatabaseUtils.dumpCursorToString(c));
        Log.d("Tagname", "Value:" + c.getCount());

        String[] s = new String[c.getCount()];
        int i = 0;
        while (!c.isAfterLast()) {
            s[i] = (c.getString(0));
            i++;
            c.moveToNext();
        }
        c.close();
        return s;
    }

    private static String[] getPriceColumn() {

        SQLiteDatabase db = LeapProvider.mLeapHelper.getReadableDatabase();
        Cursor c = db.query(LeapContract.LeapEntry.Table_Name, new String[]{"price"}, null, null, null, null, "id");

        if (c != null) {
            c.moveToFirst();
        }

        Log.d("TAG2", DatabaseUtils.dumpCursorToString(c));
        Log.d("Tagprice", "Value:" + Objects.requireNonNull(c).getCount());

        String[] s = new String[c.getCount()];
        int i = 0;
        while (!c.isAfterLast()) {
            s[i] = (c.getString(0));
            i++;
            c.moveToNext();
        }
        c.close();
        return s;
    }

    private static String[] getUserColumn() {

        SQLiteDatabase db = LeapProvider.mLeapHelper.getReadableDatabase();
        Cursor c = db.rawQuery("SELECT User.name FROM User INNER JOIN Leap ON User.id = Leap.user_id", null);

        if (c != null) {
            c.moveToFirst();
        }

        Log.d("TAG2", DatabaseUtils.dumpCursorToString(c));
        Log.d("Taguser", "Value:" + Objects.requireNonNull(c).getCount());

        String[] s = new String[c.getCount()];
        int i = 0;
        while (!c.isAfterLast()) {
            s[i] = (c.getString(0));
            i++;
            c.moveToNext();
        }
        c.close();
        return s;
    }

    private static String[] getImageColumn() {
        SQLiteDatabase db = LeapProvider.mLeapHelper.getReadableDatabase();
        Cursor c = db.query(LeapContract.LeapEntry.Table_Name, new String[]{"imageURI"}, null, null, null, null, "id");

        if (c != null) {
            c.moveToFirst();
        }
        Log.d("TAG2", DatabaseUtils.dumpCursorToString(c));
        Log.d("Tagimage", "Value:" + c.getCount());

        String[] s = new String[c.getCount()];
        int i = 0;
        while (!c.isAfterLast()) {
            s[i] = (c.getString(0));
            i++;
            c.moveToNext();
        }
        c.close();
        Log.d("TAG4", "Values: " + s[2]);
        return s;
    }

    private static int[] getLeapIdColumn() {

        SQLiteDatabase db = LeapProvider.mLeapHelper.getReadableDatabase();
        Cursor c = db.query(LeapContract.LeapEntry.Table_Name, new String[]{"id"}, null, null, null, null, "id");

        if (c != null) {
            c.moveToFirst();
        }

        Log.d("TAG5", DatabaseUtils.dumpCursorToString(c));
        Log.d("Tagname", "Value:" + c.getCount());

        int[] s = new int[c.getCount()];
        int i = 0;
        while (!c.isAfterLast()) {
            s[i] = (c.getInt(0));
            i++;
            c.moveToNext();
        }
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

    public String getLeapId() {
        return leapId;
    }

    public void setLeapId(String leapId) {
        this.leapId = leapId;
    }

    public ArrayList<Leap> getLeaps() {
        return leaps;
    }

    public void setLeaps(ArrayList<Leap> leaps) {
        this.leaps = leaps;
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
        dest.writeString(leapId);
    }
}
