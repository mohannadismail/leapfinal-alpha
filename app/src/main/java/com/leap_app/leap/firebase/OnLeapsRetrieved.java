package com.leap_app.leap.firebase;

import com.leap_app.leap.models.LeapBaseInfo;

import java.util.ArrayList;

public interface OnLeapsRetrieved {
    void dataRetrieved(ArrayList<LeapBaseInfo> leaps);

    void markersRetrieved(double[] latts, double[] lonns, String[] markerIDs);
}
