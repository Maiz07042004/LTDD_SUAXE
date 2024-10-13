package com.example.ltdd_suaxe;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

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
    Button btn;
    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_cua_hang_detail);


        btn=findViewById(R.id.datdichvu);
        AnhXa();

        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            String tencuahang = bundle.getString("ten_cua_hang");
            int likeBundle = bundle.getInt("like");
            int hinhCuaHang = bundle.getInt("img");
            String sdtBundle=bundle.getString("sdt");

            // Hiển thị dữ liệu lên view
            tenCuaHang.setText(tencuahang);
            like.setText(String.valueOf(likeBundle));
            imgCuaHang.setImageResource(hinhCuaHang);
            sdt.setText(sdtBundle);
        }

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(CuaHangDetail_Activity.this, CuaHangOrder_Activity.class);
                startActivity(intent);
            }
        });

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
    public void AnhXa(){
        tenCuaHang=findViewById(R.id.ten_cuahang);
        sdt=findViewById(R.id.phone);
        like=findViewById(R.id.like);
        imgCuaHang=findViewById(R.id.image_cuahang);
    }
}