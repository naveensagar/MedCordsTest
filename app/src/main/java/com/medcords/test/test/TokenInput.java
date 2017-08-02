package com.medcords.test.test;

import com.google.gson.annotations.SerializedName;

/**
 * Created by naveen on 2/8/17.
 */

public class TokenInput {

    @SerializedName("authType")
    private String authType = "TKN";


    @SerializedName("token")
    private String token;

    public void setToken(String token) {
        this.token = token;
    }
}

