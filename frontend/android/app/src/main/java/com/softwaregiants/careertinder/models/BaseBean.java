package com.softwaregiants.careertinder.models;

import com.google.gson.annotations.SerializedName;

public class BaseBean {

    @SerializedName("status_code")
    String statusCode;

    public String getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(String status) {
        this.statusCode = statusCode;
    }
}
