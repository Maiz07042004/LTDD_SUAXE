package com.example.ltdd_suaxe.fragment_cuahang;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.ltdd_suaxe.R;

import java.util.ArrayList;
import java.util.List;

public class PersonFragmentCuaHang extends Fragment {
    private View mView;
    private Spinner spinnerQuan;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.fragment_person_cuahang,container,false);
        spinnerQuan = mView.findViewById(R.id.spinner_quan);

        // Tạo danh sách dữ liệu cho Spinner
        List<String> list = new ArrayList<>();
        list.add("Hải Châu");
        list.add("Thanh Khê");
        list.add("Sơn Trà");
        list.add("Cẩm Lệ");
        list.add("Liên Chiểu");

        // Tạo Adapter cho Spinner
        ArrayAdapter<String> adapter = new ArrayAdapter<>(mView.getContext(), android.R.layout.simple_spinner_item, list);
        adapter.setDropDownViewResource(android.R.layout.simple_list_item_single_choice);  // Giao diện khi mở Spinner
        spinnerQuan.setAdapter(adapter);

        spinnerQuan.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                // Lấy item được chọn
                String selectedItem = spinnerQuan.getSelectedItem().toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // Không làm gì nếu không có item nào được chọn
            }
        });
        return mView;
    }
}
