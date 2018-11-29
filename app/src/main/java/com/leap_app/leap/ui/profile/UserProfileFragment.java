package com.leap_app.leap.ui.profile;
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

import com.leap_app.leap.R;


public class UserProfileFragment extends Fragment {

    private TabLayout tabLayout;
    private ViewPager viewPager;
    private int int_items = 4;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        /**
         *Inflate tab_layout and setup Views.
         */

        View x =  inflater.inflate(R.layout.fragment_profile,null);
        tabLayout = x.findViewById(R.id.profile_tab);
        viewPager = x.findViewById(R.id.pager);

//        floatingActionButton = (FloatingActionButton) x.findViewById(R.id.fab);
//        floatingActionButton.setVisibility(View.INVISIBLE);

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
        public android.support.v4.app.Fragment getItem(int position)
        {
            switch (position){
                case 0 : return new UserCreatedTab();
                case 1 : return new UserLeapedTab();
                case 2 : return new UserLikesTab();
                case 3 : return new UserReviewsTab();
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
                    return getString(R.string.created);
                case 1 :
                    return getString(R.string.leaped);
                case 2 :
                    return getString(R.string.likes);
                case 3 :
                    return getString(R.string.reviewss);


            }
            return null;
        }
    }


}