package com.medcords.test.test.api;

import com.medcords.test.test.model.LastLoginResponse;
import com.medcords.test.test.model.LoginResponse;
import com.medcords.test.test.model.PasswordInput;
import com.medcords.test.test.model.TokenInput;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Header;
import retrofit2.http.POST;

/**
 * Created by naveen on 2/8/17.
 */

public interface ApiInterface {


    @POST("authenticate")
    Call<LoginResponse> getResponseWithToken(@Body TokenInput tokenInput);

    @POST("authenticate")
    Call<LoginResponse> getResponseWithPassWord(@Body PasswordInput passwordInput);

    @POST("get_last_login_details")
    Call<LastLoginResponse> getLastLoginDetails(@Header("Authorization") String accessToken);

}
