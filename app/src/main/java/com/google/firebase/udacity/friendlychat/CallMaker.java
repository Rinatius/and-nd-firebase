package com.google.firebase.udacity.friendlychat;

import android.app.Activity;

/**
 * Objects of this class prepare and make calls from
 * user identified by callerId to users identified by
 * ids in caleeIds array.
 *
 */
public class CallMaker extends Object{

    private String callerId;
    private String[] calleeIds;
    private Activity context;

    public CallMaker(String callerId, String[] calleeIds, Activity context) {
        this.callerId = callerId;
        this.calleeIds = calleeIds;
        this.context = context;
    }

    public void makeCall() {

    }

    public void finishCall() {

    }
}
