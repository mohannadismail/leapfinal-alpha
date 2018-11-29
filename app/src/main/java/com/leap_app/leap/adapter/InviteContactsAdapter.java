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


public class InviteContactsAdapter extends BaseAdapter {
    private Context mContext;
    private final String[] names;

    public InviteContactsAdapter(Context c, String[] names) {
        this.mContext = c;
        this.names = names;
    }

    public int getCount() {
        //return mThumbIds.length;
        return names.length;
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
            ImageView pic = gridView
                    .findViewById(R.id.user_account_circle);
            pic.setImageResource(R.drawable.account_circle);

            final CheckBox checkBox = gridView
                    .findViewById(R.id.user_checkbox);

            //Set Circles Name
            final TextView userName = gridView
                    .findViewById(R.id.user_name);
            userName.setText(names[position]);
            userName.setGravity(Gravity.CENTER);
            gridView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (checkBox.isChecked()) {
                        checkBox.setChecked(false);
                    } else checkBox.setChecked(true);
                }
            });

        } else {
            gridView = convertView;
        }

        return gridView;

    }

}