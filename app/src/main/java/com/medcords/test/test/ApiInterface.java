package com.medcords.test.test;

import retrofit2.Call;
import retrofit2.http.Body;
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
    Call<LastLoginResponse> getLastLoginDetails();

}
