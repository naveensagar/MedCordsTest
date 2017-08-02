package com.medcords.test.test;

import com.google.gson.annotations.SerializedName;

/**
 * Created by naveen on 2/8/17.
 */

public class LoginResponse {

    @SerializedName("accessToken")
    private String token;

    public String getToken() {
        return token;
    }
}
