package com.example.ltdd_suaxe;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class nDoiMatKhau_Activity extends AppCompatActivity {

    private Button btnDoiMK;
    private EditText oldPassword, newPassword, confirmPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_ndoi_mat_khau);

        // Tìm các EditText theo ID
        oldPassword = findViewById(R.id.editTextTextPassword);
        newPassword = findViewById(R.id.editTextTextPassword2);
        confirmPassword = findViewById(R.id.editTextTextPassword3);

        // Tìm nút theo ID
        btnDoiMK = findViewById(R.id.nbtn_doimk);

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
                } else {
                    // Hiển thị thông báo thành công nếu mọi thứ hợp lệ
                    showAlert("Thông báo", "Bạn đã đổi mật khẩu thành công!",true);
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
                    Intent intent = new Intent(nDoiMatKhau_Activity.this, User_Home_Activity.class); // Thay YourNextActivity bằng Activity bạn muốn chuyển tới
                    startActivity(intent);
                    finish(); // Kết thúc Activity hiện tại nếu không cần quay lại
                }, 1000); // Độ trễ 1 giây
            }
        });

        // Tạo và hiển thị hộp thoại
        AlertDialog dialog = builder.create();
        dialog.show();
    }
}
