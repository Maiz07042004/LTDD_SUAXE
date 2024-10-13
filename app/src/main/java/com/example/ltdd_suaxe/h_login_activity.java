package com.example.ltdd_suaxe;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class h_login_activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.h_login);


        Button btn_login = findViewById(R.id.button_login);
        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent_login = new Intent(h_login_activity.this,User_Home_Activity.class);
                startActivity(intent_login);
            }
        });

        // Lấy tham chiếu đến TextView
        TextView textViewOpenNewActivity = findViewById(R.id.textView_dangkitaikhoan_login);

        // Đặt sự kiện click cho TextView
        textViewOpenNewActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Tạo Intent để chuyển đến NewActivity
                Intent intent = new Intent(h_login_activity.this, h_begin_user_Activity.class);
                startActivity(intent); // Bắt đầu Activity mới
            }
        });

    }


}