package com.leap_app.leap.UI;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v4.app.FragmentManager;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListPopupWindow;
import android.widget.PopupWindow;
import android.widget.RatingBar;
import android.widget.TextView;

import com.leap_app.leap.Models.LeapInfo;
import com.leap_app.leap.R;
import com.leap_app.leap.Utility.CircleTransform;
import com.squareup.picasso.Picasso;


public class LeapInfoActivity extends AppCompatActivity implements NewCircleDialog.EditNameDialogListener{

    public String leapid;
    private RatingBar ratingBar;
    private PopupWindow pwindo;
    Button btnClosePopup;
    Button btnSubmit;
    NestedScrollView leap;
    Toolbar toolbar;
    FragmentManager mFragmentManager;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_leap_view);
        addListenerOnRatingBar();

        btnSubmit = (Button) findViewById(R.id.submitReview);
        toolbar = (Toolbar) findViewById(R.id.tool_bar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);



        /* btnSubmit.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
// TODO Auto-generated method stub
            }
        });

        */



        leapid = this.getIntent().getStringExtra("LeapId");
        Log.d("LeapID ", "" + leapid);

        TextView v1 = (TextView) findViewById(R.id.leaptitle);
        v1.setText(LeapInfo.getLeapNameColumn(Integer.parseInt(leapid)));

        CollapsingToolbarLayout collapsingToolbar =
                (CollapsingToolbarLayout) findViewById(R.id.collapsingToolbarLayout);
        collapsingToolbar.setTitle(LeapInfo.getLeapNameColumn(Integer.parseInt(leapid)));

        TextView v2 = (TextView) findViewById(R.id.leapprice);
        v2.setText(LeapInfo.getLeapPriceColumn(Integer.parseInt(leapid)));

        TextView v3 = (TextView) findViewById(R.id.description);
        v3.setText(LeapInfo.getLeapDescColumn(Integer.parseInt(leapid)));

        TextView v41 = (TextView) findViewById(R.id.leapuser);
        v41.setText(LeapInfo.getLeapUserColumn(Integer.parseInt(leapid)));

        TextView v42 = (TextView) findViewById(R.id.usernamereview);
        v42.setText(LeapInfo.getLeapUserColumn(Integer.parseInt(leapid)));

        TextView v5 = (TextView) findViewById(R.id.numofleaps);
        v5.setText(LeapInfo.getNumOfLeapsColumn(Integer.parseInt(leapid)));

        ImageView v6 = (ImageView) findViewById(R.id.poster);
        Picasso.with(this.getBaseContext()).load(LeapInfo.getImageColumn(Integer.parseInt(leapid))).into(v6);

        ImageView v71 = (ImageView) findViewById(R.id.profile);
        Picasso.with(this.getBaseContext()).load(LeapInfo.getUserImageColumn(Integer.parseInt(leapid))).placeholder(R.drawable.ic_account_circle_black_48px).transform(new CircleTransform()).into(v71);

        ImageView v72 = (ImageView) findViewById(R.id.profilerev);
        Picasso.with(this.getBaseContext()).load(LeapInfo.getUserImageColumn(Integer.parseInt(leapid))).placeholder(R.drawable.ic_account_circle_black_48px).transform(new CircleTransform()).into(v72);

        // getActionBar().setDisplayHomeAsUpEnabled(true);
        Toolbar myToolbar = (Toolbar) findViewById(R.id.toolbar);


        //setActionBar(myToolbar);
    }

    public void viewMap(View view) {
        Intent i = new Intent(this, Place.class);
        i.putExtra("LeapPlace", leapid);
        startActivity(i);

    }

    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.menuleap, menu);
//        MenuItem menuShare = menu.findItem(R.id.share);
//        MenuItem menuSearch = menu.findItem(R.id.search);

    }


    public void addListenerOnRatingBar() {

        ratingBar = (RatingBar) findViewById(R.id.ratingBar1);

        //if rating value is changed,
        //display the current rating value in the result (textview) automatically
        ratingBar.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            public void onRatingChanged(RatingBar ratingBar, float rating,
                                        boolean fromUser) {

                initiatePopupWindow();


            }
        });
    }


    private void initiatePopupWindow() {

        FragmentManager fm = getSupportFragmentManager();
        ReviewDialog review = new ReviewDialog();
        review.show(fm, "fragment_edit_name");

        /**
        try {
// We need to get the instance of the LayoutInflater
            LayoutInflater inflater = (LayoutInflater) LeapInfoActivity.this
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            View layout = inflater.inflate(R.layout.review_popup,
                    (ViewGroup) findViewById(R.id.popup_element));
            leap = (NestedScrollView) findViewById(R.id.leap_view);
            leap.setAlpha(0.5f);
            pwindo = new PopupWindow(layout, ListPopupWindow.WRAP_CONTENT, ListPopupWindow.WRAP_CONTENT, true);
            pwindo.showAtLocation(layout, Gravity.CENTER, 0, 0);

            btnClosePopup = (Button) layout.findViewById(R.id.buttonclose);
            btnClosePopup.setOnClickListener(cancel_button_click_listener);


        } catch (Exception e) {
            e.printStackTrace();
        }



    }
    private View.OnClickListener cancel_button_click_listener = new View.OnClickListener() {
        public void onClick(View v) {
            leap = (NestedScrollView) findViewById(R.id.leap_view);
            leap.setAlpha(1f);
            pwindo.dismiss();

        }
    };
         */
    }


    @Override
    public void onFinishEditDialog(String inputText) {
        ratingBar.setRating(5);
    }
}



