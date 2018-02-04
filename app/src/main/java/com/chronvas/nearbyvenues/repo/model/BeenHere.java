package com.chronvas.nearbyvenues.repo.model;

import com.google.gson.annotations.SerializedName;

public class BeenHere {
    @SerializedName("lastCheckinExpiredAt")
    private Integer lastCheckinExpiredAt;

    public Integer getLastCheckinExpiredAt() {
        return lastCheckinExpiredAt;
    }

    public void setLastCheckinExpiredAt(Integer lastCheckinExpiredAt) {
        this.lastCheckinExpiredAt = lastCheckinExpiredAt;
    }
}
