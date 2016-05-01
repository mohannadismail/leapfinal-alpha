package com.leap_app.leap.LeapProvider;


import android.content.ContentResolver;
import android.content.ContentUris;
import android.net.Uri;
import android.provider.BaseColumns;

public class LeapContract {
    public static final String CONTENT_AUTHORITY = "com.example.CIE.LeapApp";
    public static final Uri BASE_CONTENT_URI = Uri.parse("content://" + CONTENT_AUTHORITY);
    public static final String PATH_USER = "User";
    public static final String PATH_PLACE = "Place";
    public static final String PATH_LEAP = "Leap";
    public static final String PATH_PLACE_LEAP = "PlaceLeap";
    public static final String PATH_REVIEW = "Review";



    public static final class UserEntry implements BaseColumns{

        public static final Uri CONTENT_URI1 = BASE_CONTENT_URI.buildUpon().appendPath(PATH_USER).build();

        public static final String CONTENT_TYPE = ContentResolver.CURSOR_DIR_BASE_TYPE + "/" + CONTENT_AUTHORITY + "/" + PATH_USER;
        public static final String CONTENT_ITEM_TYPE = ContentResolver.CURSOR_ITEM_BASE_TYPE + "/" + CONTENT_AUTHORITY + "/" + PATH_USER;

        public static final String Table_Name="User";

        public static final String COLUMN_User_ID = "id";
        public static final String COLUMN_Name = "name";
        public static final String COLUMN_UserName = "username";
        public static final String COLUMN_Image = "imageUri";
        public static final String COLUMN_No_Of_Leaps = "leapsNumber";
        public static final String COLUMN_password = "password";
        public static final String COLUMN_Address = "address";
        public static final String COLUMN_Email = "email";
        public static final String COLUMN_Date_Of_birth = "dateofbirth";
        public static final String COLUMN_About_me = "aboutme";
        public static final String COLUMN_HomePage = "homepage";

        public static Uri buildUserUri(long id){
            return ContentUris.withAppendedId(CONTENT_URI1, id);
        }

    }

    public static final class PlaceEntry implements BaseColumns{

        public static final Uri CONTENT_URI1 = BASE_CONTENT_URI.buildUpon().appendPath(PATH_PLACE).build();

        public static final String CONTENT_TYPE = ContentResolver.CURSOR_DIR_BASE_TYPE + "/" + CONTENT_AUTHORITY + "/" + PATH_PLACE;
        public static final String CONTENT_ITEM_TYPE = ContentResolver.CURSOR_ITEM_BASE_TYPE + "/" + CONTENT_AUTHORITY + "/" + PATH_PLACE;

        public static final String Table_Name="Place";

        public static final String COLUMN_Place_ID = "id";
        public static final String COLUMN_Name = "name";
        public static final String COLUMN_Address = "Address";
        public static final String COLUMN_Image= "imageUri";
        public static final String COLUMN_Category_Name= "category";
        public static final String COLUMN_Description = "description";
        public static final String COLUMN_Latitude= "Latitude";
        public static final String COLUMN_Longitude= "longitude";

        public static Uri buildPlaceUri(long id){
            return ContentUris.withAppendedId(CONTENT_URI1, id);
        }
    }

    public static final class Leap_Place_Entry implements BaseColumns{

        public static final Uri CONTENT_URI1 = BASE_CONTENT_URI.buildUpon().appendPath(PATH_PLACE_LEAP).build();

        public static final String CONTENT_TYPE = ContentResolver.CURSOR_DIR_BASE_TYPE + "/" + CONTENT_AUTHORITY + "/" + PATH_PLACE_LEAP;
        public static final String CONTENT_ITEM_TYPE = ContentResolver.CURSOR_ITEM_BASE_TYPE + "/" + CONTENT_AUTHORITY + "/" + PATH_PLACE_LEAP;

            public static final String Table_Name="Leap_Place";

        public static final String COLUMN_Place_Key = "place_id";
        public static final String COLUMN_Leap_Key = "leap_id";

        public static Uri buildPlaceUri(long id){
            return ContentUris.withAppendedId(CONTENT_URI1, id);
        }
    }

    public static final class LeapEntry implements BaseColumns{

        public static final Uri CONTENT_URI1 = BASE_CONTENT_URI.buildUpon().appendPath(PATH_LEAP).build();

        public static final String CONTENT_TYPE = ContentResolver.CURSOR_DIR_BASE_TYPE + "/" + CONTENT_AUTHORITY + "/" + PATH_LEAP;
        public static final String CONTENT_ITEM_TYPE = ContentResolver.CURSOR_ITEM_BASE_TYPE + "/" + CONTENT_AUTHORITY + "/" + PATH_LEAP;

            public static final String Table_Name="Leap";

        public static final String COLUMN_Leap_ID = "id";
        public static final String COLUMN_Name = "name";
        public static final String COLUMN_User_Key= "user_id";
        public static final String COLUMN_Image= "imageUri";
        public static final String COLUMN_No_Of_Reviews = "reviewsNumber";
        public static final String COLUMN_Description = "description";
        public static final String COLUMN_Price= "price";
        public static final String COLUMN_Map_Image= "mapUri";
        public static final String COLUMN_Review = "review";
        public static final String COLUMN_Latitude = "latitude";
        public static final String COLUMN_Longitude = "longitude";

        public static Uri buildPlaceUri(long id){
            return ContentUris.withAppendedId(CONTENT_URI1, id);
        }
    }

    public static final class ReviewEntry implements BaseColumns{

        public static final Uri CONTENT_URI1 = BASE_CONTENT_URI.buildUpon().appendPath(PATH_REVIEW).build();

        public static final String CONTENT_TYPE = ContentResolver.CURSOR_DIR_BASE_TYPE + "/" + CONTENT_AUTHORITY + "/" + PATH_REVIEW;
        public static final String CONTENT_ITEM_TYPE = ContentResolver.CURSOR_ITEM_BASE_TYPE + "/" + CONTENT_AUTHORITY + "/" + PATH_REVIEW;

             public static final String Table_Name="Review";

        public static final String COLUMN_Review_ID = "id";
        public static final String COLUMN_Rating = "rating";
        public static final String COLUMN_Title = "title";
        public static final String COLUMN_User_Key = "user_id";
//        image_Uri >>>>> "description"
        public static final String COLUMN_Description= "imageUri";
        public static final String COLUMN_Date = "Date";
        public static final String COLUMN_Leap_Key= "leap_id";

        public static Uri buildPlaceUri(long id){
            return ContentUris.withAppendedId(CONTENT_URI1, id);
        }
    }



}
