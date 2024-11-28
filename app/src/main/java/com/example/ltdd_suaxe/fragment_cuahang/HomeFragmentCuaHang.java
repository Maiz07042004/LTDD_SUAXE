package com.example.ltdd_suaxe.fragment_cuahang;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.ltdd_suaxe.CuaHang_Activity;
import com.example.ltdd_suaxe.Model.Quan;
import com.example.ltdd_suaxe.QuanAdapter;
import com.example.ltdd_suaxe.R;

import java.util.ArrayList;
import java.util.List;

public class HomeFragmentCuaHang extends Fragment {
    private View mView;
    ListView lvQuan;
    List<Quan> arrayQuan;
    QuanAdapter adapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.fragment_home_cuahang,container,false);
        AnhXa();
        adapter= new QuanAdapter(getContext(),R.layout.dong_quan,arrayQuan);
        lvQuan.setAdapter(adapter);

        lvQuan.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(getContext(), CuaHang_Activity.class);
                startActivity(intent);
            }
        });

        return mView;
    }
    private void AnhXa(){
        lvQuan=(ListView) mView.findViewById(R.id.list_quan);
//        arrayQuan=new List<Quan>();
//        arrayQuan.add(new Quan("Quận Hải Châu","Khu vực trung tâm với nhiều cửa hàng sửa chữa","https://cdn.tuoitre.vn/thumb_w/480/471584752817336320/2023/12/14/hai-chau-1-17025207586071731071527.jpg"));
//        arrayQuan.add(new Quan("Quận Liên Chiểu","Khu vực trung tâm với nhiều cửa hàng sửa chữa",R.drawable.quan_lienchieu));
//        arrayQuan.add(new Quan("Quận Cẩm Lệ","Khu vực trung tâm với nhiều cửa hàng sửa chữa",R.drawable.quan_camle));
//        arrayQuan.add(new Quan("Quận Thanh Khê","Khu vực trung tâm với nhiều cửa hàng sửa chữa",R.drawable.quan_thanhkhe));
//        arrayQuan.add(new Quan("Quận Thanh Khê","Khu vực trung tâm với nhiều cửa hàng sửa chữa",R.drawable.quan_thanhkhe));
    }
}
