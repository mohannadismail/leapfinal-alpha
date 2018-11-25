package com.leap_app.leap.Adapter;


import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.leap_app.leap.Models.Leap;
import com.leap_app.leap.R;
import com.leap_app.leap.UI.LeapInfoActivity;
import com.squareup.picasso.Picasso;

import java.util.List;


public class DiscoverLeapsAdapter extends RecyclerView.Adapter<DiscoverLeapsAdapter.LeapViewHolder> {
    private static List<Leap> leaps;
    private Context c;

    public DiscoverLeapsAdapter(Context context, List<Leap> leaps) {
        this.c = context;
        DiscoverLeapsAdapter.leaps = leaps;
    }


    @Override
    public void onBindViewHolder(@NonNull LeapViewHolder personViewHolder, int i) {
        personViewHolder.LeapName.setText(leaps.get(i).getName());
        personViewHolder.LeapPrice.setText(leaps.get(i).getPrice() + c.getString(R.string.LE));
        personViewHolder.LeapCreator.setText(leaps.get(i).getUser());

        Picasso.get()
                .load(leaps.get(i).getPhotoId())
                .into(personViewHolder.LeapPhoto);
        personViewHolder.LeapId.setText(leaps.get(i).getLeapId());
    }


    @Override
    public int getItemCount() {
        if (leaps != null)
            return leaps.size();
        else return 0;
    }

    @NonNull
    @Override
    public LeapViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.leapcardlayout, viewGroup, false);
        LeapViewHolder pvh = new LeapViewHolder(v);
        return pvh;
    }

    public static class LeapViewHolder extends RecyclerView.ViewHolder {
        CardView cv;
        public View view;
        TextView LeapName;
        TextView LeapCreator;
        TextView LeapPrice;
        ImageView LeapPhoto;
        TextView LeapId;
        private Context context;


        LeapViewHolder(View itemView) {
            super(itemView);
            view = itemView;
            cv = itemView.findViewById(R.id.cv);
            LeapName = itemView.findViewById(R.id.LeapName);
            LeapCreator = itemView.findViewById(R.id.LeapCreator);
            LeapPrice = itemView.findViewById(R.id.LeapPrice);
            LeapPhoto = itemView.findViewById(R.id.LeapImage);
            LeapId = itemView.findViewById(R.id.Leapid);

            //Show Leap Details

            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int position = getLayoutPosition();
                    String x = String.valueOf(leaps.get(position).getLeapId());
                    Intent i = new Intent(view.getContext(), LeapInfoActivity.class);
                    i.putExtra(context.getString(R.string.LeapId), x);
                    view.getContext().startActivity(i);
                }
            });
        }
    }

    @Override
    public void onAttachedToRecyclerView(@NonNull RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }


}
