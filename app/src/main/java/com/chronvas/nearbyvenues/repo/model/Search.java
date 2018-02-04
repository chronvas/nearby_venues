package com.chronvas.nearbyvenues.repo.model;

import com.google.gson.annotations.SerializedName;

public class Search {

    @SerializedName("meta")
    private SearchMeta meta;
    @SerializedName("response")
    private SearchResponse response;

    public SearchMeta getMeta() {
        return meta;
    }

    public void setMeta(SearchMeta meta) {
        this.meta = meta;
    }

    public SearchResponse getResponse() {
        return response;
    }

    public void setResponse(SearchResponse response) {
        this.response = response;
    }
}
