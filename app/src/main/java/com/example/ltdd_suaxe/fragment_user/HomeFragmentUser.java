package com.example.ltdd_suaxe.fragment_user;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.ltdd_suaxe.API.APIService;
import com.example.ltdd_suaxe.API.RetrofitApp;
import com.example.ltdd_suaxe.ThongBao_Activity;
import com.example.ltdd_suaxe.User_CuaHang_Activity;
import com.example.ltdd_suaxe.Model.Quan;
import com.example.ltdd_suaxe.QuanAdapter;
import com.example.ltdd_suaxe.R;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

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

        APIService apiService = RetrofitApp.getRetrofitInstance().create(APIService.class);
        // Gọi API để lấy danh sách quận
        Call<List<Quan>> call = apiService.getQuanList();
        call.enqueue(new Callback<List<Quan>>() {
            @Override
            public void onResponse(Call<List<Quan>> call, Response<List<Quan>> response) {
                if (response.isSuccessful()) {
                    List<Quan> quanList = response.body();
                    arrayQuan.clear();
                    arrayQuan.addAll(quanList);
                    adapter.notifyDataSetChanged();
                } else {
                    Toast.makeText(getContext(), "Lỗi: " + response.message(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<List<Quan>> call, Throwable t) {
                Toast.makeText(getContext(), "Lỗi kết nối: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
        adapter= new QuanAdapter(getContext(),R.layout.dong_quan,arrayQuan);
        lvQuan.setAdapter(adapter);

        lvQuan.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                // Lấy thông tin quận đã nhấn
                Quan quan = arrayQuan.get(position);

                // Truyền _id của quận qua Intent
                Intent intent = new Intent(getContext(), User_CuaHang_Activity.class);
                intent.putExtra("IdQuan", quan.get_id());  // Truyền _id quận qua Intent
                intent.putExtra("TenQuan", quan.getTenQuan());
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
//        arrayQuan.add(new Quan("Quận Hải Châu","Khu vực trung tâm với nhiều cửa hàng sửa chữa","https://cdn.tuoitre.vn/thumb_w/480/471584752817336320/2023/12/14/hai-chau-1-17025207586071731071527.jpg"));
//        arrayQuan.add(new Quan("Quận Liên Chiểu","Khu vực trung tâm với nhiều cửa hàng sửa chữa",R.drawable.quan_lienchieu));
//        arrayQuan.add(new Quan("Quận Cẩm Lệ","Khu vực trung tâm với nhiều cửa hàng sửa chữa",R.drawable.quan_camle));
//        arrayQuan.add(new Quan("Quận Thanh Khê","Khu vực trung tâm với nhiều cửa hàng sửa chữa",R.drawable.quan_thanhkhe));
//        arrayQuan.add(new Quan("Quận Thanh Khê","Khu vực trung tâm với nhiều cửa hàng sửa chữa",R.drawable.quan_thanhkhe));
    }
}
