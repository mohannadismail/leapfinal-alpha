package com.leap_app.leap.Utility;


import com.leap_app.leap.BuildConfig;

/**
 * Constants class store most important strings and paths of the app
 */
public final class Constants {

    /**
     * Constants related to locations in Firebase, such as the name of the node
     * where user lists are stored
     */
    public static final String FIREBASE_Leap = "https://leapappeg.firebaseio.com/leap/Leap";
    static final String FIREBASE_USER_CIRCLE = "circle";
    private static final String FIREBASE_LEAP_USERS = "users";
    private static final String FIREBASE_LEAP_PLACES = "places";
    private static final String FIREBASE_USER_FRIENDS = "userFollow";
    private static final String FIREBASE_LEAP_LEAPPLACE = "Leap_Place";
    private static final String FIREBASE_LEAP_REVIEWS = "reviews";
    public static final String KEY = "key";
    public static final String MyPREFERENCES = "MyPrefs";
    public static final String flag = "flag";
    public static final String Name1 = "nameKey";
    public static final String signup1 = "signupKey";
    public static final String member1 = "memberKey";
    public static final String login1 = "loginKey";

    /**
     * Constants for Firebase object properties
     */
    public static final String FIREBASE_PROPERTY_ID= "id";
    public static final String FIREBASE_PROPERTY_NAME = "name";
    public static final String FIREBASE_PROPERTY_User_Key= "user_id";
    public static final String FIREBASE_PROPERTY_Image= "imageUri";
    public static final String FIREBASE_PROPERTY_No_Of_Reviews = "reviewsNumber";
    public static final String FIREBASE_PROPERTY_Description = "description";
    public static final String FIREBASE_PROPERTY_Price= "price";
    public static final String FIREBASE_PROPERTY_Map_Image= "mapUri";
    public static final String FIREBASE_PROPERTY_Review = "review";
    public static final String FIREBASE_PROPERTY_Latitude = "latitude";
    public static final String FIREBASE_PROPERTY_Longitude = "longitude";
    public static final String FIREBASE_PROPERTY_Rating = "rating";
    public static final String FIREBASE_PROPERTY_Title = "title";
    public static final String FIREBASE_PROPERTY_Date = "Date";
    public static final String FIREBASE_PROPERTY_Place_Key = "place_id";
    public static final String FIREBASE_PROPERTY_Leap_Key = "leap_id";
    public static final String FIREBASE_PROPERTY_Address = "Address";
    public static final String FIREBASE_PROPERTY_Category_Name= "category";
    public static final String FIREBASE_PROPERTY_No_Of_Leaps = "leapsNumber";
    public static final String FIREBASE_PROPERTY_password = "password";
    public static final String FIREBASE_PROPERTY_Email = "email";
    public static final String FIREBASE_PROPERTY_Date_Of_birth = "dateofbirth";
    public static final String FIREBASE_PROPERTY_About_me = "aboutme";
    public static final String FIREBASE_PROPERTY_HomePage = "homepage";
    public static final String FIREBASE_PROPERTY_TIMESTAMP_LAST_CHANGED = "timestampLastChanged";
    public static final String FIREBASE_PROPERTY_TIMESTAMP = "timestamp";
    public static final String FIREBASE_PROPERTY_EMAIL = "email";
    public static final String FIREBASE_PROPERTY_USER_HAS_LOGGED_IN_WITH_PASSWORD = "hasLoggedInWithPassword";
    public static final String FIREBASE_PROPERTY_TIMESTAMP_LAST_CHANGED_REVERSE = "timestampLastChangedReverse";
    public static final String TAG_LEAP = "TAGleap";
    public static final String TAG_LEAP_LAT = "Tagleaplat";
    public static final String LEAP_ID = "LeapID";
    public static String FIREBASE_PROPERTY_Phone= "phone";


    /**
     * Constants for Firebase URL
     */
    public static final String FIREBASE_URL = BuildConfig.UNIQUE_FIREBASE_ROOT_URL;
    public static final String FIREBASE_Leap_URL = FIREBASE_URL + "/" + FIREBASE_Leap;
    public static final String FIREBASE_LEAP_USERS_URL = FIREBASE_URL + "/" + FIREBASE_LEAP_USERS;
    public static final String FIREBASE_LEAP_PLACES_URL = FIREBASE_URL + "/" + FIREBASE_LEAP_PLACES;
    public static final String FIREBASE_USER_FRIENDS_URL = FIREBASE_URL + "/" + FIREBASE_USER_FRIENDS;
    public static final String FIREBASE_LEAP_LEAPPLACE_URL = FIREBASE_URL + "/" + FIREBASE_LEAP_LEAPPLACE;
    public static final String FIREBASE_LEAP_REVIEWS_URL = FIREBASE_URL + "/" + FIREBASE_LEAP_REVIEWS;
    public static final String FIREBASE_USER_CIRCLE_URL = FIREBASE_URL + "/" + FIREBASE_USER_CIRCLE;

    public static final String FIREBASE_TEST = FIREBASE_URL + "/" + "Test";

    /**
     * Constants for bundles, extras and shared preferences keys
     */
//    public static final String KEY_LIST_NAME = "LIST_NAME";
//    public static final String KEY_LAYOUT_RESOURCE = "LAYOUT_RESOURCE";
//    public static final String KEY_LIST_ID = "LIST_ID";
//    public static final String KEY_SIGNUP_EMAIL = "SIGNUP_EMAIL";
//    public static final String KEY_LIST_ITEM_NAME = "ITEM_NAME";
//    public static final String KEY_LIST_ITEM_ID = "LIST_ITEM_ID";
//    public static final String KEY_PROVIDER = "PROVIDER";
//    public static final String KEY_ENCODED_EMAIL = "ENCODED_EMAIL";
//    public static final String KEY_LIST_OWNER = "LIST_OWNER";
//    public static final String KEY_GOOGLE_EMAIL = "GOOGLE_EMAIL";
//    public static final String KEY_PREF_SORT_ORDER_LISTS = "PERF_SORT_ORDER_LISTS";
//    public static final String KEY_SHARED_WITH_USERS = "SHARED_WITH_USERS";
//
//
    /**
     * Constants for Firebase login
     */
    public static final String PASSWORD_PROVIDER = "password";
    public static final String GOOGLE_PROVIDER = "google";
    public static final String PROVIDER_DATA_DISPLAY_NAME = "displayName";


    /**
     * Constant for sorting
     */
    public static final String ORDER_BY_KEY = "orderByPushKey";
    public static final String ORDER_BY_OWNER_EMAIL = "orderByOwnerEmail";


}
