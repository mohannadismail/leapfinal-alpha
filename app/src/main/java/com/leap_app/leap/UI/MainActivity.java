package com.leap_app.leap.UI;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
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
import android.widget.DatePicker;
import android.widget.ImageButton;
import android.app.DialogFragment;
import android.widget.TextView;

import com.leap_app.leap.LeapProvider.LeapContract;
import com.leap_app.leap.LeapProvider.LeapProvider;
import com.leap_app.leap.R;

import static android.app.PendingIntent.getActivity;

public class MainActivity extends AppCompatActivity implements DiscoverLeapsFragment.OnFragmentInteractionListener,DiscoverMapFragment.OnFragmentInteractionListener,CreationInfoFragment.OnFragmentInteractionListener,CreationPlacesFragment.OnFragmentInteractionListener {
    DrawerLayout mDrawerLayout;
    NavigationView mNavigationView;
    FragmentManager mFragmentManager;
    FragmentTransaction mFragmentTransaction;
    android.support.v7.widget.Toolbar toolbar;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);





        /**
         * Insert mock data in the database
         */
        SQLiteDatabase db = LeapProvider.mLeapHelper.getReadableDatabase();
        Cursor mCursor = db.rawQuery(" SELECT * FROM " + LeapContract.LeapEntry.Table_Name, null);
            if(mCursor.getCount()== 0){
                LeapProvider.insertUserRecords();
                LeapProvider.insertPlaceRecords();
                LeapProvider.insertLeapRecords();
                LeapProvider.insertLeapPlaceRecords();
                LeapProvider.insertReviewRecords();
            }
        mCursor.close();
        db.close();

        LeapProvider.updatelatlon();


        /**
         *Setup the DrawerLayout and NavigationView
         */

        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawerLayout);
        mNavigationView = (NavigationView) findViewById(R.id.nav_menu);

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
                    toolbar = (android.support.v7.widget.Toolbar) findViewById(R.id.toolbar);
                    toolbar.setTitle("Discover");

                }

                if (menuItem.getItemId() == R.id.nav_item_create ) {
                    FragmentTransaction xfragmentTransaction = mFragmentManager.beginTransaction();
                    xfragmentTransaction.replace(R.id.containerView, new CreationFragment()).commit();
                    toolbar = (android.support.v7.widget.Toolbar) findViewById(R.id.toolbar);
                    toolbar.setTitle("Create");


                }
                if (menuItem.getItemId() == R.id.nav_item_myleaps) {
                    FragmentTransaction xfragmentTransaction = mFragmentManager.beginTransaction();
                    xfragmentTransaction.replace(R.id.containerView, new MyLeapsFragment()).commit();
                    toolbar = (android.support.v7.widget.Toolbar) findViewById(R.id.toolbar);
                    toolbar.setTitle("My Leaps");
                }
                if (menuItem.getItemId() == R.id.nav_item_circles){
                    Intent intent = new Intent(getApplicationContext(), CirclesActivity.class);
                    startActivity(intent);
                    toolbar = (android.support.v7.widget.Toolbar) findViewById(R.id.toolbar);
                    toolbar.setTitle("Circles");
                }
                if (menuItem.getItemId() == R.id.nav_item_settings){
                    FragmentTransaction xfragmentTransaction = mFragmentManager.beginTransaction();
                    xfragmentTransaction.replace(R.id.containerView, new SettingsFragment()).commit();
                    toolbar = (android.support.v7.widget.Toolbar) findViewById(R.id.toolbar);
                    toolbar.setTitle("Settings");
                }

                return false;
            }

        });

        toolbar = (android.support.v7.widget.Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("Discover");
        ActionBarDrawerToggle mDrawerToggle = new ActionBarDrawerToggle(this,mDrawerLayout, toolbar,R.string.app_name,
                R.string.app_name);

        mDrawerLayout.setDrawerListener(mDrawerToggle);
        mDrawerToggle.syncState();

    }





    @Override
    public void onFragmentInteraction(Uri uri) {

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
    public void profile(View v){
        mFragmentTransaction = mFragmentManager.beginTransaction();
        mFragmentTransaction.replace(R.id.containerView, new UserProfileFragment()).commit();
        toolbar = (android.support.v7.widget.Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("Profile");
        mDrawerLayout.closeDrawers();


    }

    //Create Leap By Clicking on FAB
    public void Create(View v){
        mFragmentTransaction = mFragmentManager.beginTransaction();
        mFragmentTransaction.replace(R.id.containerView, new CreationFragment()).commit();
        toolbar = (android.support.v7.widget.Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("Create");
        mDrawerLayout.closeDrawers();


    }





}