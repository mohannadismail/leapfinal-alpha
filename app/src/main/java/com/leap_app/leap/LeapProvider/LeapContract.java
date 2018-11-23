package com.leap_app.leap.LeapProvider;


import android.content.ContentResolver;
import android.content.ContentUris;
import android.net.Uri;
import android.provider.BaseColumns;

/*This class specifies the names of the tables and URI paths to be used for creation of the database and communication with it.*/
public class LeapContract {
    static final String CONTENT_AUTHORITY = "com.example.CIE.LeapApp";
    private static final Uri BASE_CONTENT_URI = Uri.parse("content://" + CONTENT_AUTHORITY);
    static final String PATH_USER = "User";
    static final String PATH_PLACE = "Place";
    static final String PATH_LEAP = "Leap";
    static final String PATH_PLACE_LEAP = "PlaceLeap";
    static final String PATH_REVIEW = "Review";


    /*Table name, row names and URI path for the User table*/
    public static final class UserEntry implements BaseColumns {

        static final Uri CONTENT_URI1 = BASE_CONTENT_URI.buildUpon().appendPath(PATH_USER).build();

        static final String CONTENT_TYPE = ContentResolver.CURSOR_DIR_BASE_TYPE + "/" + CONTENT_AUTHORITY + "/" + PATH_USER;
        static final String CONTENT_ITEM_TYPE = ContentResolver.CURSOR_ITEM_BASE_TYPE + "/" + CONTENT_AUTHORITY + "/" + PATH_USER;

        public static final String Table_Name = "User";

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

        public static Uri buildUserUri(long id) {
            return ContentUris.withAppendedId(CONTENT_URI1, id);
        }

    }

    /*Table name, row names and URI path for the Place table*/
    public static final class PlaceEntry implements BaseColumns {

        public static final Uri CONTENT_URI1 = BASE_CONTENT_URI.buildUpon().appendPath(PATH_PLACE).build();

        public static final String CONTENT_TYPE = ContentResolver.CURSOR_DIR_BASE_TYPE + "/" + CONTENT_AUTHORITY + "/" + PATH_PLACE;
        public static final String CONTENT_ITEM_TYPE = ContentResolver.CURSOR_ITEM_BASE_TYPE + "/" + CONTENT_AUTHORITY + "/" + PATH_PLACE;

        public static final String Table_Name = "Place";

        public static final String COLUMN_Place_ID = "id";
        public static final String COLUMN_Name = "name";
        public static final String COLUMN_Address = "Address";
        public static final String COLUMN_Image = "imageUri";
        public static final String COLUMN_Category_Name = "category";
        public static final String COLUMN_Description = "description";
        public static final String COLUMN_Latitude = "Latitude";
        public static final String COLUMN_Longitude = "longitude";

        public static Uri buildPlaceUri(long id) {
            return ContentUris.withAppendedId(CONTENT_URI1, id);
        }
    }

    /*Table name, row names and URI path for the Leap_place table. This table is the intermediary between the Leap and Place tables,
     * since they have a many-to-many relationship*/
    public static final class Leap_Place_Entry implements BaseColumns {

        public static final Uri CONTENT_URI1 = BASE_CONTENT_URI.buildUpon().appendPath(PATH_PLACE_LEAP).build();

        public static final String CONTENT_TYPE = ContentResolver.CURSOR_DIR_BASE_TYPE + "/" + CONTENT_AUTHORITY + "/" + PATH_PLACE_LEAP;
        public static final String CONTENT_ITEM_TYPE = ContentResolver.CURSOR_ITEM_BASE_TYPE + "/" + CONTENT_AUTHORITY + "/" + PATH_PLACE_LEAP;

        public static final String Table_Name = "Leap_Place";

        public static final String COLUMN_Place_Key = "place_id";
        public static final String COLUMN_Leap_Key = "leap_id";

        public static Uri buildPlaceUri(long id) {
            return ContentUris.withAppendedId(CONTENT_URI1, id);
        }
    }

    /*Table name, row names and URI path for the Leap table*/
    public static final class LeapEntry implements BaseColumns {

        public static final Uri CONTENT_URI1 = BASE_CONTENT_URI.buildUpon().appendPath(PATH_LEAP).build();

        public static final String CONTENT_TYPE = ContentResolver.CURSOR_DIR_BASE_TYPE + "/" + CONTENT_AUTHORITY + "/" + PATH_LEAP;
        public static final String CONTENT_ITEM_TYPE = ContentResolver.CURSOR_ITEM_BASE_TYPE + "/" + CONTENT_AUTHORITY + "/" + PATH_LEAP;

        public static final String Table_Name = "Leap";

        public static final String COLUMN_Leap_ID = "id";
        public static final String COLUMN_Name = "name";
        public static final String COLUMN_User_Key = "user_id";
        public static final String COLUMN_Image = "imageUri";
        public static final String COLUMN_No_Of_Reviews = "reviewsNumber";
        public static final String COLUMN_Description = "description";
        public static final String COLUMN_Price = "price";
        public static final String COLUMN_Map_Image = "mapUri";
        public static final String COLUMN_Review = "review";
        public static final String COLUMN_Latitude = "latitude";
        public static final String COLUMN_Longitude = "longitude";

        static Uri buildPlaceUri(long id) {
            return ContentUris.withAppendedId(CONTENT_URI1, id);
        }
    }


    /*Table name, row names and URI path for the Review table*/
    public static final class ReviewEntry implements BaseColumns {

        static final Uri CONTENT_URI1 = BASE_CONTENT_URI.buildUpon().appendPath(PATH_REVIEW).build();

        static final String CONTENT_TYPE = ContentResolver.CURSOR_DIR_BASE_TYPE + "/" + CONTENT_AUTHORITY + "/" + PATH_REVIEW;
        public static final String CONTENT_ITEM_TYPE = ContentResolver.CURSOR_ITEM_BASE_TYPE + "/" + CONTENT_AUTHORITY + "/" + PATH_REVIEW;

        public static final String Table_Name = "Review";
        static final String COLUMN_Review_ID = "id";
        static final String COLUMN_Rating = "rating";
        static final String COLUMN_Title = "title";
        static final String COLUMN_User_Key = "user_id";
        //        image_Uri >>>>> "description"
        static final String COLUMN_Description = "imageUri";
        static final String COLUMN_Date = "Date";
        static final String COLUMN_Leap_Key = "leap_id";

        static Uri buildPlaceUri(long id) {
            return ContentUris.withAppendedId(CONTENT_URI1, id);
        }
    }


}
