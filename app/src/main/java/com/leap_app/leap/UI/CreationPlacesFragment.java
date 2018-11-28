package com.leap_app.leap.UI;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.client.Firebase;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.places.Places;
import com.google.android.gms.location.places.ui.PlacePicker;
import com.leap_app.leap.Adapter.PlacesCreationAdapter;
import com.leap_app.leap.LeapProvider.LeapDbHelper;
import com.leap_app.leap.Models.Placeview;
import com.leap_app.leap.R;
import com.leap_app.leap.Utility.Constants;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Objects;


public class CreationPlacesFragment extends Fragment implements GoogleApiClient.OnConnectionFailedListener {
    private Placeview placeview;
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private static final int PLACE_PICKER_REQUEST = 1;
    private static CreationPlacesFragment instance;

    private OnFragmentInteractionListener mListener;
    private FloatingActionButton fab;
    private int PLACE_AUTOCOMPLETE_REQUEST_CODE = 1;
    private Collection<Place> PlacesList = new ArrayList<>();
    private Context context;
    private com.google.android.gms.location.places.Place place;

    public Placeview getPlaceview() {
        return placeview;
    }

    private ArrayList<Placeview> placeviewList = new ArrayList<>();
    private RecyclerView placesListView;
    private GoogleApiClient mGoogleApiClient;
    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor e;
    private TextView placeNumber;
    private TextView placeAddress;
    private int i = 0;


    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */


    public CreationPlacesFragment() {
        // Required empty public constructor

    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment DiscoverLeapsFragment.
     */
//     TODO: Rename and change types and number of parameters
    public static CreationPlacesFragment newInstance(String param1, String param2) {
        CreationPlacesFragment fragment = new CreationPlacesFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    public static CreationPlacesFragment getInstance() {
        if (instance == null) {
            instance = new CreationPlacesFragment();
        }
        return instance;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            // TODO: Rename and change types of parameters
            String mParam1 = getArguments().getString(ARG_PARAM1);
            String mParam2 = getArguments().getString(ARG_PARAM2);
        }
        String tag = this.getTag();
        LeapDbHelper mLeapHelper = new LeapDbHelper(getContext());

        //Assign List items


    }

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
//             Inflate the layout for this fragment
        final View view = inflater.inflate(R.layout.fragment_creation_places, container, false);

        placesListView = view.findViewById(R.id.creation_places_list);

        // place list item components
        FloatingActionButton addPlace = view.findViewById(R.id.addPlace);
        TextView placeName = view.findViewById(R.id.creation_place_name);
//        placeAddress = (TextView) view.findViewById(R.id.creation_place_address);
//        placeNumber = /(TextView) view.findViewById(R.id.placeNumber);

        // Initializing Adapter
        context = this.getActivity();

        // initializing the place picker event
        addPlace.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                try {


                    mGoogleApiClient = new GoogleApiClient
                            .Builder(Objects.requireNonNull(getContext()))
                            .addApi(Places.GEO_DATA_API)
                            .addApi(Places.PLACE_DETECTION_API)
                            .addApi(Places.PLACE_DETECTION_API)
                            .build();

                    PlacePicker.IntentBuilder builder = new PlacePicker.IntentBuilder();

                    try {
                        startActivityForResult(builder.build(Objects.requireNonNull(getActivity())), PLACE_PICKER_REQUEST);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }


                } catch (Exception e) {
                    e.printStackTrace();
                }
                Log.e("PlASSCCCEEEDS", PlacesList.toString());

                LinearLayoutManager llm = new LinearLayoutManager(context);
                placesListView.setLayoutManager(llm);
            }

        });
        return view;
    }

