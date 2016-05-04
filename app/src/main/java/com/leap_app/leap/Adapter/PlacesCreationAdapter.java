package com.leap_app.leap.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.Query;
import com.firebase.client.ValueEventListener;
import com.leap_app.leap.Models.Placeview;
import com.leap_app.leap.R;
import com.leap_app.leap.Utility.Constants;

import java.util.ArrayList;

/**
 * Created by RamyFRadwan on 11/04/2016.
 */
public class PlacesCreationAdapter extends RecyclerView.Adapter<PlacesCreationAdapter.PlaceViewHolder> {
    public ArrayList<Placeview> places = new ArrayList<>();
    Context c;
//    SharedPreferences sharedPreferences;
//    SharedPreferences.Editor editor;
    public static int ii;
    public PlacesCreationAdapter(Context c, ArrayList<Placeview> places, int i){

        this.c = c;
        this.places = places;
        this.ii = i;
//        sharedPreferences = c.getSharedPreferences("SharedPlaces",Context.MODE_PRIVATE);
//        editor = sharedPreferences.edit();

    }
    public static class PlaceViewHolder extends RecyclerView.ViewHolder{

        TextView placeName;
        Context context;
        View view;

        public PlaceViewHolder(View itemView){
            super(itemView);
            view =itemView;
            placeName = (TextView) view.findViewById(R.id.creation_place_name);
//            placeAddress = (TextView) view.findViewById(R.id.creation_place_address);
//            placeNumber = (TextView) view.findViewById(R.id.placeNumber);




        }
    }
    @Override
    public PlaceViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
//        Intent i = new Intent(c, PlacePickerFragment.class);
//        c.startActivity(i);
//        Log.e("hhhhhhhhhhhhhhhhhhhhhh", "hhhhhhhhhhhhhhhhhhhhhhhhhhhhhhh");
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.creation_place_item, parent, false);
        PlaceViewHolder placeViewHolder = new PlaceViewHolder(v);
        return placeViewHolder;
    }
    int i=0;
    public void getPlaces(int ii){

        Firebase placeRef = new Firebase(Constants.FIREBASE_LEAP_PLACES_URL);
        Query query = placeRef.startAt(ii);
        query.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot placesSnapShot : dataSnapshot.getChildren()){
                    Placeview placeview = placesSnapShot.getValue(Placeview.class);
                    places.add(placeview);
                }
            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {

            }
        });


    }

    @Override
    public void onBindViewHolder(PlaceViewHolder holder, int position) {
//        for(int i = 0; i < places.size() ; i++) {
        getPlaces(ii);
//            holder.placeNumber.setText(String.valueOf(i + 1));
            if(i< places.size()-1)
            position+=i;
//            String [] strings = new String[places.size()];

//            strings[i] = sharedPreferences.getString("key",null);

//            Log.e("Stakabakaaka" , strings[i]);

//        Placeview placeview= places.get(position);
        Placeview placeview = places.get(position);
//        Toast.makeText(c,position+"",Toast.LENGTH_LONG).show();
        holder.placeName.setText(String.valueOf(placeview.placeName));
//        holder.placeAddress.setText(String.valueOf(placeview.placeAddress));


//        }



    }
    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }


    @Override
    public int getItemCount() {
        if(places != null){
            return places.size();}
        else {return 0;}
    }
}
