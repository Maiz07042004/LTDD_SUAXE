package com.example.ltdd_suaxe;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class h_begin_user_Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.h_begin_user); // Thiết lập layout

        // Lấy tham chiếu đến nút bắt đầu
        Button buttonNagivation = findViewById(R.id.button_batdau);
        buttonNagivation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(h_begin_user_Activity.this, h_register_user_activity.class);
                startActivity(intent);
            }
        });


        TextView textViewOpenNewActivity = findViewById(R.id.textView_dacotaikhoan_user);

            textViewOpenNewActivity.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    Intent intent = new Intent(h_begin_user_Activity.this, h_login_activity.class);
                    startActivity(intent);
                }
            });

    }
}