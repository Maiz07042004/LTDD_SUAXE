package com.example.ltdd_suaxe;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.CenterCrop;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.request.RequestOptions;
import com.example.ltdd_suaxe.API.APIService;
import com.example.ltdd_suaxe.API.RetrofitApp;
import com.example.ltdd_suaxe.Model.ChiTietDatHangResponse;
import com.example.ltdd_suaxe.Model.CuaHang;
import com.example.ltdd_suaxe.Model.ResponseChung;

import java.text.SimpleDateFormat;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class nChiTietDatHang_AcTiViTy extends AppCompatActivity {
    Button btnDatLai, btnlike;
    TextView tenCuaHang, dichvu, trangthai, ngayDat,maDonHang,tenKhachHang,SDT,diachi;
    ImageView imgCuaHang;
    private ChiTietDatHangResponse donDatHang;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_nchitietdathang);
        AnhXa();
        // Nhận dữ liệu từ Intent
        Intent intent = getIntent();
        String IdDonHang = intent.getStringExtra("IdDonHang");
        getChiTietDatHang(IdDonHang);
//        String dichvuValue = intent.getStringExtra("DichVu");
//        String hinhAnh = intent.getStringExtra("HinhAnh");
//        String trangThaiValue = intent.getStringExtra("TrangThai");
//        String ngayDatValue = intent.getStringExtra("NgayDat");
//
//        // Gán dữ liệu lên giao diện
//        tenCuaHang.setText(tenCuaHangValue);
//        dichvu.setText(dichvuValue);
//        ngayDat.setText(ngayDatValue);
//        trangthai.setText(trangThaiValue);
//
//        Glide.with(this)
//                .load(hinhAnh)
//                .apply(RequestOptions.bitmapTransform(new CenterCrop()))
//                .apply(RequestOptions.bitmapTransform(new RoundedCorners(20)))
//                .into(imgCuaHang);





        // Sử dụng để căn chỉnh view cho hệ thống gesture navigation (nếu cần)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    private void getChiTietDatHang(String IdDonHang) {

        // Khởi tạo ApiService
        APIService apiService = RetrofitApp.getRetrofitInstance().create(APIService.class);

        // Gọi API để lấy chi tiết cửa hàng
        Call<ChiTietDatHangResponse> call = apiService.getChiTietDatHang(IdDonHang);
        call.enqueue(new Callback<ChiTietDatHangResponse>() {
            @Override
            public void onResponse(Call<ChiTietDatHangResponse> call, Response<ChiTietDatHangResponse> response) {
                if (response.isSuccessful() && response.body() != null) {
                    donDatHang = response.body();
                    maDonHang.setText(donDatHang.get_id());
                    switch (donDatHang.getTrangThai()){
                        case "ChoXacNhan":
                            trangthai.setText("Chờ xác nhận");
                            break;
                        case "DaXacNhan":
                            trangthai.setText("Đã xác nhận");
                            break;
                        case "DaHoanThanh":
                            trangthai.setText("Đã hoàn thành");
                            break;
                        case "DaHuy":
                            trangthai.setText("Đã huỷ");
                            break;
                        default:
                            break;
                    }

                    // Tạo một SimpleDateFormat với định dạng bạn muốn
                    SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
                    ngayDat.setText(dateFormat.format(donDatHang.getNgayDatDon()));

                    tenKhachHang.setText(donDatHang.getTenKhachHang());
                    SDT.setText(donDatHang.getSDT());
                    diachi.setText(donDatHang.getDiaChi());

                    String dichVuString = TextUtils.join(", ", donDatHang.getDichVu());
                    dichvu.setText(dichVuString);
                    tenCuaHang.setText(donDatHang.getTenCuaHang());

                    String imageUrl = donDatHang.getHinhAnh();
                    Glide.with(nChiTietDatHang_AcTiViTy.this)
                            .load(imageUrl)
                            .apply(RequestOptions.bitmapTransform(new CenterCrop()))  // Cắt ảnh sao cho phủ đầy ImageView
                            .apply(RequestOptions.bitmapTransform(new RoundedCorners(20))) // Bo góc ảnh với bán kính 20
                            .into(imgCuaHang);  // Hiển thị ảnh trong ImageView
                    // Kiểm tra nếu TrangThaiLike = true thì ẩn nút like
                    if (!"DaHoanThanh".equals(donDatHang.getTrangThai())) {
                        btnlike.setVisibility(View.GONE);
                        btnDatLai.setVisibility(View.GONE);
                    }
                    if (donDatHang.getDaLike()) {
                        btnlike.setVisibility(View.GONE);
                    }
                    // Gán sự kiện click cho nút "Đặt lại"
                    btnDatLai.setOnClickListener(view -> {
                        // Truyền dữ liệu khi bấm nút
                        Intent newIntent = new Intent(nChiTietDatHang_AcTiViTy.this, CuaHangDetail_Activity.class);
                        newIntent.putExtra("IdCuaHang", donDatHang.getIdCuaHang());
                        startActivity(newIntent);
                    });

                    btnlike.setOnClickListener(view -> {
                        updateLike(donDatHang.get_id());
                        Toast.makeText(nChiTietDatHang_AcTiViTy.this, "Đã like", Toast.LENGTH_SHORT).show();
                        //Khi click like
                        Intent ratingIntent = new Intent(nChiTietDatHang_AcTiViTy.this, User_Home_Activity.class);

                        startActivity(ratingIntent);
                    });
                } else {
                    Toast.makeText(nChiTietDatHang_AcTiViTy.this, "Không thể lấy dữ liệu", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ChiTietDatHangResponse> call, Throwable t) {
                Toast.makeText(nChiTietDatHang_AcTiViTy.this, "Lỗi kết nối: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void updateLike(String IdDonHang){
        // Khởi tạo ApiService
        APIService apiService = RetrofitApp.getRetrofitInstance().create(APIService.class);

        // Gọi API để lấy chi tiết cửa hàng
        Call<ResponseChung> call = apiService.updateLike(IdDonHang);
        call.enqueue(new Callback<ResponseChung>() {
            @Override
            public void onResponse(Call<ResponseChung> call, Response<ResponseChung> response) {
                if (response.isSuccessful() && response.body() != null) {
                    ResponseChung responseChung=response.body();
                    if(responseChung.getCode()==200){
                        Toast.makeText(nChiTietDatHang_AcTiViTy.this, responseChung.getMessage(), Toast.LENGTH_SHORT).show();
                    }else{
                        Toast.makeText(nChiTietDatHang_AcTiViTy.this, responseChung.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(nChiTietDatHang_AcTiViTy.this, "Không thể lấy dữ liệu", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ResponseChung> call, Throwable t) {
                Toast.makeText(nChiTietDatHang_AcTiViTy.this, "Lỗi kết nối: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void AnhXa() {
        btnDatLai = findViewById(R.id.buttondatlai);
        btnlike = findViewById(R.id.buttonlike);
        maDonHang=findViewById(R.id.madonhang);
        trangthai = findViewById(R.id.trangthaidon);
        ngayDat = findViewById(R.id.ngaydat);
        tenKhachHang=findViewById(R.id.ten_khachhang);
        SDT=findViewById(R.id.sdt);
        diachi=findViewById(R.id.diachi);
        dichvu = findViewById(R.id.dichvu);
        tenCuaHang = findViewById(R.id.ten_cuahang);
        imgCuaHang = findViewById(R.id.imgCuaHang);
    }
}
