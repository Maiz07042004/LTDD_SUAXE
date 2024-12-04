package com.example.ltdd_suaxe.dathang_user_tab;

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
import com.example.ltdd_suaxe.Adapter.User_DaXacNhan_Adapter;
import com.example.ltdd_suaxe.Model.DonSuaChua_Daxacnhan;
import com.example.ltdd_suaxe.R;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Tab_Fragment_User_DaXacNhan extends Fragment {
    private View mView;
    ListView lvDaXacNhan;
    ArrayList<DonSuaChua_Daxacnhan> arrayDonXacNhan;
    User_DaXacNhan_Adapter adapter;

    public Tab_Fragment_User_DaXacNhan() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        mView = inflater.inflate(R.layout.fragment_tab_user_daxacnhan, container, false);

        // Gọi hàm AnhXa để ánh xạ và khởi tạo dữ liệu
        AnhXa();

        // Thiết lập adapter
        adapter = new User_DaXacNhan_Adapter(getContext(), R.layout.dong_user_daxacnhan, arrayDonXacNhan);
        lvDaXacNhan.setAdapter(adapter);

        return mView; // Trả về mView
    }

    @Override
    public void onResume() {
        super.onResume();
        // Lấy userId từ SharedPreferences
        SharedPreferences sharedPreferences = getContext().getSharedPreferences("user_prefs", MODE_PRIVATE);
        String userId = sharedPreferences.getString("userId", null);  // Nếu không có giá trị, trả về null

        // Gọi API để lấy lại dữ liệu mỗi khi fragment được hiển thị
        if (userId != null) {
            getListDonSuaChuaTheoTrangThai(userId);
        }
    }


    private  void getListDonSuaChuaTheoTrangThai(String userId){
        APIService apiService = RetrofitApp.getRetrofitInstance().create(APIService.class);

        Call<List<DonSuaChua_Daxacnhan>> call = apiService.getListDonSuaChuaTheoTrangThai(userId, "DaXacNhan");

        call.enqueue(new Callback<List<DonSuaChua_Daxacnhan>>() {
            @Override
            public void onResponse(Call<List<DonSuaChua_Daxacnhan>> call, Response<List<DonSuaChua_Daxacnhan>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    List<DonSuaChua_Daxacnhan> donSuaChuaList = response.body();
                    arrayDonXacNhan.clear();
                    arrayDonXacNhan.addAll(donSuaChuaList);
                    adapter.notifyDataSetChanged();
                } else {
                    Log.e("API", "Error: " + response.message());
                }
            }

            @Override
            public void onFailure(Call<List<DonSuaChua_Daxacnhan>> call, Throwable t) {
                // Xử lý khi yêu cầu thất bại (lỗi kết nối...)
                Log.e("API", "Failure: " + t.getMessage());
            }
        });
    }

    private void AnhXa() {
        lvDaXacNhan = mView.findViewById(R.id.lv1); // Ánh xạ ListView
        arrayDonXacNhan = new ArrayList<>(); // Khởi tạo danh sách dữ liệu

//        // Dữ liệu mẫu
//        String[] dichVu1 = {"Vá xe", "Thay lốp","Sửa còi","Thay nhớt","Sữa chữa điện"};
//        String[] dichVu2 = {"Sửa còi","Thay nhớt","Sữa chữa điện"};
//        String[] dichVu3 = {"Thay nhớt","Sữa chữa điện"};
//        arrayDonXacNhan.add(new DonSuaChua_Daxacnhan(
//                "https://timthosuaxe.com/wp-content/uploads/2021/05/sua-xe-may-da-nang-thanh-tam.png",
//                "Đã xác nhận",
//                "Không",
//                "16 Võ Nguyên Giáp",
//                Arrays.asList(dichVu1),
//                new Date(),
//                "21331",
//                "CH003",
//                "Công Hiếu Garage",
//               "123"
//        ));

    }

}
