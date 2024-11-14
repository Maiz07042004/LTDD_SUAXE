package com.example.ltdd_suaxe;

import android.os.Bundle;
import android.widget.ListView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;
import java.util.List;

public class ThongBao_Activity extends AppCompatActivity {

    private ListView listView,listView1;
    private ThongBaoAdapter adapter,adapter1;
    private List<ThongBao> notificationList,notificationListCu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thong_bao);

        // Khởi tạo ListView
        listView = findViewById(R.id.listViewNotifications);
        listView1 = findViewById(R.id.listTBcu);

        // Khởi tạo danh sách thông báo
        notificationList = new ArrayList<>();
        notificationList.add(new ThongBao("Thanh Mai Garage", "Đã xác nhận sửa chữa"));

        notificationListCu = new ArrayList<>();
        notificationListCu.add(new ThongBao("Nhật Bike", "Đã xác nhận sửa chữa"));

        // Tạo adapter và gán vào ListView
        adapter = new ThongBaoAdapter(this, notificationList);
        listView.setAdapter(adapter);

        adapter1 = new ThongBaoAdapter(this, notificationListCu);
        listView1.setAdapter(adapter1);

        // Sự kiện nhấp vào mục trong ListView
        listView.setOnItemClickListener((parent, view, position, id) -> {
            ThongBao notification = notificationList.get(position);
            Toast.makeText(ThongBao_Activity.this, "Đã chọn: " + notification.getTitle(), Toast.LENGTH_SHORT).show();
        });
    }
}