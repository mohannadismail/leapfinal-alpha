package com.leap_app.leap.UI;

import android.app.FragmentManager;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.leap_app.leap.R;

import java.util.Calendar;

/**
 * Created by aya on 3/14/16.
 */
public class CreationInfoFragment extends Fragment {
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    public android.support.v7.widget.AppCompatImageView circleImageView;
    public TextView dateText;
    public TextView timeText;
    final Calendar now = Calendar.getInstance();


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

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View view = inflater.inflate(R.layout.fragment_creation_info, container, false);
        circleImageView = (android.support.v7.widget.AppCompatImageView) view.findViewById(R.id.make_public);
        dateText = (TextView) view.findViewById(R.id.date_spinner);
        timeText = (TextView) view.findViewById(R.id.time_spinner);

        int hour = now.get(Calendar.HOUR_OF_DAY);
        int minute = now.get(Calendar.MINUTE);
        int year = now.get(Calendar.YEAR);
        int month = now.get(Calendar.MONTH);
        int day = now.get(Calendar.DAY_OF_MONTH);


        timeText.setText(String.valueOf(hour) + " : " + String.valueOf(minute));
        dateText.setText(String.valueOf(day + " \\ " + (month+1) + " \\ " + year));
        dateText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager fm = getActivity().getFragmentManager();
                DatePickerFragment dialog = new DatePickerFragment();
                dialog.show(fm, "datePicker");
            }
        });
        timeText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager fm = getActivity().getFragmentManager();
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