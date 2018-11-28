package com.leap_app.leap.UI;

import android.Manifest;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.widget.TextView;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.PolygonOptions;
import com.leap_app.leap.Models.Placeview;
import com.leap_app.leap.R;
import com.sothree.slidinguppanel.SlidingUpPanelLayout;

import static com.sothree.slidinguppanel.SlidingUpPanelLayout.PanelState.ANCHORED;


public class Place extends FragmentActivity implements OnMapReadyCallback {
    private GoogleMap mMap;
    private SlidingUpPanelLayout slidingUpPanelLayout;
    private String leapid;
    private MapView mapView;

    public void onCreate(Bundle savedInstanceState) {
//        leapid = getIntent().getStringExtra(getString(R.string.leapplace));
//        Log.d(getString(R.string.place_category), leapid);
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.place_view);

    }


    @Override
    public void onResume() {
        super.onResume();
        setUpMap();
    }

//
//    private void setUpMapIfNeeded() {
//        // Do a null check to confirm that we have not already instantiated the map.
//        if (mMap == null) {
//            // Try to obtain the map from the SupportMapFragment.
//            mMap =  getSupportFragmentManager().findFragmentById(R.id.leap_map);
//            // Check if we were successful in obtaining the map.
//            if (mMap != null)
//            {
//                setUpMap();
//            }
//        }
//    }


    private void setUpMap() {
        // Hide Panel by Clicking on Map
//        mMap.setOnMapClickListener(new GoogleMap.OnMapClickListener() {
//
//            @Override
//            public void onMapClick(LatLng point) {
//
//                if (slidingUpPanelLayout
//                        .getPanelState()
//                        .equals(EXPANDED)
//                        | slidingUpPanelLayout
//                        .getPanelState()
//                        .equals(ANCHORED)) {
//
//                    slidingUpPanelLayout.setPanelState(HIDDEN);
//                }
//            }
//        });

        /*
         *Latitude and Longitude Arrays
         */
//        double markersLat[] = Placeview.getLat(Integer.parseInt(leapid));
//        double markersLng[] = Placeview.getLon(Integer.parseInt(leapid));


        //Draw Fake Markers with Path
//        addMarker(markersLat, markersLng);




        /*
         Current Location & Zoom in/out Buttons
         */

        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED
                && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION)
                != PackageManager.PERMISSION_GRANTED) {
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
        mMap.getUiSettings().setZoomControlsEnabled(true);


    }


    /*
    Function for Adding Markers & Paths to Map
     */
    public void addMarker(double Lat[], double Lng[]) {

         /*
            Path
         */

        for (int i = 0; i < Lat.length - 1; i++) {
            LatLng pLatLng = new LatLng(Lat[i], Lng[i]);
            LatLng latLng = new LatLng(Lat[i + 1], Lng[i + 1]);
            mMap.addPolygon(new PolygonOptions().add(latLng).add(pLatLng).strokeColor(Color.rgb(183, 28, 28)).strokeWidth(15));
        }
         /*
            Markers
         */
        for (int i = 0; i < Lng.length; i++) {
            final LatLng latLng = new LatLng(Lat[i], Lng[i]);
            String MarkerNumber = Integer.toString(i);
            MarkerOptions mOptions = new MarkerOptions().position(latLng).icon(BitmapDescriptorFactory.defaultMarker(0));
            mMap.addMarker(mOptions);




         /*
        TO set Camera Position at first Marker in the Path
         */


            LatLng Target = new LatLng(Lat[0], Lng[0]);
            CameraPosition cp = new CameraPosition.Builder().
                    target(Target)
                    .zoom(15)
                    .build();
            mMap.moveCamera(CameraUpdateFactory.newCameraPosition(cp));
            mMap.animateCamera(CameraUpdateFactory.newCameraPosition(cp), 1000, null);

            mMap
                    .setOnMarkerClickListener(
                            new GoogleMap.OnMarkerClickListener() {
                                @Override
                                public boolean onMarkerClick(Marker marker) {
                                    //  Take some action here
                                    Log.d("Marker", "" + marker.getId());

                                    LatLng place = marker.getPosition();
                                    TextView p1 = findViewById(R.id.placename);
                                    p1.setText(Placeview.getPlaceNameColumn(place.latitude, place.longitude));

                                    TextView p2 = findViewById(R.id.placecategory);
                                    p2.setText(Placeview.getPlaceCatColumn(place.latitude, place.longitude));

                                    TextView p3 = findViewById(R.id.placeaddress);
                                    p3.setText(Placeview.getPlaceAddColumn(place.latitude, place.longitude));

                                    TextView p4 = findViewById(R.id.placedesc);
                                    p4.setText(Placeview.getPlaceDescColumn(place.latitude, place.longitude));

                                    slidingUpPanelLayout = findViewById(R.id.sliding_layout);
                                    slidingUpPanelLayout.setPanelState(ANCHORED);
                                    return true;
                                }

                            }
                    );
        }


    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        setUpMap();
    }
}
