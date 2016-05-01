package com.leap_app.leap.UI;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.UiSettings;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.leap_app.leap.Models.LeapMarkerInfo;
import com.leap_app.leap.R;
import com.leap_app.leap.Utility.LeapLatLon;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link DiscoverMapFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link DiscoverMapFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class DiscoverMapFragment extends SupportMapFragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    public GoogleMap myMap;
    private LayoutInflater inflatr;
//    private String[] titles = new String[] {"Shawerma", "Pizza", "Burger"};
//    private String[] Snippet = new String[]{"Shawerma","Pizza","Burger"};

    private LatLng latLon;



    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public DiscoverMapFragment() {
        super();
        // Required empty public constructor
    }


    // TODO: Rename and change types and number of parameters
    public static DiscoverMapFragment newInstance(LatLng position) {
        DiscoverMapFragment fragment = new DiscoverMapFragment();
        fragment.latLon = position;
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View v = super.onCreateView(inflater, container, savedInstanceState);
        this.inflatr = inflater;
        try {
            initMap();
        }catch (Exception e){
            Log.e("Map Exception", ""+e);
            Toast.makeText(this.getContext(),"Error Loading map", Toast.LENGTH_LONG);
        }
//        FloatingActionButton fab  = (FloatingActionButton) v.findViewById(R.id.fab);
//        fab.setVisibility(View.INVISIBLE);
        return v;
    }


    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    private void initMap() {

        // Get the button view
        // and next place it, for exemple, on bottom right (as Google Maps app)

        // position on right bottom

        UiSettings settings = getMap().getUiSettings();
        settings.setZoomControlsEnabled(true);
        settings.setAllGesturesEnabled(true);
        settings.setMyLocationButtonEnabled(true);
        getMap().moveCamera(CameraUpdateFactory.newLatLngZoom(latLon, 13));
        getMap().addMarker(new MarkerOptions().position(latLon));
        double lats [] = LeapLatLon.getLeapLat();
        double lngs [] = LeapLatLon.getLeapLon();

        addLeaps(lats, lngs);


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

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }


    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }

    public int leapIdddd;
    View v;
    double lat, lon;

    public void addLeaps(double Lat[], double Lng[]) {

         /*
            Path


        for (int i = 0; i < Lat.length - 1; i++) {
            LatLng pLatLng = new LatLng(Lat[i], Lng[i]);
            LatLng latLng = new LatLng(Lat[i + 1], Lng[i + 1]);
            getMap().addPolygon(new PolygonOptions().add(latLng).add(pLatLng).strokeColor(Color.rgb(183, 28, 28)).strokeWidth(15));
        }
         /*
            Markers
         */
        for (int i = 0; i < Lng.length; i++) {
            final LatLng latLng = new LatLng(Lat[i], Lng[i]);
            // String MarkerNumber = Integer.toString(i);
            Log.d("MarkerLatLng ", "" + latLng);
            final MarkerOptions mOptions = new MarkerOptions().position(latLng).icon(BitmapDescriptorFactory.defaultMarker(0));/*.title(titles[i]).snippet(Snippet[i]+" "+Lat[i]+","+Lng[i]);*/
            getMap().addMarker(mOptions);

            Log.d("LngLength", "" + Lng.length + Lat.length);

         /*
        TO set Camera Position at first Marker in the Path
         */


            LatLng Target = new LatLng(Lat[0], Lng[0]);
            CameraPosition cp = new CameraPosition.Builder().
                    target(Target)
                    .zoom(13)
                    .build();
            getMap().moveCamera(CameraUpdateFactory.newCameraPosition(cp));
            getMap().animateCamera(CameraUpdateFactory.newCameraPosition(cp), 1000, null);
            getMap().setOnMarkerClickListener(new GoogleMap.OnMarkerClickListener() {
                                                  @Override
                                                  public boolean onMarkerClick(Marker marker) {
                                                      getMap().setInfoWindowAdapter(new GoogleMap.InfoWindowAdapter() {
                                                          @Override
                                                          public View getInfoContents(Marker marker) {



                                                              return null;
                                                          }

                                                          @Override
                                                          public View getInfoWindow(Marker marker) {
                                                              v = inflatr.inflate(R.layout.infowindow_card, null);

                                                              TextView title = (TextView) v.findViewById(R.id.LeapNameIW);
                                                              TextView price = (TextView) v.findViewById(R.id.LeapPriceIW);
                                                              TextView creator = (TextView) v.findViewById(R.id.LeapCreatorIW);

                                                              LatLng place = marker.getPosition();
                                                              lat = place.latitude; lon =place.longitude;


                                                              LeapMarkerInfo leapMarkerInfo = new LeapMarkerInfo(lat,lon);
                                                              leapIdddd = leapMarkerInfo.getLeapID();
                                                              Log.v("LeapIDMap", "" + leapIdddd);
                                                              String n = leapMarkerInfo.getLeapNameColumn();
                                                              String c = leapMarkerInfo.getLeapUserColumn();
                                                              String p  = leapMarkerInfo.getLeapPriceColumn();

                                                              Log.e("nnnnnnn", n + "");
                                                              Log.e("nnnnnnnnnn", p + "");
                                                              Log.e("nnnnnnn", c + "");



                                                                  price.setText(String.valueOf(p));
                                                                  title.setText(String.valueOf(n));
                                                                  creator.setText(String.valueOf(c));
//
                                                              return v;
                                                          }
                                                      });
                                                      //  Take some action here
                                                      marker.showInfoWindow();
                                                      getMap().setOnInfoWindowClickListener(new GoogleMap.OnInfoWindowClickListener() {

                                                          @Override
                                                          public void onInfoWindowClick(Marker marker) {
                                                              LeapMarkerInfo leapMarkerInfo = new LeapMarkerInfo(lat,lon);
                                                              leapIdddd = leapMarkerInfo.getLeapID();
                                                              Log.v("LeapIDMap", "" + leapIdddd);
                                                              Intent i = new Intent(v.getContext(), LeapInfoActivity.class);
                                                              i.putExtra("LeapId", ""+leapIdddd);
                                                              Log.d("EXTRA","" + ""+leapIdddd);
                                                              v.getContext().startActivity(i);




                                                          }
                                                      });
                                                      return true;
                                                  }
                                              }
            );
//
//            getMap().setOnMarkerClickListener(new GoogleMap.OnMarkerClickListener() {
//                                                  @Override
//                                                  public boolean onMarkerClick(Marker marker) {
//                                                      //  Take some action here
//                                                      marker.showInfoWindow();
//                                                      getMap().setOnInfoWindowClickListener(new GoogleMap.OnInfoWindowClickListener() {
//
//                                                          @Override
//                                                          public void onInfoWindowClick(Marker marker) {
//
//
//                                                          }
//                                                      });
//                                                      return true;
//                                                  }
//
//
//                                              }
//            );
//



        }



    }
}
