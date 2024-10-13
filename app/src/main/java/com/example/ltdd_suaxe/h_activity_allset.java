package com.example.ltdd_suaxe;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class h_activity_allset extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.h_activity_allset);


        Button btn_dangnhap = findViewById(R.id.button_dangnhap_allset);
        btn_dangnhap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent_dangnhap = new Intent(h_activity_allset.this, h_login_activity.class);
                startActivity(intent_dangnhap);
            }
        });
    }
}

