package com.leap_app.leap.Models;

import java.util.ArrayList;

/**
 * Created by Mohannad on 04/05/2016.
 */
public class LeapBase {

    ArrayList<Placeview> placeviews;
    LeapBaseInfo leapBaseInfo;
    public LeapBase(LeapBaseInfo leapBaseInfo, ArrayList<Placeview> placeviews){
        this.placeviews = placeviews;
        this.leapBaseInfo = leapBaseInfo;

    }

    public static class LeapBaseInfo{
        public String getTime() {
            return time;
        }

        public void setTime(String time) {
            this.time = time;
        }

        public String getDate() {

            return date;
        }

        public void setDate(String date) {
            this.date = date;
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

        String leapName; String leapDescription; String leapLocation; String leapPrice; String date; String time;

        public LeapBaseInfo(String leapName, String leapDescription, String leapLocation, String leapPrice, String date, String time)
        {

            this.leapName = leapName;
            this.leapDescription = leapDescription;
            this.leapLocation = leapLocation;
            this.leapPrice = leapPrice;
            this.date = date;
            this.time = time;
        }

    }
}
