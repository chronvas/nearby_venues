package com.chronvas.nearbyvenues.repo.model;

import com.google.gson.annotations.SerializedName;

public class HereNow {

    @SerializedName("count")
    private Integer count;
    @SerializedName("summary")
    private String summary;
//    @SerializedName("groups")
//    private List<Object> groups = null;

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

//    public List<Object> getGroups() {
//        return groups;
//    }
//
//    public void setGroups(List<Object> groups) {
//        this.groups = groups;
//    }
}
