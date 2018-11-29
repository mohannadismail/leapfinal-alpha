package com.leap_app.leap.ui;

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

import com.leap_app.leap.R;


public class Invite extends Fragment {
    private TabLayout tabLayout;
    private ViewPager viewPager;

    @Nullable
    public View onCreateView(@NonNull LayoutInflater inflater, final ViewGroup container, Bundle savedInstanceState) {


        /**
         *Inflate tab_layout and setup Views.
         */

        View x = inflater.inflate(R.layout.invite, null);
        tabLayout = x.findViewById(R.id.invite_tabs);
        viewPager = x.findViewById(R.id.inviteViewPager);
        Button next = x.findViewById(R.id.next);
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager fm = getFragmentManager();
                InviteConfirmDialog confirm = new InviteConfirmDialog();
                confirm.show(fm, "confirm");
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

    class MyAdapter extends FragmentPagerAdapter {

        MyAdapter(FragmentManager fm) {
            super(fm);
        }

        /**
         * Return fragment with respect to Position .
         */

        @Override
        public android.support.v4.app.Fragment getItem(int position) {
            switch (position) {
                case 0:
                    return new InviteCirclesTab();
                case 1:
                    return new InviteContactsTab();
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
                    return getString(R.string.circles);
                case 1:
                    return getString(R.string.contacts);
            }
            return null;
        }
    }


}
