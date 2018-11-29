package com.leap_app.leap.ui.invites;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;

import com.leap_app.leap.R;

import java.util.Objects;


public class InviteConfirmDialog extends DialogFragment {


    public InviteConfirmDialog() {
        // Empty constructor required for DialogFragment
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_creation_invite_confirmwindow, container);
        getDialog().getWindow();
        Objects.requireNonNull(getDialog().getWindow()).requestFeature(Window.FEATURE_NO_TITLE);

        // Show soft keyboard automatically

        Toolbar toolbar = view.findViewById(R.id.confirm_toolbar);
        toolbar.setTitle(R.string.confirmation);

        Button inviteMore = view.findViewById(R.id.invite_more);
        inviteMore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                InviteConfirmDialog.this.dismiss();
            }
        });

        Button confirm = view.findViewById(R.id.confirm);
        confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                InviteConfirmDialog.this.dismiss();
            }
        });

        return view;
    }


}