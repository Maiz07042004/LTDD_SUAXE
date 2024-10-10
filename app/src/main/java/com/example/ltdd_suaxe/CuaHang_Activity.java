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

import java.util.ArrayList;

public class CuaHang_Activity extends AppCompatActivity {
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

        lvCuaHang.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent=new Intent(CuaHang_Activity.this, CuaHangDetail_Activity.class);
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
        arrayCuaHang.add(new CuaHang("Quận Hải Châu",1000,R.drawable.cuahang1));
        arrayCuaHang.add(new CuaHang("Quận Liên Chiểu",2000,R.drawable.cuahang2));
        arrayCuaHang.add(new CuaHang("Quận Cẩm Lệ",50000,R.drawable.cuahang3));
        arrayCuaHang.add(new CuaHang("Quận Thanh Khê",40000,R.drawable.cuahang4));
        arrayCuaHang.add(new CuaHang("Quận Thanh Khê",30034,R.drawable.cuahang3));
    }
}