//    @Override
//    public void onStart() {
//        super.onStart();
//        mGoogleApiClient.connect();
//    }
//
//    @Override
//    public void onStop() {
//        super.onStop();
//        mGoogleApiClient.disconnect();
//    }

    @Override
    public void onActivityResult(int requestCode,
                                 int resultCode, Intent data) {

        if (requestCode == PLACE_PICKER_REQUEST
                && resultCode == Activity.RESULT_OK) {

            com.google.android.gms.location.places.Place place = PlacePicker.getPlace(Objects.requireNonNull(this.getActivity()), data);
            if (place != null) {

                //Reading Data from places API

                String name = String.valueOf(place.getName());
                String address = String.valueOf(place.getAddress());
                double lat = place.getLatLng().latitude;
                double lon = place.getLatLng().longitude;
                int price = place.getPriceLevel();
                String phone = (String) place.getPhoneNumber();
                String id = place.getId();

                //Adding data to model
//                SQLiteDatabase db = new LeapDbHelper(getContext()).getWritableDatabase();
                Placeview placeview = new Placeview(lat, lon, name, address, price, phone, id);
                placeviewList.add(placeview);
                i++;
                placesListView.setAdapter(new PlacesCreationAdapter(context, placeviewList, i));

//                Toast.makeText(getContext(), name + "  " + address + "  Added to places", Toast.LENGTH_SHORT).show();
//                ContentValues values = new ContentValues();
//                values.put(LeapContract.PlaceEntry.COLUMN_Name, name);
//                values.put(LeapContract.PlaceEntry.COLUMN_Address, address);
//                values.put(LeapContract.PlaceEntry.COLUMN_Latitude, lat);
////                values.put(LeapContract.PlaceEntry.COLUMN_Category_Name, );
//                values.put(LeapContract.PlaceEntry.COLUMN_Longitude, lon);
////                db.insert(LeapContract.PlaceEntry.Table_Name, null, values);

//                new Handler().postDelayed(new Runnable() {
//
//                    @Override
//                    public void run() {
//                        ContentValues values1 = new ContentValues();
//                        SQLiteDatabase db = new LeapDbHelper(getContext()).getWritableDatabase();
//
//                        Cursor c = db.rawQuery("SELECT place_id FROM Leap_Place ORDER BY place_id DESC LIMIT 1", null);
//                        if (c != null) {
//                            c.moveToFirst();
//                        }
//                        String s = (Objects.requireNonNull(c).getString(0));
//                        Log.d("PLACEID", s);
//                        Integer i = Integer.parseInt(s);
//
//                        c.close();
//                        values1.put(LeapContract.Leap_Place_Entry.COLUMN_Leap_Key, CreationInfoFragment.leapBaseInfooo.getLeapID());
//                        values1.put(LeapContract.Leap_Place_Entry.COLUMN_Place_Key, i + 1);
//
//                        db.insert(LeapContract.Leap_Place_Entry.Table_Name, null, values1);
//                    }
//                }, 500);


                Firebase refname = new Firebase(Constants.FIREBASE_LEAP_PLACES_URL).child(Constants.FIREBASE_PROPERTY_NAME);
                Firebase refaddress = new Firebase(Constants.FIREBASE_LEAP_PLACES_URL).child(Constants.FIREBASE_PROPERTY_Address);
                Firebase reflat = new Firebase(Constants.FIREBASE_LEAP_PLACES_URL).child(Constants.FIREBASE_PROPERTY_Latitude);
                Firebase reflon = new Firebase(Constants.FIREBASE_LEAP_PLACES_URL).child(Constants.FIREBASE_PROPERTY_Longitude);
                Firebase refid = new Firebase(Constants.FIREBASE_LEAP_PLACES_URL).child(Constants.FIREBASE_PROPERTY_ID);
                Firebase refprice = new Firebase(Constants.FIREBASE_LEAP_PLACES_URL).child(Constants.FIREBASE_PROPERTY_Price);
                Firebase refPhone = new Firebase(Constants.FIREBASE_LEAP_PLACES_URL).child(Constants.FIREBASE_PROPERTY_Phone);

                //Writing data to Firebase
                reflat.push().setValue(lat);
                reflon.push().setValue(lon);
                refid.push().setValue(id);
                refprice.push().setValue(price);
                refPhone.push().setValue(phone);
                refname.push().setValue(name);
                refaddress.push().setValue(address);



                String attributions = (String) place.getAttributions();

                data.putExtra("Name", place.getName());

            } else {
                Toast.makeText(this.getContext(), R.string.error_loading_place_data, Toast.LENGTH_LONG).show();
            }
        } else {
            super.onActivityResult(requestCode, resultCode, data);
        }
    }

//
//    int RESULT_OK = 1;
//    int RESULT_CANCELED = -1;
//    @Override
//    public void onActivityResult(int requestCode, int resultCode, Intent data) {
//        if (requestCode == PLACE_AUTOCOMPLETE_REQUEST_CODE) {
//            if (resultCode == RESULT_OK) {
//                place = PlaceAutocomplete.getPlace(getContext(), data);
//                Log.e("EEEEEEEEEEEEEEEEEE", "Place: " + place.getName());
//            } else if (resultCode == PlaceAutocomplete.RESULT_ERROR) {
//                Status status = PlaceAutocomplete.getStatus(this.getContext(), data);
////                 TODO: Handle the error.
//                Log.i("EEEEEEEEEEEEE", status.getStatusMessage());
//
//            } else if (resultCode == RESULT_CANCELED) {
////                 The user canceled the operation.
//            }
//        }
//    }


    // TODO: Rename method, update argument and hook method into UI event

    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + getString(R.string.must_implement_onfraginter));
        }
    }

    //
    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {
        Toast.makeText(this.getContext(), "Error loading GOOGLE client, Please check connection", Toast.LENGTH_LONG).show();

    }

    public ArrayList<Placeview> getPlaceviewList() {
        return placeviewList;
    }


//    @Override
//    public void onStart() {
//        super.onStart();

    // ATTENTION: This was auto-generated to implement the App Indexing API.
    // See https://g.co/AppIndexing/AndroidStudio for more information.
//        client.connect();
//        Action viewAction = Action.newAction(
//                Action.TYPE_VIEW, // TODO: choose an action type.
//                "CreationPlacesFragment Page", // TODO: Define a title for the content shown.
//                 TODO: If you have web page content that matches this app activity's content,
//                 make sure this auto-generated web page URL is correct.
    // Otherwise, set the URL to null.
//                Uri.parse("http://host/path"),
    // TODO: Make sure this auto-generated app deep link URI is correct.
//                Uri.parse("android-app://com.leap_app.leap.UI/http/host/path")
//        );
//        AppIndex.AppIndexApi.start(client, viewAction);
//    }

//    @Override
//    public void onStop() {
//        super.onStop();
//
//         ATTENTION: This was auto-generated to implement the App Indexing API.
//         See https://g.co/AppIndexing/AndroidStudio for more information.
//        Action viewAction = Action.newAction(
//                Action.TYPE_VIEW, // TODO: choose an action type.
//                "CreationPlacesFragment Page", // TODO: Define a title for the content shown.
//                 TODO: If you have web page content that matches this app activity's content,
//                 make sure this auto-generated web page URL is correct.
//                 Otherwise, set the URL to null.
//                Uri.parse("http://host/path"),
//                 TODO: Make sure this auto-generated app deep link URI is correct.
//                Uri.parse("android-app://com.leap_app.leap.UI/http/host/path")
//        );
//        AppIndex.AppIndexApi.end(client, viewAction);
//        client.disconnect();
//    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p/>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }


}
