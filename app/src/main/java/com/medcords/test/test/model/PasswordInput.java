package com.medcords.test.test.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by naveen on 2/8/17.
 */

public class PasswordInput {

    @SerializedName("authType")
    private String authType = "PASSWD";

    @SerializedName("userName")
    private String userName;

    @SerializedName("password")
    private String password;

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
