package com.example.ltdd_suaxe.fragment_user;


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
import android.widget.ImageView;
import android.widget.RelativeLayout;

import android.widget.TextView;
import android.widget.Toast;


import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.viewpager2.widget.ViewPager2;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.ltdd_suaxe.API.APIService;
import com.example.ltdd_suaxe.API.RetrofitApp;
import com.example.ltdd_suaxe.Model.CapNhatKhachHangRequest;
import com.example.ltdd_suaxe.Model.CuaHang;
import com.example.ltdd_suaxe.Model.User;
import com.example.ltdd_suaxe.R;
import com.example.ltdd_suaxe.User_CuaHang_Activity;
import com.example.ltdd_suaxe.User_Home_Activity;
import com.example.ltdd_suaxe.h_login_activity;
import com.example.ltdd_suaxe.nCuaHangDaLuu_Activity;
import com.example.ltdd_suaxe.nDoiMatKhau_Activity;
import com.google.android.material.imageview.ShapeableImageView;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SettingsFragmentUser extends Fragment {
    private View mView;
    private RelativeLayout rlDeleteAccount, rlLogout,rlInfo,cuahangdaluu,doiMk;
    private TextView tvTenKH,tvEmailKH;
    private ShapeableImageView imageView ;

    @SuppressLint("WrongViewCast")
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {


        mView = inflater.inflate(R.layout.fragment_settings_user, container, false);

        AnhXa();


        //Thiết lập sự kiện cho "Cửa hàng đã lưu"
        cuahangdaluu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), nCuaHangDaLuu_Activity.class);
                startActivity(intent);
            }
        });

        //Thiết lập sự kiện cho "Đổi mật khẩu"
        doiMk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), nDoiMatKhau_Activity.class);
                startActivity(intent);
            }
        });

        //Thiết lập sự kiện cho "Thông tin tài khoản"
        rlInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Chuyển từ Fragment 4 sang Fragment 3
                ViewPager2 viewPager = getActivity().findViewById(R.id.view_pager_home_user);
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
        String userId = sharedPreferences.getString("userId", null);  // Nếu không có giá trị, trả về null

        // Gọi API để lấy lại dữ liệu mỗi khi fragment được hiển thị
        if (userId != null) {
            detailKhachHang(userId);
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
                    // Xóa userId khi đăng xuất
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

    private void detailKhachHang(String userId){
        // Khởi tạo ApiService
        APIService apiService = RetrofitApp.getRetrofitInstance().create(APIService.class);



        // Gọi API để lấy thông tin khách hàng
        Call<User> call = apiService.getUserDetail(userId);
        call.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                if (response.isSuccessful()) {
                    User user = response.body();
                    // Hiển thị dữ liệu lên UI
                    tvTenKH.setText(user.getTenKhachHang());
                    tvEmailKH.setText(user.getEmail());

                    String imageUrl = user.getHinhAnh(); // URL ảnh

                    Glide.with(getContext())
                            .load(imageUrl)
                            .apply(RequestOptions.circleCropTransform()) // Sử dụng Glide để cắt ảnh thành hình tròn
                            .into(imageView);
                } else {
                    Toast.makeText(getContext(), "Bạn chưa lưu cửa hàng này", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                Toast.makeText(getContext(), "Lỗi kết nối: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void AnhXa(){
        tvTenKH=mView.findViewById(R.id.ten_khachhang);
        tvEmailKH=mView.findViewById(R.id.email_khachhang);
        // Ánh xạ các RelativeLayout
        rlDeleteAccount = mView.findViewById(R.id.txt_XoaTk);
        rlLogout = mView.findViewById(R.id.DangXuat);
        rlInfo = mView.findViewById(R.id.thongtincanhan);
        cuahangdaluu = mView.findViewById(R.id.cuahangdaluu);
        doiMk = mView.findViewById(R.id.txt_Doimk);

        imageView = mView.findViewById(R.id.imageViewAvatar);
    }

}
