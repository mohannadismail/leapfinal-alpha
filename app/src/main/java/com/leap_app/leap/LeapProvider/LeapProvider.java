package com.leap_app.leap.LeapProvider;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.util.Log;

import com.leap_app.leap.Utility.LeapLatLon;

/**/
public class LeapProvider extends ContentProvider {

    public static LeapDbHelper mLeapHelper;
    private static final UriMatcher sUriMatcher = buildUriMatcher();
    static final int USER = 1;
    static final int PLACE = 2;
    static final int LEAP = 3;
    static final int REVIEW = 4;
    static final int PLACELEAP = 5;

    static final String TAG = "Leap Provider";
    static final String PROVIDER_NAME = LeapContract.CONTENT_AUTHORITY;
    public static final String URL = "content://" + PROVIDER_NAME ;



    static UriMatcher buildUriMatcher() {
        final UriMatcher matcher = new UriMatcher(UriMatcher.NO_MATCH);
        final String authority = LeapContract.CONTENT_AUTHORITY;
        matcher.addURI(authority, LeapContract.PATH_USER, USER);
        matcher.addURI(authority, LeapContract.PATH_PLACE, PLACE);
        matcher.addURI(authority, LeapContract.PATH_LEAP, LEAP);
        matcher.addURI(authority, LeapContract.PATH_REVIEW, REVIEW);
        matcher.addURI(authority, LeapContract.PATH_PLACE_LEAP, PLACELEAP);

        return matcher;
    }

//    static final String SINGLE_RECORD_MIME_TYPE = "vnd.android.cursor.item/vnd.marakana.android.lifecycle.status";
//    static final String MULTIPLE_RECORDS_MIME_TYPE = "vnd.android.cursor.dir/vnd.marakana.android.lifecycle.status";

    @Override
    public boolean onCreate() {
        Log.d(TAG, "onCreate");
        mLeapHelper = new LeapDbHelper(getContext());
        SQLiteDatabase db = mLeapHelper.getWritableDatabase();
        return (db == null)? false:true;
//        return true;
    }

    @Override
    public String getType(Uri uri) {
        final int match = sUriMatcher.match(uri);

        switch (match){
            case USER:
                return LeapContract.UserEntry.CONTENT_TYPE;

            case PLACE:
                return LeapContract.PlaceEntry.CONTENT_TYPE;

            case LEAP:
                return LeapContract.LeapEntry.CONTENT_TYPE;

            case REVIEW:
                return LeapContract.ReviewEntry.CONTENT_TYPE;

            case PLACELEAP:
                return LeapContract.Leap_Place_Entry.CONTENT_TYPE;

            default:
                throw new UnsupportedOperationException("Unknown URI: " +  uri);
        }
    }

    @Override
    public Uri insert(Uri uri, ContentValues values) {
        final SQLiteDatabase db = mLeapHelper.getWritableDatabase();
        Uri _uri;
        switch (sUriMatcher.match(uri)){
            case USER:
                long _ID1 = db.insert(LeapContract.UserEntry.Table_Name, null, values);
                //---if added successfully---
                if (_ID1 > 0) {
                    _uri = LeapContract.UserEntry.buildUserUri(_ID1);
                }

                else{
                    throw  new android.database.SQLException("Failed to insert row into " + uri);
                }
                break;

            case PLACE:
                long _ID2 = db.insert(LeapContract.PlaceEntry.Table_Name,null, values);
                //---if added successfully---
                if (_ID2 > 0) {
                    _uri = LeapContract.PlaceEntry.buildPlaceUri(_ID2);
                }
                else{
                    throw  new android.database.SQLException("Failed to insert row into " + uri);
                }
                break;

            case LEAP:
                long _ID3 = db.insert(LeapContract.LeapEntry.Table_Name,null, values);
                //---if added successfully---
                if (_ID3 > 0) {
                    _uri = LeapContract.LeapEntry.buildPlaceUri(_ID3);
                }
                else{
                    throw  new android.database.SQLException("Failed to insert row into " + uri);
                }
                break;

            case REVIEW:
                long _ID4 = db.insert(LeapContract.ReviewEntry.Table_Name,null, values);
                //---if added successfully---
                if (_ID4 > 0) {
                    _uri =  LeapContract.ReviewEntry.buildPlaceUri(_ID4);
                }
                else{
                    throw  new android.database.SQLException("Failed to insert row into " + uri);
                }
                break;

            case PLACELEAP:
                long _ID5 = db.insert( LeapContract.Leap_Place_Entry.Table_Name,null, values);
                //---if added successfully---
                if (_ID5 > 0) {
                    _uri =  LeapContract.Leap_Place_Entry.buildPlaceUri(_ID5);
                }
                else{
                    throw  new android.database.SQLException("Failed to insert row into " + uri);
                }
                break;

            default: throw new UnsupportedOperationException("Unknown uri: " + uri);
        }

        Log.d(TAG, "insert uri: " + uri.toString());
        return _uri;
    }

