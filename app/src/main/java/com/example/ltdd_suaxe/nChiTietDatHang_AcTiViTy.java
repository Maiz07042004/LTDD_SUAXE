package com.example.ltdd_suaxe;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.CenterCrop;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.request.RequestOptions;

public class nChiTietDatHang_AcTiViTy extends AppCompatActivity {
    Button btnDatLai, btndanhgia;
    TextView tenCuaHang, dichvu, trangthai, ngayDat;
    ImageView imgCuaHang;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_nchitietdathang);
        AnhXa();
//        // Nhận dữ liệu từ Intent
//        Intent intent = getIntent();
//        String tenCuaHangValue = intent.getStringExtra("TenCuaHang");
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



        // Gán sự kiện click cho nút "Đặt lại"
        btnDatLai.setOnClickListener(view -> {
            // Truyền dữ liệu khi bấm nút
            Intent newIntent = new Intent(nChiTietDatHang_AcTiViTy.this, CuaHangDetail_Activity.class);

            startActivity(newIntent);
        });

        btndanhgia.setOnClickListener(view -> {
            // Chuyển đến màn hình đánh giá
            Intent ratingIntent = new Intent(nChiTietDatHang_AcTiViTy.this, h_chitietdathang_danhgia.class);

            startActivity(ratingIntent);
        });

        // Sử dụng để căn chỉnh view cho hệ thống gesture navigation (nếu cần)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    public void AnhXa() {
        btnDatLai = findViewById(R.id.buttondatlai);
        btndanhgia = findViewById(R.id.buttondanhgia);
        tenCuaHang = findViewById(R.id.ten_cuahang);
        dichvu = findViewById(R.id.dichvu);
        trangthai = findViewById(R.id.trangthai);
        ngayDat = findViewById(R.id.ngaydat);
        imgCuaHang = findViewById(R.id.image_cuahang);

    }
}
