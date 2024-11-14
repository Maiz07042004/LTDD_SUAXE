package com.example.ltdd_suaxe;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;


import androidx.appcompat.app.AppCompatActivity;
public class h_chitietdathang_danhgia extends AppCompatActivity {
    Button btn_gui;
    EditText input_ykien;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_hchitietdathang_danhgia);


        // Ánh xạ nút "Đặt lại"
        btn_gui = findViewById(R.id.buttongui);


        // Gán sự kiện click cho nút "Đặt lại"
        btn_gui.setOnClickListener(view -> {


            input_ykien = findViewById(R.id.edittextykien);
            String yKien = input_ykien.getText().toString().trim();
            if (yKien.equals("")) {
                // Nếu thông tin đăng nhập đúng cho người dùng
                Toast.makeText(h_chitietdathang_danhgia.this, "Ý kiến không được để trống", Toast.LENGTH_SHORT).show();
            }else {
                // Hiển thị thông báo nếu thông tin đăng nhập không đúng
                Toast.makeText(h_chitietdathang_danhgia.this, "Cảm ơn bạn đã đánh giá dịch vụ của chúng tôi!", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(h_chitietdathang_danhgia.this, nChiTietDatHang_AcTiViTy.class);
                startActivity(intent);
            }

        });




}
}