    @Override
    public int update(Uri uri, ContentValues values, String selection,
                      String[] selectionArgs) {

        final SQLiteDatabase db = mLeapHelper.getWritableDatabase();
        int rowsUpdated;

        switch (sUriMatcher.match(uri)){
            case USER :
                rowsUpdated = db.update( LeapContract.UserEntry.Table_Name, values, selection, selectionArgs);
                break;

            case PLACE :
                rowsUpdated = db.update( LeapContract.PlaceEntry.Table_Name, values, selection, selectionArgs);
                break;

            case LEAP :
                rowsUpdated = db.update( LeapContract.LeapEntry.Table_Name, values, selection, selectionArgs);
                break;

            case REVIEW :
                rowsUpdated = db.update( LeapContract.ReviewEntry.Table_Name, values, selection, selectionArgs);
                break;

            case PLACELEAP :
                rowsUpdated = db.update( LeapContract.Leap_Place_Entry.Table_Name, values, selection, selectionArgs);
                break;

            default: throw new UnsupportedOperationException("Unknown uri: " + uri);
        }

        if (rowsUpdated != 0){
            getContext().getContentResolver().notifyChange(uri,null);
        }
        Log.d(TAG, "update uri: " + uri.toString());
        return rowsUpdated;
    }

    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {

        final SQLiteDatabase db = mLeapHelper.getWritableDatabase();
        int rowsDeleted;

        if (null == selection) selection = "1";

        switch (sUriMatcher.match(uri)){
            case USER :
                rowsDeleted = db.delete(LeapContract.UserEntry.Table_Name, selection, selectionArgs);
                break;

            case PLACE :
                rowsDeleted = db.delete(LeapContract.PlaceEntry.Table_Name, selection, selectionArgs);
                break;

            case LEAP :
                rowsDeleted = db.delete(LeapContract.LeapEntry.Table_Name, selection, selectionArgs);
                break;

            case REVIEW :
                rowsDeleted = db.delete(LeapContract.ReviewEntry.Table_Name, selection, selectionArgs);
                break;

            case PLACELEAP :
                rowsDeleted = db.delete(LeapContract.Leap_Place_Entry.Table_Name, selection, selectionArgs);
                break;

            default: throw new UnsupportedOperationException("Unknown uri: " + uri);
        }

        if (rowsDeleted != 0){
            getContext().getContentResolver().notifyChange(uri,null);
        }

        Log.d(TAG, "delete uri: " + uri.toString());
        return rowsDeleted;
    }

    @Override
    public Cursor query(Uri uri, String[] projection, String selection,
                        String[] selectionArgs, String sortOrder) {

        Cursor cursor;

        switch (sUriMatcher.match(uri)){
            case USER: {
                cursor = mLeapHelper.getReadableDatabase().query(
                        LeapContract.UserEntry.Table_Name,
                        projection,
                        selection,
                        selectionArgs,
                        null,
                        null,
                        sortOrder);
                break;
            }

            case PLACE: {
                cursor = mLeapHelper.getReadableDatabase().query(
                        LeapContract.PlaceEntry.Table_Name,
                        projection,
                        selection,
                        selectionArgs,
                        null,
                        null,
                        sortOrder);
                break;
            }

            case LEAP: {
                cursor = mLeapHelper.getReadableDatabase().query(
                         LeapContract.LeapEntry.Table_Name,
                        projection,
                        selection,
                        selectionArgs,
                        null,
                        null,
                        sortOrder);
                break;
            }

            case REVIEW: {
                cursor = mLeapHelper.getReadableDatabase().query(
                         LeapContract.ReviewEntry.Table_Name,
                        projection,
                        selection,
                        selectionArgs,
                        null,
                        null,
                        sortOrder);
                break;
            }

            case PLACELEAP: {
                cursor = mLeapHelper.getReadableDatabase().query(
                         LeapContract.Leap_Place_Entry.Table_Name,
                        projection,
                        selection,
                        selectionArgs,
                        null,
                        null,
                        sortOrder);
                break;
            }

            default: throw new UnsupportedOperationException("Unknown uri: " + uri);
        }

        cursor.setNotificationUri(getContext().getContentResolver(), uri);
        Log.d(TAG, "query with uri: " + uri.toString());
        return cursor;
    }


