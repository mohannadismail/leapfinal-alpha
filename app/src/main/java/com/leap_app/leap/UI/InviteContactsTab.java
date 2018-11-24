package com.leap_app.leap.UI;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.Toast;

import com.leap_app.leap.Adapter.InviteCirclesAdapter;
import com.leap_app.leap.R;


public class InviteContactsTab extends Fragment {
    static final String[] contacts = new String[]{
            "Toto", "Soso", "Fofo", "Kiko", "Koko"};

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        final View view = inflater.inflate(R.layout.invite_contacts_tab, container, false);
        GridView gridview = view.findViewById(R.id.inviteContacts);
        gridview.setAdapter(new InviteCirclesAdapter(getContext(), contacts));


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
