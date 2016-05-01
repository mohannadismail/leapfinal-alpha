package com.leap_app.leap.UI;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.GridView;

import com.leap_app.leap.Adapter.CirclesAdapter;
import com.leap_app.leap.Adapter.circleFriendsAdapter;
import com.leap_app.leap.R;

/**
 * Created by aya on 4/18/16.
 */
public class CircleDetails extends AppCompatActivity {
    Button editCircle;
    FragmentManager mFragmentManager;
    static final String[] user_names = new String[] {
            "Ahmed", "Abbas","Fatakat", "Sania","User" };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.circle_view);
        final String title = getIntent().getStringExtra("key").toString();
        setTitle(title);
        editCircle = (Button) findViewById(R.id.edit_circle);
        editCircle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(CircleDetails.this, addCircle.class);
                i.putExtra("key", title);
                startActivity(i);
            }
        });

        mFragmentManager = getSupportFragmentManager();
        GridView gridview = (GridView) findViewById(R.id.friendsGridView);
        gridview.setAdapter(new circleFriendsAdapter(this, user_names));



    }
}