    public static void insertUserRecords(){

        SQLiteDatabase db = mLeapHelper.getWritableDatabase();

        ContentValues values1 = new ContentValues();
        ContentValues values2 = new ContentValues();
        ContentValues values3 = new ContentValues();

        values1.put( LeapContract.UserEntry.COLUMN_Name, "Monica");
        values1.put( LeapContract.UserEntry.COLUMN_UserName, "Guy123");
        values1.put( LeapContract.UserEntry.COLUMN_Image, "https://upload.wikimedia.org/wikipedia/en/d/d0/Courteney_Cox_as_Monica_Geller.jpg");
        values1.put( LeapContract.UserEntry.COLUMN_No_Of_Leaps, "2");
        values1.put( LeapContract.UserEntry.COLUMN_password, "12345");
        values1.put( LeapContract.UserEntry.COLUMN_Address, "45 Blue St.");
        values1.put( LeapContract.UserEntry.COLUMN_Email, "123@abc.com");
        values1.put( LeapContract.UserEntry.COLUMN_Date_Of_birth, "12/3/1993");
        values1.put( LeapContract.UserEntry.COLUMN_About_me, "I am Ahmad");
        values1.put( LeapContract.UserEntry.COLUMN_HomePage, "MY HOME1");

        values2.put( LeapContract.UserEntry.COLUMN_Name, "Mohammad");
        values2.put( LeapContract.UserEntry.COLUMN_UserName, "Guy1234");
        values2.put( LeapContract.UserEntry.COLUMN_Image, "https://www.bluerunnersolutions.com/images/icon-idea1-profile1.jpg");
        values2.put( LeapContract.UserEntry.COLUMN_No_Of_Leaps, "1");
        values2.put( LeapContract.UserEntry.COLUMN_password, "123456");
        values2.put( LeapContract.UserEntry.COLUMN_Address, "45 Green St.");
        values2.put( LeapContract.UserEntry.COLUMN_Email, "1234@abc.com");
        values2.put( LeapContract.UserEntry.COLUMN_Date_Of_birth, "16/3/1993");
        values2.put( LeapContract.UserEntry.COLUMN_About_me, "I am Mohammad");
        values2.put( LeapContract.UserEntry.COLUMN_HomePage, "MY HOME2");

        values3.put( LeapContract.UserEntry.COLUMN_Name, "Khaled");
        values3.put( LeapContract.UserEntry.COLUMN_UserName, "Guy12345");
        values3.put( LeapContract.UserEntry.COLUMN_Image, "https://www.bluerunnersolutions.com/images/icon-idea1-profile1.jpg");
        values3.put( LeapContract.UserEntry.COLUMN_No_Of_Leaps, "1");
        values3.put( LeapContract.UserEntry.COLUMN_password, "1234567");
        values3.put( LeapContract.UserEntry.COLUMN_Address, "45 Pink St.");
        values3.put( LeapContract.UserEntry.COLUMN_Email, "12345@abc.com");
        values3.put( LeapContract.UserEntry.COLUMN_Date_Of_birth, "11/8/1993");
        values3.put( LeapContract.UserEntry.COLUMN_About_me, "I am Khaled");
        values3.put(LeapContract.UserEntry.COLUMN_HomePage, "MY HOME3");

        db.insert(LeapContract.UserEntry.Table_Name, null, values1);
        db.insert( LeapContract.UserEntry.Table_Name, null, values2);
        db.insert( LeapContract.UserEntry.Table_Name, null, values3);
        db.close();

    }

