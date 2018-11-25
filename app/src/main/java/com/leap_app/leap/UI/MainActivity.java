package com.leap_app.leap.UI;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.leap_app.leap.LeapProvider.LeapContract;
import com.leap_app.leap.LeapProvider.LeapProvider;
import com.leap_app.leap.R;

import static com.leap_app.leap.Utility.Constants.MyPREFERENCES;
import static com.leap_app.leap.Utility.Constants.Name1;
import static com.leap_app.leap.Utility.Constants.flag;
import static com.leap_app.leap.Utility.Constants.login1;
import static com.leap_app.leap.Utility.Constants.member1;
import static com.leap_app.leap.Utility.Constants.signup1;

public class MainActivity extends AppCompatActivity implements DiscoverLeapsFragment.OnFragmentInteractionListener, DiscoverMapFragment.OnFragmentInteractionListener, CreationInfoFragment.OnFragmentInteractionListener, CreationPlacesFragment.OnFragmentInteractionListener {
    private static MainActivity instance;
    private DrawerLayout mDrawerLayout;
    private FragmentManager mFragmentManager;
    private FragmentTransaction mFragmentTransaction;
    private android.support.v7.widget.Toolbar toolbar;
    private SharedPreferences sharedpreferences;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        sharedpreferences = getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);
        instance = this;

        /**
         * Insert mock data in the database
         */
        SQLiteDatabase db = LeapProvider.mLeapHelper.getReadableDatabase();
        Cursor mCursor = db.rawQuery(" SELECT * FROM " + LeapContract.LeapEntry.Table_Name, null);
//        if (mCursor.getCount() == 0) {
//            LeapProvider.insertUserRecords();
//            LeapProvider.insertPlaceRecords();
//            LeapProvider.insertLeapRecords();
//            LeapProvider.insertLeapPlaceRecords();
//            LeapProvider.insertReviewRecords();
//        }
//        mCursor.close();
//        db.close();

