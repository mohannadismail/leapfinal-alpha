package com.leap_app.leap.UI;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v4.app.FragmentManager;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.RatingBar;
import android.widget.TextView;

import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.GenericTypeIndicator;
import com.firebase.client.Query;
import com.firebase.client.ValueEventListener;
import com.leap_app.leap.Models.LeapBaseInfo;
import com.leap_app.leap.Models.Placeview;
import com.leap_app.leap.R;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Map;


public class LeapInfoActivity extends AppCompatActivity implements NewCircleDialog.EditNameDialogListener{

    public String leapid;
    private RatingBar ratingBar;
    private PopupWindow pwindo;
    Button btnClosePopup;
    Button btnSubmit;
    NestedScrollView leap;
    Toolbar toolbar;
    FragmentManager mFragmentManager;

    public static ArrayList<Placeview> placeviews = new ArrayList<>();
    public static ArrayList<Object> objectArrayList = new ArrayList<>();
    Firebase ref2;
    Query query2;
    public static boolean flagigo;
    public static double markersLat[];
    public static double markersLng[];
    int i =0;



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

        final CollapsingToolbarLayout collapsingToolbar =
                (CollapsingToolbarLayout) findViewById(R.id.collapsingToolbarLayout);

        final TextView v1 = (TextView) findViewById(R.id.leaptitle);


        final ImageView mapImage = (ImageView) findViewById(R.id.mapIm);
        final TextView v2 = (TextView) findViewById(R.id.leapprice);

        final TextView v3 = (TextView) findViewById(R.id.description);

        final TextView leapLocation = (TextView) findViewById(R.id.leapLocation);
        final TextView v41 = (TextView) findViewById(R.id.leapuser);

        final TextView v42 = (TextView) findViewById(R.id.usernamereview);
        final TextView v5 = (TextView) findViewById(R.id.numofleaps);

//
//        ImageView v6 = (ImageView) findViewById(R.id.poster);
//        Picasso.with(this.getBaseContext()).load(LeapInfo.getImageColumn(Integer.parseInt(leapid))).into(v6);
//
//        ImageView v71 = (ImageView) findViewById(R.id.profile);
//        Picasso.with(this.getBaseContext()).load(LeapInfo.getUserImageColumn(Integer.parseInt(leapid))).placeholder(R.drawable.ic_account_circle_black_48px).transform(new CircleTransform()).into(v71);
//
//        ImageView v72 = (ImageView) findViewById(R.id.profilerev);
//        Picasso.with(this.getBaseContext()).load(LeapInfo.getUserImageColumn(Integer.parseInt(leapid))).placeholder(R.drawable.ic_account_circle_black_48px).transform(new CircleTransform()).into(v72);

        leapid = this.getIntent().getStringExtra("LeapId");
        Log.e("LeapID ", "" + leapid);
        Firebase ref = new Firebase("https://leapappeg.firebaseio.com/leap/Leap/");
        ref2 = new Firebase("https://leapappeg.firebaseio.com/leap/Places/");
        Query query = ref.orderByChild(leapid);

        query.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                GenericTypeIndicator<Map<String,LeapBaseInfo>> T = new GenericTypeIndicator<Map<String, LeapBaseInfo>>() {};
                Map<String,LeapBaseInfo> leapBaseInfoHashMap = dataSnapshot.getValue(T);

                LeapBaseInfo leapBaseInfo = leapBaseInfoHashMap.get(leapid);
                Log.d("LOGi", leapid);
                Log.d("G Object" , leapBaseInfo.getLeapName().toString());
                collapsingToolbar.setTitle(leapBaseInfo.getLeapName());
                v1.setText(leapBaseInfo.getLeapName());
                v2.setText(leapBaseInfo.getLeapPrice() + " L.E");
                v3.setText(leapBaseInfo.getDate() + "\n" + leapBaseInfo.getLeapDescription());
                leapLocation.setText(leapBaseInfo.getLeapLocation());
//                v41.setText(LeapInfo.getLeapUserColumn(Integer.parseInt(leapid)));
//                v42.setText(LeapInfo.getLeapUserColumn(Integer.parseInt(leapid)));
//                v5.setText(LeapInfo.getNumOfLeapsColumn(Integer.parseInt(leapid)));

            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {

            }
        });
        mapImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                query2 = ref2.orderByChild(leapid).limitToLast(1);
                query2.addValueEventListener(new ValueEventListener() {
                    ArrayList<Map<String, String>> stringArrayList = new ArrayList<Map<String, String>>();
                    Placeview placeview;


                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        Log.e("ObjectAL size", String.valueOf(dataSnapshot.getChildrenCount()));

                        for (DataSnapshot dsp : dataSnapshot.getChildren()) {
                            if (i < dataSnapshot.getChildrenCount() - 1)
                                i++;
                            objectArrayList.add(dsp.getValue(Object.class));

                        }
                        try {
                            for (int b = 0; b < LeapInfoActivity.objectArrayList.size(); b++) {
                                stringArrayList.addAll((ArrayList<Map<String, String>>) LeapInfoActivity.objectArrayList.get(b));
                            }
                            Log.e("Snapshot children Log", stringArrayList.toString());
                            for (int c = 0; c <= LeapInfoActivity.objectArrayList.size(); c++) {
                                if (c < LeapInfoActivity.objectArrayList.size()) {
                                    placeview = new Placeview(stringArrayList.get(c).get("lat"), stringArrayList.get(c).get("lon"), stringArrayList.get(c).get("placeName"), stringArrayList.get(c).get("placeAddress"), stringArrayList.get(c).get("Price"), stringArrayList.get(c).get("phone"), stringArrayList.get(c).get("placeID"));
                                    placeviews.add(c, placeview);
                                } else if (placeviews.size() == dataSnapshot.getChildrenCount()) {
                                    flagigo = true;
                                }
                            }
                        } catch (IndexOutOfBoundsException e) {
                            e.printStackTrace();
                        }
                        markersLat =  new double[LeapInfoActivity.placeviews.size()];
                        markersLng = new double[LeapInfoActivity.placeviews.size()];
                      if(LeapInfoActivity.flagigo) {
                          for (int i = 0; i < LeapInfoActivity.placeviews.size(); i++) {
                              markersLat[i] = Double.parseDouble(LeapInfoActivity.placeviews.get(i).getLat());
                              markersLng[i] = Double.parseDouble(LeapInfoActivity.placeviews.get(i).getLon());

                          }
                      }
                        Log.e("markers points", Arrays.toString(markersLat));


                    }

                    @Override
                    public void onCancelled(FirebaseError firebaseError) {

                    }
                });

//                for (i=0 ; i<=placeviews.size() ; i++)
                    viewMap();

            }
        });



//
        // getActionBar().setDisplayHomeAsUpEnabled(true);
        Toolbar myToolbar = (Toolbar) findViewById(R.id.toolbar);


        //setActionBar(myToolbar);
    }

    public void viewMap() {
//        if (flagigo) {
            Intent i = new Intent(this, Place.class);
            i.putExtra("LeapPlace", leapid);
            startActivity(i);
//        }
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




