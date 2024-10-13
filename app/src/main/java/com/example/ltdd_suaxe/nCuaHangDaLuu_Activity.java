package com.example.ltdd_suaxe;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.ListView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class nCuaHangDaLuu_Activity extends AppCompatActivity {
    ListView lvnCuaHangDaLuu;
    ArrayList<nCuaHangDaLuu> arraynCuaHangDaLuu;
    nCuaHangDaLuu_Adapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_ncuahangdaluu);

        AnhXa();
        loadSavedStores(); // Tải danh sách cửa hàng đã lưu
        adapter = new nCuaHangDaLuu_Adapter(this, R.layout.ndong_cuahangdaluu, arraynCuaHangDaLuu);
        lvnCuaHangDaLuu.setAdapter(adapter);
    }

    private void AnhXa() {
        lvnCuaHangDaLuu = findViewById(R.id.list_cuaHangDaLuu);
        arraynCuaHangDaLuu = new ArrayList<>();
    }

    private void loadSavedStores() {
        SharedPreferences sharedPreferences = getSharedPreferences("SavedStores", MODE_PRIVATE);
        // Lấy tất cả các khóa (tên cửa hàng)
        Set<String> savedStoreNames = sharedPreferences.getAll().keySet();

        for (String storeName : savedStoreNames) {
            int likes = Integer.parseInt(sharedPreferences.getString(storeName, "0")); // Số lượt like
            arraynCuaHangDaLuu.add(new nCuaHangDaLuu(storeName, likes, R.drawable.cuahang1, R.drawable.luu)); // Hình ảnh cần tùy chỉnh
        }
    }
}
