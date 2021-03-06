package com.leap_app.leap.ui.discover;

import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v4.app.FragmentManager;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.widget.Button;
import android.widget.PopupWindow;
import android.widget.RatingBar;
import android.widget.TextView;

import com.leap_app.leap.R;
import com.leap_app.leap.models.LeapBaseInfo;
import com.leap_app.leap.ui.base.BaseActivity;
import com.leap_app.leap.ui.circles.NewCircleDialog;
import com.leap_app.leap.ui.utility.ReviewDialog;
import com.leap_app.leap.utility.Constants;

import java.util.Objects;


public class LeapInfoActivity extends BaseActivity
        implements NewCircleDialog.EditNameDialogListener {

    private LeapBaseInfo leapid;
    private RatingBar ratingBar;
    private PopupWindow pwindo;
    private Button btnClosePopup;
    private Button btnSubmit;
    private NestedScrollView leap;
    private Toolbar toolbar;
    private FragmentManager mFragmentManager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_leap_view);
        addListenerOnRatingBar();

        btnSubmit = findViewById(R.id.submitReview);
        toolbar = findViewById(R.id.tool_bar);
        setSupportActionBar(toolbar);
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        Bundle bundle = getIntent().getExtras();
        leapid = Objects.requireNonNull(bundle).getParcelable(Constants.LEAP_ID);

        TextView v1 = findViewById(R.id.leaptitle);
        v1.setText(leapid.getLeapName());

        CollapsingToolbarLayout collapsingToolbar =
                findViewById(R.id.collapsingToolbarLayout);
        collapsingToolbar.setTitle(leapid.getLeapName());

        TextView v2 = findViewById(R.id.leapprice);
        v2.setText(leapid.getLeapPrice() + " L.E");

        TextView v3 = findViewById(R.id.description);
        v3.setText(leapid.getLeapDescription());

        TextView v41 = findViewById(R.id.leapuser);
        v41.setText(leapid.getDate());

//        TextView v42 = findViewById(R.id.usernamereview);
//        v42.setText(LeapInfo.getLeapUserColumn(Integer.parseInt(leapid)));
//
//        TextView v5 = findViewById(R.id.numofleaps);
//        v5.setText(LeapInfo.getNumOfLeapsColumn(Integer.parseInt(leapid)));
//
//        ImageView v6 = findViewById(R.id.poster);
//        Picasso.get()
//                .load(LeapInfo.getImageColumn(Integer.parseInt(leapid)))
//                .into(v6);
//
//        ImageView v71 = findViewById(R.id.profile);
//        Picasso.
//                get()
//                .load(LeapInfo.getUserImageColumn(Integer.parseInt(leapid))).placeholder(R.drawable.ic_account_circle_black_48px).transform(new CircleTransform())
//                .into(v71);
//
//        ImageView v72 = findViewById(R.id.profilerev);
//        Picasso.get()
//                .load(LeapInfo.getUserImageColumn(Integer.parseInt(leapid))).placeholder(R.drawable.ic_account_circle_black_48px).transform(new CircleTransform())
//                .into(v72);
//
//        TextView v8 = findViewById(R.id.leapLocation);
//        v8.setText(LeapInfo.getLocationColumn(Integer.parseInt(leapid)));

        // getActionBar().setDisplayHomeAsUpEnabled(true);
        Toolbar myToolbar = findViewById(R.id.toolbar);

    }

    public void viewMap(View view) {
//        Intent i = new Intent(this, Place.class);
//        i.putExtra(Constants.FIREBASE_PROPERTY_Place_Key, leapid);
//        startActivity(i);
    }

    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.menuleap, menu);

    }


    public void addListenerOnRatingBar() {

        ratingBar = findViewById(R.id.ratingBar1);

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
        review.show(fm, getString(R.string.fragment_edit_name));

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