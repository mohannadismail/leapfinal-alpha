package com.leap_app.leap.ui.circles;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.leap_app.leap.R;
import com.leap_app.leap.ui.base.BaseActivity;
import com.leap_app.leap.utility.Constants;

public class addCircle extends BaseActivity {

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
