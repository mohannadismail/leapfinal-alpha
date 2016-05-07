package com.leap_app.leap.UI;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.Toast;

import com.leap_app.leap.Adapter.CirclesAdapter;
import com.leap_app.leap.Adapter.InviteCirclesAdapter;
import com.leap_app.leap.R;

/**
 * Created by aya on 5/7/16.
 */
public class InviteCirclesTab extends Fragment {
    static final String[] circles = new String[] {
            "Family", "Friends","Shella", "Nas Keda","Block" };
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        final View view = inflater.inflate(R.layout.invite_circles_tab, container, false);
        GridView gridview = (GridView) view.findViewById(R.id.inviteCircles);
        gridview.setAdapter(new InviteCirclesAdapter(getContext(),circles));


        gridview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View v,
                                    int position, long id) {
                Toast.makeText(getContext(), "" + position,
                        Toast.LENGTH_SHORT).show();

            }
        });
        return view;
    }
}
