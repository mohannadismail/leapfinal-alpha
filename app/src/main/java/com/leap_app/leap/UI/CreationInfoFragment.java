package com.leap_app.leap.UI;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.leap_app.leap.LeapProvider.LeapContract;
import com.leap_app.leap.LeapProvider.LeapDbHelper;
import com.leap_app.leap.Models.LeapBaseInfo;
import com.leap_app.leap.R;

import java.util.Calendar;
import java.util.Objects;


public class CreationInfoFragment extends Fragment {
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private static CreationInfoFragment instance;
    //    public TextView dateText;
//    public TextView timeText;
//    public EditText leapTitle;
//    public EditText leapDesc;
//    public EditText leapLocation;
//    public EditText leapPrice;
    private final Calendar now = Calendar.getInstance();

    private static boolean flagg = Boolean.parseBoolean(null);
    private android.support.v7.widget.AppCompatImageView circleImageView;

    public static boolean isFlagg() {
        return flagg;
    }

    public static void setFlagg(boolean flagg) {
        CreationInfoFragment.flagg = flagg;
    }

    public static CreationInfoFragment getInstance() {
        if (instance == null) {
            instance = new CreationInfoFragment();
        }
        return instance;
    }

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public CreationInfoFragment() {
        // Required empty public constructor

    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment DiscoverLeapsFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static CreationInfoFragment newInstance(String param1, String param2) {
        CreationInfoFragment fragment = new CreationInfoFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }

    }

    public Context context;

    public static LeapBaseInfo leapBaseInfooo;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View view = inflater.inflate(R.layout.fragment_creation_info, container, false);
        circleImageView = view.findViewById(R.id.make_public);
        final TextView dateText = view.findViewById(R.id.date_spinner);
        final TextView timeText = view.findViewById(R.id.time_spinner);
        final EditText leapTitle = view.findViewById(R.id.LeapTitle);
        final EditText leapDesc = view.findViewById(R.id.LeapDescription);
        final EditText leapLocation = view.findViewById(R.id.LeapLocation);
        final EditText leapPrice = view.findViewById(R.id.Leap_Price);

        int hour = now.get(Calendar.HOUR_OF_DAY);
        int minute = now.get(Calendar.MINUTE);
        int year = now.get(Calendar.YEAR);
        int month = now.get(Calendar.MONTH);
        int day = now.get(Calendar.DAY_OF_MONTH);


        String date;
        String Time;
        String Title;
        String Desc;
        String Location;
        String Price;
        final String[] s = new String[1];


        FloatingActionButton floatingActionButton = view.findViewById(R.id.nextButton);
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ContentValues values = new ContentValues();
                SQLiteDatabase db = new LeapDbHelper(getContext()).getWritableDatabase();

                values.put(LeapContract.LeapEntry.COLUMN_Name, leapTitle.getText().toString());
                values.put(LeapContract.LeapEntry.COLUMN_Description, leapDesc.getText().toString());
                values.put(LeapContract.LeapEntry.COLUMN_Map_Image, leapLocation.getText().toString());
                values.put(LeapContract.LeapEntry.COLUMN_Price, leapPrice.getText().toString());
                values.put(LeapContract.LeapEntry.COLUMN_User_Key, LoginActivity.getId1());

                db.insert(LeapContract.LeapEntry.Table_Name, null, values);
                db.execSQL("UPDATE User SET leapsNumber = leapsNumber + 1 WHERE id =?", new String[]{LoginActivity.getId1()});
                new Handler().postDelayed(new Runnable() {

                    @Override
                    public void run() {
                        SQLiteDatabase db = new LeapDbHelper(getContext()).getReadableDatabase();
                        Cursor c = db.rawQuery("SELECT id FROM Leap ORDER BY id DESC LIMIT 1", null);
                        if (c != null) {
                            c.moveToFirst();
                        }
                        s[0] = (Objects.requireNonNull(c).getString(0));
                        c.close();

                    }
                }, 500);

                db.close();

//                    leapBaseInfooo = new LeapBaseInfo(leapTitle.getText().toString(), leapDesc.getText().toString(), leapLocation.getText().toString(), leapPrice.getText().toString(), dateText.getText().toString(), timeText.getText().toString());
                flagg = true;
                Toast.makeText(getContext(), "Info Saved, go to add your Places", Toast.LENGTH_LONG).show();
            }
        });

        date = dateText.getText().toString();
        Time = timeText.getText().toString();
        Title = leapTitle.getText().toString();
        Desc = leapDesc.getText().toString();
        Location = leapLocation.getText().toString();
        Price = leapPrice.getText().toString();

        timeText.setText(String.valueOf(hour) + " : " + String.valueOf(minute));
        dateText.setText(String.valueOf(day + " \\ " + (month + 1) + " \\ " + year));
        dateText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                android.support.v4.app.FragmentManager fm = Objects.requireNonNull(getActivity()).getSupportFragmentManager();
                DatePickerFragment dialog = new DatePickerFragment();
                dialog.show(fm, "datePicker");
            }
        });
        timeText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                android.support.v4.app.FragmentManager fm = Objects.requireNonNull(getActivity()).getSupportFragmentManager();
                TimePickerFragment dialog = new TimePickerFragment();
                dialog.show(fm, "timePicker");
            }

        });


        addListenerOnButton();


        return view;
    }


    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p/>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }

    public void addListenerOnButton() {
        circleImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {


                Toast.makeText(getContext(),
                        "All users can view this leap!", Toast.LENGTH_SHORT).show();

            }

        });

    }


}
