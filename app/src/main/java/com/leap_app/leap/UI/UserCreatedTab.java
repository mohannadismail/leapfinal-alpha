package com.leap_app.leap.UI;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.leap_app.leap.Adapter.DiscoverLeapsAdapter;
import com.leap_app.leap.Models.Leap;
import com.leap_app.leap.R;

import java.util.List;

public class UserCreatedTab extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // return inflater.inflate(R.layout.user_created_tab, container, false);
        View view = inflater.inflate(R.layout.fragment_leaps, container, false);
        FragmentActivity context = getActivity();
        RecyclerView rv = (RecyclerView) view.findViewById(R.id.rv);
        LinearLayoutManager llm = new LinearLayoutManager(context);
        rv.setLayoutManager(llm);
        context = this.getActivity();
        List<Leap> leaps;
        leaps = Leap.initializeData();
        DiscoverLeapsAdapter adapter = new DiscoverLeapsAdapter(context, leaps);
        rv.setAdapter(adapter);

        return view;
    }
}