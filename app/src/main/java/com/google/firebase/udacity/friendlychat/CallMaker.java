package com.google.firebase.udacity.friendlychat;

import android.app.Activity;
import android.content.Intent;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Map;

/**
 * Objects of this class prepare and make calls from
 * user identified by callerId to users identified by
 * ids in caleeIds array. Class methods work only in case
 * calling user is authenticated.
 *
 */
public class CallMaker extends Object{

    private DatabaseReference callsDbReference;
    private String callerId;
    private String[] calleeIds;
    private Activity context;
    private FirebaseDatabase database;

    public CallMaker(String callerId, String[] calleeIds,
                     Activity context, FirebaseDatabase database) {
        this.callerId = callerId;
        this.calleeIds = calleeIds;
        this.context = context;
        this.database = database;
        callsDbReference = database.getReference().child(MyConstants.FIREBASE_CALLS);
    }

    /**
     * TODO: Document usage of this method: called only from inside AsyncTask or separate thread
     * TODO: Throw exception on timeout
     */
    public void makeCall() {
        VixiCall call = new VixiCall(callerId);
        String callKey = saveCallToDataBase(call);
        //DatabaseReference thisCallDbReference = callsDbReference.child(callKey);
        //FirebaseHelper.addArrayAsChildren(calleeIds,
        //                                  thisCallDbReference.child(MyConstants.FIREBASE_CALLEES),
        //                                  database);
        //if (isSessionIdPresent(thisCallDbReference)) startProxyService(thisCallDbReference);
    }

    public void finishCall() {
        Intent intent = new Intent(context, ProxyService.class);
        context.stopService(intent);
    }

    //Saves call to database under newly generated unique key, returns this key as String.
    private String saveCallToDataBase(VixiCall call) {
        String key = callsDbReference.push().getKey();
        callsDbReference.child(key).setValue(call);
        return key;
    }

    private boolean isSessionIdPresent(DatabaseReference thisCallDbReference) {
        return false;
    }

    private void startProxyService(DatabaseReference thisCallDbReference) {
        Intent intent = new Intent(context, ProxyService.class);
        context.startService(intent);
    }

}
