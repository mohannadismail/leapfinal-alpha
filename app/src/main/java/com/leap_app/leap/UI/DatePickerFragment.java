package com.leap_app.leap.UI;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;
import android.widget.TextView;

import com.leap_app.leap.R;

import java.util.Calendar;

/**
 * Created by aya on 3/28/16.
 */
public class DatePickerFragment extends DialogFragment
        implements DatePickerDialog.OnDateSetListener {
    TextView dateText;

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        // Use the current date as the default date in the picker
        final Calendar c = Calendar.getInstance();
        int year = c.get(Calendar.YEAR);
        int month = c.get(Calendar.MONTH);
        int day = c.get(Calendar.DAY_OF_MONTH);


        return new DatePickerDialog(getActivity(), this, year, month, day);

    }

    @Override
    public void onDateSet(DatePicker view, int year, int month, int day) {

        onDateSetUpdate(year, month + 1, day);


    }

    public void onDateSetUpdate(int yy, int mm, int dd) {
        dateText = (TextView) getActivity().findViewById(R.id.date_spinner);
        dateText.setText(String.valueOf(dd + " \\ " + mm + " \\ " + yy));


    }
}