    public static void insertPlaceRecords(){

        SQLiteDatabase db = mLeapHelper.getWritableDatabase();

        ContentValues values1 = new ContentValues();
        ContentValues values2 = new ContentValues();
        ContentValues values3 = new ContentValues();
        ContentValues values4 = new ContentValues();
        ContentValues values5 = new ContentValues();
        ContentValues values6 = new ContentValues();
        ContentValues values7 = new ContentValues();
        ContentValues values8 = new ContentValues();
        ContentValues values9 = new ContentValues();
        ContentValues values10 = new ContentValues();
        ContentValues values11 = new ContentValues();
        ContentValues values12 = new ContentValues();
        ContentValues values13 = new ContentValues();
        ContentValues values14 = new ContentValues();


        //values1.put(LeapContract.PlaceEntry.COLUMN_Place_ID, "1");
        values1.put( LeapContract.PlaceEntry.COLUMN_Name, "KFC");
        values1.put( LeapContract.PlaceEntry.COLUMN_Address, "40 EL-Gomhorya St.");
        values1.put( LeapContract.PlaceEntry.COLUMN_Image, "http://famouslogos.net/images/kfc-logo.jpg");
        values1.put( LeapContract.PlaceEntry.COLUMN_Description, "A fast-food Restaurant");
        values1.put( LeapContract.PlaceEntry.COLUMN_Category_Name, "Fast-food Restaurant");
        values1.put( LeapContract.PlaceEntry.COLUMN_Latitude, "31.045257");
        values1.put( LeapContract.PlaceEntry.COLUMN_Longitude, "31.359905");

        values2.put( LeapContract.PlaceEntry.COLUMN_Name, "Elghaly Coffee");
        values2.put( LeapContract.PlaceEntry.COLUMN_Address, "5 El-Gomhorya St.");
        values2.put( LeapContract.PlaceEntry.COLUMN_Image, "ImageURI");
        values2.put( LeapContract.PlaceEntry.COLUMN_Description, "drinks");
        values2.put( LeapContract.PlaceEntry.COLUMN_Category_Name, "Caffee");
        values2.put( LeapContract.PlaceEntry.COLUMN_Latitude, "31.045220");
        values2.put( LeapContract.PlaceEntry.COLUMN_Longitude, "31.352959");

        values3.put( LeapContract.PlaceEntry.COLUMN_Name, "Teto");
        values3.put( LeapContract.PlaceEntry.COLUMN_Address, "Gehan El-Sadat St. beside Xbox");
        values3.put( LeapContract.PlaceEntry.COLUMN_Image, "ImageURI");
        values3.put( LeapContract.PlaceEntry.COLUMN_Description, "playstation");
        values3.put( LeapContract.PlaceEntry.COLUMN_Category_Name, "Fun");
        values3.put( LeapContract.PlaceEntry.COLUMN_Latitude, "31.035456");
        values3.put( LeapContract.PlaceEntry.COLUMN_Longitude, "31.3588755");
//////////////////////////////////////////////////////////////////////////////////////////////////
        values4.put( LeapContract.PlaceEntry.COLUMN_Name, "Algma3a Plaza Mall");
        values4.put( LeapContract.PlaceEntry.COLUMN_Address, "Gehan El-sadat St.");
        values4.put( LeapContract.PlaceEntry.COLUMN_Image, "ImageURI");
        values4.put( LeapContract.PlaceEntry.COLUMN_Description, "mall");
        values4.put( LeapContract.PlaceEntry.COLUMN_Category_Name, "Mall");
        values4.put( LeapContract.PlaceEntry.COLUMN_Latitude, "31.037365");
        values4.put( LeapContract.PlaceEntry.COLUMN_Longitude, "31.361139");

        values5.put( LeapContract.PlaceEntry.COLUMN_Name, "McDonald`s");
        values5.put( LeapContract.PlaceEntry.COLUMN_Address, "40th el-Gomhorya St.");
        values5.put(LeapContract.PlaceEntry.COLUMN_Image, "ImageURI");
        values5.put( LeapContract.PlaceEntry.COLUMN_Description, "A fast-food Restaurant");
        values5.put( LeapContract.PlaceEntry.COLUMN_Category_Name, "Fast-food Restaurant");
        values5.put( LeapContract.PlaceEntry.COLUMN_Latitude, "31.045257");
        values5.put( LeapContract.PlaceEntry.COLUMN_Longitude, "31.359905");

        values6.put( LeapContract.PlaceEntry.COLUMN_Name, "Ivano Cafe");
        values6.put( LeapContract.PlaceEntry.COLUMN_Address, "El-Gomhorya St.");
        values6.put( LeapContract.PlaceEntry.COLUMN_Image, "ImageURI");
        values6.put( LeapContract.PlaceEntry.COLUMN_Description, "Food-Restaurant");
        values6.put( LeapContract.PlaceEntry.COLUMN_Category_Name, "Restaurant");
        values6.put( LeapContract.PlaceEntry.COLUMN_Latitude, "31.037822");
        values6.put( LeapContract.PlaceEntry.COLUMN_Longitude, "31.355418");
//////////////////////////////////////////////////////////////////////////////////////////////////
        values7.put( LeapContract.PlaceEntry.COLUMN_Name, "Chick Fried Chicken");
        values7.put( LeapContract.PlaceEntry.COLUMN_Address, "Gehan El-sadat St.");
        values7.put( LeapContract.PlaceEntry.COLUMN_Image, "ImageURI");
        values7.put( LeapContract.PlaceEntry.COLUMN_Description, "A fast-food Restaurant");
        values7.put( LeapContract.PlaceEntry.COLUMN_Category_Name, "Fast-Food Restaurant");
        values7.put( LeapContract.PlaceEntry.COLUMN_Latitude, "31.037721");
        values7.put( LeapContract.PlaceEntry.COLUMN_Longitude, "31.361566");

        values8.put( LeapContract.PlaceEntry.COLUMN_Name, "Locked In");
        values8.put( LeapContract.PlaceEntry.COLUMN_Address, "Gehan El-sadat St. In front of El-Gam3a Stadium");
        values8.put( LeapContract.PlaceEntry.COLUMN_Image, "ImageURI");
        values8.put( LeapContract.PlaceEntry.COLUMN_Description, "Mystery & Adventure");
        values8.put( LeapContract.PlaceEntry.COLUMN_Category_Name, "Puzzle & Fun");
        values8.put( LeapContract.PlaceEntry.COLUMN_Latitude, "31.035994");
        values8.put( LeapContract.PlaceEntry.COLUMN_Longitude, "31.359694");

        values9.put( LeapContract.PlaceEntry.COLUMN_Name, "Cinnabon");
        values9.put( LeapContract.PlaceEntry.COLUMN_Address, "Gehan El-sadat St.");
        values9.put( LeapContract.PlaceEntry.COLUMN_Image, "ImageURI");
        values9.put( LeapContract.PlaceEntry.COLUMN_Description, "Cinnamon Rolls");
        values9.put( LeapContract.PlaceEntry.COLUMN_Category_Name, "Food");
        values9.put( LeapContract.PlaceEntry.COLUMN_Latitude, "31.037365");
        values9.put( LeapContract.PlaceEntry.COLUMN_Longitude, "31.361139");

        values10.put( LeapContract.PlaceEntry.COLUMN_Name, "El-Gam3a stadium");
        values10.put(LeapContract.PlaceEntry.COLUMN_Address, "Gehan El-sadat St.");
        values10.put( LeapContract.PlaceEntry.COLUMN_Image, "ImageURI");
        values10.put(LeapContract.PlaceEntry.COLUMN_Description, "Football playground");
        values10.put( LeapContract.PlaceEntry.COLUMN_Category_Name, "Fun");
        values10.put( LeapContract.PlaceEntry.COLUMN_Latitude, "31.036935");
        values10.put( LeapContract.PlaceEntry.COLUMN_Longitude, "31.359970");
/////////////////////////////////////////////////////////////////////////////////////////////////

        values11.put( LeapContract.PlaceEntry.COLUMN_Name, "Geziret ElWard Club");
        values11.put( LeapContract.PlaceEntry.COLUMN_Address, "El-Mashaya El-Sofleya St.");
        values11.put( LeapContract.PlaceEntry.COLUMN_Image, "ImageURI");
        values11.put( LeapContract.PlaceEntry.COLUMN_Description, "Athletics & Sports playground");
        values11.put(LeapContract.PlaceEntry.COLUMN_Category_Name, "Fun");
        values11.put( LeapContract.PlaceEntry.COLUMN_Latitude, "31.046293");
        values11.put( LeapContract.PlaceEntry.COLUMN_Longitude, "31.368496");

        values12.put( LeapContract.PlaceEntry.COLUMN_Name, "Adidas Store");
        values12.put( LeapContract.PlaceEntry.COLUMN_Address, "4 Sally Plaza Tower (EL-Mashaya)");
        values12.put( LeapContract.PlaceEntry.COLUMN_Image, "ImageURI");
        values12.put(LeapContract.PlaceEntry.COLUMN_Description, "Clothing store & sport Goods shop");
        values12.put( LeapContract.PlaceEntry.COLUMN_Category_Name, "shop");
        values12.put( LeapContract.PlaceEntry.COLUMN_Latitude, "31.045999");
        values12.put( LeapContract.PlaceEntry.COLUMN_Longitude, "31.357580");

        values13.put( LeapContract.PlaceEntry.COLUMN_Name, "Bayro");
        values13.put( LeapContract.PlaceEntry.COLUMN_Address, "Akl St. (Gehan El-sadat) St.");
        values13.put( LeapContract.PlaceEntry.COLUMN_Image, "ImageURI");
        values13.put( LeapContract.PlaceEntry.COLUMN_Description, "Juice Bar");
        values13.put( LeapContract.PlaceEntry.COLUMN_Category_Name, "Juice");
        values13.put( LeapContract.PlaceEntry.COLUMN_Latitude, "31.038003");
        values13.put( LeapContract.PlaceEntry.COLUMN_Longitude, "31.365937");

        values14.put( LeapContract.PlaceEntry.COLUMN_Name, "Teto");
        values14.put( LeapContract.PlaceEntry.COLUMN_Address, "Gehan El-Sadat St. beside Xbox");
        values14.put( LeapContract.PlaceEntry.COLUMN_Image, "ImageURI");
        values14.put( LeapContract.PlaceEntry.COLUMN_Description, "playstation");
        values14.put( LeapContract.PlaceEntry.COLUMN_Category_Name, "Fun");
        values14.put( LeapContract.PlaceEntry.COLUMN_Latitude, "31.035456");
        values14.put( LeapContract.PlaceEntry.COLUMN_Longitude, "31.3588755");

        db.insert(LeapContract.PlaceEntry.Table_Name, null, values1);
        db.insert( LeapContract.PlaceEntry.Table_Name, null, values2);
        db.insert( LeapContract.PlaceEntry.Table_Name, null, values3);
        db.insert( LeapContract.PlaceEntry.Table_Name, null, values4);
        db.insert( LeapContract.PlaceEntry.Table_Name, null, values5);
        db.insert( LeapContract.PlaceEntry.Table_Name, null, values6);
        db.insert( LeapContract.PlaceEntry.Table_Name, null, values7);
        db.insert( LeapContract.PlaceEntry.Table_Name, null, values8);
        db.insert( LeapContract.PlaceEntry.Table_Name, null, values9);
        db.insert( LeapContract.PlaceEntry.Table_Name, null, values10);
        db.insert( LeapContract.PlaceEntry.Table_Name, null, values11);
        db.insert( LeapContract.PlaceEntry.Table_Name, null, values12);
        db.insert( LeapContract.PlaceEntry.Table_Name, null, values13);
        db.insert( LeapContract.PlaceEntry.Table_Name, null, values14);
        db.close();
    }

