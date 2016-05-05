package com.leap_app.leap.Models;

import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.leap_app.leap.LeapProvider.LeapContract;
import com.leap_app.leap.LeapProvider.LeapProvider;

/**
 * Place model to set and get place attributes from all over the app.
 */

public class Placeview {
    public double getLat() {
        return lat;
    }

    public double lat;

    public String getPlaceAddress() {
        return placeAddress;
    }

    public String getPlaceDesc() {
        return placeDesc;
    }

    public double lon;

    public int getPrice() {
        return price;
    }


    public String getPhone() {
        return phone;
    }

    public String getPlaceName() {
        return placeName;
    }

    public String placeName;

    public String getPlaceCategory() {
        return placeCategory;
    }

    public String placeCategory;

    public double getLon() {
        return lon;
    }

    public String placeAddress;

    public String getPlaceID() {
        return placeID;
    }

    public String placeDesc;
    public int price;
    public String phone;
    public String placeID;

//    public String getFPlaceID() {
//        Firebase refID = new Firebase(Constants.FIREBASE_LEAP_PLACES_URL).child(Constants.FIREBASE_PROPERTY_ID);
//        refID.addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(DataSnapshot dataSnapshot) {
//                placeID = dataSnapshot.getValue().toString();
//            }
//
//            @Override
//            public void onCancelled(FirebaseError firebaseError) {
//
//            }
//        });
//
//        return placeID;
//    }

    public void setPlaceID(String placeID) {
        this.placeID = placeID;
    }

//    public String getFPhone() {
//        Firebase refPhone = new Firebase(Constants.FIREBASE_LEAP_PLACES_URL).child(Constants.FIREBASE_PROPERTY_Phone);
//        refPhone.addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(DataSnapshot dataSnapshot) {
//                phone = dataSnapshot.getValue().toString();
//            }
//
//            @Override
//            public void onCancelled(FirebaseError firebaseError) {
//
//            }
//        });
//
//
//        return phone;
//    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

//    public int getFPrice() {
//        Firebase refPrice = new Firebase(Constants.FIREBASE_LEAP_PLACES_URL).child(Constants.FIREBASE_PROPERTY_Price);
//        refPrice.addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(DataSnapshot dataSnapshot) {
//                price = (int) dataSnapshot.getValue();
//            }
//
//            @Override
//            public void onCancelled(FirebaseError firebaseError) {
//
//            }
//        });
//
//        return price;
//    }

    public void setPrice(int price) {
        this.price = price;
    }


