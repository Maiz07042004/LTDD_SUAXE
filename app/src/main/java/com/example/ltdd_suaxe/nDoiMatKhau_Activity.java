package com.example.ltdd_suaxe;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.ltdd_suaxe.API.APIService;
import com.example.ltdd_suaxe.API.RetrofitApp;
import com.example.ltdd_suaxe.Model.DoiMatKhauRequest;
import com.example.ltdd_suaxe.Model.ResponseChung;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class nDoiMatKhau_Activity extends AppCompatActivity {

    private Button btnDoiMK;
    private EditText oldPassword, newPassword, confirmPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_ndoi_mat_khau);

        AnhXa();
        // Lấy SharedPreferences
        SharedPreferences sharedPreferences =getSharedPreferences("user_prefs", MODE_PRIVATE);

// Lấy userId từ SharedPreferences
        String userId = sharedPreferences.getString("userId", null);  // Nếu không có giá trị, trả về null
        String cuaHangId = sharedPreferences.getString("cuaHangId", null);

        // Xử lý sự kiện click cho nút
        btnDoiMK.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Kiểm tra xem các trường mật khẩu có trống hay không
                if (oldPassword.getText().toString().isEmpty() ||
                        newPassword.getText().toString().isEmpty() ||
                        confirmPassword.getText().toString().isEmpty()) {

                    // Hiển thị thông báo lỗi nếu bất kỳ trường nào trống
                    showAlert("Lỗi", "Vui lòng nhập đầy đủ thông tin!",false);
                } else if (!newPassword.getText().toString().equals(confirmPassword.getText().toString())) {
                    // Hiển thị thông báo lỗi nếu mật khẩu mới và xác nhận không khớp
                    showAlert("Lỗi", "Mật khẩu mới và xác nhận mật khẩu không khớp!",false);
                }else {
                    DoiMatKhauRequest doiMatKhauRequest = new DoiMatKhauRequest(
                            oldPassword.getText().toString(), newPassword.getText().toString());
                    if (cuaHangId != null) {
                        // Cập nhật mật khẩu cho cửa hàng
                        DoiMatKhauCuaHang(cuaHangId, doiMatKhauRequest);
                    } else if (userId != null) {
                        // Cập nhật mật khẩu cho khách hàng
                        DoiMatKhauKhachHang(userId, doiMatKhauRequest);
                    } else {
                        // Nếu không có cuaHangId hay userId
                        showAlert("Lỗi", "Không tìm thấy thông tin người dùng!", false);
                    }
                }
            }
        });

        // Xử lý insets để điều chỉnh layout
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    private void showAlert(String title, String message, boolean success) {
        AlertDialog.Builder builder = new AlertDialog.Builder(nDoiMatKhau_Activity.this);
        builder.setTitle(title);
        builder.setMessage(message);

        // Nút đóng hộp thoại
        builder.setPositiveButton("Đóng", (dialog, which) -> {
            dialog.dismiss();
            if (success) {
                // Tạo một Handler để chuyển Activity sau 1 giây
                new Handler().postDelayed(() -> {
                    SharedPreferences sharedPreferences = getSharedPreferences("user_prefs", MODE_PRIVATE);
                    String cuaHangId = sharedPreferences.getString("cuaHangId", null); // Lấy cuaHangId từ SharedPreferences

                    Intent intent;
                    if (cuaHangId != null) {
                        // Nếu là cửa hàng, chuyển tới CuaHang_Home_Activity
                        intent = new Intent(nDoiMatKhau_Activity.this, CuaHang_Home_Activity.class);
                    } else {
                        // Nếu là khách hàng, chuyển tới User_Home_Activity
                        intent = new Intent(nDoiMatKhau_Activity.this, User_Home_Activity.class);
                    }
                    startActivity(intent);
                    finish(); // Kết thúc Activity hiện tại nếu không cần quay lại
                }, 1000); // Độ trễ 1 giây
            }
        });

        // Tạo và hiển thị hộp thoại
        AlertDialog dialog = builder.create();
        dialog.show();
    }

    private  void DoiMatKhauKhachHang(String userId, DoiMatKhauRequest doiMatKhauRequest){
// Khởi tạo APIService
        APIService apiService = RetrofitApp.getRetrofitInstance().create(APIService.class);

        // Gọi API cập nhật thông tin khách hàng
        Call<ResponseChung> call = apiService.updatePasswordUser(userId, doiMatKhauRequest);
        call.enqueue(new Callback<ResponseChung>() {
            @Override
            public void onResponse(Call<ResponseChung> call, Response<ResponseChung> response) {
                if (response.isSuccessful()) {
                    if(response.body().getCode()==200){
                        showAlert("Thông báo", "Bạn đã đổi mật khẩu thành công!",true);
                    } else{
                        showAlert("Lỗi", response.body().getMessage(),false);
                    }
                } else {
                    // Thông báo lỗi
                    Toast.makeText(nDoiMatKhau_Activity.this, "Cập nhật thất bại, vui lòng thử lại!", Toast.LENGTH_SHORT).show();
                }
            }
            @Override
            public void onFailure(Call<ResponseChung> call, Throwable t) {
                // Thông báo lỗi kết nối
                Toast.makeText(nDoiMatKhau_Activity.this, "Lỗi kết nối: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
    private void DoiMatKhauCuaHang(String cuaHangId, DoiMatKhauRequest doiMatKhauRequest) {
        // Khởi tạo APIService
        APIService apiService = RetrofitApp.getRetrofitInstance().create(APIService.class);

        // Gọi API cập nhật mật khẩu cho cửa hàng
        Call<ResponseChung> call = apiService.updatePasswordCuaHang(cuaHangId, doiMatKhauRequest);
        call.enqueue(new Callback<ResponseChung>() {
            @Override
            public void onResponse(Call<ResponseChung> call, Response<ResponseChung> response) {
                if (response.isSuccessful()) {
                    if (response.body().getCode() == 200) {
                        showAlert("Thông báo", "Bạn đã đổi mật khẩu cửa hàng thành công!", true);
                    } else {
                        showAlert("Lỗi", response.body().getMessage(), false);
                    }
                } else {
                    // Thông báo lỗi
                    Toast.makeText(nDoiMatKhau_Activity.this, "Cập nhật thất bại, vui lòng thử lại!", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ResponseChung> call, Throwable t) {
                // Thông báo lỗi kết nối
                Toast.makeText(nDoiMatKhau_Activity.this, "Lỗi kết nối: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
    private void AnhXa(){
        oldPassword = findViewById(R.id.editTextTextPassword);
        newPassword = findViewById(R.id.editTextTextPassword2);
        confirmPassword = findViewById(R.id.editTextTextPassword3);
        btnDoiMK = findViewById(R.id.nbtn_doimk);
    }
}
