package com.leap_app.leap.leap_provider;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.util.Log;

import java.util.Objects;

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
    public static final String URL = "content://" + PROVIDER_NAME;


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
        return db != null;
//        return true;
    }

    @Override
    public String getType(@NonNull Uri uri) {
        final int match = sUriMatcher.match(uri);

        switch (match) {
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
                throw new UnsupportedOperationException("Unknown URI: " + uri);
        }
    }

    @Override
    public Uri insert(@NonNull Uri uri, ContentValues values) {
        final SQLiteDatabase db = mLeapHelper.getWritableDatabase();
        Uri _uri;
        switch (sUriMatcher.match(uri)) {
            case USER:
                long _ID1 = db.insert(LeapContract.UserEntry.Table_Name, null, values);
                //---if added successfully---
                if (_ID1 > 0) {
                    _uri = LeapContract.UserEntry.buildUserUri(_ID1);
                } else {
                    throw new android.database.SQLException("Failed to insert row into " + uri);
                }
                break;

            case PLACE:
                long _ID2 = db.insert(LeapContract.PlaceEntry.Table_Name, null, values);
                //---if added successfully---
                if (_ID2 > 0) {
                    _uri = LeapContract.PlaceEntry.buildPlaceUri(_ID2);
                } else {
                    throw new android.database.SQLException("Failed to insert row into " + uri);
                }
                break;

            case LEAP:
                long _ID3 = db.insert(LeapContract.LeapEntry.Table_Name, null, values);
                //---if added successfully---
                if (_ID3 > 0) {
                    _uri = LeapContract.LeapEntry.buildPlaceUri(_ID3);
                } else {
                    throw new android.database.SQLException("Failed to insert row into " + uri);
                }
                break;

            case REVIEW:
                long _ID4 = db.insert(LeapContract.ReviewEntry.Table_Name, null, values);
                //---if added successfully---
                if (_ID4 > 0) {
                    _uri = LeapContract.ReviewEntry.buildPlaceUri(_ID4);
                } else {
                    throw new android.database.SQLException("Failed to insert row into " + uri);
                }
                break;

            case PLACELEAP:
                long _ID5 = db.insert(LeapContract.Leap_Place_Entry.Table_Name, null, values);
                //---if added successfully---
                if (_ID5 > 0) {
                    _uri = LeapContract.Leap_Place_Entry.buildPlaceUri(_ID5);
                } else {
                    throw new android.database.SQLException("Failed to insert row into " + uri);
                }
                break;

            default:
                throw new UnsupportedOperationException("Unknown uri: " + uri);
        }

        Log.d(TAG, "insert uri: " + uri.toString());
        return _uri;
    }

    @Override
    public int update(@NonNull Uri uri, ContentValues values, String selection,
                      String[] selectionArgs) {

        final SQLiteDatabase db = mLeapHelper.getWritableDatabase();
        int rowsUpdated;

        switch (sUriMatcher.match(uri)) {
            case USER:
                rowsUpdated = db.update(LeapContract.UserEntry.Table_Name, values, selection, selectionArgs);
                break;

            case PLACE:
                rowsUpdated = db.update(LeapContract.PlaceEntry.Table_Name, values, selection, selectionArgs);
                break;

            case LEAP:
                rowsUpdated = db.update(LeapContract.LeapEntry.Table_Name, values, selection, selectionArgs);
                break;

            case REVIEW:
                rowsUpdated = db.update(LeapContract.ReviewEntry.Table_Name, values, selection, selectionArgs);
                break;

            case PLACELEAP:
                rowsUpdated = db.update(LeapContract.Leap_Place_Entry.Table_Name, values, selection, selectionArgs);
                break;

            default:
                throw new UnsupportedOperationException("Unknown uri: " + uri);
        }

        if (rowsUpdated != 0) {
            Objects.requireNonNull(getContext()).getContentResolver().notifyChange(uri, null);
        }
        Log.d(TAG, "update uri: " + uri.toString());
        return rowsUpdated;
    }

    @Override
    public int delete(@NonNull Uri uri, String selection, String[] selectionArgs) {

        final SQLiteDatabase db = mLeapHelper.getWritableDatabase();
        int rowsDeleted;

        if (null == selection) selection = "1";

        switch (sUriMatcher.match(uri)) {
            case USER:
                rowsDeleted = db.delete(LeapContract.UserEntry.Table_Name, selection, selectionArgs);
                break;

            case PLACE:
                rowsDeleted = db.delete(LeapContract.PlaceEntry.Table_Name, selection, selectionArgs);
                break;

            case LEAP:
                rowsDeleted = db.delete(LeapContract.LeapEntry.Table_Name, selection, selectionArgs);
                break;

            case REVIEW:
                rowsDeleted = db.delete(LeapContract.ReviewEntry.Table_Name, selection, selectionArgs);
                break;

            case PLACELEAP:
                rowsDeleted = db.delete(LeapContract.Leap_Place_Entry.Table_Name, selection, selectionArgs);
                break;

            default:
                throw new UnsupportedOperationException("Unknown uri: " + uri);
        }

        if (rowsDeleted != 0) {
            Objects.requireNonNull(getContext()).getContentResolver().notifyChange(uri, null);
        }

        Log.d(TAG, "delete uri: " + uri.toString());
        return rowsDeleted;
    }

    @Override
    public Cursor query(@NonNull Uri uri, String[] projection, String selection,
                        String[] selectionArgs, String sortOrder) {

        Cursor cursor;

        switch (sUriMatcher.match(uri)) {
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

            default:
                throw new UnsupportedOperationException("Unknown uri: " + uri);
        }

        cursor.setNotificationUri(Objects.requireNonNull(getContext()).getContentResolver(), uri);
        Log.d(TAG, "query with uri: " + uri.toString());
        return cursor;
    }

}


