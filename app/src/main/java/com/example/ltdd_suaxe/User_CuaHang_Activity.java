package com.example.ltdd_suaxe;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.ltdd_suaxe.Model.CuaHang;

import java.util.ArrayList;

public class User_CuaHang_Activity extends AppCompatActivity {
    ListView lvCuaHang;
    ArrayList<CuaHang> arrayCuaHang;
    CuaHangAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_cuahang);

        AnhXa();
        adapter= new CuaHangAdapter(this,R.layout.dong_cuahang,arrayCuaHang);
        lvCuaHang.setAdapter(adapter);

        // Sự kiện click vào một cửa hàng trong ListView
        lvCuaHang.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                CuaHang selectedCuaHang = arrayCuaHang.get(i);

                // Tạo intent để mở CuaHangDetail_Activity
                Intent intent = new Intent(User_CuaHang_Activity.this, CuaHangDetail_Activity.class);

                // Tạo Bundle để chứa dữ liệu
                Bundle bundle = new Bundle();
                bundle.putString("ten_cua_hang", selectedCuaHang.getTen());
                bundle.putInt("img", selectedCuaHang.getHinh());
                bundle.putInt("like", selectedCuaHang.getLike());
                bundle.putString("sdt",selectedCuaHang.getSdt());

                // Đưa Bundle vào Intent
                intent.putExtras(bundle);

                // Khởi chạy activity
                startActivity(intent);
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