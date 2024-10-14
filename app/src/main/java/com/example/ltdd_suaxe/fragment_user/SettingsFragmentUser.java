package com.example.ltdd_suaxe.fragment_user;


import android.content.Intent;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.DialogInterface;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import android.widget.Toast;


import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.ltdd_suaxe.R;
import com.example.ltdd_suaxe.User_CuaHang_Activity;
import com.example.ltdd_suaxe.nCuaHangDaLuu_Activity;
import com.example.ltdd_suaxe.nDoiMatKhau_Activity;

public class SettingsFragmentUser extends Fragment {
    private View mView;
    private RelativeLayout rlDeleteAccount, rlLogout,rl_info;

    @SuppressLint("WrongViewCast")
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {






        mView = inflater.inflate(R.layout.fragment_settings_user, container, false);

        // Ánh xạ các RelativeLayout
        rl_info=mView.findViewById(R.id.rlt_info);
        rlDeleteAccount = mView.findViewById(R.id.txt_XoaTk);
        rlLogout = mView.findViewById(R.id.DangXuat);
        RelativeLayout cuahangdaluu = mView.findViewById(R.id.cuahangdaluu);
        cuahangdaluu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), nCuaHangDaLuu_Activity.class);
                startActivity(intent);
            }
        });
        RelativeLayout doiMk = mView.findViewById(R.id.txt_Doimk);
        doiMk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), nDoiMatKhau_Activity.class);
                startActivity(intent);
            }
        });

        rl_info.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent int_info = new Intent(getContext(),PersonFragmentUser.class);
            }
        });
        // Thiết lập sự kiện click cho "Xoá tài khoản"
        rlDeleteAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showConfirmationDialog("Xoá tài khoản", "Bạn có chắc chắn muốn xoá tài khoản này không?");
            }
        });



        // Thiết lập sự kiện click cho "Đăng xuất"
        rlLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showConfirmationDialog("Đăng xuất", "Bạn có chắc chắn muốn đăng xuất không?");
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
                    // Thực hiện logic xoá tài khoản tại đây
                } else if (title.equals("Đăng xuất")) {
                    Toast.makeText(getContext(), "Đăng xuất thành công", Toast.LENGTH_SHORT).show();
                    // Thực hiện logic đăng xuất tại đây
                }
            }
        });

        // Hiển thị hộp thoại
        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }
}
