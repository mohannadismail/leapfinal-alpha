package com.leap_app.leap.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.leap_app.leap.R;
import com.leap_app.leap.ui.circles.CircleDetails;


public class CirclesAdapter extends BaseAdapter {
    private Context mContext;
    private final String[] userNames;

    public CirclesAdapter(Context c , String[] userNames) {
        this.mContext = c;
        this.userNames = userNames;
    }

    public int getCount() {
        //return mThumbIds.length;
        return userNames.length;
    }

    public Object getItem(int position) {
        return null;
    }

    public long getItemId(int position) {
        return 0;
    }

    // create a new ImageView for each item referenced by the Adapter
    public View getView(int position, View convertView, ViewGroup parent) {

        LayoutInflater inflater = (LayoutInflater) mContext
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View gridView;

        if (convertView == null) {

            gridView = new View(mContext);

            // get layout
            gridView = inflater.inflate(R.layout.circles_item, null);

            // set image based on selected text
            ImageView pic = gridView
                    .findViewById(R.id.circle_image);
            pic.setImageResource(R.drawable.account_circle);

            //Set Circles Name
            final TextView circleName = gridView
                    .findViewById(R.id.circle_name);
            circleName.setText(userNames[position]);
            circleName.setGravity(Gravity.CENTER);


            String users = userNames[position];
            switch (users) {
                case "Family":
                    pic.setImageResource(R.drawable.family);
                    break;
                case "Shella":
                    pic.setImageResource(R.drawable.shella);
                    break;
                default:
                    pic.setImageResource(R.drawable.leaplogo);
                    break;
            }
            gridView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent i = new Intent(v.getContext(), CircleDetails.class);
                    i.putExtra("key",circleName.getText().toString());
                    v.getContext().startActivity(i);
                }
            });


        }
        else {
            gridView = convertView;
        }


        return gridView;

        /**
        ImageView imageView;
        if (convertView == null) {
            // if it's not recycled, initialize some attributes
            imageView = new ImageView(mContext);
            imageView.setLayoutParams(new GridView.LayoutParams(85, 85));
            imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
            imageView.setPadding(8, 8, 8, 8);
        } else {
            imageView = (ImageView) convertView;
        }

        imageView.setImageResource(mThumbIds[position]);
        return imageView;
         */
    }







}
