package com.example.webwerks.mvvmstructure.service;


import com.example.webwerks.mvvmstructure.model.response.LoginResponse;
import io.reactivex.Single;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface ApiServices {


   @POST("users/login")
   @FormUrlEncoded
   Single<LoginResponse> userLogin(
           @Field("email") String email,
           @Field("password") String password);

   @POST("users/forgot")
   @FormUrlEncoded
   Single<LoginResponse> userForget(
           @Field("email") String email);
}
