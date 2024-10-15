package com.example.ltdd_suaxe.fragment_cuahang;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
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
    private Button btnupdate;
    private Spinner spinnerQuan;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.fragment_person_cuahang,container,false);
        spinnerQuan = mView.findViewById(R.id.spinner_quan);
        btnupdate = mView.findViewById(R.id.btnupdateperson_cuahang);


        btnupdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showConfirmationDialog("Cập nhật", "Bạn có chắc chắn muốn cập nhật thông tin không?");
            }
        });

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



    // Phương thức hiển thị hộp thoại xác minh
    private void showConfirmationDialog(String title, String message) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setTitle(title);
        builder.setMessage(message);

        // Nút "Cancel"
        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss(); // Đóng hộp thoại
            }
        });

        // Nút "Submit"
        builder.setPositiveButton("Submit", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                // Xử lý hành động khi người dùng nhấn "Submit"
                if (title.equals("Xoá tài khoản")) {
                    Toast.makeText(getContext(), "Tài khoản đã bị xoá", Toast.LENGTH_SHORT).show();

                } else if (title.equals("Cập nhật")) {
                    Toast.makeText(getContext(), "Cập nhật thành công", Toast.LENGTH_SHORT).show();

                }  else if (title.equals("Đăng xuất")) {
                    Toast.makeText(getContext(), "Đăng xuất thành công", Toast.LENGTH_SHORT).show();

                }
            }
        });

        // Hiển thị hộp thoại
        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }
}
