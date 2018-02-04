package com.chronvas.nearbyvenues.repo.model;

import com.google.gson.annotations.SerializedName;

public class Contact {

    @SerializedName("phone")
    private String phone;
    @SerializedName("formattedPhone")
    private String formattedPhone;
    @SerializedName("twitter")
    private String twitter;
    @SerializedName("facebook")
    private String facebook;
    @SerializedName("facebookUsername")
    private String facebookUsername;
    @SerializedName("facebookName")
    private String facebookName;
    @SerializedName("instagram")
    private String instagram;

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getFormattedPhone() {
        return formattedPhone;
    }

    public void setFormattedPhone(String formattedPhone) {
        this.formattedPhone = formattedPhone;
    }

    public String getTwitter() {
        return twitter;
    }

    public void setTwitter(String twitter) {
        this.twitter = twitter;
    }

    public String getFacebook() {
        return facebook;
    }

    public void setFacebook(String facebook) {
        this.facebook = facebook;
    }

    public String getFacebookUsername() {
        return facebookUsername;
    }

    public void setFacebookUsername(String facebookUsername) {
        this.facebookUsername = facebookUsername;
    }

    public String getFacebookName() {
        return facebookName;
    }

    public void setFacebookName(String facebookName) {
        this.facebookName = facebookName;
    }

    public String getInstagram() {
        return instagram;
    }

    public void setInstagram(String instagram) {
        this.instagram = instagram;
    }

}
