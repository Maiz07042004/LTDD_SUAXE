package com.example.ltdd_suaxe.API;

import com.example.ltdd_suaxe.Model.CuaHang;
import com.example.ltdd_suaxe.Model.DonSuaChua;
import com.example.ltdd_suaxe.Model.DonSuaChuaRequest;
import com.example.ltdd_suaxe.Model.LoginRequest;
import com.example.ltdd_suaxe.Model.LoginResponse;
import com.example.ltdd_suaxe.Model.Quan;
import com.example.ltdd_suaxe.Model.ResponseChung;
import com.example.ltdd_suaxe.Model.User;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

import java.util.List;
import java.util.Map;

public interface APIService {

    @GET("users")  // Đây là endpoint API bạn cung cấp
    Call<List<User>> getUsers();

    @POST("users/login")  // Endpoint của API
    Call<LoginResponse> loginUser(@Body LoginRequest loginRequest);  // Gửi request body và nhận response

    @GET("quan")
    Call<List<Quan>> getQuanList();

//  Lấy ra danh sách cửa hàng từ IdQuan
    @GET("cuaHang/{IdQuan}")
    Call<List<CuaHang>> getCuaHangListById(@Path("IdQuan") String idQuan);

//    Lấy ra chi tiết cửa hàng từ IdCuaHang
    @GET("cuaHang/detail/{IdCuaHang}")
    Call<CuaHang> getCuaHangDetail(@Path("IdCuaHang") String idCuaHang);

    @POST("cuaHang/luu-cua-hang/{userId}")
    Call<ResponseBody> luuCuaHang(@Path("userId") String userId, @Body Map<String, String> body);

    @POST("donSuaChua/create")  // Endpoint của API
    Call<ResponseChung> createDonSuaChua(@Body DonSuaChuaRequest donSuaChuaRequest);
}

