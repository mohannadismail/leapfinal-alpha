package com.leap_app.leap.UI;

import android.app.Activity;
import android.content.ContentValues;
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
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import com.google.android.gms.common.GooglePlayServicesRepairableException;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.places.Places;
import com.google.android.gms.location.places.ui.PlacePicker;
import com.leap_app.leap.Adapter.PlacesCreationAdapter;
import com.leap_app.leap.LeapProvider.LeapDbHelper;
import com.leap_app.leap.Models.Placeview;
import com.leap_app.leap.R;

import java.util.ArrayList;
import java.util.Collection;

/**
 * Created by aya on 3/14/16.
 */
public class CreationPlacesFragment extends Fragment implements GoogleApiClient.OnConnectionFailedListener {
    public Placeview placeview;
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";


    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private OnFragmentInteractionListener mListener;
    FloatingActionButton fab;
    //    int PLACE_AUTOCOMPLETE_REQUEST_CODE = 1;
    Collection<com.google.android.gms.location.places.Place> PlacesList = new ArrayList<>();
    public static ArrayList<Placeview> placeviewList = new ArrayList<>();


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

    public RecyclerView placesListView;
    String tag;
    LeapDbHelper mLeapHelper;

    @Override

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
        tag = this.getTag();
        mLeapHelper = new LeapDbHelper(getContext());

        //Assign List items





    }


    GoogleApiClient mGoogleApiClient;

    SharedPreferences sharedPreferences;

    SharedPreferences.Editor e;
    private static final int PLACE_PICKER_REQUEST = 1;
    private TextView placeName, placeNumber, placeAddress;
    public Context context;
    com.google.android.gms.location.places.Place place;

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
//             Inflate the layout for this fragment
         final View view = inflater.inflate(R.layout.fragment_creation_places, container, false);

        placesListView = (RecyclerView) view.findViewById(R.id.creation_places_list);


//        e.clear();
//        sharedPreferences = getContext().getSharedPreferences("SharedPlaces", Context.MODE_PRIVATE);



        // place list item components
        Button addPlace = (Button) view.findViewById(R.id.addPlace);
        placeName = (TextView) view.findViewById(R.id.creation_place_name);
//        placeAddress = (TextView) view.findViewById(R.id.creation_place_address);
//        placeNumber = (TextView) view.findViewById(R.id.placeNumber);

        // Initializing Adapter

        context = this.getActivity();

        // initializing the place picker event


        addPlace.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                try {


                    mGoogleApiClient = new GoogleApiClient
                            .Builder(getContext())
                            .addApi(Places.GEO_DATA_API)
                            .addApi(Places.PLACE_DETECTION_API)
                            .build();

                    PlacePicker.IntentBuilder builder = new PlacePicker.IntentBuilder();

                    try {
                        startActivityForResult(builder.build(getActivity()), PLACE_PICKER_REQUEST);
                    } catch (GooglePlayServicesRepairableException | GooglePlayServicesNotAvailableException e) {
                        e.printStackTrace();
                    }


                } catch (Exception e) {
                    e.printStackTrace();
                    Log.e("ERRooor", "" + e);
                }
                PlacesList.toString();

                LinearLayoutManager llm = new LinearLayoutManager(context);
                placesListView.setLayoutManager(llm);

//                    i++;
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

    private int i = 0 ;
    @Override
    public void onActivityResult(int requestCode,
                                 int resultCode, Intent data) {

        if (requestCode == PLACE_PICKER_REQUEST
                && resultCode == Activity.RESULT_OK) {

            com.google.android.gms.location.places.Place place = PlacePicker.getPlace(this.getActivity(), data);
            if (place != null) {

                //Reading Data from places API

                String name = String.valueOf(place.getName());
                String address = String.valueOf(place.getAddress());
                double lat = place.getLatLng().latitude;
                double lon = place.getLatLng().longitude;
                int price = place.getPriceLevel();
                String phone = (String) place.getPhoneNumber();
                String id = place.getId();
                String Lat = String.valueOf(lat);
                String Lon = String.valueOf(lon);
                String Price = String.valueOf(price);




//                ContentValues contentValues= new ContentValues();
//                contentValues.put("Name",name);
                // Writing to shared preferences



                //Adding data to model

                Placeview placeview = new Placeview(Lat,Lon, name, address, Price, phone, id);
                placeviewList.add(placeview);
                i++;
                placesListView.setAdapter(new PlacesCreationAdapter(context, placeviewList,i));
                ContentValues values = new ContentValues();
                Toast.makeText(getContext(), name + "  " + address + "  Added to places", Toast.LENGTH_SHORT).show();


//                Firebase refname = new Firebase(Constants.FIREBASE_LEAP_PLACES_URL).child(Constants.FIREBASE_PROPERTY_NAME);
//                Firebase refaddress = new Firebase(Constants.FIREBASE_LEAP_PLACES_URL).child(Constants.FIREBASE_PROPERTY_Address);
//                Firebase reflat = new Firebase(Constants.FIREBASE_LEAP_PLACES_URL).child(Constants.FIREBASE_PROPERTY_Latitude);
//                Firebase reflon = new Firebase(Constants.FIREBASE_LEAP_PLACES_URL).child(Constants.FIREBASE_PROPERTY_Longitude);
//                Firebase refid = new Firebase(Constants.FIREBASE_LEAP_PLACES_URL).child(Constants.FIREBASE_PROPERTY_ID);
//                Firebase refprice = new Firebase(Constants.FIREBASE_LEAP_PLACES_URL).child(Constants.FIREBASE_PROPERTY_Price);
//                Firebase refPhone = new Firebase(Constants.FIREBASE_LEAP_PLACES_URL).child(Constants.FIREBASE_PROPERTY_Phone);
//
//                //Writing data to Firebase
//                reflat.push().setValue(lat);
//                reflon.push().setValue(lon);
//                refid.push().setValue(id);
//                refprice.push().setValue(price);
//                refPhone.push().setValue(phone);
//                refname.push().setValue(name);
//                refaddress.push().setValue(address);
//




                String attributions = (String) place.getAttributions();
//                Log.e("Attributionsssssss", attributions);
                if (attributions == null) {
                    attributions = "";
                }
                data.putExtra("Name", place.getName());

            } else {
                Toast.makeText(this.getContext(), " Error loading place data", Toast.LENGTH_LONG).show();
            }
        } else {
            super.onActivityResult(requestCode, resultCode, data);
        }
    }
//
//
//    int RESULT_OK = 1;
//    int RESULT_CANCELED = -1;
//    @Override
//    public void onActivityResult(int requestCode, int resultCode, Intent data) {
//        if (requestCode == PLACE_AUTOCOMPLETE_REQUEST_CODE) {
//            if (resultCode == RESULT_OK) {
//                place = PlaceAutocomplete.getPlace(this.getContext(), data);
//                Log.e("EEEEEEEEEEEEEEEEEE", "Place: " + place.getName());
//            } else if (resultCode == PlaceAutocomplete.RESULT_ERROR) {
//                Status status = PlaceAutocomplete.getStatus(this.getContext(), data);
//                 TODO: Handle the error.
//                Log.i("EEEEEEEEEEEEE", status.getStatusMessage());
//
//            } else if (resultCode == RESULT_CANCELED) {
//                 The user canceled the operation.
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
                    + " must implement OnFragmentInteractionListener");
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
