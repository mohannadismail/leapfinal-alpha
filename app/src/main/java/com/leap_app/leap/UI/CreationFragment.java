package com.leap_app.leap.UI;

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

import com.firebase.client.Firebase;
import com.leap_app.leap.Models.LeapBaseInfo;
import com.leap_app.leap.Models.Placeview;
import com.leap_app.leap.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;


public class CreationFragment extends Fragment {
    private TabLayout tabLayout;
    private ViewPager viewPager;

    private android.support.v7.widget.Toolbar toolbar;
    private ArrayList<Placeview> placeviews = new ArrayList<>();
    private Boolean flag = null;


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
                    if (!CreationInfoFragment.isFlagg()) {
                        Toast.makeText(getContext(), R.string.forgot_save_data_alert, Toast.LENGTH_SHORT).show();

//                        ContentValues values = new ContentValues();
//                        SQLiteDatabase db = new LeapDbHelper(getContext()).getWritableDatabase();
//
//                        values.put(LeapContract.LeapEntry.COLUMN_Name, CreationInfoFragment.leapBaseInfooo.getLeapName());
//                        values.put(LeapContract.LeapEntry.COLUMN_Description, CreationInfoFragment.leapBaseInfooo.getLeapDescription());
//                        values.put(LeapContract.LeapEntry.COLUMN_Map_Image, CreationInfoFragment.leapBaseInfooo.getLeapLocation());
//                        values.put(LeapContract.LeapEntry.COLUMN_Price, CreationInfoFragment.leapBaseInfooo.getLeapPrice());
//
//                        db.insert(LeapContract.LeapEntry.Table_Name,null, values);

                        LeapBaseInfo leapBase = new LeapBaseInfo(CreationInfoFragment.leapBaseInfooo.getLeapName(), CreationInfoFragment.leapBaseInfooo.getLeapDescription(), CreationInfoFragment.leapBaseInfooo.getLeapLocation(), CreationInfoFragment.leapBaseInfooo.getLeapPrice(), CreationInfoFragment.leapBaseInfooo.getDate(), CreationInfoFragment.leapBaseInfooo.getTime());
                        pushToFirebase(leapBase, CreationPlacesFragment.getInstance().getPlaceviewList());
                    }
//                    else Toast.makeText(getContext(),"You forgot to save your data", Toast.LENGTH_SHORT).show();
//
                    if (CreationPlacesFragment.getInstance().getPlaceviewList().isEmpty())
                        Toast.makeText(getContext(), R.string.didnt_add_places_alert, Toast.LENGTH_SHORT).show();
//
//
//                    if(!CreationInfoFragment.flagg)
//                        Toast.makeText(getContext(),"You forgot to save your data", Toast.LENGTH_SHORT).show();
//
                    if (CreationInfoFragment.isFlagg() && CreationPlacesFragment.getInstance().getPlaceviewList() != null) {

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

    public void pushToFirebase(LeapBaseInfo leapBase, ArrayList<Placeview> placeviewList) {
        Firebase ref = new Firebase("https://leapappeg.firebaseio.com/leap/Leap/");
        Firebase ref2 = new Firebase("https://leapappeg.firebaseio.com/leap/Places/");


        Map<String, LeapBaseInfo> newLeap = new HashMap<>();
        try {
            newLeap.put(CreationInfoFragment.leapBaseInfooo.getLeapName(), leapBase);
        } catch (NullPointerException e) {
            e.printStackTrace();
            Toast.makeText(getContext(), "You forgot to save your data", Toast.LENGTH_LONG).show();

        }
        Firebase newRef = ref.push();
        String key = newRef.getKey();

        ref.child(key).setValue(leapBase);

        ref2.child(key).setValue(placeviewList);
        ref.push();
        ref2.push();

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


}
