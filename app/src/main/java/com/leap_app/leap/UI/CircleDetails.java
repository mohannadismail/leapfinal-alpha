package com.leap_app.leap.UI;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.GridView;

import com.leap_app.leap.R;
import com.leap_app.leap.Utility.Constants;


public class CircleDetails extends AppCompatActivity {
    private Button editCircle;
    private FragmentManager mFragmentManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.circle_view);
        final String title = getIntent().getStringExtra(Constants.KEY);
        setTitle(title);
        editCircle = findViewById(R.id.edit_circle);
        editCircle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(CircleDetails.this, addCircle.class);
                i.putExtra(Constants.KEY, title);
                startActivity(i);
            }
        });

        mFragmentManager = getSupportFragmentManager();
        GridView gridview = findViewById(R.id.friendsGridView);
//        gridview.setAdapter(new circleFriendsAdapter(this, user_names));

    }
}
