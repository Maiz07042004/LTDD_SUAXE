package com.example.ltdd_suaxe;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import com.example.ltdd_suaxe.API.APIService;
import com.example.ltdd_suaxe.API.RetrofitApp;
import com.example.ltdd_suaxe.Model.CuaHang;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class nCuaHangDaLuu_Activity extends AppCompatActivity {
    ListView lvnCuaHangDaLuu;
    ArrayList<CuaHang> arraynCuaHangDaLuu;
    nCuaHangDaLuu_Adapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_ncuahangdaluu);

        AnhXa();
        // Lấy SharedPreferences
        SharedPreferences sharedPreferences = getSharedPreferences("user_prefs", MODE_PRIVATE);

// Lấy userId từ SharedPreferences
        String userId = sharedPreferences.getString("userId", null);



//        loadSavedStores(); // Tải danh sách cửa hàng đã lưu
        adapter = new nCuaHangDaLuu_Adapter(this, R.layout.ndong_cuahangdaluu, arraynCuaHangDaLuu,userId);
        lvnCuaHangDaLuu.setAdapter(adapter);

        getCuaHangDaLuu(userId);
    }

    private void AnhXa() {
        lvnCuaHangDaLuu = findViewById(R.id.list_cuaHangDaLuu);
        arraynCuaHangDaLuu = new ArrayList<>();
    }

    private void getCuaHangDaLuu(String userId){
        APIService apiService = RetrofitApp.getRetrofitInstance().create(APIService.class);
        // Gọi API để lấy danh sách cửa hàng
        Call<List<CuaHang>> call = apiService.getCuaHangDaLuu(userId);
        call.enqueue(new Callback<List<CuaHang>>() {
            @Override
            public void onResponse(Call<List<CuaHang>> call, Response<List<CuaHang>> response) {
                if (response.isSuccessful()) {
                    List<CuaHang> cuaHangList = response.body();
                    if (cuaHangList != null) {
                        // Cập nhật danh sách cửa hàng vào arrayCuaHang
                        arraynCuaHangDaLuu.clear();  // Xóa dữ liệu cũ trong danh sách
                        arraynCuaHangDaLuu.addAll(cuaHangList);  // Thêm dữ liệu mới vào

                        // Cập nhật UI sau khi thay đổi dữ liệu
                        adapter.notifyDataSetChanged();
                    } else {
                        Toast.makeText(nCuaHangDaLuu_Activity.this, "Không có dữ liệu cửa hàng", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(nCuaHangDaLuu_Activity.this, "Lỗi: " + response.message(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<List<CuaHang>> call, Throwable t) {
                Toast.makeText(nCuaHangDaLuu_Activity.this, "Lỗi kết nối: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
//    private void loadSavedStores() {
//        SharedPreferences sharedPreferences = getSharedPreferences("SavedStores", MODE_PRIVATE);
//        // Lấy tất cả các khóa (tên cửa hàng)
//        Set<String> savedStoreNames = sharedPreferences.getAll().keySet();
//
//        for (String storeName : savedStoreNames) {
//            int likes = Integer.parseInt(sharedPreferences.getString(storeName, "0")); // Số lượt like
////            arraynCuaHangDaLuu.add(new nCuaHangDaLuu(storeName, likes, R.drawable.cuahang1, R.drawable.luu)); // Hình ảnh cần tùy chỉnh
//        }
//    }
}
