package com.leap_app.leap.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.leap_app.leap.R;
import com.leap_app.leap.models.Placeview;
import com.leap_app.leap.utility.Constants;

import java.util.ArrayList;

public class PlacesCreationAdapter extends RecyclerView.Adapter<PlacesCreationAdapter.PlaceViewHolder> {
    private ArrayList<Placeview> places;
    private static int ii;
    private Context c1;

    public PlacesCreationAdapter(Context c, ArrayList<Placeview> places, int i) {

        this.c1 = c;
        this.places = places;
        ii = i;
    }

    @Override
    public void onBindViewHolder(@NonNull PlaceViewHolder holder, int position) {
        getPlaces(ii);
        int i = 0;
        if (i < places.size() - 1)
            position += i;
        Placeview placeview = places.get(position);
        holder.placeName.setText(String.valueOf(placeview.getPlaceName()));
    }


    @NonNull
    @Override
    public PlaceViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.creation_place_item, parent, false);
        return new PlaceViewHolder(v);
    }

    private void getPlaces(int ii) {
        DatabaseReference placeRef = FirebaseDatabase.getInstance().getReference(Constants.FIREBASE_LEAP_PLACES_URL);
        com.google.firebase.database.Query query = placeRef.startAt(ii);
        query.addValueEventListener(new com.google.firebase.database.ValueEventListener() {
            @Override
            public void onDataChange(@NonNull com.google.firebase.database.DataSnapshot dataSnapshot) {
                for (com.google.firebase.database.DataSnapshot placesSnapShot : dataSnapshot.getChildren()) {
                    Placeview placeview = placesSnapShot.getValue(Placeview.class);
                    places.add(placeview);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                databaseError.toException().printStackTrace();
            }
        });


    }

    static class PlaceViewHolder extends RecyclerView.ViewHolder {

        TextView placeName;
        Context context;
        View view;

        PlaceViewHolder(View itemView) {
            super(itemView);
            view = itemView;
            placeName = view.findViewById(R.id.creation_place_name);
        }
    }

    @Override
    public void onAttachedToRecyclerView(@NonNull RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }


    @Override
    public int getItemCount() {
        if (places != null) {
            return places.size();
        } else {
            return 0;
        }
    }
}
