package com.leap_app.leap.UI;

import android.app.Activity;
import android.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.leap_app.leap.Adapter.DiscoverLeapsAdapter;
import com.leap_app.leap.Models.Leap;
import com.leap_app.leap.R;

import java.util.List;

/**
 * Created by aya on 4/13/16.
 */
public class addCircle extends AppCompatActivity {
    Button doneEditing;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_edit_circle);
        final String title = getIntent().getStringExtra("key").toString();
        setTitle(title);
        doneEditing = (Button) findViewById(R.id.done_editing);
        doneEditing.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(addCircle.this,CircleDetails.class);
                i.putExtra("key",title);
                startActivity(i);
            }
        });



    }

}
