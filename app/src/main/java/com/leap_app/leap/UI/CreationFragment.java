package com.leap_app.leap.UI;

import android.content.Intent;
import android.os.Bundle;
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
import com.leap_app.leap.Models.LeapBase;
import com.leap_app.leap.Models.Placeview;
import com.leap_app.leap.R;
import com.leap_app.leap.Utility.Constants;

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
                    LeapBase leapBase = new LeapBase(CreationInfoFragment.leapBaseInfooo, CreationPlacesFragment.placeviewList);

                    pushToFirebase(leapBase);

                    if(!CreationInfoFragment.flagg)
                        Toast.makeText(getContext(),"You forgot to save your data", Toast.LENGTH_SHORT).show();

                    if (CreationInfoFragment.flagg) {

                        Intent i = new Intent(getContext(), MainActivity.class);
                        v.getContext().startActivity(i);
                        Toast.makeText(getContext(),"Leap Saved", Toast.LENGTH_SHORT).show();
                        CreationInfoFragment.flagg = false;


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
    public void pushToFirebase(LeapBase leapBase){
        Firebase ref = new Firebase(Constants.FIREBASE_Leap_URL);
        Map<String, Object> newLeap = new HashMap<String, Object>();
        try {


            newLeap.put(CreationInfoFragment.leapBaseInfooo.getLeapName(), leapBase);
        }catch (NullPointerException e){
            e.printStackTrace();
            Toast.makeText(getContext(),"You forgot to save your data", Toast.LENGTH_LONG).show();

        }
        ref.push().setValue(newLeap);

        flag = true;
    }


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
