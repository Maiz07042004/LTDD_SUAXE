package com.example.ltdd_suaxe.dathang_tab;

import static android.content.Context.MODE_PRIVATE;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import androidx.fragment.app.Fragment;

import com.example.ltdd_suaxe.API.APIService;
import com.example.ltdd_suaxe.API.RetrofitApp;
import com.example.ltdd_suaxe.Model.DonYeuCau;
import com.example.ltdd_suaxe.Adapter.DonYeuCauAdapter;
import com.example.ltdd_suaxe.R;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Tab_Fragment_DonXacNhan extends Fragment {
    private View mView;
    ListView lvYeuCau;
    ArrayList<DonYeuCau> arrayDonXacNhan;
    DonYeuCauAdapter adapter;

    public Tab_Fragment_DonXacNhan() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        mView =inflater.inflate(R.layout.fragment_tab_don_xac_nhan, container, false);
        AnhXa();
        adapter= new DonYeuCauAdapter(getContext(),R.layout.dong_xacnhan,arrayDonXacNhan);
        lvYeuCau.setAdapter(adapter);


        return mView;
    }
    @Override
    public void onResume() {
        super.onResume();
        // Lấy userId từ SharedPreferences
        SharedPreferences sharedPreferences = getContext().getSharedPreferences("user_prefs", MODE_PRIVATE);
        String cuaHangId = sharedPreferences.getString("cuaHangId", null);  // Nếu không có giá trị, trả về null

        // Gọi API để lấy lại dữ liệu mỗi khi fragment được hiển thị
        if (cuaHangId != null) {
            getListDonSuaChuaTheoTrangThai(cuaHangId);
        }
    }

    private  void getListDonSuaChuaTheoTrangThai(String cuaHangId){
        APIService apiService = RetrofitApp.getRetrofitInstance().create(APIService.class);

        Call<List<DonYeuCau>> call = apiService.getListDonSuaChuaTheoTrangThaiCuaHang(cuaHangId, "DaXacNhan");

        call.enqueue(new Callback<List<DonYeuCau>>() {
            @Override
            public void onResponse(Call<List<DonYeuCau>> call, Response<List<DonYeuCau>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    List<DonYeuCau> donSuaChuaList = response.body();
                    arrayDonXacNhan.clear();
                    arrayDonXacNhan.addAll(donSuaChuaList);
                    adapter.notifyDataSetChanged();
                } else {
                    Log.e("API", "Error: " + response.message());
                }
            }

            @Override
            public void onFailure(Call<List<DonYeuCau>> call, Throwable t) {
                // Xử lý khi yêu cầu thất bại (lỗi kết nối...)
                Log.e("API", "Failure: " + t.getMessage());
            }
        });
    }
    private void AnhXa(){
        lvYeuCau=(ListView) mView.findViewById(R.id.listxacnhan);
        arrayDonXacNhan=new ArrayList<>();
        // Khởi tạo dữ liệu cho từng item
//        String[] dichVu1 = {"Vá xe", "Thay lốp"};
//        String[] dichVu2 = {"Sửa điện", "Thay ắc quy"};
//        String[] dichVu3 = {"Vá xe", "Thay lốp"};
//        String[] dichVu4 = {"Vá xe", "Thay lốp"};
//        String[] dichVu5 = {"Vá xe", "Thay lốp"};

//        arrayDonYeuCau.add(new DonYeuCau("Đặng Thanh Mai", "0123456789", dichVu1, "16 Võ Nguyên Giáp", "Chờ xác nhận", R.drawable.image_user));
//        arrayDonYeuCau.add(new DonYeuCau("Trần Công Hiếu", "0987654321", dichVu2, "16 Võ Nguyên Giáp", "Đã xác nhận", R.drawable.image_user));
//        arrayDonYeuCau.add(new DonYeuCau("Phan Minh Nhật", "0369852147", dichVu3, "16 Võ Nguyên Giáp", "Đang thực hiện", R.drawable.image_user));
//        arrayDonYeuCau.add(new DonYeuCau("Phạm Thị D", "0741852963", dichVu4, "16 Võ Nguyên Giáp", "Hoàn thành", R.drawable.image_user));
//        arrayDonYeuCau.add(new DonYeuCau("Hoàng Văn E", "0258963147", dichVu5, "Method...", "Chờ xác nhận", R.drawable.image_user));
    }
}

