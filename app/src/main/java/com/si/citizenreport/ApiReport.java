package com.si.citizenreport;

import com.si.citizenreport.model.User;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface ApiReport {
    @POST("user")
    Call<User> registerUser(@Body User user);

    @POST("user/login")
    Call<User> loginUser(@Body User loginDetails);
}

