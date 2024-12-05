package com.example.ltdd_suaxe.API;

import com.example.ltdd_suaxe.Model.CapNhatCuaHangRequest;
import com.example.ltdd_suaxe.Model.CapNhatCuaHangResponse;
import com.example.ltdd_suaxe.Model.CapNhatKhachHangRequest;
import com.example.ltdd_suaxe.Model.CuaHang;
import com.example.ltdd_suaxe.Model.DangKyCuaHangRequest;
import com.example.ltdd_suaxe.Model.DangKyKhachHangRequest;
import com.example.ltdd_suaxe.Model.DoiMatKhauRequest;
import com.example.ltdd_suaxe.Model.DonSuaChuaRequest;
import com.example.ltdd_suaxe.Model.DonSuaChua_Daxacnhan;
import com.example.ltdd_suaxe.Model.DonYeuCau;
import com.example.ltdd_suaxe.Model.LoginCuaHangResponse;
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

    @POST("users/register")
    Call<ResponseChung> registerUser(@Body DangKyKhachHangRequest dangKyKhachHangRequest);

    @GET("users/detail/{userId}")  // Lấy ra tông tin cá nhân khách hàng
    Call<User> getUserDetail(@Path("userId") String userId);

    @POST("users/update/{userId}")  //Cập nhật thông tin khách hàng
    Call<ResponseChung> updateUser(@Path("userId") String userId, @Body CapNhatKhachHangRequest capNhatKhachHangRequest);

    @POST("users/updatePassword/{userId}")  //Cập nhật thông tin khách hàng
    Call<ResponseChung> updatePasswordUser(@Path("userId") String userId, @Body DoiMatKhauRequest doiMatKhauRequest);

    @POST("users/login")  // Api Login
    Call<LoginResponse> loginUser(@Body LoginRequest loginRequest);

    @GET("quan") //Lấy ra danh sách quận
    Call<List<Quan>> getQuanList();

    // Lấy ra danh sách cửa hàng từ IdQuan
    @GET("cuaHang/{IdQuan}")
    Call<List<CuaHang>> getCuaHangListById(@Path("IdQuan") String idQuan);

    // Lấy ra chi tiết cửa hàng từ IdCuaHang
    @GET("cuaHang/detail/{IdCuaHang}")
    Call<CuaHang> getCuaHangDetail(@Path("IdCuaHang") String idCuaHang);

    // Lưu cửa hàng đã lưu khách hàng
    @POST("cuaHang/luu-cua-hang/{userId}")
    Call<ResponseBody> luuCuaHang(@Path("userId") String userId, @Body Map<String, String> body);

    // Lấy ra danh sách cửa hàng đã lưu
    @GET("cuaHang/cua-hang-da-luu/{userId}")
    Call<List<CuaHang>> getCuaHangDaLuu(@Path("userId") String userId);

    // Xoá cửa hàng đã lưu khỏi danh sách
    @POST("cuaHang/xoa-cua-hang-da-luu/{userId}")
    Call<ResponseBody> deleteCuaHangDaLuu(@Path("userId") String userId, @Body Map<String, String> body);

    // Tạo đơn sửa chữa
    @POST("donSuaChua/create")
    Call<ResponseChung> createDonSuaChua(@Body DonSuaChuaRequest donSuaChuaRequest);

    //Lấy danh sách đơn sửa chữa theo trạng thái bên khách hàng
    @GET("donSuaChua/khachHang/{IdKhachHang}/{status}")
    Call<List<DonSuaChua_Daxacnhan>> getListDonSuaChuaTheoTrangThai(
            @Path("IdKhachHang") String IdKhachHang,
            @Path("status") String status
    );

    //Lấy danh sách đơn sửa chữa bên khách hàng
    @GET("donSuaChua/khachHang/{IdKhachHang}")
    Call<List<DonSuaChua_Daxacnhan>> getListDonSuaChua(
            @Path("IdKhachHang") String IdKhachHang
    );

    // Update trạng thái đơn sửa chữa
    @POST("donSuaChua/updateDonSuaChua/{IdDonSuaChua}/{status}")
    Call<ResponseChung> updateDonSuaChua(@Path("IdDonSuaChua") String IdDonSuaChua, @Path("status") String status);

    @POST("cuaHang/login")  // Api Login
    Call<LoginCuaHangResponse> loginCuaHang(@Body LoginRequest loginRequest);

    //Api đăng ký cửa hàng
    @POST("cuaHang/register")
    Call<ResponseChung> registerCuaHang(@Body DangKyCuaHangRequest dangKyCuaHangRequest);

    //Lấy danh sách đơn sửa chữa theo trạng thái bên khách hàng
    @GET("donSuaChua/cuaHang/{IdCuaHang}/{status}")
    Call<List<DonYeuCau>> getListDonSuaChuaTheoTrangThaiCuaHang(
            @Path("IdCuaHang") String IdKhachHang,
            @Path("status") String status
    );

    @GET("cuaHang/detail/{cuaHangId}")  // Lấy ra tông tin cá nhân khách hàng
    Call<CapNhatCuaHangResponse> CuaHangDetail(@Path("cuaHangId") String cuaHangId);

    @POST("cuaHang/update/{cuaHangId}")  //Cập nhật thông tin khách hàng
    Call<ResponseChung> updateCuaHang(@Path("cuaHangId") String cuaHangId, @Body CapNhatCuaHangRequest capNhatCuaHangRequest);

    @POST("cuaHang/updatePassword/{cuaHangId}")  //Đổi mật khẩu cửa hàng
    Call<ResponseChung> updatePasswordCuaHang(@Path("cuaHangId") String cuaHangId, @Body DoiMatKhauRequest doiMatKhauRequest);
}

