package com.leap_app.leap.UI;

import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.leap_app.leap.R;

/**
 * Created by aya on 4/18/16.
 */
public class ReviewDialog extends DialogFragment implements TextView.OnEditorActionListener {
    public interface EditNameDialogListener {
        void onFinishEditDialog(String inputText);
    }

    private EditText mEditText;
    private Button dismissDialog;
    private Button doneAdding;

    public ReviewDialog() {
        // Empty constructor required for DialogFragment
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.review_popup, container);
        mEditText = (EditText) view.findViewById(R.id.review_title);
        getDialog().getWindow().requestFeature(Window.FEATURE_NO_TITLE);

        // Show soft keyboard automatically
        mEditText.requestFocus();
        getDialog().getWindow().setSoftInputMode(
                WindowManager.LayoutParams.SOFT_INPUT_STATE_VISIBLE);
        mEditText.setOnEditorActionListener(this);

        dismissDialog = (Button) view.findViewById(R.id.buttonclose);
        dismissDialog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ReviewDialog.this.dismiss();
            }
        });

        doneAdding = (Button) view.findViewById(R.id.submitReview);
        doneAdding.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditNameDialogListener activity = (EditNameDialogListener) getActivity();
                activity.onFinishEditDialog(mEditText.getText().toString());
                ReviewDialog.this.dismiss();
            }
        });

        return view;
    }

    @Override
    public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
        if (EditorInfo.IME_ACTION_DONE == actionId) {
            // Return input text to activity
            EditNameDialogListener activity = (EditNameDialogListener) getActivity();
            activity.onFinishEditDialog(mEditText.getText().toString());
            this.dismiss();
            return true;
        }
        return false;
    }

}