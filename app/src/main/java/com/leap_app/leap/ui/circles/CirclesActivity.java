package com.leap_app.leap.ui.circles;

import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.PopupWindow;
import android.widget.Toast;

import com.leap_app.leap.R;
import com.leap_app.leap.ui.base.BaseActivity;
import com.leap_app.leap.ui.discover.MainActivity;
import com.leap_app.leap.utility.Constants;

public class CirclesActivity extends BaseActivity implements NewCircleDialog.EditNameDialogListener {

    private PopupWindow newCirclePopup;
    Button cancelAdding;
    Button doneAdding;
    EditText newCircle;
    FragmentManager mFragmentManager;
    FragmentTransaction mFragmentTransaction;

    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.circles_activity);


        mFragmentManager = getSupportFragmentManager();
        GridView gridview = findViewById(R.id.circlesGridView);
//        gridview.setAdapter(new CirclesAdapter(this, user_names));


        gridview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View v,
                                    int position, long id) {
                Toast.makeText(CirclesActivity.this, "" + position,
                        Toast.LENGTH_SHORT).show();
            }
        });
    }


    public void addCircle(View v) {
        FragmentManager fm = getSupportFragmentManager();
        NewCircleDialog circleNameDialog = new NewCircleDialog();
        circleNameDialog.show(fm, getString(R.string.fragment_edit_name));


    }

    @Override
    public void onFinishEditDialog(String inputText) {
        Intent i = new Intent(CirclesActivity.this, addCircle.class);
        i.putExtra(Constants.KEY, inputText);
        startActivity(i);


    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        // disable going back to the MainActivity
        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
        startActivity(intent);
    }
}
