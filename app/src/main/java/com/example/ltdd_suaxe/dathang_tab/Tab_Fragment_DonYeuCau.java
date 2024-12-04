package com.example.ltdd_suaxe.dathang_tab;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.ltdd_suaxe.CuaHang_Activity;
import com.example.ltdd_suaxe.DonYeuCau;
import com.example.ltdd_suaxe.DonYeuCauAdapter;
import com.example.ltdd_suaxe.R;

import java.util.ArrayList;


public class Tab_Fragment_DonYeuCau extends Fragment {
    private View mView;
    ListView lvYeuCau;
    ArrayList<DonYeuCau> arrayDonYeuCau;
    DonYeuCauAdapter adapter;

    public Tab_Fragment_DonYeuCau() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        mView =inflater.inflate(R.layout.fragment_tab_don_yeu_cau, container, false);
        AnhXa();
        adapter= new DonYeuCauAdapter(getContext(),R.layout.dong_yeucau,arrayDonYeuCau);
        lvYeuCau.setAdapter(adapter);


        return mView;
    }
    private void AnhXa(){
        lvYeuCau=(ListView) mView.findViewById(R.id.listyeucau);
        arrayDonYeuCau=new ArrayList<>();
        // Khởi tạo dữ liệu cho từng item
        String[] dichVu1 = {"Vá xe", "Thay lốp"};
        String[] dichVu2 = {"Sửa điện", "Thay ắc quy"};
        String[] dichVu3 = {"Vá xe", "Thay lốp"};
        String[] dichVu4 = {"Vá xe", "Thay lốp"};
        String[] dichVu5 = {"Vá xe", "Thay lốp"};

        arrayDonYeuCau.add(new DonYeuCau("Đặng Thanh Mai", "0123456789", dichVu1, "16 Võ Nguyên Giáp", "Chờ xác nhận", R.drawable.image_user));
        arrayDonYeuCau.add(new DonYeuCau("Trần Công Hiếu", "0987654321", dichVu2, "16 Võ Nguyên Giáp", "Đã xác nhận", R.drawable.image_user));
        arrayDonYeuCau.add(new DonYeuCau("Phan Minh Nhật", "0369852147", dichVu3, "16 Võ Nguyên Giáp", "Đang thực hiện", R.drawable.image_user));
        arrayDonYeuCau.add(new DonYeuCau("Phạm Thị D", "0741852963", dichVu4, "16 Võ Nguyên Giáp", "Hoàn thành", R.drawable.image_user));
        arrayDonYeuCau.add(new DonYeuCau("Hoàng Văn E", "0258963147", dichVu5, "16 Võ Nguyên Giáp", "Chờ xác nhận", R.drawable.image_user));
    }
}