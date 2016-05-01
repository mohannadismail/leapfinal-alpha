package com.leap_app.leap.UI;


import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.leap_app.leap.R;

/**
 * Created by aya on 4/24/16.
 */
public class MyLeapsCurrentFragment extends Fragment {


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_my_leaps_current, container, false);
    }


}
