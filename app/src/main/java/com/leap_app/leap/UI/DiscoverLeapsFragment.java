package com.leap_app.leap.UI;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;
import com.leap_app.leap.Adapter.DiscoverLeapsAdapter;
import com.leap_app.leap.Models.LeapBaseInfo;
import com.leap_app.leap.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link DiscoverLeapsFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link DiscoverLeapsFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class DiscoverLeapsFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public DiscoverLeapsFragment() {
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
    public static DiscoverLeapsFragment newInstance(String param1, String param2) {
        DiscoverLeapsFragment fragment = new DiscoverLeapsFragment();
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

    /**
     * Created by Psychalafy on 1/3/2016.
     */
    public Context context;
    int q =0;
    ArrayList<LeapBaseInfo> leapBaseInfoList = new ArrayList<>();
    ArrayList<Object> objectArrayList = new ArrayList<>();
    ArrayList<String> keysArray = new ArrayList<>();
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_leaps, container, false);
        FragmentActivity context = getActivity();
        final RecyclerView rv = (RecyclerView) view.findViewById(R.id.rv);
        LinearLayoutManager llm = new LinearLayoutManager(context);
        rv.setLayoutManager(llm);
        context = this.getActivity();
        final Firebase ref = new Firebase("https://leapappeg.firebaseio.com/leap/Leap/");
        final FragmentActivity finalContext = context;
        ref.addValueEventListener(new ValueEventListener() {

            int i = 0;

            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if (dataSnapshot != null) {

                    Map<String, Object> stringmap = new HashMap<>();


                    for (DataSnapshot dsp : dataSnapshot.getChildren()) {
                        stringmap.put(dsp.getKey(), dsp.getValue(Object.class));
                        if (i < dataSnapshot.getChildrenCount() - 1)
                            i++;
                        objectArrayList.add(stringmap.get(dsp.getKey()));
                        keysArray.add(dsp.getKey());
                    }

                    q++;
                        leapBaseInfoList.clear();
                        for (int b = 0 ; b< objectArrayList.size() ; b++){
                            LinkedHashMap<String,String> linkedHashMap = (LinkedHashMap<String, String>) objectArrayList.get(b);
                            LeapBaseInfo leapBaseInfo = new LeapBaseInfo(linkedHashMap.get("leapName"),linkedHashMap.get("leapDescription"),linkedHashMap.get("leapLocation"),linkedHashMap.get("leapPrice"),linkedHashMap.get("date"),linkedHashMap.get("time"),keysArray.get(b));
                            leapBaseInfoList.add(leapBaseInfo);
                        }
                        DiscoverLeapsAdapter adapter = new DiscoverLeapsAdapter(finalContext, leapBaseInfoList);
                        rv.setAdapter(adapter);
                    }
                }
            @Override
            public void onCancelled(FirebaseError firebaseError) {

            }

        });
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


}


