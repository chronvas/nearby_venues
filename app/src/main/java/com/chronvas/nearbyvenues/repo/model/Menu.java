package com.chronvas.nearbyvenues.repo.model;

import com.google.gson.annotations.SerializedName;

public class Menu {
    @SerializedName("type")
    private String type;
    @SerializedName("label")
    private String label;
    @SerializedName("anchor")
    private String anchor;
    @SerializedName("url")
    private String url;
    @SerializedName("mobileUrl")
    private String mobileUrl;
    @SerializedName("externalUrl")
    private String externalUrl;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String getAnchor() {
        return anchor;
    }

    public void setAnchor(String anchor) {
        this.anchor = anchor;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getMobileUrl() {
        return mobileUrl;
    }

    public void setMobileUrl(String mobileUrl) {
        this.mobileUrl = mobileUrl;
    }

    public String getExternalUrl() {
        return externalUrl;
    }

    public void setExternalUrl(String externalUrl) {
        this.externalUrl = externalUrl;
    }
}
