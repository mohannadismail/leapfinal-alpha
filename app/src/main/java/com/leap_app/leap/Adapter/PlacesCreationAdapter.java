package com.leap_app.leap.Adapter;

import android.content.Context;
import android.support.annotation.NonNull;
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

public class PlacesCreationAdapter extends RecyclerView.Adapter<PlacesCreationAdapter.PlaceViewHolder> {
    private ArrayList<Placeview> places = new ArrayList<>();
    private Context c;
    private static int ii;
    public PlacesCreationAdapter(Context c, ArrayList<Placeview> places, int i){

        this.c = c;
        this.places = places;
        this.ii = i;
    }

    static class PlaceViewHolder extends RecyclerView.ViewHolder{

        TextView placeName;
        Context context;
        View view;

        PlaceViewHolder(View itemView){
            super(itemView);
            view =itemView;
            placeName = (TextView) view.findViewById(R.id.creation_place_name);
        }
    }


    @NonNull
    @Override
    public PlaceViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.creation_place_item, parent, false);
        return new PlaceViewHolder(v);
    }

    private void getPlaces(int ii){
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
    public void onBindViewHolder(@NonNull PlaceViewHolder holder, int position) {
        getPlaces(ii);
        int i = 0;
        if(i < places.size()-1)
            position+= i;Placeview placeview = places.get(position);
        holder.placeName.setText(String.valueOf(placeview.placeName));
    }

    @Override
    public void onAttachedToRecyclerView(@NonNull RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }


    @Override
    public int getItemCount() {
        if(places != null){
            return places.size();}
        else {return 0;}
    }
}
