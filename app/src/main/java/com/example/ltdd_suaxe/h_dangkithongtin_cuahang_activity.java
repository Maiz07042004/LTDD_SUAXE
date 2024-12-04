package com.example.ltdd_suaxe;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class h_dangkithongtin_cuahang_activity extends AppCompatActivity {
    private Spinner spinnerQuan;
    private EditText editTextAddress, editTextPhone,editTextmota;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.h_dangkithongtin_cuahang_activity);

        // Khởi tạo Spinner trước khi sử dụng
        spinnerQuan = findViewById(R.id.spinner_quan);
        editTextAddress = findViewById(R.id.editTextemail);
        editTextPhone = findViewById(R.id.editTextemail2);
        editTextmota = findViewById(R.id.editTextmota);
        // Danh sách cho Spinner Quận
        String[] districts = {"Thanh Khê", "Hải Châu", "Vĩnh Trung", "Ngũ Hành Sơn"};
        ArrayAdapter<String> districtAdapter = new ArrayAdapter<>(h_dangkithongtin_cuahang_activity.this, android.R.layout.simple_spinner_item, districts);
        districtAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerQuan.setAdapter(districtAdapter);

        Button button_dangki_cuahang = findViewById(R.id.button_dangki_cuahang);
        button_dangki_cuahang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Chuyển sang Activity mới khi nhấn nút đăng ký cửa hàng
                Intent intent_dangki_cuahang = new Intent(h_dangkithongtin_cuahang_activity.this, h_activity_allset.class);
                startActivity(intent_dangki_cuahang);
            }
        });

        // Lấy tham chiếu đến TextView cho đăng ký tài khoản người dùng
        TextView textViewOpenNewActivity = findViewById(R.id.textView_dangkitaikhoanuser_thongtincuahang);
        textViewOpenNewActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String address = editTextAddress.getText().toString();
                String phone = editTextPhone.getText().toString();
                String mota = editTextmota.getText().toString();
                String selectedDistrict = spinnerQuan.getSelectedItem().toString();
                // Chuyển đến Activity đăng ký tài khoản người dùng
                Intent intent = new Intent(h_dangkithongtin_cuahang_activity.this, h_register_user_activity.class);
                startActivity(intent); // Bắt đầu Activity mới
            }
        });
    }
}
