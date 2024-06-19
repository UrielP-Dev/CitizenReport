package com.si.citizenreport;

import com.si.citizenreport.model.Report;
import com.si.citizenreport.model.User;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;

public interface ApiReport {
    @POST("user")
    Call<User> registerUser(@Body User user);

    @POST("user/login")
    Call<User> loginUser(@Body User loginDetails);

    @Multipart
    @POST("report")
    Call<Report> createReport(
            @Part MultipartBody.Part photo,
            @Part("description") RequestBody description,
            @Part("location") RequestBody location,
            @Part("date") RequestBody date,
            @Part("time") RequestBody time,
            @Part("plate") RequestBody plate,
            @Part("status") RequestBody status,
            @Part("userId") RequestBody userId,
            @Part("managerID") RequestBody managerID
    );

}