    Placeview(double lat, double lon, String placeName, String placeCategory, String placeAddress, String placeDesc,String placeID) {
        setLat(lat);
        setLon(lon);
        setPlaceName(placeName);
        setPlaceAddress(placeAddress);
        setPlaceDesc(placeDesc);
        setPlaceCategory(placeCategory);
        setPlaceID(placeID);
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

    public static double[] getLat(int leapID) {
        SQLiteDatabase db = LeapProvider.mLeapHelper.getReadableDatabase();
        Cursor c = db.rawQuery("SELECT Place.Latitude FROM Place INNER JOIN Leap_Place ON Place.id = Leap_place.place_id WHERE Leap_place.leap_id =?", new String[]{String.valueOf(leapID)});

        if (c != null) {
            c.moveToFirst();
        }

        Log.d("TAG5", DatabaseUtils.dumpCursorToString(c));
        Log.d("Taglat", "Value:" + c.getCount());

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

    public static double[] getLon(int leapID) {
        SQLiteDatabase db = LeapProvider.mLeapHelper.getReadableDatabase();
        Cursor c = db.rawQuery("SELECT Place.longitude FROM Place INNER JOIN Leap_Place ON Place.id = Leap_place.place_id WHERE Leap_place.leap_id =?", new String[]{String.valueOf(leapID)});

        if (c != null) {
            c.moveToFirst();
        }

        Log.d("TAG5", DatabaseUtils.dumpCursorToString(c));
        Log.d("Taglon", "Value:" + c.getCount());

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


    public static String getPlaceNameColumn(double lat, double lon) {

        SQLiteDatabase db = LeapProvider.mLeapHelper.getReadableDatabase();
        Cursor c = db.query(LeapContract.PlaceEntry.Table_Name, new String[]{"name"}, "latitude =" + lat + " AND " + "longitude =" + lon, null, null, null, "id");

        if (c != null) {
            c.moveToFirst();
        }

        Log.d("TAGP1", DatabaseUtils.dumpCursorToString(c));
        Log.d("TagPname", "Value:" + c.getCount());

        String s = (c.getString(0));

        c.close();
        return s;
    }

    public static String getPlaceCatColumn(double lat, double lon) {

        SQLiteDatabase db = LeapProvider.mLeapHelper.getReadableDatabase();
        Cursor c = db.query(LeapContract.PlaceEntry.Table_Name, new String[]{"category"}, "latitude =" + lat + " AND " + "longitude =" + lon, null, null, null, "id");

        if (c != null) {
            c.moveToFirst();
        }

        Log.d("TAGP2", DatabaseUtils.dumpCursorToString(c));
        Log.d("TagPCat", "Value:" + c.getCount());

        String s = (c.getString(0));

        c.close();
        return s;
    }

    public static String getPlaceAddColumn(double lat, double lon) {

        SQLiteDatabase db = LeapProvider.mLeapHelper.getReadableDatabase();
        Cursor c = db.query(LeapContract.PlaceEntry.Table_Name, new String[]{"Address"}, "latitude =" + lat + " AND " + "longitude =" + lon, null, null, null, "id");

        if (c != null) {
            c.moveToFirst();
        }

        Log.d("TAGP3", DatabaseUtils.dumpCursorToString(c));
        Log.d("TagPAdd", "Value:" + c.getCount());

        String s = (c.getString(0));

        c.close();
        return s;
    }

    public static String getPlaceDescColumn(double lat, double lon) {

        SQLiteDatabase db = LeapProvider.mLeapHelper.getReadableDatabase();
        Cursor c = db.query(LeapContract.PlaceEntry.Table_Name, new String[]{"description"}, "latitude =" + lat + " AND " + "longitude =" + lon, null, null, null, "id");

        if (c != null) {
            c.moveToFirst();
        }

        Log.d("TAGP4", DatabaseUtils.dumpCursorToString(c));
        Log.d("TagPDesc", "Value:" + c.getCount());

        String s = (c.getString(0));

        c.close();
        return s;
    }

    //setters and getters
//    public double getFLat() {
//        Firebase refLat = new Firebase(Constants.FIREBASE_LEAP_PLACES_URL).child(Constants.FIREBASE_PROPERTY_Latitude);
//        refLat.addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(DataSnapshot dataSnapshot) {
//                lat = (double) dataSnapshot.getValue();
//            }
//
//            @Override
//            public void onCancelled(FirebaseError firebaseError) {
//
//            }
//        });
//
//        return lat;
//    }

    public void setLat(double lat) {
        this.lat = lat;
    }


//    public String getFPlaceDesc() {
//        Firebase refDescription = new Firebase(Constants.FIREBASE_LEAP_PLACES_URL).child(Constants.FIREBASE_PROPERTY_Description);
//        refDescription.addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(DataSnapshot dataSnapshot) {
//                placeDesc = dataSnapshot.getValue().toString();
//            }
//
//            @Override
//            public void onCancelled(FirebaseError firebaseError) {
//
//            }
//        });
//        return placeDesc;
//    }

    public void setPlaceDesc(String placeDesc) {
        this.placeDesc = placeDesc;
    }

//    public String getFPlaceAddress() {
//        Firebase refAdress = new Firebase(Constants.FIREBASE_LEAP_PLACES_URL).child(Constants.FIREBASE_PROPERTY_Address);
//        refAdress.addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(DataSnapshot dataSnapshot) {
//                placeAddress = dataSnapshot.getValue().toString();
//
//            }
//
//
//            @Override
//            public void onCancelled(FirebaseError firebaseError) {
//
//            }
//        });
//        return placeAddress;
//    }

    public void setPlaceAddress(String placeAddress) {
        this.placeAddress = placeAddress;
    }

//    public String getFPlaceCategory() {
//        Firebase refCategory = new Firebase(Constants.FIREBASE_LEAP_PLACES_URL).child(Constants.FIREBASE_PROPERTY_Category_Name);
//        refCategory.addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(DataSnapshot dataSnapshot) {
//                placeCategory = dataSnapshot.getValue().toString();
//            }
//
//            @Override
//            public void onCancelled(FirebaseError firebaseError) {
//
//            }
//        });
//
//        return placeCategory;
//    }

    public void setPlaceCategory(String placeCategory) {
        this.placeCategory = placeCategory;
    }

//    public String getFPlaceName() {
//        Firebase refname = new Firebase(Constants.FIREBASE_LEAP_PLACES_URL).child(Constants.FIREBASE_PROPERTY_NAME);
//        refname.addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(DataSnapshot dataSnapshot) {
//                placeName = dataSnapshot.getValue().toString();
//            }
//
//            @Override
//            public void onCancelled(FirebaseError firebaseError) {
//
//            }
//        });
//        return placeName;
//    }

    public void setPlaceName(String placeName) {
        this.placeName = placeName;
    }



//    public double getFLon() {
//        Firebase refLon = new Firebase(Constants.FIREBASE_LEAP_PLACES_URL).child(Constants.FIREBASE_PROPERTY_Longitude);
//        refLon.addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(DataSnapshot dataSnapshot) {
//                lon = (double) dataSnapshot.getValue();
//            }
//
//            @Override
//            public void onCancelled(FirebaseError firebaseError) {
//
//            }
//        });
//        return lon;
//    }

    public void setLon(double lon) {
        this.lon = lon;
    }


}
