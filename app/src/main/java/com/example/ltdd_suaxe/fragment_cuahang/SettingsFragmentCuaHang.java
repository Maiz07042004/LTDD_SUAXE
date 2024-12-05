package com.example.ltdd_suaxe.fragment_cuahang;

import static android.content.Context.MODE_PRIVATE;

import android.content.Intent;
import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.widget.ViewPager2;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.ltdd_suaxe.API.APIService;
import com.example.ltdd_suaxe.API.RetrofitApp;
import com.example.ltdd_suaxe.Model.CapNhatCuaHangResponse;
import com.example.ltdd_suaxe.Model.CuaHang;
import com.example.ltdd_suaxe.Model.User;
import com.example.ltdd_suaxe.R;
import com.example.ltdd_suaxe.h_login_activity;
import com.example.ltdd_suaxe.nCuaHangDaLuu_Activity;
import com.example.ltdd_suaxe.nDoiMatKhau_Activity;
import com.google.android.material.imageview.ShapeableImageView;
import com.google.android.material.tabs.TabLayout;

import java.util.HashMap;
import java.util.Map;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SettingsFragmentCuaHang extends Fragment  {
    private View mView;
    private RelativeLayout rlDeleteAccount, rlLogout,rlInfo,rlllichsusuachua,doiMk;
    private TextView tvTenCuaHang,tvEmailCuaHang;
    private ShapeableImageView imageView ;

    @SuppressLint("WrongViewCast")
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        mView = inflater.inflate(R.layout.fragment_settings_cuahang, container, false);
        AnhXa();

        rlllichsusuachua.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Chuyển từ Fragment 4 sang Fragment 2
                ViewPager2 viewPager = getActivity().findViewById(R.id.view_pager_home_cuahang);
                viewPager.setCurrentItem(1, true);  // Chuyển đến Fragment 2 (tab 2)
            }
        });

        doiMk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), nDoiMatKhau_Activity.class);
                startActivity(intent);
            }
        });

        rlInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Chuyển từ Fragment 4 sang Fragment 3
                ViewPager2 viewPager = getActivity().findViewById(R.id.view_pager_home_cuahang);
                viewPager.setCurrentItem(2, true);
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

    @Override
    public void onResume() {
        super.onResume();
        // Lấy userId từ SharedPreferences
        SharedPreferences sharedPreferences = getContext().getSharedPreferences("user_prefs", MODE_PRIVATE);
        String cuaHangId = sharedPreferences.getString("cuaHangId", null);  // Nếu không có giá trị, trả về null

        // Gọi API để lấy lại dữ liệu mỗi khi fragment được hiển thị
        if (cuaHangId != null) {
            detailCuaHang(cuaHangId);
        }
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
                    goToLoginActivity();
                } else if (title.equals("Đăng xuất")) {
                    // Xóa cuaHangId khi đăng xuất
                    SharedPreferences sharedPreferences = getContext().getSharedPreferences("user_prefs", MODE_PRIVATE);
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.clear();
                    editor.apply();
                    Toast.makeText(getContext(), "Đăng xuất thành công", Toast.LENGTH_SHORT).show();
                    goToLoginActivity();
                }
            }
        });

        // Hiển thị hộp thoại
        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }
    private void goToLoginActivity() {
        Intent intent = new Intent(getActivity(), h_login_activity.class); // Chuyển sang LoginActivity
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK); // Xóa stack hiện tại
        startActivity(intent); // Bắt đầu LoginActivity

        if (getActivity() != null) {
            getActivity().finish(); // Đóng Activity hiện tại nếu không phải null
        }
    }

    private void detailCuaHang(String cuaHangId){
        // Khởi tạo ApiService
        APIService apiService = RetrofitApp.getRetrofitInstance().create(APIService.class);

        // Gọi API để lấy thông tin cửa hàng
        Call<CapNhatCuaHangResponse> call = apiService.CuaHangDetail(cuaHangId);
        call.enqueue(new Callback<CapNhatCuaHangResponse>() {
            @Override
            public void onResponse(Call<CapNhatCuaHangResponse> call, Response<CapNhatCuaHangResponse> response) {
                if (response.isSuccessful()) {
                    CapNhatCuaHangResponse cuaHang = response.body();
                    // Hiển thị dữ liệu lên UI
                    tvTenCuaHang.setText(cuaHang.getTenCuaHang());
                    tvEmailCuaHang.setText(cuaHang.getEmail());

                    String imageUrl = cuaHang.getHinhAnh(); // URL ảnh

                    Glide.with(getContext())
                            .load(imageUrl)
                            .apply(RequestOptions.circleCropTransform()) // Sử dụng Glide để cắt ảnh thành hình tròn
                            .into(imageView);
                } else {
                    Toast.makeText(getContext(), "Bạn chưa lưu cửa hàng này", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<CapNhatCuaHangResponse> call, Throwable t) {
                Toast.makeText(getContext(), "Lỗi kết nối: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void AnhXa(){
        // Ánh xạ các RelativeLayout
        rlDeleteAccount = mView.findViewById(R.id.xoataikhoan_cuahang);
        rlLogout = mView.findViewById(R.id.dangxuat_cuahang);
        rlInfo = mView.findViewById(R.id.thongtincanhan_cuahang);
        rlllichsusuachua = mView.findViewById(R.id.lichsusuachua);
        doiMk = mView.findViewById(R.id.doimatkhau_cuahang);
        imageView = mView.findViewById(R.id.imageViewAvatar);
        tvTenCuaHang=mView.findViewById(R.id.ten_cuahang);
        tvEmailCuaHang=mView.findViewById(R.id.email_cuahang);
    }

}






