package com.leap_app.leap.UI;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.firebase.client.Firebase;
import com.leap_app.leap.LeapProvider.LeapContract;
import com.leap_app.leap.LeapProvider.LeapDbHelper;
import com.leap_app.leap.Models.Leap;
import com.leap_app.leap.Models.LeapBaseInfo;
import com.leap_app.leap.Models.Placeview;
import com.leap_app.leap.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by aya on 3/14/16.
 */
public class CreationFragment extends Fragment{
    public static TabLayout tabLayout;
    public static ViewPager viewPager;
    public static int int_items = 2 ;
    public Button Done, Invite;

    android.support.v7.widget.Toolbar toolbar;
    ArrayList<Placeview> placeviews = new ArrayList<>();
    Boolean flag = null;


    @Nullable
    public View onCreateView(LayoutInflater inflater, final ViewGroup container, Bundle savedInstanceState) {
        /**
         *Inflate tab_layout and setup Views.
         */

        View x =  inflater.inflate(R.layout.fragment_creation, null);
        tabLayout = (TabLayout) x.findViewById(R.id.creation_tabs);
        viewPager = (ViewPager) x.findViewById(R.id.view_pager);


//        floatingActionButton = (FloatingActionButton) x.findViewById(R.id.fab);
//        floatingActionButton.setVisibility(View.INVISIBLE);


        Done = (Button) x.findViewById(R.id.Done);


        try {
            Done.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
//                Toast.makeText(getContext(),CreationInfoFragment.Title,Toast.LENGTH_LONG).show();
//                    if (CreationInfoFragment.leapBaseInfooo != null) {
                    if (CreationInfoFragment.flagg == false){
                        Toast.makeText(getContext(),"You forgot to save your data", Toast.LENGTH_SHORT).show();

//                        ContentValues values = new ContentValues();
//                        SQLiteDatabase db = new LeapDbHelper(getContext()).getWritableDatabase();
//
//                        values.put(LeapContract.LeapEntry.COLUMN_Name, CreationInfoFragment.leapBaseInfooo.getLeapName());
//                        values.put(LeapContract.LeapEntry.COLUMN_Description, CreationInfoFragment.leapBaseInfooo.getLeapDescription());
//                        values.put(LeapContract.LeapEntry.COLUMN_Map_Image, CreationInfoFragment.leapBaseInfooo.getLeapLocation());
//                        values.put(LeapContract.LeapEntry.COLUMN_Price, CreationInfoFragment.leapBaseInfooo.getLeapPrice());
//
//                        db.insert(LeapContract.LeapEntry.Table_Name,null, values);

//                        LeapBaseInfo leapBase = new LeapBaseInfo(CreationInfoFragment.leapBaseInfooo.getLeapName(), CreationInfoFragment.leapBaseInfooo.getLeapDescription(), CreationInfoFragment.leapBaseInfooo.getLeapLocation(), CreationInfoFragment.leapBaseInfooo.getLeapPrice(), CreationInfoFragment.leapBaseInfooo.getDate(), CreationInfoFragment.leapBaseInfooo.getTime());
//                        pushToFirebase(leapBase, CreationPlacesFragment.placeviewList);
                    }
//                    else Toast.makeText(getContext(),"You forgot to save your data", Toast.LENGTH_SHORT).show();
//
                    if( CreationPlacesFragment.placeviewList.isEmpty())
                        Toast.makeText(getContext(),"You didn't add your outing's places", Toast.LENGTH_SHORT).show();
//
//
//                    if(!CreationInfoFragment.flagg)
//                        Toast.makeText(getContext(),"You forgot to save your data", Toast.LENGTH_SHORT).show();
//
                    if (CreationInfoFragment.flagg && CreationPlacesFragment.placeviewList != null) {

                        Intent i = new Intent(getContext(), MainActivity.class);
                        v.getContext().startActivity(i);
//                        SQLiteDatabase db = new LeapDbHelper(getContext()).getReadableDatabase();
//                        Cursor c = db.rawQuery("SELECT name FROM User WHERE id =?" , new String[]{LoginActivity.id1});
//                        if(c!=null)
//                        {
//                            c.moveToFirst();
//                        }
//                        String s = c.getString(0);
//                        Log.d("TAGI", s);
//
//                        MainActivity.instance.fillTextview(s);
//                        LoginActivity.flag = true;
//                        c.close();
                        Toast.makeText(getContext(),"Leap Saved", Toast.LENGTH_SHORT).show();
                        CreationInfoFragment.flagg = false;

//
////                        CreationPlacesFragment.placeviewList = new ArrayList<Placeview>();
//
                    }
                }
            });


        }catch (NullPointerException e){
            e.printStackTrace();
            flag = true;
        }

        Invite = (Button) x.findViewById(R.id.Invite);
        toolbar = (android.support.v7.widget.Toolbar) getActivity().findViewById(R.id.toolbar);
        toolbar.setTitle("Create");
        Invite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Invite nextFrag= new Invite();
                getFragmentManager().beginTransaction()
                        .replace(R.id.containerView, nextFrag,"frag")
                        .addToBackStack(null)
                        .commit();

                toolbar.setTitle("Invite");
            }
        });

        /**
         *Set an Adapter for the View Pager
         */
        viewPager.setAdapter(new MyAdapter(getChildFragmentManager()));

        /**
         * Now , this is a workaround ,
         * The setupWithViewPager dose't works without the runnable .
         * Maybe a Support Library Bug .
         */

        tabLayout.post(new Runnable() {
            @Override
            public void run() {
                tabLayout.setupWithViewPager(viewPager);
            }
        });



        return x;

    }
//    public void pushToFirebase(LeapBaseInfo leapBase, ArrayList<Placeview> placeviewList){
//        Firebase ref = new Firebase("https://leapappeg.firebaseio.com/leap/Leap/");
//        Firebase ref2 = new Firebase("https://leapappeg.firebaseio.com/leap/Places/");
//
//
//        Map<String, LeapBaseInfo> newLeap = new HashMap<String, LeapBaseInfo>();
//        try {
//            newLeap.put(CreationInfoFragment.leapBaseInfooo.getLeapName(), leapBase);
//        }catch (NullPointerException e){
//            e.printStackTrace();
//            Toast.makeText(getContext(),"You forgot to save your data", Toast.LENGTH_LONG).show();
//
//        }
//        Firebase newRef = ref.push();
//        String key = newRef.getKey();
//
//        ref.child(key).setValue(leapBase);
//
//        ref2.child(key).setValue(placeviewList);
//
//        flag = true;
//    }


    class MyAdapter extends FragmentPagerAdapter {

        public MyAdapter(FragmentManager fm) {
            super(fm);
        }

        /**
         * Return fragment with respect to Position .
         */

        @Override
        public Fragment getItem(int position)
        {
            switch (position){
                case 0 : return new CreationInfoFragment();
                case 1 :return new CreationPlacesFragment();
            }
            return null;
        }

        @Override
        public int getCount() {

            return int_items;

        }

        /**
         * This method returns the title of the tab according to the position.
         */

        @Override
        public CharSequence getPageTitle(int position) {

            switch (position){
                case 0 :
                    return "INFO";
                case 1 :
                    return "PLACES";
            }
            return null;
        }
    }


}
