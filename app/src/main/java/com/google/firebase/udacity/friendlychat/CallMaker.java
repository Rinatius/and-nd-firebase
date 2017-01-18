package com.google.firebase.udacity.friendlychat;

/**
 * Objects of this class prepare and make calls from
 * user identified by callerId to users identified by
 * ids in caleeIds array.
 *
 */
public class CallMaker extends Object{

    private String callerId;
    private String[] calleeIds;

    public CallMaker(String callerId, String[] calleeIds) {
        this.callerId = callerId;
        this.calleeIds = calleeIds;
    }

    public void makeCall() {

    }
}
