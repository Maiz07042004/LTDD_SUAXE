package com.example.ltdd_suaxe.fragment_user;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.ltdd_suaxe.CuaHang;
import com.example.ltdd_suaxe.LichSuDat;
import com.example.ltdd_suaxe.LichSuDatAdapter;
import com.example.ltdd_suaxe.Quan;
import com.example.ltdd_suaxe.QuanAdapter;
import com.example.ltdd_suaxe.R;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class ListFragmentUser extends Fragment {
    private View mView;
    ListView lvLichSuDat;
    ArrayList<LichSuDat> arrayLichSuDat;
    LichSuDatAdapter adapter;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.fragment_list_user,container,false);
        AnhXa();
        adapter= new LichSuDatAdapter(getContext(),R.layout.dong_lichsudat,arrayLichSuDat);
        lvLichSuDat.setAdapter(adapter);
        return mView;
    }
    private void AnhXa(){
        lvLichSuDat=(ListView) mView.findViewById(R.id.listLichSuDat);
        arrayLichSuDat=new ArrayList<>();
        // Khởi tạo dữ liệu cho từng item
        String[] dichVu1 = {"Vá xe", "Thay lốp"};
        String[] dichVu2 = {"Sửa điện", "Thay ắc quy"};
        String[] dichVu3 = {"Bảo dưỡng", "Thay nhớt"};
        String[] dichVu4 = {"Rửa xe", "Thay đèn"};
        String[] dichVu5 = {"Kiểm tra phanh", "Bơm lốp"};

// Sử dụng Calendar để thiết lập ngày đặt
        Calendar cal = Calendar.getInstance();

// Item 1
        cal.set(2024, Calendar.OCTOBER, 27);
        Date ngayDat1 = cal.getTime();
        arrayLichSuDat.add(new LichSuDat("Thanh Mai Garage", dichVu1, ngayDat1, "Đã hoàn thành", R.drawable.cuahang1));

// Item 2
        cal.set(2024, Calendar.OCTOBER, 28);
        Date ngayDat2 = cal.getTime();
        arrayLichSuDat.add(new LichSuDat("Thanh Mai Garage", dichVu2, ngayDat2, "Chưa hoàn thành", R.drawable.cuahang2));

// Item 3
        cal.set(2024, Calendar.OCTOBER, 29);
        Date ngayDat3 = cal.getTime();
        arrayLichSuDat.add(new LichSuDat("Thanh Mai Garage", dichVu3, ngayDat3, "Đã hoàn thành", R.drawable.cuahang3));

// Item 4
        cal.set(2024, Calendar.OCTOBER, 30);
        Date ngayDat4 = cal.getTime();
        arrayLichSuDat.add(new LichSuDat("Thanh Mai Garage", dichVu4, ngayDat4, "Đang thực hiện", R.drawable.cuahang4));

// Item 5
        cal.set(2024, Calendar.OCTOBER, 31);
        Date ngayDat5 = cal.getTime();
        arrayLichSuDat.add(new LichSuDat("Thanh Mai Garage", dichVu5, ngayDat5, "Chưa hoàn thành", R.drawable.cuahang4));
    }
}