    public static void insertLeapRecords(){

        SQLiteDatabase db = mLeapHelper.getWritableDatabase();

        ContentValues values1 = new ContentValues();
        ContentValues values2 = new ContentValues();
        ContentValues values3 = new ContentValues();
        ContentValues values4 = new ContentValues();

        values1.put( LeapContract.LeapEntry.COLUMN_Name, "College reunion");
        values1.put( LeapContract.LeapEntry.COLUMN_Image,"http://media-cdn.tripadvisor.com/media/photo-s/05/00/f5/7d/spectra.jpg");
        values1.put( LeapContract.LeapEntry.COLUMN_Description, "Reunion of the 2003-2004 alumni. We'll meet up at a cafe, drink coffee and hangout, then have dinner at KFC, and finally chill out and tell memories");
        values1.put( LeapContract.LeapEntry.COLUMN_Map_Image, "Mansoura");
        values1.put( LeapContract.LeapEntry.COLUMN_No_Of_Reviews, "1");
        values1.put( LeapContract.LeapEntry.COLUMN_Price, "200");
        values1.put( LeapContract.LeapEntry.COLUMN_Review, "8");
//        values1.put( LeapContract.LeapEntry.COLUMN_Latitude, LeapLatLon.LeapCenter(LeapLatLon.LeapLat(1), LeapLatLon.LeapLon(1))[0]);
//        values1.put( LeapContract.LeapEntry.COLUMN_Longitude, LeapLatLon.LeapCenter(LeapLatLon.LeapLat(1), LeapLatLon.LeapLon(1))[1]);
        values1.put( LeapContract.LeapEntry.COLUMN_User_Key, "1");

        values2.put( LeapContract.LeapEntry.COLUMN_Name, "Sports FTW");
        values2.put( LeapContract.LeapEntry.COLUMN_Image, "http://chandlerparkgolfcourse.com/images/golf%20course.jpg");
        values2.put( LeapContract.LeapEntry.COLUMN_Description, "Golf");
        values2.put( LeapContract.LeapEntry.COLUMN_Map_Image, "Mansoura");
        values2.put( LeapContract.LeapEntry.COLUMN_No_Of_Reviews, "6");
        values2.put( LeapContract.LeapEntry.COLUMN_Price, "160");
        values2.put( LeapContract.LeapEntry.COLUMN_Review, "5");
//        values2.put( LeapContract.LeapEntry.COLUMN_Latitude, "" + LeapLatLon.LeapCenter(LeapLatLon.LeapLat(2), LeapLatLon.LeapLon(2))[0]);
//        values2.put( LeapContract.LeapEntry.COLUMN_Longitude, "" + LeapLatLon.LeapCenter(LeapLatLon.LeapLat(2), LeapLatLon.LeapLon(2))[1]);
        values2.put( LeapContract.LeapEntry.COLUMN_User_Key, "1");

        values3.put( LeapContract.LeapEntry.COLUMN_Name, "Cultural Tour");
        values3.put( LeapContract.LeapEntry.COLUMN_Image, "http://thenews-chronicle.com/wp-content/uploads/2015/07/Mosque.jpg");
        values3.put( LeapContract.LeapEntry.COLUMN_Description, "The third outing");
        values3.put( LeapContract.LeapEntry.COLUMN_Map_Image, "Mansoura");
        values3.put( LeapContract.LeapEntry.COLUMN_No_Of_Reviews, "2");
        values3.put( LeapContract.LeapEntry.COLUMN_Price, "250");
        values3.put( LeapContract.LeapEntry.COLUMN_Review, "9");
//        values3.put( LeapContract.LeapEntry.COLUMN_Latitude, "" + LeapLatLon.LeapCenter(LeapLatLon.LeapLat(3), LeapLatLon.LeapLon(3))[0]);
//        values3.put( LeapContract.LeapEntry.COLUMN_Longitude, "" + LeapLatLon.LeapCenter(LeapLatLon.LeapLat(3), LeapLatLon.LeapLon(3))[1]);
        values3.put( LeapContract.LeapEntry.COLUMN_User_Key, "2");

        values4.put( LeapContract.LeapEntry.COLUMN_Name, "A day on the beach");
        values4.put( LeapContract.LeapEntry.COLUMN_Image, "http://www.clickeer.com/wp-content/uploads/2014/01/Sunglasses2.jpg");
        values4.put( LeapContract.LeapEntry.COLUMN_Description, "The fourth outing");
        values4.put( LeapContract.LeapEntry.COLUMN_Map_Image, "Mansoura");
        values4.put( LeapContract.LeapEntry.COLUMN_No_Of_Reviews, "4");
        values4.put( LeapContract.LeapEntry.COLUMN_Price, "50");
        values4.put( LeapContract.LeapEntry.COLUMN_Review, "7");
//        values4.put( LeapContract.LeapEntry.COLUMN_Latitude, "" + LeapLatLon.LeapCenter(LeapLatLon.LeapLat(4), LeapLatLon.LeapLon(4))[0]);
//        values4.put( LeapContract.LeapEntry.COLUMN_Longitude, "" + LeapLatLon.LeapCenter(LeapLatLon.LeapLat(4), LeapLatLon.LeapLon(4))[1]);
        values4.put( LeapContract.LeapEntry.COLUMN_User_Key, "3");


        db.insert(LeapContract.LeapEntry.Table_Name, null, values1);
        db.insert( LeapContract.LeapEntry.Table_Name, null, values2);
        db.insert( LeapContract.LeapEntry.Table_Name, null, values3);
        db.insert( LeapContract.LeapEntry.Table_Name, null, values4);
    }

