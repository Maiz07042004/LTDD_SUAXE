package com.example.ltdd_suaxe;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class h_login_activity extends AppCompatActivity {
        EditText input_taikhoan,input_mk;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.h_login);

        input_taikhoan = findViewById(R.id.text_taikhoan);
        input_mk = findViewById(R.id.TextPassword);
        Button btn_login = findViewById(R.id.button_login);
        btn_login.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                // Lấy giá trị từ EditText
                String taikhoan = input_taikhoan.getText().toString().trim();
                String matkhau = input_mk.getText().toString().trim();

                // Kiểm tra thông tin đăng nhập
                if (taikhoan.equals("user") && matkhau.equals("123456")) {
                    // Nếu thông tin đăng nhập đúng cho người dùng
                    Intent intent_login = new Intent(h_login_activity.this, User_Home_Activity.class);
                    startActivity(intent_login);
                } else if (taikhoan.equals("shop") && matkhau.equals("123456")) {
                    // Nếu thông tin đăng nhập đúng cho cửa hàng
                    Intent intent_login = new Intent(h_login_activity.this, CuaHang_Home_Activity.class);
                    startActivity(intent_login);
                } else {
                    // Hiển thị thông báo nếu thông tin đăng nhập không đúng
                    Toast.makeText(h_login_activity.this, "Tài khoản hoặc mật khẩu không đúng", Toast.LENGTH_SHORT).show();
                }
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