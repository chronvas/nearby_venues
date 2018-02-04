package com.chronvas.nearbyvenues.repo.model;

import com.google.gson.annotations.SerializedName;

public class SearchMeta {
    @SerializedName("code")
    private Integer code;
    @SerializedName("requestId")
    private String requestId;

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getRequestId() {
        return requestId;
    }

    public void setRequestId(String requestId) {
        this.requestId = requestId;
    }
}
