package com.leap_app.leap.adapter;

import android.content.Context;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import com.leap_app.leap.R;


public class InviteCirclesAdapter extends BaseAdapter {
    private Context mContext;
    private final String[] circles;

    public InviteCirclesAdapter(Context c , String[] circles) {
        this.mContext = c;
        this.circles = circles;
    }

    public int getCount() {
        //return mThumbIds.length;
        return circles.length;
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
            gridView = inflater.inflate(R.layout.invite_circles_item, null);

            // set image based on selected text
            ImageView pic = gridView
                    .findViewById(R.id.circle_image);
            pic.setImageResource(R.drawable.account_circle);

            final CheckBox checkBox = gridView
                    .findViewById(R.id.circle_checkbox);

            //Set Circles Name
            final TextView circleName = gridView
                    .findViewById(R.id.circle_name);
            circleName.setText(circles[position]);
            circleName.setGravity(Gravity.CENTER);
            gridView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                   if(checkBox.isChecked())
                   {
                    checkBox.setChecked(false);
                   }
                    else checkBox.setChecked(true);
                }
            });



            String users = circles[position];
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



        }
        else {
            gridView = convertView;
        }


        return gridView;

    }


}
