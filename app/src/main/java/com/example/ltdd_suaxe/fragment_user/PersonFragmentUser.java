package com.example.ltdd_suaxe.fragment_user;

import static android.content.Context.MODE_PRIVATE;

import android.app.AlertDialog;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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
import com.example.ltdd_suaxe.Model.CapNhatKhachHangRequest;
import com.example.ltdd_suaxe.Model.ResponseChung;
import com.example.ltdd_suaxe.Model.User;
import com.example.ltdd_suaxe.R;
import com.google.android.material.imageview.ShapeableImageView;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PersonFragmentUser extends Fragment {
    private View mView;
    private Spinner spinnerGender;
    private EditText tvTenKH,tvEmailKH,tvSDT;
    private Button btnUpdate;
    private ShapeableImageView imageView ;

    @Nullable



    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {


        mView = inflater.inflate(R.layout.fragment_person_user, container, false);

        AnhXa();

        // Tạo adapter cho Spinner
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(getActivity(),
                R.array.gender_array, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // Gán adapter cho Spinner
        spinnerGender.setAdapter(adapter);

        // Lấy SharedPreferences
        SharedPreferences sharedPreferences = getContext().getSharedPreferences("user_prefs", MODE_PRIVATE);

// Lấy userId từ SharedPreferences
        String userId = sharedPreferences.getString("userId", null);  // Nếu không có giá trị, trả về null

        detailKhachHang(userId);


        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Lấy dữ liệu từ các trường nhập liệu
                String tenKhachHang = tvTenKH.getText().toString().trim();
                String emailKhachHang = tvEmailKH.getText().toString().trim();
                String sdt = tvSDT.getText().toString().trim();
                String gioiTinh = spinnerGender.getSelectedItem().toString();

                // Kiểm tra dữ liệu hợp lệ
                if (tenKhachHang.isEmpty() || emailKhachHang.isEmpty() || sdt.isEmpty()) {
                    Toast.makeText(getContext(), "Vui lòng điền đầy đủ thông tin!", Toast.LENGTH_SHORT).show();
                    return;
                }

                // Tạo đối tượng CapNhatKhachHangRequest
                CapNhatKhachHangRequest capNhatKhachHangRequest = new CapNhatKhachHangRequest(tenKhachHang, emailKhachHang, sdt, gioiTinh);

                // Gọi API để cập nhật thông tin người dùng
                updateThongTinKhachHang(userId, capNhatKhachHangRequest);

            }
        });

        return mView;
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
                    tvSDT.setText(user.getSDT());

                    String gender = user.getGioiTinh();

                    // Gán giá trị giới tính cho Spinner
                    setGenderToSpinner(gender);

                    String imageUrl = user.getHinhAnh(); // URL ảnh

                    Glide.with(getContext())
                            .load(imageUrl)
                            .apply(RequestOptions.circleCropTransform()) // Sử dụng Glide để cắt ảnh thành hình tròn
                            .into(imageView);

                } else {
                    Toast.makeText(getContext(), "Lỗi", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                Toast.makeText(getContext(), "Lỗi kết nối: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void updateThongTinKhachHang(String userId, CapNhatKhachHangRequest capNhatKhachHangRequest) {
        // Khởi tạo APIService
        APIService apiService = RetrofitApp.getRetrofitInstance().create(APIService.class);

        // Gọi API cập nhật thông tin khách hàng
        Call<ResponseChung> call = apiService.updateUser(userId, capNhatKhachHangRequest);
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

    //Hàm gán giá trị giới tính
    private void setGenderToSpinner(String gender) {
        // Duyệt qua các giá trị trong Spinner và gán giá trị đúng
        ArrayAdapter<CharSequence> adapter = (ArrayAdapter<CharSequence>) spinnerGender.getAdapter();
        int position = adapter.getPosition(gender); // Tìm vị trí của giới tính trong Spinner
        if (position != -1) {
            spinnerGender.setSelection(position); // Gán giá trị vào Spinner
        }
    }

    private void AnhXa(){
        tvTenKH=mView.findViewById(R.id.edtTenKH);
        tvEmailKH=mView.findViewById(R.id.edtEmailKH);
        tvSDT=mView.findViewById(R.id.edtSDT);
        btnUpdate = mView.findViewById(R.id.button3);
        // Khởi tạo Spinner
        spinnerGender = mView.findViewById(R.id.spinnerGender);
        imageView = mView.findViewById(R.id.imageViewUser);
    }
}
