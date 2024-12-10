package com.example.ltdd_suaxe;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.ltdd_suaxe.API.APIService;
import com.example.ltdd_suaxe.API.RetrofitApp;
import com.example.ltdd_suaxe.Model.LoginRequest;
import com.example.ltdd_suaxe.Model.LoginResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class h_login_activity extends AppCompatActivity {
    private EditText edtSDT, edtPassword;

    EditText input_taikhoan,input_mk;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.h_login);

        CheckBox checkBoxRememberMe = findViewById(R.id.checkBox);
        edtSDT  = findViewById(R.id.text_SDT);
        edtPassword  = findViewById(R.id.textPassword);
        Button btn_login = findViewById(R.id.button_login);
        btn_login.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Login();
            }
        });

        // Lấy tham chiếu đến TextView
        TextView tvDangKy = findViewById(R.id.textView_dangkitaikhoan_login);
        // Đặt sự kiện click cho TextView
        tvDangKy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Tạo Intent để chuyển đến NewActivity
                Intent intent = new Intent(h_login_activity.this, h_begin_user_Activity.class);
                startActivity(intent); // Bắt đầu Activity mới
            }
        });
        // Lấy tham chiếu đến TextView
        TextView tvDangNhapShop = findViewById(R.id.dangnhap_shop);
        // Đặt sự kiện click cho TextView
        tvDangNhapShop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Tạo Intent để chuyển đến NewActivity
                Intent intent = new Intent(h_login_activity.this, Login_CuaHang_Activity.class);
                startActivity(intent); // Bắt đầu Activity mới
            }
        });

    }
    private void Login() {
        String sdt = edtSDT.getText().toString().trim();
        String password = edtPassword.getText().toString().trim();
        CheckBox checkBoxRememberMe = findViewById(R.id.checkBox);

        if (sdt.isEmpty() || password.isEmpty()) {
            Toast.makeText(this, "Vui lòng nhập đủ thông tin", Toast.LENGTH_SHORT).show();
            return;
        }

        // Tạo request body
        LoginRequest loginRequest = new LoginRequest(sdt, password);


        // Tạo đối tượng ApiService
        APIService apiService = RetrofitApp.getRetrofitInstance().create(APIService.class);

        // Gửi yêu cầu đăng nhập
        Call<LoginResponse> call = apiService.loginUser(loginRequest);
        call.enqueue(new Callback<LoginResponse>() {
            @Override
            public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {
                if (response.isSuccessful() && response.body() != null) {
                    // Xử lý khi đăng nhập thành công
                    LoginResponse loginResponse = response.body();
                    if (loginResponse.getCode() == 200) {
                        Toast.makeText(h_login_activity.this, "Đăng nhập thành công", Toast.LENGTH_SHORT).show();

                        // Lưu userId vào SharedPreferences
                        SharedPreferences sharedPreferences = getSharedPreferences("user_prefs", MODE_PRIVATE);
                        SharedPreferences.Editor editor = sharedPreferences.edit();
                        editor.putString("userId", loginResponse.getUserId());
                        // Kiểm tra nếu người dùng tick "Ghi nhớ mật khẩu"
                        if (checkBoxRememberMe.isChecked()) {
                            editor.putString("rememberPassUser", "1");
                        } else {
                            editor.remove("rememberPassUser");
                        }// Lưu userId
                        editor.apply();  // Áp dụng thay đổi

                        // Chuyển tới màn hình Home
                        Intent intent_login = new Intent(h_login_activity.this, User_Home_Activity.class);
                        startActivity(intent_login);
                    } else {
                        Toast.makeText(h_login_activity.this, loginResponse.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(h_login_activity.this, "Đăng nhập thất bại: " + response.code(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<LoginResponse> call, Throwable t) {
                // Xử lý khi yêu cầu thất bại
                Log.d("Lỗi láo ","Nhạc Trịnh"+t.getMessage());
                Toast.makeText(h_login_activity.this, "Lỗi: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}