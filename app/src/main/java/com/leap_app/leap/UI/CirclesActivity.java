package com.leap_app.leap.UI;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.app.FragmentTransaction;

import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.PopupWindow;
import android.widget.Toast;

import com.leap_app.leap.Adapter.CirclesAdapter;
import com.leap_app.leap.R;

import static android.app.PendingIntent.getActivity;

/**
 * Created by aya on 4/11/16.
 */
public class CirclesActivity extends AppCompatActivity implements NewCircleDialog.EditNameDialogListener {

    static final String[] user_names = new String[] {
            "Family", "Friends","Shella", "Nas Keda","Block" };
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
        GridView gridview = (GridView) findViewById(R.id.circlesGridView);
        gridview.setAdapter(new CirclesAdapter(this,user_names));


        gridview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View v,
                                    int position, long id) {
                Toast.makeText(CirclesActivity.this, "" + position,
                        Toast.LENGTH_SHORT).show();
            }
        });
    }


    public void addCircle(View v){
        FragmentManager fm = getSupportFragmentManager();
        NewCircleDialog circleNameDialog = new NewCircleDialog();
        circleNameDialog.show(fm, "fragment_edit_name");


    }

    @Override
    public void onFinishEditDialog(String inputText) {
        Intent i = new Intent(CirclesActivity.this,addCircle.class);

        String title = inputText;
        i.putExtra("key",title);
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