    public static void insertLeapPlaceRecords(){

        SQLiteDatabase db = mLeapHelper.getWritableDatabase();

        ContentValues values1 = new ContentValues();
        ContentValues values2 = new ContentValues();
        ContentValues values3 = new ContentValues();
        ContentValues values4 = new ContentValues();
        ContentValues values5 = new ContentValues();
        ContentValues values6 = new ContentValues();
        ContentValues values7 = new ContentValues();
        ContentValues values8 = new ContentValues();
        ContentValues values9 = new ContentValues();
        ContentValues values10 = new ContentValues();
        ContentValues values11 = new ContentValues();
        ContentValues values12 = new ContentValues();
        ContentValues values13 = new ContentValues();
        ContentValues values14 = new ContentValues();

        values1.put( LeapContract.Leap_Place_Entry.COLUMN_Leap_Key, "1");
        values1.put( LeapContract.Leap_Place_Entry.COLUMN_Place_Key, "1");

        values2.put( LeapContract.Leap_Place_Entry.COLUMN_Leap_Key, "1");
        values2.put( LeapContract.Leap_Place_Entry.COLUMN_Place_Key, "2");

        values3.put( LeapContract.Leap_Place_Entry.COLUMN_Leap_Key, "1");
        values3.put( LeapContract.Leap_Place_Entry.COLUMN_Place_Key, "3");

        values4.put( LeapContract.Leap_Place_Entry.COLUMN_Leap_Key, "2");
        values4.put( LeapContract.Leap_Place_Entry.COLUMN_Place_Key, "4");

        values5.put( LeapContract.Leap_Place_Entry.COLUMN_Leap_Key, "2");
        values5.put( LeapContract.Leap_Place_Entry.COLUMN_Place_Key, "5");

        values6.put( LeapContract.Leap_Place_Entry.COLUMN_Leap_Key, "2");
        values6.put( LeapContract.Leap_Place_Entry.COLUMN_Place_Key, "6");

        values7.put( LeapContract.Leap_Place_Entry.COLUMN_Leap_Key, "3");
        values7.put( LeapContract.Leap_Place_Entry.COLUMN_Place_Key, "7");

        values8.put( LeapContract.Leap_Place_Entry.COLUMN_Leap_Key, "3");
        values8.put( LeapContract.Leap_Place_Entry.COLUMN_Place_Key, "8");

        values9.put( LeapContract.Leap_Place_Entry.COLUMN_Leap_Key, "3");
        values9.put( LeapContract.Leap_Place_Entry.COLUMN_Place_Key, "9");

        values10.put( LeapContract.Leap_Place_Entry.COLUMN_Leap_Key, "3");
        values10.put( LeapContract.Leap_Place_Entry.COLUMN_Place_Key, "10");

        values11.put( LeapContract.Leap_Place_Entry.COLUMN_Leap_Key, "4");
        values11.put( LeapContract.Leap_Place_Entry.COLUMN_Place_Key, "11");

        values12.put( LeapContract.Leap_Place_Entry.COLUMN_Leap_Key, "4");
        values12.put( LeapContract.Leap_Place_Entry.COLUMN_Place_Key, "12");

        values13.put( LeapContract.Leap_Place_Entry.COLUMN_Leap_Key, "4");
        values13.put( LeapContract.Leap_Place_Entry.COLUMN_Place_Key, "13");

        values14.put( LeapContract.Leap_Place_Entry.COLUMN_Leap_Key, "4");
        values14.put(LeapContract.Leap_Place_Entry.COLUMN_Place_Key, "14");

        db.insert( LeapContract.Leap_Place_Entry.Table_Name, null, values1);
        db.insert( LeapContract.Leap_Place_Entry.Table_Name, null, values2);
        db.insert( LeapContract.Leap_Place_Entry.Table_Name, null, values3);
        db.insert( LeapContract.Leap_Place_Entry.Table_Name, null, values4);
        db.insert( LeapContract.Leap_Place_Entry.Table_Name, null, values5);
        db.insert( LeapContract.Leap_Place_Entry.Table_Name, null, values6);
        db.insert( LeapContract.Leap_Place_Entry.Table_Name, null, values7);
        db.insert( LeapContract.Leap_Place_Entry.Table_Name, null, values8);
        db.insert( LeapContract.Leap_Place_Entry.Table_Name, null, values9);
        db.insert( LeapContract.Leap_Place_Entry.Table_Name, null, values10);
        db.insert( LeapContract.Leap_Place_Entry.Table_Name, null, values11);
        db.insert( LeapContract.Leap_Place_Entry.Table_Name, null, values12);
        db.insert( LeapContract.Leap_Place_Entry.Table_Name, null, values13);
        db.insert( LeapContract.Leap_Place_Entry.Table_Name, null, values14);
        db.close();
    }

