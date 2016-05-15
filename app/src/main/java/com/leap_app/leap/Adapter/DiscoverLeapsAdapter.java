package com.leap_app.leap.Adapter;

/**
 * Created by Psychalafy on 1/3/2016.
 */

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.leap_app.leap.Models.LeapBaseInfo;
import com.leap_app.leap.R;
import com.leap_app.leap.UI.LeapInfoActivity;

import java.util.List;

//import com.firebase.client.Firebase;



public class DiscoverLeapsAdapter extends RecyclerView.Adapter<DiscoverLeapsAdapter.LeapViewHolder> {
    public static List<LeapBaseInfo> leaps;
    public static Context c;

    public DiscoverLeapsAdapter(Context context, List<LeapBaseInfo> leaps) {
        this.c = context;
        this.leaps =  leaps;
    }


    public static class LeapViewHolder extends RecyclerView.ViewHolder  {
        CardView cv;
        public View view;
        TextView LeapName;
        TextView LeapCreator;
        TextView LeapPrice;
        ImageView LeapPhoto;
        TextView LeapId;
        private Context context;


        public LeapViewHolder(View itemView) {
            super(itemView);
            view = itemView;
            cv = (CardView) itemView.findViewById(R.id.cv);
            LeapName = (TextView) itemView.findViewById(R.id.LeapName);
            LeapCreator = (TextView) itemView.findViewById(R.id.LeapCreator);
            LeapPrice = (TextView) itemView.findViewById(R.id.LeapPrice);
            LeapPhoto = (ImageView) itemView.findViewById(R.id.LeapImage);
            LeapId = (TextView) itemView.findViewById(R.id.Leapid);

            //Show Leap Details

            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int position = getLayoutPosition();
                    String x = String.valueOf(leaps.get(position).getLeapID());
                    Intent i = new Intent(view.getContext(), LeapInfoActivity.class);
                    i.putExtra("LeapId", x);
                    view.getContext().startActivity(i);
                }
            });
        }
    }


    @Override
    public int getItemCount() {
        if (leaps!= null)
            return leaps.size();
        else return 0;
    }

    @Override
    public LeapViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.leapcardlayout, viewGroup, false);
        LeapViewHolder pvh = new LeapViewHolder(v);
        return pvh;
    }

    @Override
    public void onBindViewHolder(LeapViewHolder personViewHolder, int i) {
        personViewHolder.LeapName.setText(leaps.get(i).getLeapName());
        personViewHolder.LeapPrice.setText(leaps.get(i).getLeapPrice() + " L.E");
        personViewHolder.LeapCreator.setText("No creator");
//        Picasso.with(c).load(leaps.get(i).photoId).into(personViewHolder.LeapPhoto);
        personViewHolder.LeapId.setText(leaps.get(i).getLeapLocation());
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }


}
