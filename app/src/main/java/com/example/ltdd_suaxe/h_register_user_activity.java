package com.example.ltdd_suaxe;
import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class h_register_user_activity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.h_register_user);


        Button btn = findViewById(R.id.button_dangki);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(h_register_user_activity.this, h_activity_allset.class);
                startActivity(intent);
            }
        });

        // Lấy tham chiếu đến TextView
        TextView textViewOpenNewActivity = findViewById(R.id.textViewdangkicuahang);

        // Đặt sự kiện click cho TextView
        textViewOpenNewActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Tạo Intent để chuyển đến NewActivity
                Intent intent = new Intent(h_register_user_activity.this, h_begin_cua_hang_activity.class);
                startActivity(intent); // Bắt đầu Activity mới
            }
        });

    }
}