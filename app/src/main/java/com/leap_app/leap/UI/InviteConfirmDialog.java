package com.leap_app.leap.UI;

import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import com.leap_app.leap.R;

/**
 * Created by aya on 5/7/16.
 */
public class InviteConfirmDialog extends DialogFragment {


    private Button InviteMore;
    private Button Confirm;
    Toolbar toolbar ;

    public InviteConfirmDialog() {
        // Empty constructor required for DialogFragment
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_creation_invite_confirmwindow, container);
        getDialog().getWindow();
        getDialog().getWindow().requestFeature(Window.FEATURE_NO_TITLE);

        // Show soft keyboard automatically


        toolbar = (Toolbar) view.findViewById(R.id.confirm_toolbar);
        toolbar.setTitle("Confirmation");

        InviteMore = (Button) view.findViewById(R.id.invite_more);
        InviteMore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                InviteConfirmDialog.this.dismiss();
            }
        });

        Confirm = (Button) view.findViewById(R.id.confirm);
        Confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                InviteConfirmDialog.this.dismiss();
            }
        });

        return view;
    }



}