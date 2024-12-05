package com.example.ltdd_suaxe;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.pm.PermissionInfoCompat;

import com.example.ltdd_suaxe.API.APIService;
import com.example.ltdd_suaxe.API.RetrofitApp;
import com.example.ltdd_suaxe.Model.DangKyCuaHangRequest;
import com.example.ltdd_suaxe.Model.DangKyKhachHangRequest;
import com.example.ltdd_suaxe.Model.ResponseChung;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class h_register_cua_hang_activity extends AppCompatActivity {
    EditText editTextEmail, editTextPhone, editTextPassword, editTextConfirmPassword;
    Button btnContinue;
    TextView openViewDangKyKhachHang;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.h_register_cua_hang_activity);
        AnhXa();

        btnContinue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = editTextEmail.getText().toString();
                String phone = editTextPhone.getText().toString();
                String password = editTextPassword.getText().toString();
                String confirmPassword = editTextConfirmPassword.getText().toString();


                // Kiểm tra xem mật khẩu và xác nhận mật khẩu có giống nhau và dài ít nhất 6 ký tự
                if (password.equals(confirmPassword)) {
                    if (password.length() >= 6) {
                        // Nếu mật khẩu và xác nhận mật khẩu khớp và dài ít nhất 6 ký tự, chuyển sang Activity tiếp theo
                        Intent intent_dangki_cuahang = new Intent(h_register_cua_hang_activity.this, h_dangkithongtin_cuahang_activity.class);
                        intent_dangki_cuahang.putExtra("email", email);
                        intent_dangki_cuahang.putExtra("phone", phone);
                        intent_dangki_cuahang.putExtra("password", password);

                        startActivity(intent_dangki_cuahang);
                    } else {
                        // Nếu mật khẩu ngắn hơn 6 ký tự, hiển thị thông báo
                        Toast.makeText(h_register_cua_hang_activity.this, "Mật khẩu phải có ít nhất 6 ký tự!", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    // Nếu mật khẩu và xác nhận mật khẩu không khớp, hiển thị thông báo
                    Toast.makeText(h_register_cua_hang_activity.this, "Mật khẩu và xác nhận mật khẩu không khớp!", Toast.LENGTH_SHORT).show();
                }

            }
        });


        // Đặt sự kiện click cho TextView
        openViewDangKyKhachHang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent_dangki_khachHang =new Intent(h_register_cua_hang_activity.this, h_register_user_activity.class);
                startActivity(intent_dangki_khachHang);
            }
        });


    }
    private void AnhXa(){
        editTextEmail = findViewById(R.id.editTextemail);
        editTextPhone = findViewById(R.id.editTextText);
        editTextPassword = findViewById(R.id.editTextTextPassword);
        editTextConfirmPassword = findViewById(R.id.editTextxnmatkhau_cuahang);
        btnContinue = findViewById(R.id.button_tieptuc_cuahang);

        openViewDangKyKhachHang = findViewById(R.id.textView_dangkitaikhoanuser_cuahang);
    }
}
