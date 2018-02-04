package com.chronvas.nearbyvenues.repo.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Specials {
    @SerializedName("count")
    private Integer count;
    @SerializedName("items")
    private List<Object> items = null;

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public List<Object> getItems() {
        return items;
    }

    public void setItems(List<Object> items) {
        this.items = items;
    }
}
