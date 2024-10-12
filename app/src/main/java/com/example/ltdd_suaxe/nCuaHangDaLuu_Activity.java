package com.example.ltdd_suaxe;

import android.os.Bundle;
import android.widget.ListView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

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
        adapter = new nCuaHangDaLuu_Adapter(this, R.layout.ndong_cuahangdaluu,arraynCuaHangDaLuu);
        lvnCuaHangDaLuu.setAdapter(adapter);

    }
    private void AnhXa(){

        lvnCuaHangDaLuu = (ListView) findViewById(R.id.list_cuaHangDaLuu);
        arraynCuaHangDaLuu=new ArrayList<>();
        arraynCuaHangDaLuu.add(new nCuaHangDaLuu("ThanhMai Garage",1000, R.drawable.cuahang1, R.drawable.luu));
        arraynCuaHangDaLuu.add(new nCuaHangDaLuu("Nhat Bike", 1200, R.drawable.cuahang2, R.drawable.luu));
        arraynCuaHangDaLuu.add(new nCuaHangDaLuu("Hieu Garage", 2100, R.drawable.cuahang3,R.drawable.luu));

    }
}