//        LeapProvider.updatelatlon();


        /**
         *Setup the DrawerLayout and NavigationView
         */

        mDrawerLayout = findViewById(R.id.drawerLayout);
        NavigationView mNavigationView = findViewById(R.id.nav_menu);

        /**
         * Lets inflate the very first fragment
         * Here , we are inflating the DiscoverTabsFragment as the first Fragment
         */

        mFragmentManager = getSupportFragmentManager();
        mFragmentTransaction = mFragmentManager.beginTransaction();
        mFragmentTransaction.replace(R.id.containerView, new DiscoverTabsFragment()).commit();
        /**
         * Setup click events on the Navigation View Items.
         */

        mNavigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem menuItem) {
                mDrawerLayout.closeDrawers();


                if (menuItem.getItemId() == R.id.nav_item_discover) {
                    FragmentTransaction fragmentTransaction = mFragmentManager.beginTransaction();
                    fragmentTransaction.replace(R.id.containerView, new DiscoverTabsFragment()).commit();
                    toolbar = findViewById(R.id.toolbar);
                    toolbar.setTitle("Discover");

                }

                if (menuItem.getItemId() == R.id.nav_item_create) {
                    if (LoginActivity.isFlag()) {
                        FragmentTransaction xfragmentTransaction = mFragmentManager.beginTransaction();
                        xfragmentTransaction.replace(R.id.containerView, new CreationFragment()).commit();
                        toolbar = findViewById(R.id.toolbar);
                        toolbar.setTitle("Create");
                    } else {
                        Toast.makeText(getBaseContext(), "Please Login first", Toast.LENGTH_LONG).show();
                    }
                }
                if (menuItem.getItemId() == R.id.nav_item_myleaps) {
                    FragmentTransaction xfragmentTransaction = mFragmentManager.beginTransaction();
                    xfragmentTransaction.replace(R.id.containerView, new MyLeapsFragment()).commit();
                    toolbar = findViewById(R.id.toolbar);
                    toolbar.setTitle("My Leaps");
                }
                if (menuItem.getItemId() == R.id.nav_item_circles) {
                    if (LoginActivity.isFlag()) {
                        Intent intent = new Intent(getApplicationContext(), CirclesActivity.class);
                        startActivity(intent);
                        toolbar = findViewById(R.id.toolbar);
                        toolbar.setTitle("Circles");
                    } else {
                        Toast.makeText(getBaseContext(), "Please Login first", Toast.LENGTH_LONG).show();
                    }
                }
                if (menuItem.getItemId() == R.id.nav_item_logout) {
                    Intent intent = new Intent(getApplicationContext(), LogoutActivity.class);
                    startActivity(intent);
                    toolbar = findViewById(R.id.toolbar);
                    toolbar.setTitle("Logout");
                }
                if (menuItem.getItemId() == R.id.nav_item_settings) {
                    FragmentTransaction xfragmentTransaction = mFragmentManager.beginTransaction();
                    xfragmentTransaction.replace(R.id.containerView, new SettingsFragment()).commit();
                    toolbar = findViewById(R.id.toolbar);
                    toolbar.setTitle("Settings");
                }

                return false;
            }

        });

        toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle("Discover");
        ActionBarDrawerToggle mDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout, toolbar, R.string.app_name,
                R.string.app_name);

        mDrawerLayout.setDrawerListener(mDrawerToggle);
        mDrawerToggle.syncState();
    }


    public void fillTextview(String s) {
        TextView user = findViewById(R.id.account_name);
        Button signup = findViewById(R.id.signup_button);
        TextView member = findViewById(R.id.member);
        TextView login = findViewById(R.id.login);
        TextView location = findViewById(R.id.location);

        SharedPreferences.Editor editor = sharedpreferences.edit();
        editor.putBoolean(flag, LoginActivity.isFlag());
        editor.putString(Name1, s);
        editor.putInt(signup1, View.INVISIBLE);
        editor.putString(member1, "");
        editor.putString(login1, "");
        editor.apply();

        user.setText(s);
        location.setText("Mansoura");
        signup.setVisibility(View.INVISIBLE);
        member.setText("");
        login.setText("");
        Log.d("MAIN", "DONE");
    }

    public void clearTextview() {
        TextView user = findViewById(R.id.account_name);
        Button signup = findViewById(R.id.signup_button);
        TextView member = findViewById(R.id.member);
        TextView login = findViewById(R.id.login);

        user.setText("@string/profileName");
        signup.setVisibility(View.VISIBLE);
        member.setText("@string/login");
        login.setText("@string/alreadyMember");
    }


    @Override
    public void onFragmentInteraction(Uri uri) {
//        if (sharedpreferences.getBoolean(flag, false) == true) {
//            TextView user = (TextView) findViewById(R.id.account_name);
//            Button signup = (Button) findViewById(R.id.signup_button);
//            TextView member = (TextView) findViewById(R.id.member);
//            TextView login = (TextView) findViewById(R.id.login);
//
//            user.setText(sharedpreferences.getString(Name1,""));
//            signup.setVisibility(View.INVISIBLE);
//            member.setText(sharedpreferences.getString(member1,""));
//            login.setText(sharedpreferences.getString(login1,""));
//        }

        return;
    }

    // SignUp from Navigation drawer button
    public void SignUp(View v) {
        Intent intent = new Intent(getApplicationContext(), SignupActivity.class);
        startActivity(intent);
    }


    //Login from Navigation Drawer
    public void Login(View v) {
        Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
        startActivity(intent);
    }

    // View User Profile from User Image
    public void profile(View v) {
        mFragmentTransaction = mFragmentManager.beginTransaction();
        mFragmentTransaction.replace(R.id.containerView, new UserProfileFragment()).commit();
        toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle("Profile");
        mDrawerLayout.closeDrawers();


    }

    //Create Leap By Clicking on FAB
    public void Create(View v) {
        if (LoginActivity.isFlag()) {
            mFragmentTransaction = mFragmentManager.beginTransaction();
            mFragmentTransaction.replace(R.id.containerView, new CreationFragment()).commit();
            toolbar = findViewById(R.id.toolbar);
            toolbar.setTitle("Create");
            mDrawerLayout.closeDrawers();
        } else {
            Toast.makeText(getBaseContext(), "Please Login first", Toast.LENGTH_LONG).show();
        }

    }

    @Override
    public void onBackPressed() {
        if (getSupportFragmentManager().getBackStackEntryCount() == 0) {
            this.finish();
        } else {
            getSupportFragmentManager().popBackStack();
        }
    }
}