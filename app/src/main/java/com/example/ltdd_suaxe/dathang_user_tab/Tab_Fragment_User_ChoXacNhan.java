package com.example.ltdd_suaxe.dathang_user_tab;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import androidx.fragment.app.Fragment;

import com.example.ltdd_suaxe.Adapter.User_DaXacNhan_Adapter;
import com.example.ltdd_suaxe.Model.DonSuaChua_Daxacnhan;
import com.example.ltdd_suaxe.R;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;

public class Tab_Fragment_User_ChoXacNhan extends Fragment {
    private View mView;
    private ListView lvChoXacNhan;
    private ArrayList<DonSuaChua_Daxacnhan> arrayDonChoXacNhan;
    private User_DaXacNhan_Adapter adapter;

    public Tab_Fragment_User_ChoXacNhan() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        mView = inflater.inflate(R.layout.fragment_tab_user_choxacnhan, container, false);

        // Gọi hàm AnhXa để ánh xạ và khởi tạo dữ liệu
        AnhXa();

        // Thiết lập adapter
        adapter = new User_DaXacNhan_Adapter(getContext(), R.layout.dong_choxacnhan, arrayDonChoXacNhan);
        lvChoXacNhan.setAdapter(adapter);

        return mView; // Trả về mView
    }

    private void AnhXa() {
        lvChoXacNhan = mView.findViewById(R.id.listxacnhan); // Ánh xạ ListView
        arrayDonChoXacNhan = new ArrayList<>(); // Khởi tạo danh sách dữ liệu

        // Dữ liệu mẫu
        String[] dichVu1 = {"Vá xe", "Thay lốp", "Sửa còi", "Thay nhớt", "Sửa chữa điện"};
        String[] dichVu2 = {"Sửa còi", "Thay nhớt", "Sửa chữa điện"};
        String[] dichVu3 = {"Thay nhớt", "Sửa chữa điện"};
        arrayDonChoXacNhan.add(new DonSuaChua_Daxacnhan(
                "0965869182",
                "https://aitvietnam.com/wp-content/uploads/2022/02/thiet-ke-cua-hang-sua-chua-xe-may-1-min.jpg",
                "Chờ xác nhận",
                "Có",
                "16 Võ Nguyên Giáp",
                Arrays.asList(dichVu1),
                new Date(),
                "21331",
                "CH003",
                "Công Hiếu Garage",
                "123"
        ));
        arrayDonChoXacNhan.add(new DonSuaChua_Daxacnhan(
                "0965869182",
                "https://timthosuaxe.com/wp-content/uploads/2021/05/Sua-xe-may-Tan-tien-1024x576.jpg",
                "Chờ xác nhận",
                "Có",
                "16 Võ Nguyên Giáp",
                Arrays.asList(dichVu2),
                new Date(),
                "21331",
                "CH002",
                "Thanh Mai Garage",
                "125"
        ));
        arrayDonChoXacNhan.add(new DonSuaChua_Daxacnhan(
                "0965869182",
                "https://cdn.khamphadanang.vn/wp-content/uploads/2024/07/sua-xe-may-uy-tin-da-nang-6.jpg?strip=all&lossy=1&ssl=1",
                "Chờ xác nhận",
                "Có",
                "16 Võ Nguyên Giáp",
                Arrays.asList(dichVu3),
                new Date(),
                "21331",
                "CH001",
                "Minh Nhật Garage",
                "124"
        ));
    }
}