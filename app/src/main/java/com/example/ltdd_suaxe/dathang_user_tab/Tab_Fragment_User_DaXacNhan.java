package com.example.ltdd_suaxe.dathang_user_tab;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import androidx.fragment.app.Fragment;

import com.example.ltdd_suaxe.Adapter.User_DaXacNhan_Adapter;
import com.example.ltdd_suaxe.DonYeuCau;
import com.example.ltdd_suaxe.DonYeuCauAdapter;
import com.example.ltdd_suaxe.Model.DonSuaChua_Daxacnhan;
import com.example.ltdd_suaxe.R;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;

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

    private void AnhXa() {
        lvDaXacNhan = mView.findViewById(R.id.lv1); // Ánh xạ ListView
        arrayDonXacNhan = new ArrayList<>(); // Khởi tạo danh sách dữ liệu

        // Dữ liệu mẫu
        String[] dichVu1 = {"Vá xe", "Thay lốp","Sửa còi","Thay nhớt","Sữa chữa điện"};
        String[] dichVu2 = {"Sửa còi","Thay nhớt","Sữa chữa điện"};
        String[] dichVu3 = {"Thay nhớt","Sữa chữa điện"};
        arrayDonXacNhan.add(new DonSuaChua_Daxacnhan(
                "0965869182",
                "https://timthosuaxe.com/wp-content/uploads/2021/05/sua-xe-may-da-nang-thanh-tam.png",
                "Đã xác nhận",
                "Không",
                "16 Võ Nguyên Giáp",
                Arrays.asList(dichVu1),
                new Date(),
                "21331",
                "CH003",
                "Công Hiếu Garage",
               "123"
        ));
        arrayDonXacNhan.add(new DonSuaChua_Daxacnhan(
                "0965869182",
                "https://truemotocare.com/wp-content/uploads/2023/10/Cua-hang-sua-xe-Quang-Hau-68-sau-khi-nang-cap-600x450.jpg",
                "Đã xác nhận",
                "Không",
                "16 Võ Nguyên Giáp",
                Arrays.asList(dichVu2),
                new Date(),
                "21331",
                "CH002",
                "Thanh Mai Garage",
                "125"
        ));
        arrayDonXacNhan.add(new DonSuaChua_Daxacnhan(
                "0965869182",
                "https://truemotocare.com/wp-content/uploads/2023/10/True-Moto-Care-Mr-To-600x630.jpg",
                "Đã xác nhận",
                "Không",
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
