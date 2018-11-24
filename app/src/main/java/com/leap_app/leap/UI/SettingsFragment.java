package com.leap_app.leap.UI;

import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import com.leap_app.leap.R;


public class SettingsFragment extends Fragment{

    ImageButton publicSearch;
    ImageButton contactsOnly;
    ImageButton publicLeaps;
    @Override
    public View onCreateView(LayoutInflater inflater, final ViewGroup container, Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.settings, container, false);
        publicSearch = view.findViewById(R.id.publicSearchInfo);
        contactsOnly = view.findViewById(R.id.contactsOnlyInfo);
        publicLeaps = view.findViewById(R.id.publicLeapsInfo);

        publicSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Snackbar.make(container, "", Snackbar.LENGTH_SHORT).show();

            }
        });
        contactsOnly.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Snackbar.make(container, "Uncheck to receive invitations from anyone.", Snackbar.LENGTH_SHORT).show();

            }
        });
        publicLeaps.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Snackbar.make(container, "Can be changed for each leap!", Snackbar.LENGTH_SHORT).show();

            }
        });


        return view;

    }
}
