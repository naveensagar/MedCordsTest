package com.medcords.test.test;

import com.google.gson.annotations.SerializedName;

/**
 * Created by naveen on 2/8/17.
 */

public class LastLoginResponse {

    @SerializedName("userName")
    private String userName;

    @SerializedName("lastLoginDate")
    private String lastLoginDate;

    @SerializedName("device")
    private String device;

    public String getUserName() {
        return userName;
    }

    public String getLastLoginDate() {
        return lastLoginDate;
    }

    public String getDevice() {
        return device;
    }
}
