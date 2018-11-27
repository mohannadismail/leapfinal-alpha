package com.leap_app.leap.UI;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.leap_app.leap.R;
import com.leap_app.leap.Utility.Constants;

public class addCircle extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_edit_circle);
        final String title = getIntent().getStringExtra(Constants.KEY);
        setTitle(title);
        Button doneEditing = findViewById(R.id.done_editing);
        doneEditing.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(addCircle.this, CircleDetails.class);
                i.putExtra(getString(R.string.key), title);
                startActivity(i);
            }
        });

    }
}
