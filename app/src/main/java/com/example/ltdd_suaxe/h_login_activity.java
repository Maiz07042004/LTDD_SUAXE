package com.example.ltdd_suaxe;

import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.h_login);

        CheckBox checkBoxRememberMe = findViewById(R.id.checkBox);
        edtSDT  = findViewById(R.id.text_SDT);
        edtPassword  = findViewById(R.id.textPassword);
        // ImageView Instagram
        ImageView instagram = findViewById(R.id.instaimage);
        instagram.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openWebPage("https://www.instagram.com/");
            }
        });

        // ImageView Facebook
        ImageView facebook = findViewById(R.id.facebookimage);
        facebook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openWebPage("https://www.facebook.com/");
            }
        });

        // ImageView Twitter
        ImageView twitter = findViewById(R.id.twiterimage);
        twitter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openWebPage("https://www.twitter.com/");
            }
        });

        // Tham chiếu tới các EditText và Button
        edtSDT = findViewById(R.id.text_SDT);
        edtPassword = findViewById(R.id.textPassword);
        Button btn_login = findViewById(R.id.button_login);

        // Đặt sự kiện click cho nút login
        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Login();
            }
        });

        // Tham chiếu và xử lý sự kiện click cho TextView đăng ký tài khoản
        TextView tvDangKy = findViewById(R.id.textView_dangkitaikhoan_login);
        tvDangKy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(h_login_activity.this, h_begin_user_Activity.class);
                startActivity(intent);
            }
        });

        // Tham chiếu và xử lý sự kiện click cho TextView đăng nhập shop
        TextView tvDangNhapShop = findViewById(R.id.dangnhap_shop);
        tvDangNhapShop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(h_login_activity.this, Login_CuaHang_Activity.class);
                startActivity(intent);
            }
        });
    }

    // Phương thức mở liên kết web
    private void openWebPage(String url) {
        Uri webpage = Uri.parse(url);
        Intent intent = new Intent(Intent.ACTION_VIEW, webpage);
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        }
    }

    // Phương thức xử lý đăng nhập
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
                Log.d("Lỗi đăng nhập", "Chi tiết lỗi: " + t.getMessage());
                Toast.makeText(h_login_activity.this, "Lỗi: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}
