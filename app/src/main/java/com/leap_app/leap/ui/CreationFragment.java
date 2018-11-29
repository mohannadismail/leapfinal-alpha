package com.leap_app.leap.ui;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.leap_app.leap.R;
import com.leap_app.leap.models.LeapBaseInfo;
import com.leap_app.leap.models.Placeview;
import com.leap_app.leap.utility.Constants;
import com.leap_app.leap.utility.LeapLatLon;

import java.util.ArrayList;
import java.util.Objects;

import static com.leap_app.leap.ui.CreationPlacesFragment.placesviewList;


public class CreationFragment extends Fragment {
    private TabLayout tabLayout;
    private ViewPager viewPager;

    private android.support.v7.widget.Toolbar toolbar;
    private ArrayList<Placeview> placeviews = new ArrayList<>();
    private Boolean flag = null;
    private double[] lats, lons;


    @Nullable
    public View onCreateView(@NonNull LayoutInflater inflater, final ViewGroup container, Bundle savedInstanceState) {
        /**
         *Inflate tab_layout and setup Views.
         */

        View x = inflater.inflate(R.layout.fragment_creation, null);
        tabLayout = x.findViewById(R.id.creation_tabs);
        viewPager = x.findViewById(R.id.view_pager);

        Button done = x.findViewById(R.id.Done);


        try {
            done.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
//                    if (!CreationInfoFragment.isFlagg()) {
//                        Toast.makeText(getContext(), R.string.forgot_save_data_alert, Toast.LENGTH_SHORT).show();

//                        ContentValues values = new ContentValues();
//                        SQLiteDatabase db = new LeapDbHelper(getContext()).getWritableDatabase();
//
//                        values.put(LeapContract.LeapEntry.COLUMN_Name, CreationInfoFragment.leapBaseInfooo.getLeapName());
//                        values.put(LeapContract.LeapEntry.COLUMN_Description, CreationInfoFragment.leapBaseInfooo.getLeapDescription());
//                        values.put(LeapContract.LeapEntry.COLUMN_Map_Image, CreationInfoFragment.leapBaseInfooo.getLeapLocation());
//                        values.put(LeapContract.LeapEntry.COLUMN_Price, CreationInfoFragment.leapBaseInfooo.getLeapPrice());
//
//                        db.insert(LeapContract.LeapEntry.Table_Name,null, values);

                    LeapBaseInfo leapBase = CreationInfoFragment.leapBaseInfooo;
                    pushToFirebase(leapBase);
//                    }
//                    else Toast.makeText(getContext(),"You forgot to save your data", Toast.LENGTH_SHORT).show();
//
//                    if (CreationPlacesFragment.getInstance().getPlaceviewList().isEmpty())
//                        Toast.makeText(getContext(), R.string.didnt_add_places_alert, Toast.LENGTH_SHORT).show();
//
//
//                    if(!CreationInfoFragment.flagg)
//                        Toast.makeText(getContext(),"You forgot to save your data", Toast.LENGTH_SHORT).show();
//
                    if (CreationInfoFragment.isFlagg() && placesviewList != null) {

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
                        Toast.makeText(getContext(), R.string.leap_saved, Toast.LENGTH_SHORT).show();
                        CreationInfoFragment.setFlagg(false);

//
////                        CreationPlacesFragment.placeviewList = new ArrayList<Placeview>();
//
                    }
                }
            });


        } catch (NullPointerException e) {
            e.printStackTrace();
            flag = true;
        }

        Button invite = x.findViewById(R.id.Invite);
        toolbar = getActivity().findViewById(R.id.toolbar);
        toolbar.setTitle(R.string.create);
        invite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Invite nextFrag = new Invite();
                Objects.requireNonNull(getFragmentManager()).beginTransaction()
                        .replace(R.id.containerView, nextFrag, getString(R.string.frag))
                        .addToBackStack(null)
                        .commit();

                toolbar.setTitle(R.string.invite);
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

    public void pushToFirebase(LeapBaseInfo leapBase) {
        lats = new double[placesviewList.size()];
        lons = new double[placesviewList.size()];
        if (placesviewList.size() != 0) {
            for (int i = 0; i < placesviewList.size(); i++) {
                lats[i] = placesviewList.get(i).getLat();
                lons[i] = placesviewList.get(i).getLon();
            }
        }
        double[] centerPoint = new double[2];
        centerPoint = LeapLatLon.LeapCenter(lats, lons);

        DatabaseReference centerPointRef = FirebaseDatabase.getInstance().getReference(Constants.CENTER_POINT);
        DatabaseReference database = FirebaseDatabase.getInstance().getReference(Constants.LEAP);
        DatabaseReference database1 = FirebaseDatabase.getInstance().getReference(Constants.PLACES);
        DatabaseReference newRef = database.push();

        //Getting a common key for all Leap Data
        String key = newRef.getKey();
        database.push();

        //Setting Leap Info
        database.child(key).setValue(leapBase);

        //Pushing center Point`
        centerPointRef.child(key).child("0").setValue(centerPoint[0]);
        centerPointRef.child(key).child("1").setValue(centerPoint[1]);

        //Pushing Places
        database1.child(key).setValue(placesviewList);


        //Final Push
        database.push();
        database1.push();
        centerPointRef.push();
        flag = true;
    }


    class MyAdapter extends FragmentPagerAdapter {

        MyAdapter(FragmentManager fm) {
            super(fm);
        }

        /**
         * Return fragment with respect to Position .
         */

        @Override
        public Fragment getItem(int position) {
            switch (position) {
                case 0:
                    return new CreationInfoFragment();
                case 1:
                    return new CreationPlacesFragment();
            }
            return null;
        }

        @Override
        public int getCount() {

            int int_items = 2;
            return int_items;

        }

        /**
         * This method returns the title of the tab according to the position.
         */

        @Override
        public CharSequence getPageTitle(int position) {

            switch (position) {
                case 0:
                    return "INFO";
                case 1:
                    return "PLACES";
            }
            return null;
        }
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    //
    @Override
    public void onDetach() {
        super.onDetach();
    }

}
