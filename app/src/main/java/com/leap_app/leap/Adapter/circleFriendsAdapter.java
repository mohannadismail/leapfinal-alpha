package com.leap_app.leap.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import com.leap_app.leap.R;
import com.leap_app.leap.UI.CircleDetails;

/**
 * Created by aya on 4/18/16.
 */
public class circleFriendsAdapter extends BaseAdapter{
    private Context mContext;
    private final String[] userNames;

    public circleFriendsAdapter(Context c , String[] userNames) {
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
            gridView = inflater.inflate(R.layout.invited_user_item, null);

            // set image based on selected text
            ImageView pic = (ImageView) gridView
                    .findViewById(R.id.user_account_circle);
            pic.setImageResource(R.drawable.account_circle);

            //Set Circles Name
            final TextView circleName = (TextView) gridView
                    .findViewById(R.id.user_name);
            circleName.setText(userNames[position]);
            circleName.setGravity(Gravity.CENTER);

            CheckBox checkBox = (CheckBox) gridView
                    .findViewById(R.id.user_checkbox);
            checkBox.setVisibility(View.INVISIBLE);



            String users = userNames[position];
            if (users.equals("Ahmed")) {
                pic.setImageResource(R.drawable.account_circle);
            }
            else if (users.equals("Abbas")){
                pic.setImageResource(R.drawable.account_circle);
            }
            else {
                pic.setImageResource(R.drawable.leaplogo);
            }



        }
        else {
            gridView = (View) convertView;
        }


        return gridView;

    }
}
