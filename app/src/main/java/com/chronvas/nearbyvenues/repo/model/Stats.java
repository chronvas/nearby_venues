package com.chronvas.nearbyvenues.repo.model;

import com.google.gson.annotations.SerializedName;

public class Stats {
    @SerializedName("checkinsCount")
    private Integer checkinsCount;
    @SerializedName("usersCount")
    private Integer usersCount;
    @SerializedName("tipCount")
    private Integer tipCount;

    public Integer getCheckinsCount() {
        return checkinsCount;
    }

    public void setCheckinsCount(Integer checkinsCount) {
        this.checkinsCount = checkinsCount;
    }

    public Integer getUsersCount() {
        return usersCount;
    }

    public void setUsersCount(Integer usersCount) {
        this.usersCount = usersCount;
    }

    public Integer getTipCount() {
        return tipCount;
    }

    public void setTipCount(Integer tipCount) {
        this.tipCount = tipCount;
    }
}
