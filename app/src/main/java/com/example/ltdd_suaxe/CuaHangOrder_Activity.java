package com.example.ltdd_suaxe;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AlertDialog;
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
import com.example.ltdd_suaxe.Model.CuaHang;
import com.example.ltdd_suaxe.Model.DonSuaChuaRequest;
import com.example.ltdd_suaxe.Model.LoginResponse;
import com.example.ltdd_suaxe.Model.ResponseChung;
import com.example.ltdd_suaxe.fragment_user.ListFragmentUser;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CuaHangOrder_Activity extends AppCompatActivity {
    private CheckBox checkbox_valop, checkBox_thaynhot, checkBox_suadien, checkBox_chuaro;
    private EditText diachi, ghichu;
    private ImageView imgCuaHang;
    private Button btnHuy, btnDat;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_cua_hang_order);

        AnhXa();

        // Lấy SharedPreferences
        SharedPreferences sharedPreferences = getSharedPreferences("user_prefs", MODE_PRIVATE);

// Lấy userId từ SharedPreferences
        String userId = sharedPreferences.getString("userId", null);  // Nếu không có giá trị, trả về null
//        Nhận _id cửa hàng từ Intent
        String IdCuaHang = getIntent().getStringExtra("IdCuaHang");
        getCuaHangDetail(IdCuaHang);

        // Xử lý sự kiện cho nút "Đặt"
        btnDat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                showConfirmationDialog(userId,IdCuaHang);


            }
        });

        // Xử lý sự kiện cho nút "Huỷ" nếu cần
        btnHuy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish(); // Kết thúc Activity hiện tại
            }
        });

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    private void getCuaHangDetail(String cuaHangId) {

        // Khởi tạo ApiService
        APIService apiService = RetrofitApp.getRetrofitInstance().create(APIService.class);

        // Gọi API để lấy chi tiết cửa hàng
        Call<CuaHang> call = apiService.getCuaHangDetail(cuaHangId);
        call.enqueue(new Callback<CuaHang>() {
            @Override
            public void onResponse(Call<CuaHang> call, Response<CuaHang> response) {
                if (response.isSuccessful() && response.body() != null) {
                    CuaHang cuaHang = response.body();
                    // Hiển thị dữ liệu lên UI

                    String imageUrl = cuaHang.getHinhAnh();

                    Glide.with(CuaHangOrder_Activity.this)
                            .load(imageUrl)
                            .apply(RequestOptions.bitmapTransform(new CenterCrop()))  // Cắt ảnh sao cho phủ đầy ImageView
                            .apply(RequestOptions.bitmapTransform(new RoundedCorners(20))) // Bo góc ảnh với bán kính 20
                            .into(imgCuaHang);  // Hiển thị ảnh trong ImageView
                } else {
                    Toast.makeText(CuaHangOrder_Activity.this, "Không thể lấy dữ liệu", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<CuaHang> call, Throwable t) {
                Toast.makeText(CuaHangOrder_Activity.this, "Lỗi kết nối: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }


    // Phương thức hiển thị hộp thoại xác nhận
    private void showConfirmationDialog(String IdKhachHang,String IdCuaHang) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Xác nhận đặt dịch vụ");
        builder.setMessage("Bạn có chắc chắn muốn đặt dịch vụ này không?");

        // Nút "Cancel"
        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss(); // Đóng hộp thoại
            }
        });

        // Nút "OK"
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                // Tạo một ArrayList để lưu các dịch vụ đã chọn
                ArrayList<String> selectedDichVu = new ArrayList<>();
                if (checkbox_valop.isChecked()) {
                    selectedDichVu.add("Vá săm, lốp");
                }
                if (checkBox_thaynhot.isChecked()) {
                    selectedDichVu.add("Thay nhớt");
                }
                if (checkBox_suadien.isChecked()) {
                    selectedDichVu.add("Sửa chữa điện");
                }
                if (checkBox_chuaro.isChecked()) {
                    selectedDichVu.add("Vấn đề chưa rõ");
                }

                String diaChi = diachi.getText().toString().trim();
                if(diaChi.isEmpty()){
                    Toast.makeText(CuaHangOrder_Activity.this, "Vui lòng nhập đủ thông tin", Toast.LENGTH_SHORT).show();
                    return;
                }
                String ghiChu = ghichu.getText().toString().trim();

                DonSuaChuaRequest donSuaChuaRequest=new DonSuaChuaRequest(
                    IdKhachHang,IdCuaHang,selectedDichVu,diaChi,ghiChu,"create"
                );
                APIService apiService = RetrofitApp.getRetrofitInstance().create(APIService.class);

                // Gửi yêu cầu đăng nhập
                Call<ResponseChung> call = apiService.createDonSuaChua(donSuaChuaRequest);
                call.enqueue(new Callback<ResponseChung>() {
                    @Override
                    public void onResponse(Call<ResponseChung> call, Response<ResponseChung> response) {
                        if (response.isSuccessful() && response.body() != null) {

                            ResponseChung donHangResponse = response.body();
                            if (donHangResponse.getCode() == 200) {
                                Toast.makeText(CuaHangOrder_Activity.this, "Đã đặt thành công", Toast.LENGTH_SHORT).show();

                                // Trì hoãn chuyển Activity trong 2 giây
                                new Handler(Looper.getMainLooper().getMainLooper()).postDelayed(new Runnable() {
                                    @Override
                                    public void run() {
                                        Intent intent = new Intent(CuaHangOrder_Activity.this, User_Home_Activity.class);
                                        startActivity(intent);
                                        finish(); // Kết thúc Activity hiện tại nếu muốn
                                    }
                                }, 1000); // 2000 milliseconds = 2 giây


                            } else {
                                Toast.makeText(CuaHangOrder_Activity.this, donHangResponse.getMessage(), Toast.LENGTH_SHORT).show();
                            }
                        } else {
                            Toast.makeText(CuaHangOrder_Activity.this, "Lỗi: " + response.code(), Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<ResponseChung> call, Throwable t) {
                        // Xử lý khi yêu cầu thất bại
                        Log.d("Lỗi láo ","Nhạc Trịnh"+t.getMessage());
                        Toast.makeText(CuaHangOrder_Activity.this, "Lỗi: " + t.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });

        // Tạo và hiển thị hộp thoại
        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }
    public void AnhXa() {
        checkbox_valop = findViewById(R.id.checkbox_valop);
        checkBox_thaynhot = findViewById(R.id.checkBox_thaynhot);
        checkBox_suadien = findViewById(R.id.checkBox_suadien);
        checkBox_chuaro = findViewById(R.id.checkBox_chuaro);
        diachi = findViewById(R.id.diachi);
        ghichu = findViewById(R.id.ghichu);
        btnHuy = findViewById(R.id.btnhuydichvu);
        btnDat = findViewById(R.id.btndatdichvu);
        imgCuaHang=findViewById(R.id.image_cuahang);
    }
}
