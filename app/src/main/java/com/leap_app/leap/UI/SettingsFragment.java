package com.leap_app.leap.UI;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.Toast;

import com.leap_app.leap.R;

/**
 * Created by aya on 4/24/16.
 */
public class SettingsFragment extends Fragment{

    ImageButton publicSearch;
    ImageButton contactsOnly;
    ImageButton publicLeaps;
    @Override
    public View onCreateView(LayoutInflater inflater, final ViewGroup container, Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.settings, container, false);
        publicSearch = (ImageButton) view.findViewById(R.id.publicSearchInfo);
        contactsOnly = (ImageButton) view.findViewById(R.id.contactsOnlyInfo);
        publicLeaps = (ImageButton) view.findViewById(R.id.publicLeapsInfo);

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
