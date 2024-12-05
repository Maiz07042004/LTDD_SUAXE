package com.example.ltdd_suaxe;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.ltdd_suaxe.API.APIService;
import com.example.ltdd_suaxe.API.RetrofitApp;
import com.example.ltdd_suaxe.Model.DangKyCuaHangRequest;
import com.example.ltdd_suaxe.Model.ResponseChung;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class h_dangkithongtin_cuahang_activity extends AppCompatActivity {
    private Spinner spinnerQuan;
    private EditText editTextAddress, editTextTen,editTextmota;
    private Button button_dangki_cuahang;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.h_dangkithongtin_cuahang_activity);
        AnhXa();

        // Danh sách cho Spinner Quận
        String[] districts = {"Thanh Khê", "Hải Châu", "Ngũ Hành Sơn","Sơn Trà","Liên Chiểu","Cẩm Lệ"};
        ArrayAdapter<String> districtAdapter = new ArrayAdapter<>(h_dangkithongtin_cuahang_activity.this, android.R.layout.simple_spinner_item, districts);
        districtAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerQuan.setAdapter(districtAdapter);


        button_dangki_cuahang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = getIntent();
                String email = intent.getStringExtra("email");
                String phone = intent.getStringExtra("phone");
                String password = intent.getStringExtra("password");

                String address = editTextAddress.getText().toString();
                String ten = editTextTen.getText().toString();
                String mota = editTextmota.getText().toString();
                String selectedDistrict = spinnerQuan.getSelectedItem().toString();

                DangKyCuaHangRequest dangKyCuaHangRequest=new DangKyCuaHangRequest(address,email,mota,password,phone,ten,selectedDistrict);
                DangKyCuaHang(dangKyCuaHangRequest);
            }
        });

        // Lấy tham chiếu đến TextView cho đăng ký tài khoản người dùng
        TextView textViewOpenNewActivity = findViewById(R.id.textView_dangkitaikhoanuser_thongtincuahang);
        textViewOpenNewActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                // Chuyển đến Activity đăng ký tài khoản người dùng
                Intent intent = new Intent(h_dangkithongtin_cuahang_activity.this, h_register_user_activity.class);
                startActivity(intent); // Bắt đầu Activity mới
            }
        });
    }

    private void DangKyCuaHang(DangKyCuaHangRequest dangKyCuaHangRequest){
        // Tạo đối tượng ApiService
        APIService apiService = RetrofitApp.getRetrofitInstance().create(APIService.class);

        // Gửi yêu cầu đăng ký
        Call<ResponseChung> call = apiService.registerCuaHang(dangKyCuaHangRequest);
        call.enqueue(new Callback<ResponseChung>() {
            @Override
            public void onResponse(Call<ResponseChung> call, Response<ResponseChung> response) {
                if (response.isSuccessful() && response.body() != null) {
                    // Xử lý khi đăng nhập thành công
                    ResponseChung registerResponse = response.body();
                    if (registerResponse.getCode() == 200) {
                        Toast.makeText(h_dangkithongtin_cuahang_activity.this, "Đăng ký thành công", Toast.LENGTH_SHORT).show();

                        // Chuyển tới màn hình Login
                        Intent intent_login = new Intent(h_dangkithongtin_cuahang_activity.this, h_activity_allset.class);
                        startActivity(intent_login);
                    } else {
                        Toast.makeText(h_dangkithongtin_cuahang_activity.this, registerResponse.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(h_dangkithongtin_cuahang_activity.this, "Đăng ký thất bại: " + response.code(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ResponseChung> call, Throwable t) {
                // Xử lý khi yêu cầu thất bại
                Log.d("Lỗi láo ","Nhạc Trịnh"+t.getMessage());
                Toast.makeText(h_dangkithongtin_cuahang_activity.this, "Lỗi: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
    private void AnhXa(){
        // Khởi tạo Spinner trước khi sử dụng
        spinnerQuan = findViewById(R.id.spinner_quan);
        editTextAddress = findViewById(R.id.editTextemail);
        editTextTen = findViewById(R.id.editTextemail2);
        editTextmota = findViewById(R.id.editTextmota);
        button_dangki_cuahang = findViewById(R.id.button_dangki_cuahang);
    }
}
