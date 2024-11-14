package com.example.ltdd_suaxe;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class nChiTietDatHang_AcTiViTy extends AppCompatActivity {
    Button btnDatLai,btndanhgia;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_nchitietdathang);


        // Ánh xạ nút "Đặt lại"
        btnDatLai = findViewById(R.id.buttondatlai);
        btndanhgia= findViewById(R.id.buttondanhgia);

        // Gán sự kiện click cho nút "Đặt lại"
        btnDatLai.setOnClickListener(view -> {
            // Chuyển đến CuaHangDetail_Activity mà không cần truyền dữ liệu
            Intent intent = new Intent(nChiTietDatHang_AcTiViTy.this, CuaHangDetail_Activity.class);
            startActivity(intent);
        });

        btndanhgia.setOnClickListener(view ->{
            // Chuyển đến CuaHangDetail_Activity mà không cần truyền dữ liệu
            Intent intent = new Intent(nChiTietDatHang_AcTiViTy.this, h_chitietdathang_danhgia.class);
            startActivity(intent);
        });

        // Sử dụng để căn chỉnh view cho hệ thống gesture navigation (nếu cần)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
}
