package com.example.ltdd_suaxe.fragment_user;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.ltdd_suaxe.CuaHangDetail_Activity;
import com.example.ltdd_suaxe.CuaHangOrder_Activity;
import com.example.ltdd_suaxe.ThongBao_Activity;
import com.example.ltdd_suaxe.User_CuaHang_Activity;
import com.example.ltdd_suaxe.Quan;
import com.example.ltdd_suaxe.QuanAdapter;
import com.example.ltdd_suaxe.R;

import java.util.ArrayList;

public class HomeFragmentUser extends Fragment {
    private View mView;
    ListView lvQuan;
    ArrayList<Quan> arrayQuan;
    QuanAdapter adapter;
    ImageView thongBao;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.fragment_home_user,container,false);
        AnhXa();
        adapter= new QuanAdapter(getContext(),R.layout.dong_quan,arrayQuan);
        lvQuan.setAdapter(adapter);

        lvQuan.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(getContext(), User_CuaHang_Activity.class);
                startActivity(intent);
            }
        });
        thongBao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(), ThongBao_Activity.class);
                startActivity(intent);
            }
        });

        return mView;
    }
    private void AnhXa(){
        lvQuan=(ListView) mView.findViewById(R.id.list_quan);
        thongBao=(ImageView) mView.findViewById(R.id.ic_thongbao);
        arrayQuan=new ArrayList<>();
        arrayQuan.add(new Quan("Quận Hải Châu","Khu vực trung tâm với nhiều cửa hàng sửa chữa",R.drawable.quan_haichau));
        arrayQuan.add(new Quan("Quận Liên Chiểu","Khu vực trung tâm với nhiều cửa hàng sửa chữa",R.drawable.quan_lienchieu));
        arrayQuan.add(new Quan("Quận Cẩm Lệ","Khu vực trung tâm với nhiều cửa hàng sửa chữa",R.drawable.quan_camle));
        arrayQuan.add(new Quan("Quận Thanh Khê","Khu vực trung tâm với nhiều cửa hàng sửa chữa",R.drawable.quan_thanhkhe));
        arrayQuan.add(new Quan("Quận Thanh Khê","Khu vực trung tâm với nhiều cửa hàng sửa chữa",R.drawable.quan_thanhkhe));
    }
}
