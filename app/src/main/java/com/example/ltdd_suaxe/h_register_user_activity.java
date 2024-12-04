package com.example.ltdd_suaxe;
import android.content.Intent;
import android.content.SharedPreferences;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.ltdd_suaxe.API.APIService;
import com.example.ltdd_suaxe.API.RetrofitApp;
import com.example.ltdd_suaxe.Model.DangKyKhachHangRequest;
import com.example.ltdd_suaxe.Model.LoginResponse;
import com.example.ltdd_suaxe.Model.ResponseChung;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class h_register_user_activity extends AppCompatActivity {
    private EditText editTextTenKhachHang, editTextPhone, editTextPassword, editTextEmail, editTextXnMatKhau;
    private RadioGroup radioGroupGioiTinh;
    private Button buttonDangKi;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.h_register_user);
        AnhXa();

        buttonDangKi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String tenKhachHang = editTextTenKhachHang.getText().toString();
                String phone = editTextPhone.getText().toString();
                String password = editTextPassword.getText().toString();
                String email = editTextEmail.getText().toString();
                String xnMatKhau = editTextXnMatKhau.getText().toString();

                // Lấy giới tính từ RadioGroup
                int selectedGenderId = radioGroupGioiTinh.getCheckedRadioButtonId();
                RadioButton selectedGenderRadioButton = findViewById(selectedGenderId);
                String gioiTinh = selectedGenderRadioButton != null ? selectedGenderRadioButton.getText().toString() : "";

                // Kiểm tra dữ liệu có hợp lệ không (ví dụ, kiểm tra trường nhập)
                if (tenKhachHang.isEmpty() || phone.isEmpty() || password.isEmpty() || email.isEmpty() || xnMatKhau.isEmpty()) {
                    Toast.makeText(h_register_user_activity.this, "Vui lòng điền đầy đủ thông tin.", Toast.LENGTH_SHORT).show();
                } else if (!password.equals(xnMatKhau)) {
                    Toast.makeText(h_register_user_activity.this, "Mật khẩu và xác nhận mật khẩu không khớp.", Toast.LENGTH_SHORT).show();
                } else {
                    DangKyKhachHangRequest dangKyKhachHangRequest=new DangKyKhachHangRequest(email,gioiTinh,password,phone,tenKhachHang);
                    DangKyKhachHang(dangKyKhachHangRequest);
                }
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
    private void DangKyKhachHang(DangKyKhachHangRequest dangKyKhachHangRequest){
        // Tạo đối tượng ApiService
        APIService apiService = RetrofitApp.getRetrofitInstance().create(APIService.class);

        // Gửi yêu cầu đăng ký
        Call<ResponseChung> call = apiService.registerUser(dangKyKhachHangRequest);
        call.enqueue(new Callback<ResponseChung>() {
            @Override
            public void onResponse(Call<ResponseChung> call, Response<ResponseChung> response) {
                if (response.isSuccessful() && response.body() != null) {
                    // Xử lý khi đăng nhập thành công
                    ResponseChung registerResponse = response.body();
                    if (registerResponse.getCode() == 200) {
                        Toast.makeText(h_register_user_activity.this, "Đăng ký thành công", Toast.LENGTH_SHORT).show();

                        // Chuyển tới màn hình Login
                        Intent intent_login = new Intent(h_register_user_activity.this, h_activity_allset.class);
                        startActivity(intent_login);
                    } else {
                        Toast.makeText(h_register_user_activity.this, registerResponse.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(h_register_user_activity.this, "Đăng ký thất bại: " + response.code(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ResponseChung> call, Throwable t) {
                // Xử lý khi yêu cầu thất bại
                Log.d("Lỗi láo ","Nhạc Trịnh"+t.getMessage());
                Toast.makeText(h_register_user_activity.this, "Lỗi: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
    private void AnhXa(){
        editTextTenKhachHang = findViewById(R.id.editTextTenKhachHang);
        editTextPhone = findViewById(R.id.editTextText);
        editTextPassword = findViewById(R.id.editTextTextPassword);
        editTextEmail = findViewById(R.id.editTextemail);
        editTextXnMatKhau = findViewById(R.id.editTextxnmatkhau);
        radioGroupGioiTinh = findViewById(R.id.radioGroupGioiTinh);
        buttonDangKi = findViewById(R.id.button_dangki);
    }
}