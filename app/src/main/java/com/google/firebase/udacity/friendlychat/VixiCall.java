package com.google.firebase.udacity.friendlychat;

import com.google.firebase.database.Exclude;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by rin on 1/19/17.
 */
public class VixiCall {

    private String callerId;
    private String sessionId = null;

    public VixiCall(String callerId) {
        this.callerId = callerId;
    }

    public VixiCall(){
        // Default constructor required for calls to DataSnapshot.getValue(VixiCall.class)
    }

    public String getCallerId() {
        return callerId;
    }

    public void setCallerId(String callerId) {
        this.callerId = callerId;
    }

    public String getSessionId() {
        return sessionId;
    }

    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
    }

    @Exclude
    public Map<String, Object> toMap() {
        HashMap<String, Object> result = new HashMap<>();
        result.put("callerId", callerId);
        result.put("sessionId", sessionId);

        return result;
    }
}
