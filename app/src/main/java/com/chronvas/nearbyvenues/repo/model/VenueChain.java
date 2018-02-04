package com.chronvas.nearbyvenues.repo.model;

import com.google.gson.annotations.SerializedName;


public class VenueChain {
    @SerializedName("id")
    private String id;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
