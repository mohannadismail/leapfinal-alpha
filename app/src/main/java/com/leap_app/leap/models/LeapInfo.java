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



public class LeapInfo implements Parcelable {

    private Context context;
    private String leapName;
    private String leapPrice;
    private String leapDescription;
    private String userName;
    private int userNoOfLeaps;
    private int mapPhotoId;
    private int userPhotoId;
    private int leapPhotoId;

    LeapInfo(String leapName, String leapPrice, String leapDescription, String userName, int userNoOfLeaps, int mapPhotoId, int userPhotoId, int leapPhotoId) {
        this.leapName = leapName;
        this.leapPrice = leapPrice;
        this.leapDescription = leapDescription;
        this.userName = userName;
        this.userNoOfLeaps = userNoOfLeaps;
        this.mapPhotoId = mapPhotoId;
        this.userPhotoId = userPhotoId;
        this.leapPhotoId = leapPhotoId;
    }

    private LeapInfo(Parcel in) {
        leapName = in.readString();
        leapPrice = in.readString();
        leapDescription = in.readString();
        userName = in.readString();
        userNoOfLeaps = in.readInt();
        mapPhotoId = in.readInt();
        userPhotoId = in.readInt();
        leapPhotoId = in.readInt();
    }

    public static final Creator<LeapInfo> CREATOR = new Creator<LeapInfo>() {
        @Override
        public LeapInfo createFromParcel(Parcel in) {
            return new LeapInfo(in);
        }

        @Override
        public LeapInfo[] newArray(int size) {
            return new LeapInfo[size];
        }
    };

    public static String getLeapNameColumn(int leapID) {

        SQLiteDatabase db = LeapProvider.mLeapHelper.getReadableDatabase();
        Cursor c = db.query(LeapContract.LeapEntry.Table_Name, new String[]{"name"}, "id =" + leapID, null, null, null, "id");

        if (c != null) {
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

        if (c != null) {
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

        if (c != null) {
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

        if (c != null) {
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

        if (c != null) {
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

        if (c != null) {
            c.moveToFirst();
        }
        Log.d("TAG2", DatabaseUtils.dumpCursorToString(c));
        Log.d("Tagimage1", "Value:" + c.getCount());

        String s = (c.getString(0));

        c.close();
        return s;
    }

    public static String getLocationColumn(int leapID) {
        SQLiteDatabase db = LeapProvider.mLeapHelper.getReadableDatabase();
        Cursor c = db.query(LeapContract.LeapEntry.Table_Name, new String[]{"mapUri"}, "id =" + leapID, null, null, null, "id");

        if (c != null) {
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

        if (c != null) {
            c.moveToFirst();
        }
        Log.d("TAG2", DatabaseUtils.dumpCursorToString(c));
        Log.d("Tagimage2", "Value:" + c.getCount());

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

    public String getLeapName() {
        return leapName;
    }

    public void setLeapName(String leapName) {
        this.leapName = leapName;
    }

    public String getLeapPrice() {
        return leapPrice;
    }

    public void setLeapPrice(String leapPrice) {
        this.leapPrice = leapPrice;
    }

    public String getLeapDescription() {
        return leapDescription;
    }

    public void setLeapDescription(String leapDescription) {
        this.leapDescription = leapDescription;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public int getUserNoOfLeaps() {
        return userNoOfLeaps;
    }

    public void setUserNoOfLeaps(int userNoOfLeaps) {
        this.userNoOfLeaps = userNoOfLeaps;
    }

    public int getMapPhotoId() {
        return mapPhotoId;
    }

    public void setMapPhotoId(int mapPhotoId) {
        this.mapPhotoId = mapPhotoId;
    }

    public int getUserPhotoId() {
        return userPhotoId;
    }

    public void setUserPhotoId(int userPhotoId) {
        this.userPhotoId = userPhotoId;
    }

    public int getLeapPhotoId() {
        return leapPhotoId;
    }

    public void setLeapPhotoId(int leapPhotoId) {
        this.leapPhotoId = leapPhotoId;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(leapName);
        dest.writeString(leapPrice);
        dest.writeString(leapDescription);
        dest.writeString(userName);
        dest.writeInt(userNoOfLeaps);
        dest.writeInt(mapPhotoId);
        dest.writeInt(userPhotoId);
        dest.writeInt(leapPhotoId);
    }
}
