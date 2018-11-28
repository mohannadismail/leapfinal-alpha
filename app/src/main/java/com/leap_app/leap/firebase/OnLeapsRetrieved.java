package com.leap_app.leap.firebase;

import com.leap_app.leap.Models.LeapBaseInfo;

import java.util.ArrayList;

public interface OnLeapsRetrieved {
    void dataRetrieved(ArrayList<LeapBaseInfo> leaps);
}
