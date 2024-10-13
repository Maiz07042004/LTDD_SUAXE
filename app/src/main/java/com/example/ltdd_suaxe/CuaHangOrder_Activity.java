package com.example.ltdd_suaxe;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.ltdd_suaxe.fragment_user.ListFragmentUser;

public class CuaHangOrder_Activity extends AppCompatActivity {
    Button btnHuy, btnDat;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_cua_hang_order);

        btnHuy = findViewById(R.id.btnhuydichvu);
        btnDat = findViewById(R.id.btndatdichvu);

        // Xử lý sự kiện cho nút "Đặt"
        btnDat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showConfirmationDialog();
            }
        });

        // Xử lý sự kiện cho nút "Huỷ" nếu cần
        btnHuy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish(); // Kết thúc Activity hiện tại
            }
        });

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    // Phương thức hiển thị hộp thoại xác nhận
    private void showConfirmationDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Xác nhận đặt dịch vụ");
        builder.setMessage("Bạn có chắc chắn muốn đặt dịch vụ này không?");

        // Nút "Cancel"
        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss(); // Đóng hộp thoại
            }
        });

        // Nút "OK"
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                // Hiển thị thông báo Toast
                Toast.makeText(CuaHangOrder_Activity.this, "Đã đặt thành công", Toast.LENGTH_SHORT).show();

                // Trì hoãn chuyển Activity trong 2 giây
                new Handler(Looper.getMainLooper().getMainLooper()).postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        Intent intent = new Intent(CuaHangOrder_Activity.this, User_Home_Activity.class);
                        startActivity(intent);
                        finish(); // Kết thúc Activity hiện tại nếu muốn
                    }
                }, 1000); // 2000 milliseconds = 2 giây
            }
        });

        // Tạo và hiển thị hộp thoại
        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }
}
