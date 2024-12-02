package com.example.ltdd_suaxe;

import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
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
import com.example.ltdd_suaxe.Model.CuaHang;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CuaHangDetail_Activity extends AppCompatActivity {
    TextView tenCuaHang,diaChi,sdt,like,moTa;
    ImageView imgCuaHang;
    ImageView imgLuu; // Thêm ImageView cho lưu cửa hàng
    Button btn, btnCall; // Thêm nút gọi

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_cua_hang_detail);

        AnhXa();

        // Lấy SharedPreferences
        SharedPreferences sharedPreferences = getSharedPreferences("user_prefs", MODE_PRIVATE);

// Lấy userId từ SharedPreferences
        String userId = sharedPreferences.getString("userId", null);  // Nếu không có giá trị, trả về null

//        Nhận _id cửa hàng từ Intent
        String IdCuaHang = getIntent().getStringExtra("IdCuaHang");

        getCuaHangDetail(IdCuaHang);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(CuaHangDetail_Activity.this, CuaHangOrder_Activity.class);
                intent.putExtra("IdCuaHang", IdCuaHang);  // Truyền _id cửa hàng qua Intent
                startActivity(intent);
            }
        });

        imgLuu.setOnClickListener(new View.OnClickListener() { // Thêm sự kiện cho ImageView lưu
            @Override
            public void onClick(View view) {

                luuCuaHang(userId,IdCuaHang);
            }
        });

        btnCall.setOnClickListener(new View.OnClickListener() { // Thêm sự kiện cho nút gọi
            @Override
            public void onClick(View view) {
                String phoneNumber = sdt.getText().toString(); // Lấy số điện thoại từ TextView
                Intent callIntent = new Intent(Intent.ACTION_DIAL); // Hoặc Intent.ACTION_CALL để gọi trực tiếp
                callIntent.setData(Uri.parse("tel:" + phoneNumber));
                startActivity(callIntent);
            }
        });

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    public void AnhXa() {
        tenCuaHang = findViewById(R.id.ten_cuahang);
        diaChi=findViewById(R.id.diachi);
        sdt = findViewById(R.id.phone);
        like = findViewById(R.id.like);
        moTa=findViewById(R.id.text_mota);
        imgCuaHang = findViewById(R.id.image_cuahang);
        imgLuu = findViewById(R.id.luu);
        btnCall = findViewById(R.id.call_button);
        btn = findViewById(R.id.datdichvu);
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
                    tenCuaHang.setText(cuaHang.getTenCuaHang());
                    diaChi.setText(cuaHang.getDiaChi());
                    sdt.setText(cuaHang.getSDT());
                    like.setText(String.valueOf(cuaHang.getLike()));
                    moTa.setText(cuaHang.getMoTa());

                    String imageUrl = cuaHang.getHinhAnh();

                    Glide.with(CuaHangDetail_Activity.this)
                            .load(imageUrl)
                            .apply(RequestOptions.bitmapTransform(new CenterCrop()))  // Cắt ảnh sao cho phủ đầy ImageView
                            .apply(RequestOptions.bitmapTransform(new RoundedCorners(20))) // Bo góc ảnh với bán kính 20
                            .into(imgCuaHang);  // Hiển thị ảnh trong ImageView
                } else {
                    Toast.makeText(CuaHangDetail_Activity.this, "Không thể lấy dữ liệu", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<CuaHang> call, Throwable t) {
                Toast.makeText(CuaHangDetail_Activity.this, "Lỗi kết nối: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void luuCuaHang(String userId,String cuaHangId) {

        // Khởi tạo ApiService
        APIService apiService = RetrofitApp.getRetrofitInstance().create(APIService.class);

        // Tạo một map chứa cuaHangId
        Map<String, String> body = new HashMap<>();
        body.put("cuaHangId", cuaHangId);

        // Gọi API để lấy chi tiết cửa hàng
        Call<ResponseBody> call = apiService.luuCuaHang(userId,body);
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if (response.isSuccessful() && response.body() != null) {
                    Toast.makeText(CuaHangDetail_Activity.this, "Cửa hàng đã được lưu", Toast.LENGTH_SHORT).show();
                }else
                    Toast.makeText(CuaHangDetail_Activity.this, "Bạn đã lưu cửa hàng này rồi", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Toast.makeText(CuaHangDetail_Activity.this, "Lỗi kết nối: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void saveStore() {
        // Lưu tên cửa hàng vào SharedPreferences
        SharedPreferences sharedPreferences = getSharedPreferences("SavedStores", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();

        String storeName = tenCuaHang.getText().toString(); // Lấy tên cửa hàng từ TextView
        int storeLikes = Integer.parseInt(like.getText().toString()); // Lấy số lượng like
        int storeImage = R.drawable.cuahang1; // Hình ảnh cửa hàng, cần tùy chỉnh theo logic của bạn

        // Lưu thông tin cửa hàng vào SharedPreferences
        editor.putString(storeName, String.valueOf(storeLikes)); // Lưu tên và số like
        editor.apply();

        // Hiển thị thông báo
        Toast.makeText(this, "Cửa hàng đã được lưu!", Toast.LENGTH_SHORT).show();
    }
}
