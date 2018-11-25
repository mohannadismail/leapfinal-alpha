package com.leap_app.leap.UI;

import android.app.Dialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.text.format.DateFormat;
import android.widget.TextView;
import android.widget.TimePicker;

import com.leap_app.leap.R;

import java.util.Calendar;


public class TimePickerFragment extends android.support.v4.app.DialogFragment
        implements TimePickerDialog.OnTimeSetListener {
    TextView timeText;

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        // Use the current time as the default values for the picker
        final Calendar c = Calendar.getInstance();
        int hour = c.get(Calendar.HOUR_OF_DAY);
        int minute = c.get(Calendar.MINUTE);

        // Create a new instance of TimePickerDialog and return it
        return new TimePickerDialog(getActivity(), this, hour, minute,
                DateFormat.is24HourFormat(getActivity()));
    }

    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
        timeText = getActivity().findViewById(R.id.time_spinner);
        onTimeUpdated(hourOfDay, minute);


    }

    public void onTimeUpdated(int hours, int minutes) {
        timeText.setText(String.valueOf(hours) + " : " + String.valueOf(minutes));
    }
}