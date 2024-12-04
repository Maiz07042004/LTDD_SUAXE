package com.example.ltdd_suaxe;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class h_begin_cua_hang_activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.h_begin_cua_hang_activity);

        Button btn_begin_cuahang = findViewById(R.id.button_begin_cuahang);
        btn_begin_cuahang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent_begin_cuaHang = new Intent(h_begin_cua_hang_activity.this,h_register_cua_hang_activity.class);
                startActivity(intent_begin_cuaHang);
            }
        });


        TextView textViewOpenNewActivity = findViewById(R.id.textView_dangnhap_cuahang);

        textViewOpenNewActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(h_begin_cua_hang_activity.this, h_login_activity.class);
                startActivity(intent);
            }
        });
    }
}
