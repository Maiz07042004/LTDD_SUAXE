package com.example.ltdd_suaxe;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.pm.PermissionInfoCompat;

public class h_register_cua_hang_activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.h_register_cua_hang_activity);
        Button button_dangki_cuahang= findViewById(R.id.button_tieptuc_cuahang);
        button_dangki_cuahang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent_dangki_cuahang =new Intent(h_register_cua_hang_activity.this, h_dangkithongtin_cuahang_activity.class);
                startActivity(intent_dangki_cuahang);
            }
        });

        // Lấy tham chiếu đến TextView
        TextView textViewOpenNewActivity = findViewById(R.id.textView_dangkitaikhoanuser_cuahang);

        // Đặt sự kiện click cho TextView
        textViewOpenNewActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Tạo Intent để chuyển đến NewActivity
                Intent intent = new Intent(h_register_cua_hang_activity.this, h_dangkithongtin_cuahang_activity.class);
                startActivity(intent); // Bắt đầu Activity mới
            }
        });


    }
}
