package com.leap_app.leap.ui.discover;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.MapsInitializer;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.UiSettings;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.leap_app.leap.R;
import com.leap_app.leap.firebase.FireDatabase;
import com.leap_app.leap.firebase.OnLeapsRetrieved;
import com.leap_app.leap.models.LeapBaseInfo;
import com.leap_app.leap.models.LeapMarkerInfo;

import java.util.ArrayList;
import java.util.Objects;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link DiscoverMapFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link DiscoverMapFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class DiscoverMapFragment extends Fragment
        implements OnMapReadyCallback, OnLeapsRetrieved {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private LayoutInflater inflatr;
//    private String[] titles = new String[] {"Shawerma", "Pizza", "Burger"};
//    private String[] Snippet = new String[]{"Shawerma","Pizza","Burger"};

    private LatLng latLon;
    private MapView mMapView;


    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;
    private GoogleMap mMap;

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
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_map, container, false);
        this.inflatr = inflater;

//        try {
//            initMap();
//        } catch (Exception e) {
//            e.printStackTrace();
//            Toast.makeText(this.getContext(), R.string.error_loading_map, Toast.LENGTH_LONG).show();
//        }
        mMapView = v.findViewById(R.id.location_map);
        mMapView.onCreate(savedInstanceState);
        mMapView.onResume();
        mMapView.getMapAsync(this);
        try {
            MapsInitializer.initialize(getActivity().getApplicationContext());
        } catch (Exception e) {
            e.printStackTrace();
        }

        //        FloatingActionButton fab = (FloatingActionButton) v.findViewById(R.id.fab);
//        fab.setVisibility(View.INVISIBLE);
        return v;
    }
//
//
//    // TODO: Rename method, update argument and hook method into UI event
//    public void onButtonPressed(Uri uri) {
//        if (mListener != null) {
//            mListener.onFragmentInteraction(uri);
//        }
//    }

    private void initMap() {

        // Get the button view
        // and next place it, for example, on bottom right (as Google Maps app)

        // position on right bottom

        UiSettings settings = mMap.getUiSettings();
        settings.setZoomControlsEnabled(true);
        settings.setAllGesturesEnabled(true);
        settings.setMyLocationButtonEnabled(true);
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(latLon, 13));
        mMap.addMarker(new MarkerOptions().position(latLon).visible(false));

        FireDatabase fireDatabase = new FireDatabase(this);
        fireDatabase.getMarkers();


    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + getString(R.string.must_implment_onfragint));
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    @Override
    public void dataRetrieved(ArrayList<LeapBaseInfo> leaps) {

    }

    @Override
    public void markersRetrieved(double[] latts, double[] lonns, String[] markerIDs) {
        addLeaps(latts, lonns);

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
            final MarkerOptions mOptions = new MarkerOptions().position(latLng).icon(BitmapDescriptorFactory.defaultMarker(0));/*.title(titles[i]).snippet(Snippet[i]+" "+Lat[i]+","+Lng[i]);*/
            mMap.addMarker(mOptions);

         /*
        TO set Camera Position at first Marker in the Path
         */


            LatLng Target = new LatLng(Lat[0], Lng[0]);
            CameraPosition cp = new CameraPosition.Builder().
                    target(Target)
                    .zoom(13)
                    .build();
            mMap.moveCamera(CameraUpdateFactory.newCameraPosition(cp));
            mMap.animateCamera(CameraUpdateFactory.newCameraPosition(cp), 1000, null);
            mMap
                    .setOnMarkerClickListener(
                            new GoogleMap
                                    .OnMarkerClickListener() {
                                @Override
                                public boolean onMarkerClick(Marker marker) {
                                    mMap.setInfoWindowAdapter(new GoogleMap.InfoWindowAdapter() {
                                        @Override
                                        public View getInfoContents(Marker marker) {


                                            return null;
                                        }

                                        @Override
                                        public View getInfoWindow(Marker marker) {
                                            v = inflatr.inflate(R.layout.infowindow_card, null);

                                            TextView title = v.findViewById(R.id.LeapNameIW);
                                            TextView price = v.findViewById(R.id.LeapPriceIW);
                                            TextView creator = v.findViewById(R.id.LeapCreatorIW);

                                            LatLng place = marker.getPosition();
                                            lat = place.latitude;
                                            lon = place.longitude;


                                            LeapMarkerInfo leapMarkerInfo = new LeapMarkerInfo(lat, lon);
                                            leapIdddd = leapMarkerInfo.getLeapID();
                                            String n = leapMarkerInfo.getLeapNameColumn();
                                            String c = leapMarkerInfo.getLeapUserColumn();
                                            String p = leapMarkerInfo.getLeapPriceColumn();

                                            price.setText(String.valueOf(p));
                                            title.setText(String.valueOf(n));
                                            creator.setText(String.valueOf(c));
//
                                            return v;
                                        }
                                    });
                                    //  Take some action here
                                    marker.showInfoWindow();
                                    mMap.setOnInfoWindowClickListener(new GoogleMap.OnInfoWindowClickListener() {

                                        @Override
                                        public void onInfoWindowClick(Marker marker) {
                                            LeapMarkerInfo leapMarkerInfo = new LeapMarkerInfo(lat, lon);
                                            leapIdddd = leapMarkerInfo.getLeapID();
                                            Intent i = new Intent(v.getContext(), LeapInfoActivity.class);
                                            i.putExtra("LeapId", "" + leapIdddd);
                                            v.getContext().startActivity(i);


                                        }
                                    });
                                    return true;
                                }
                            }
                    );
//
            mMap.setOnMarkerClickListener(new GoogleMap.OnMarkerClickListener() {
                                              @Override
                                              public boolean onMarkerClick(Marker marker) {
                                                  //  Take some action here
                                                  marker.showInfoWindow();
                                                  mMap.setOnInfoWindowClickListener(new GoogleMap.OnInfoWindowClickListener() {

                                                      @Override
                                                      public void onInfoWindowClick(Marker marker) {


                                                      }
                                                  });
                                                  return true;
                                              }


                                          }
            );

        }
    }

    private void setUpMapIfNeeded() {
        if (mMap == null) {
            SupportMapFragment mapFrag
                    =
                    (SupportMapFragment) getChildFragmentManager()
                            .findFragmentById(R.id.location_map);
            Objects.requireNonNull(mapFrag).getMapAsync(this);
        }
        if (ActivityCompat.checkSelfPermission(getContext(), Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(getContext(), Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        mMap.setMyLocationEnabled(true);

    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        setUpMapIfNeeded();
        initMap();
    }


}
