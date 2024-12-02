package com.example.ltdd_suaxe;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.ltdd_suaxe.API.APIService;
import com.example.ltdd_suaxe.API.RetrofitApp;
import com.example.ltdd_suaxe.Model.CuaHang;
import com.example.ltdd_suaxe.Model.Quan;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class User_CuaHang_Activity extends AppCompatActivity {
    ListView lvCuaHang;
    ArrayList<CuaHang> arrayCuaHang;
    CuaHangAdapter adapter;
    private String IdQuan,TenQuan;
    TextView tvQuan;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_cuahang);

        // Nhận _id quận từ Intent
        IdQuan = getIntent().getStringExtra("IdQuan");
        TenQuan = getIntent().getStringExtra("TenQuan");



        AnhXa();
        tvQuan.setText(TenQuan);
        adapter= new CuaHangAdapter(this,R.layout.dong_cuahang,arrayCuaHang);
        lvCuaHang.setAdapter(adapter);

        APIService apiService = RetrofitApp.getRetrofitInstance().create(APIService.class);
        // Gọi API để lấy danh sách cửa hàng
        // Gọi API để lấy danh sách cửa hàng
        Call<List<CuaHang>> call = apiService.getCuaHangListById(IdQuan);
        call.enqueue(new Callback<List<CuaHang>>() {
            @Override
            public void onResponse(Call<List<CuaHang>> call, Response<List<CuaHang>> response) {
                if (response.isSuccessful()) {
                    List<CuaHang> cuaHangList = response.body();
                    if (cuaHangList != null) {
                        // Cập nhật danh sách cửa hàng vào arrayCuaHang
                        arrayCuaHang.clear();  // Xóa dữ liệu cũ trong danh sách
                        arrayCuaHang.addAll(cuaHangList);  // Thêm dữ liệu mới vào

                        // Cập nhật UI sau khi thay đổi dữ liệu
                        adapter.notifyDataSetChanged();
                    } else {
                        Toast.makeText(User_CuaHang_Activity.this, "Không có dữ liệu cửa hàng", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(User_CuaHang_Activity.this, "Lỗi: " + response.message(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<List<CuaHang>> call, Throwable t) {
                Toast.makeText(User_CuaHang_Activity.this, "Lỗi kết nối: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });


        // Sự kiện click vào một cửa hàng trong ListView
        lvCuaHang.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                CuaHang selectedCuaHang = arrayCuaHang.get(i);

                // Tạo intent để mở CuaHangDetail_Activity
                Intent intent = new Intent(User_CuaHang_Activity.this, CuaHangDetail_Activity.class);

                // Tạo Bundle để chứa dữ liệu
//                Bundle bundle = new Bundle();
//                bundle.putString("ten_cua_hang", selectedCuaHang.getTen());
//                bundle.putInt("img", selectedCuaHang.getHinh());
//                bundle.putInt("like", selectedCuaHang.getLike());
//                bundle.putString("sdt",selectedCuaHang.getSdt());
                // Truyền _id của quận qua Intent

                intent.putExtra("IdCuaHang", selectedCuaHang.get_id());  // Truyền _id cửa hàng qua Intent
                startActivity(intent);

                // Đưa Bundle vào Intent
//                intent.putExtras(bundle);

            }
        });
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    private void AnhXa(){
        lvCuaHang=(ListView) findViewById(R.id.list_cuaHang);
        tvQuan=(TextView) findViewById(R.id.tvQuan);
        arrayCuaHang=new ArrayList<>();
//        arrayCuaHang.add(new CuaHang("Thanh Mai Garage",1000,"0364933757",R.drawable.cuahang1));
//        arrayCuaHang.add(new CuaHang("Nhật Bike",2000,"0364933757",R.drawable.cuahang2));
//        arrayCuaHang.add(new CuaHang("Công Hiếu Motor",50000,"0364933757",R.drawable.cuahang3));
//        arrayCuaHang.add(new CuaHang("Phương Thanh Garage",40000,"0364933757",R.drawable.cuahang4));
//        arrayCuaHang.add(new CuaHang("Pam",30034,"0364933757",R.drawable.cuahang3));
//        arrayCuaHang.add(new CuaHang("Ngói Garage",40000,"0364933757",R.drawable.cuahang4));
//        arrayCuaHang.add(new CuaHang("Sun",30034,"0364933757",R.drawable.cuahang3));
//        arrayCuaHang.add(new CuaHang("Mến Garagre",30034,"0364933757",R.drawable.cuahang3));
    }
}