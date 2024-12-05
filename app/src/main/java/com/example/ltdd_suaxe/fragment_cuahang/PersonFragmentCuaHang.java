package com.example.ltdd_suaxe.fragment_cuahang;

import static android.content.Context.MODE_PRIVATE;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.ltdd_suaxe.API.APIService;
import com.example.ltdd_suaxe.API.RetrofitApp;
import com.example.ltdd_suaxe.Model.CapNhatCuaHangRequest;
import com.example.ltdd_suaxe.Model.CapNhatCuaHangResponse;
import com.example.ltdd_suaxe.Model.CapNhatKhachHangRequest;
import com.example.ltdd_suaxe.Model.CuaHang;
import com.example.ltdd_suaxe.Model.ResponseChung;
import com.example.ltdd_suaxe.R;
import com.google.android.material.imageview.ShapeableImageView;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PersonFragmentCuaHang extends Fragment {
    private View mView;
    private Button btnUpdate;
    private Spinner spinnerQuan;
    private EditText tvTenCuaHang,tvEmailCuaHang,tvSDT,tvMoTa,tvDiaChi;
    private ShapeableImageView imageView ;
    String cuaHangId;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.fragment_person_cuahang,container,false);
        AnhXa();


        btnUpdate.setOnClickListener(new View.OnClickListener() {
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

        // Lấy SharedPreferences
        SharedPreferences sharedPreferences = getContext().getSharedPreferences("user_prefs", MODE_PRIVATE);

// Lấy userId từ SharedPreferences
         cuaHangId= sharedPreferences.getString("cuaHangId", null);


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

                } else if (title.equals("Cập nhật")) {
                    String tenCuaHang = tvTenCuaHang.getText().toString();
                    String email = tvEmailCuaHang.getText().toString();
                    String sdt = tvSDT.getText().toString();
                    String moTa = tvMoTa.getText().toString();
                    String diaChi = tvDiaChi.getText().toString();

                    // Lấy giá trị từ Spinner (Chọn quận)
                    String quan = spinnerQuan.getSelectedItem().toString();

                    CapNhatCuaHangRequest capNhatCuaHangRequest=new CapNhatCuaHangRequest(diaChi,email,moTa,sdt,tenCuaHang,quan);
                    updateThongTinCuaHang(cuaHangId,capNhatCuaHangRequest);

                }  else if (title.equals("Đăng xuất")) {
                    Toast.makeText(getContext(), "Đăng xuất thành công", Toast.LENGTH_SHORT).show();

                }
            }
        });

        // Hiển thị hộp thoại
        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }

    // Hàm hiển thị thông báo cập nhật thành công
    private void showUpdateDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle("Thông báo");
        builder.setMessage("Cập nhật thông tin thành công!");
        builder.setPositiveButton("OK", null);
        AlertDialog dialog = builder.create();
        dialog.show();
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
                    tvDiaChi.setText(cuaHang.getDiaChi());
                    tvSDT.setText(cuaHang.getSDT());
                    tvMoTa.setText(cuaHang.getMoTa());
                    String quan = cuaHang.getTenQuan();

                    // Gán giá trị quận cho Spinner
                    setQuanToSpinner(quan);

                    String imageUrl = cuaHang.getHinhAnh(); // URL ảnh

                    Glide.with(getContext())
                            .load(imageUrl)
                            .apply(RequestOptions.circleCropTransform()) // Sử dụng Glide để cắt ảnh thành hình tròn
                            .into(imageView); // URL ảnh
                } else {
                    Toast.makeText(getContext(), "Lỗi", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<CapNhatCuaHangResponse> call, Throwable t) {
                Toast.makeText(getContext(), "Lỗi kết nối: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void updateThongTinCuaHang(String cuaHangId, CapNhatCuaHangRequest capNhatCuaHangRequest) {
        // Khởi tạo APIService
        APIService apiService = RetrofitApp.getRetrofitInstance().create(APIService.class);

        // Gọi API cập nhật thông tin khách hàng
        Call<ResponseChung> call = apiService.updateCuaHang(cuaHangId, capNhatCuaHangRequest);
        call.enqueue(new Callback<ResponseChung>() {
            @Override
            public void onResponse(Call<ResponseChung> call, Response<ResponseChung> response) {
                if (response.isSuccessful()) {
                    // Cập nhật thành công
                    showUpdateDialog(); // Hiển thị thông báo thành công
                } else {
                    // Thông báo lỗi
                    Toast.makeText(getContext(), "Cập nhật thất bại, vui lòng thử lại!", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ResponseChung> call, Throwable t) {
                // Thông báo lỗi kết nối
                Toast.makeText(getContext(), "Lỗi kết nối: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    //Hàm gán giá trị Quận
    private void setQuanToSpinner(String quan) {
        // Duyệt qua các giá trị trong Spinner và gán giá trị đúng
        ArrayAdapter<CharSequence> adapter = (ArrayAdapter<CharSequence>) spinnerQuan.getAdapter();
        int position = adapter.getPosition(quan); // Tìm vị trí của quận trong Spinner
        if (position != -1) {
            spinnerQuan.setSelection(position); // Gán giá trị vào Spinner
        }
    }

    private void AnhXa(){
        btnUpdate = mView.findViewById(R.id.btnupdateperson_cuahang);
        spinnerQuan = mView.findViewById(R.id.spinner_quan);
        tvTenCuaHang = mView.findViewById(R.id.ten_cuahang);
        tvEmailCuaHang = mView.findViewById(R.id.email_cuahang);
        tvSDT = mView.findViewById(R.id.sdt_cuahang);
        tvMoTa = mView.findViewById(R.id.mota_cuahang);
        tvDiaChi = mView.findViewById(R.id.diachi_cuahang);
        imageView = mView.findViewById(R.id.imgProfile);
    }
}
