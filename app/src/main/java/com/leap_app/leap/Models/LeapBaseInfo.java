package com.leap_app.leap.Models;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by RamyFRadwan on 10/05/2016.
 */
public class LeapBaseInfo implements Parcelable{
    String date;
    String leapDescription;
    String leapLocation;
    String leapName;
    String leapPrice;
    String time;
    String leapID;


    public LeapBaseInfo(){}

    public LeapBaseInfo(String leapName, String leapDescription, String leapLocation, String leapPrice, String date,  String time, String leapID)
    {
        this.leapID = leapID;
        this.leapName = leapName;
        this.leapDescription = leapDescription;
        this.leapLocation = leapLocation;
        this.leapPrice = leapPrice;
        this.date = date;
        this.time = time;
    }
    public LeapBaseInfo(String leapName, String leapDescription, String leapLocation, String leapPrice, String date,  String time)
    {

        this.leapName = leapName;
        this.leapDescription = leapDescription;
        this.leapLocation = leapLocation;
        this.leapPrice = leapPrice;
        this.date = date;
        this.time = time;
    }

    protected LeapBaseInfo(Parcel in) {
        date = in.readString();
        leapDescription = in.readString();
        leapLocation = in.readString();
        leapName = in.readString();
        leapPrice = in.readString();
        time = in.readString();
    }

    public static final Creator<LeapBaseInfo> CREATOR = new Creator<LeapBaseInfo>() {
        @Override
        public LeapBaseInfo createFromParcel(Parcel in) {
            return new LeapBaseInfo(in);
        }

        @Override
        public LeapBaseInfo[] newArray(int size) {
            return new LeapBaseInfo[size];
        }
    };

    public String getLeapID() {
        return leapID;
    }

    public void setLeapID(String leapID) {
        this.leapID = leapID;
    }

    public String getDate() {

        return date;
    }
    public void setDate(String date) {
        this.date = date;
    }

    public String getLeapDescription() {

        return leapDescription;
    }

    public void setLeapDescription(String leapDescription) {
        this.leapDescription = leapDescription;
    }

    public String getLeapLocation() {

        return leapLocation;
    }


    public void setLeapLocation(String leapLocation) {
        this.leapLocation = leapLocation;
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




    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(date);
        dest.writeString(leapDescription);
        dest.writeString(leapLocation);
        dest.writeString(leapName);
        dest.writeString(leapPrice);
        dest.writeString(time);

    }

}