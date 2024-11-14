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

public class CuaHangDetail_Activity extends AppCompatActivity {
    TextView tenCuaHang;
    TextView sdt;
    TextView like;
    ImageView imgCuaHang;
    ImageView imgLuu; // Thêm ImageView cho lưu cửa hàng
    Button btn, btnCall; // Thêm nút gọi

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_cua_hang_detail);

        btn = findViewById(R.id.datdichvu);
        AnhXa();

        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            String tencuahang = bundle.getString("ten_cua_hang");
            int likeBundle = bundle.getInt("like");
            int hinhCuaHang = bundle.getInt("img");
            String sdtBundle = bundle.getString("sdt");

            // Hiển thị dữ liệu lên view
            tenCuaHang.setText(tencuahang);
            like.setText(String.valueOf(likeBundle));
            imgCuaHang.setImageResource(hinhCuaHang);
            sdt.setText(sdtBundle);
        }

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(CuaHangDetail_Activity.this, CuaHangOrder_Activity.class);
                startActivity(intent);
            }
        });

        imgLuu.setOnClickListener(new View.OnClickListener() { // Thêm sự kiện cho ImageView lưu
            @Override
            public void onClick(View view) {
                saveStore();
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
        sdt = findViewById(R.id.phone);
        like = findViewById(R.id.like);
        imgCuaHang = findViewById(R.id.image_cuahang);
        imgLuu = findViewById(R.id.luu); // Khởi tạo ImageView lưu
        btnCall = findViewById(R.id.call_button); // Khởi tạo nút gọi
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
