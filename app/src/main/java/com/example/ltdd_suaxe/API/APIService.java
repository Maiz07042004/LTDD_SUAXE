package com.example.ltdd_suaxe.API;

import com.example.ltdd_suaxe.Model.LoginRequest;
import com.example.ltdd_suaxe.Model.LoginResponse;
import com.example.ltdd_suaxe.Model.User;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

import java.util.List;

public interface APIService {

    @GET("users")  // Đây là endpoint API bạn cung cấp
    Call<List<User>> getUsers();

    @POST("users/login")  // Endpoint của API
    Call<LoginResponse> loginUser(@Body LoginRequest loginRequest);  // Gửi request body và nhận response

}

