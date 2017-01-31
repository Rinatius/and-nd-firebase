package com.google.firebase.udacity.friendlychat;

/**
 * Created by rin on 1/28/17.
 */
public class Grant {

    private String grantName;
    private String grantDescription;
    private String deadline;
    private String tags;

    public Grant() {}

    public Grant(String grantName, String grantDescription) {
        this.grantName = grantName;
        this.grantDescription = grantDescription;
    }

    public String getGrantName() {
        return grantName;
    }

    public void setGrantName(String grantName) {
        this.grantName = grantName;
    }

    public String getGrantDescription() {
        return grantDescription;
    }

    public void setGrantDescription(String grantDescription) {
        this.grantDescription = grantDescription;
    }

    public String getTags() {
        return tags;
    }

    public void setTags(String tags) {
        this.tags = tags;
    }

    public String getDeadline() {
        return deadline;
    }

    public void setDeadline(String deadline) {
        this.deadline = deadline;
    }


}