    public static void insertReviewRecords(){

        SQLiteDatabase db = mLeapHelper.getWritableDatabase();

        ContentValues values1 = new ContentValues();

        values1.put( LeapContract.ReviewEntry.COLUMN_Title, "AMAZING!!");
        values1.put( LeapContract.ReviewEntry.COLUMN_Description, "THIS IS AMAZING");
        values1.put(LeapContract.ReviewEntry.COLUMN_Rating, "4");
        values1.put(LeapContract.ReviewEntry.COLUMN_Date, "12/2/2015");
        values1.put( LeapContract.ReviewEntry.COLUMN_User_Key,"1");
        values1.put( LeapContract.ReviewEntry.COLUMN_Leap_Key,"1");

        db.insert( LeapContract.ReviewEntry.Table_Name, null, values1);
        db.close();
    }

    public static void updatelatlon() {
         /*
        TEMPORARY: Add lat and lon of leap into database
        */

        SQLiteDatabase db = mLeapHelper.getWritableDatabase();
        ContentValues value1 = new ContentValues();
        value1.put(LeapContract.LeapEntry.COLUMN_Latitude, LeapLatLon.LeapCenter(LeapLatLon.LeapLat(1), LeapLatLon.LeapLon(1))[0]);
        value1.put(LeapContract.LeapEntry.COLUMN_Longitude, LeapLatLon.LeapCenter(LeapLatLon.LeapLat(1), LeapLatLon.LeapLon(1))[1]);
        db.update(LeapContract.LeapEntry.Table_Name, value1, "id = 1 ", null);

        ContentValues value2 = new ContentValues();
        value2.put(LeapContract.LeapEntry.COLUMN_Latitude, LeapLatLon.LeapCenter(LeapLatLon.LeapLat(2), LeapLatLon.LeapLon(2))[0]);
        value2.put(LeapContract.LeapEntry.COLUMN_Longitude, LeapLatLon.LeapCenter(LeapLatLon.LeapLat(2), LeapLatLon.LeapLon(2))[1]);
        db.update(LeapContract.LeapEntry.Table_Name, value2, "id = 2 ", null);

        ContentValues value3 = new ContentValues();
        value3.put(LeapContract.LeapEntry.COLUMN_Latitude, LeapLatLon.LeapCenter(LeapLatLon.LeapLat(3), LeapLatLon.LeapLon(3))[0]);
        value3.put(LeapContract.LeapEntry.COLUMN_Longitude, LeapLatLon.LeapCenter(LeapLatLon.LeapLat(3), LeapLatLon.LeapLon(3))[1]);
        db.update(LeapContract.LeapEntry.Table_Name, value3, "id = 3 ", null);

        ContentValues value4 = new ContentValues();
        value4.put(LeapContract.LeapEntry.COLUMN_Latitude, LeapLatLon.LeapCenter(LeapLatLon.LeapLat(4), LeapLatLon.LeapLon(4))[0]);
        value4.put(LeapContract.LeapEntry.COLUMN_Longitude, LeapLatLon.LeapCenter(LeapLatLon.LeapLat(4), LeapLatLon.LeapLon(4))[1]);
        db.update(LeapContract.LeapEntry.Table_Name, value4, "id = 4 ", null);

        db.close();
    }
}


