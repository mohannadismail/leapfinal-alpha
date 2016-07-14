package com.leap_app.leap.LeapProvider;


import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/*This class is used to create the actual tables and implement attributes within the database. Also, it implements two methods,
*getReadableDatabase() and getWritableDatabase(). These are called via this class whenever any modifications are required on the database.*/
public class LeapDbHelper extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;
    static final String DATABASE_NAME = "Leap.db";

    public LeapDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }



    @Override
    /*Method for creating the database tables. Gets executed once the LeapDbHelper is called.*/
    public void onCreate(SQLiteDatabase db) {

        final String SQL_CREATE_User_TABLE = "CREATE TABLE " + LeapContract.UserEntry.Table_Name + " (" +
                LeapContract.UserEntry.COLUMN_User_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                LeapContract.UserEntry.COLUMN_Name + " TEXT NOT NULL, " +
                LeapContract.UserEntry.COLUMN_password + " TEXT NOT NULL, " +
                LeapContract.UserEntry.COLUMN_Address + " TEXT, " +
                LeapContract.UserEntry.COLUMN_Email + " TEXT NOT NULL, " +
                LeapContract.UserEntry.COLUMN_Date_Of_birth + " TEXT, " +
                LeapContract.UserEntry.COLUMN_About_me + " TEXT, " +
                LeapContract.UserEntry.COLUMN_HomePage + " TEXT, " +
                LeapContract.UserEntry.COLUMN_Image + " TEXT, " +
                LeapContract.UserEntry.COLUMN_No_Of_Leaps + " INTEGER DEFAULT 0, " +
                LeapContract.UserEntry.COLUMN_UserName + " TEXT UNIQUE " +
                " );";


       final String SQL_CREATE_Place_TABLE = "CREATE TABLE " + LeapContract.PlaceEntry.Table_Name + " (" +
                LeapContract.PlaceEntry.COLUMN_Place_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                LeapContract.PlaceEntry.COLUMN_Name + " TEXT NOT NULL, " +
                LeapContract.PlaceEntry.COLUMN_Image + " TEXT, " +
                LeapContract.PlaceEntry.COLUMN_Address + " TEXT , " +
                LeapContract.PlaceEntry.COLUMN_Description + " TEXT, " +
                LeapContract.PlaceEntry.COLUMN_Category_Name + " TEXT, " +
                LeapContract.PlaceEntry.COLUMN_Latitude + "  REAL NOT NULL, " +
                LeapContract.PlaceEntry.COLUMN_Longitude + "  REAL NOT NULL " +
                " );";


        final String SQL_CREATE_Leap_Place_TABLE = "CREATE TABLE " + LeapContract.Leap_Place_Entry.Table_Name + " (" +
                LeapContract.Leap_Place_Entry.COLUMN_Leap_Key + " INTEGER, " +
                LeapContract.Leap_Place_Entry.COLUMN_Place_Key + " INTEGER, " +

                " FOREIGN KEY (" + LeapContract.Leap_Place_Entry.COLUMN_Leap_Key + ") REFERENCES " +
                LeapContract.LeapEntry.Table_Name + " (" + LeapContract.LeapEntry.COLUMN_Leap_ID + "), " +

                " FOREIGN KEY (" + LeapContract.Leap_Place_Entry.COLUMN_Place_Key + ") REFERENCES " +
                LeapContract.PlaceEntry.Table_Name + " (" + LeapContract.PlaceEntry.COLUMN_Place_ID+ ") " +
                " );";

        final String SQL_CREATE_Leap_TABLE = "CREATE TABLE " + LeapContract.LeapEntry.Table_Name + " (" +
                LeapContract.LeapEntry.COLUMN_Leap_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                LeapContract.LeapEntry.COLUMN_Name + " TEXT NOT NULL, " +
                LeapContract.LeapEntry.COLUMN_Image + " TEXT DEFAULT 'https://cms-assets.tutsplus.com/uploads/users/41/posts/25951/image/more-more-material-design-backgrounds-134679.jpg', " +
                LeapContract.LeapEntry.COLUMN_Description + " TEXT, " +
                LeapContract.LeapEntry.COLUMN_Map_Image + " TEXT, " +
                LeapContract.LeapEntry.COLUMN_No_Of_Reviews + " INTEGER DEFAULT 0, " +
                LeapContract.LeapEntry.COLUMN_Price + " TEXT DEFAULT NULL, " +
                LeapContract.LeapEntry.COLUMN_Review + " INTEGER CHECK(review >=1 AND review <=10) DEFAULT NULL, " +
                LeapContract.LeapEntry.COLUMN_Latitude + "  REAL DEFAULT NULL, " +
                LeapContract.LeapEntry.COLUMN_Longitude + "  REAL DEFAULT NULL, " +
                LeapContract.LeapEntry.COLUMN_User_Key + "  REAL DEFAULT 2,  " +


                " FOREIGN KEY (" + LeapContract.LeapEntry.COLUMN_User_Key + ") REFERENCES " +
                LeapContract.UserEntry.Table_Name + " (" + LeapContract.UserEntry.COLUMN_User_ID + ") " +

                " );";


        final String SQL_CREATE_Review_TABLE = "CREATE TABLE " + LeapContract.ReviewEntry.Table_Name + " (" +
                LeapContract.ReviewEntry.COLUMN_Review_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                LeapContract.ReviewEntry.COLUMN_Title + " VARCHAR(25) NOT NULL, " +
                LeapContract.ReviewEntry.COLUMN_Description + " TEXT, " +
                LeapContract.ReviewEntry.COLUMN_Rating + " INTEGER CHECK(rating >=1 AND rating <=5) DEFAULT NULL, " +
                LeapContract.ReviewEntry.COLUMN_Date + " TEXT, " +
                LeapContract.ReviewEntry.COLUMN_User_Key + " INTEGER, " +
                LeapContract.ReviewEntry.COLUMN_Leap_Key + " INTEGER, " +

                " FOREIGN KEY (" + LeapContract.ReviewEntry.COLUMN_Leap_Key + ") REFERENCES " +
                LeapContract.LeapEntry.Table_Name + " (" + LeapContract.LeapEntry.COLUMN_Leap_ID + "), " +

                " FOREIGN KEY (" + LeapContract.ReviewEntry.COLUMN_User_Key + ") REFERENCES " +
                LeapContract.UserEntry.Table_Name + " (" + LeapContract.UserEntry.COLUMN_User_ID + ") " +
                " );";


        db.execSQL(SQL_CREATE_User_TABLE);
        db.execSQL(SQL_CREATE_Place_TABLE);
        db.execSQL(SQL_CREATE_Leap_Place_TABLE);
        db.execSQL(SQL_CREATE_Leap_TABLE);
        db.execSQL(SQL_CREATE_Review_TABLE);
    }

    @Override
    /*Method for preventing the repetition of the tables in the database. Any repetitions are deleted.*/
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL("DROP TABLE IF EXISTS " + LeapContract.UserEntry.Table_Name);
        db.execSQL("DROP TABLE IF EXISTS " + LeapContract.PlaceEntry.Table_Name);
        db.execSQL("DROP TABLE IF EXISTS " + LeapContract.Leap_Place_Entry.Table_Name);
        db.execSQL("DROP TABLE IF EXISTS " + LeapContract.LeapEntry.Table_Name);
        db.execSQL("DROP TABLE IF EXISTS " + LeapContract.ReviewEntry.Table_Name);

        onCreate(db);

    }
}


