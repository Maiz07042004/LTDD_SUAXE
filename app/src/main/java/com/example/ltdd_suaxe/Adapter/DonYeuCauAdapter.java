package com.example.ltdd_suaxe.Adapter;

import android.content.Context;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.CenterCrop;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.request.RequestOptions;
import com.example.ltdd_suaxe.API.APIService;
import com.example.ltdd_suaxe.API.RetrofitApp;
import com.example.ltdd_suaxe.Model.DonYeuCau;
import com.example.ltdd_suaxe.Model.ResponseChung;
import com.example.ltdd_suaxe.R;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DonYeuCauAdapter extends BaseAdapter {
    private Context context;
    private int layout;
    private List<DonYeuCau> donYeuCauList;

    public DonYeuCauAdapter(Context context, int layout, List<DonYeuCau> donYeuCauList) {
        this.context = context;
        this.layout = layout;
        this.donYeuCauList = donYeuCauList;
    }

    @Override
    public int getCount() {
        return donYeuCauList.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        LayoutInflater inflater= (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        view=inflater.inflate(layout,null);

//        Ánh xạ view
        TextView txtTen=(TextView) view.findViewById(R.id.ten_khachhang);
        TextView txtDichVu=(TextView) view.findViewById(R.id.dichvu);
        TextView txtSoDienThoai=(TextView) view.findViewById(R.id.sdt);
        TextView txtDiaChi=(TextView) view.findViewById(R.id.diachi);
        ImageView imgHinh=(ImageView) view.findViewById(R.id.imgKhachHang);
        Button btnXacNhan=(Button) view.findViewById((R.id.btnXacnhan));

//        Gán giá trị
        DonYeuCau donYeuCau=donYeuCauList.get(i);

        txtTen.setText(donYeuCau.getTenKhachHang());
        String dichVuString = TextUtils.join(", ", donYeuCau.getDichVu());
        txtDichVu.setText(dichVuString);
        txtSoDienThoai.setText(donYeuCau.getSDT());
        txtDiaChi.setText(donYeuCau.getDiaChi());

        String imageUrl = donYeuCau.getHinhAnh();;
        Glide.with(context)
                .load(imageUrl)
                .apply(RequestOptions.bitmapTransform(new CenterCrop()))  // Cắt ảnh sao cho phủ đầy ImageView
                .apply(RequestOptions.bitmapTransform(new RoundedCorners(20))) // Bo góc ảnh với bán kính 20
                .into(imgHinh);

        // Set sự kiện OnClick cho Button
        btnXacNhan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String IdDonSuaChua = donYeuCau.get_id();
                if(btnXacNhan.getText().toString().equals("Xác nhận")){
                    // Thay đổi màu nền của Button khi nhấn
                    updateDonSuaChua(IdDonSuaChua,"DaXacNhan");
                    btnXacNhan.setBackgroundColor(context.getResources().getColor(R.color.white));
                    btnXacNhan.setText("Đã xác nhận");
                }
                if(btnXacNhan.getText().toString().equals("Hoàn thành")){
                    // Thay đổi màu nền của Button khi nhấn
                    updateDonSuaChua(IdDonSuaChua,"DaHoanThanh");
                    btnXacNhan.setBackgroundColor(context.getResources().getColor(R.color.white));
                    btnXacNhan.setText("Đã hoàn thành");
                }
            }
        });


        return view;
    }

    private void updateDonSuaChua(String IdDonSuaChua,String status){
        // Khởi tạo ApiService
        APIService apiService = RetrofitApp.getRetrofitInstance().create(APIService.class);


        // Gọi API để xóa cửa hàng
        Call<ResponseChung> call = apiService.updateDonSuaChua(IdDonSuaChua,status);
        call.enqueue(new Callback<ResponseChung>() {
            @Override
            public void onResponse(Call<ResponseChung> call, Response<ResponseChung> response) {
                if (response.isSuccessful()) {
                    ResponseChung responseChung=response.body();
                    if(responseChung.getCode()==200){
                        // Cập nhật lại danh sách sau khi xóa
                        donYeuCauList.removeIf(donSuaChua -> donSuaChua.get_id().equals(IdDonSuaChua   ));
                        notifyDataSetChanged();  // Cập nhật giao diện
                        Toast.makeText(context, "Đã xác nhận", Toast.LENGTH_SHORT).show();
                    }else {
                        Toast.makeText(context, "Lỗi", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(context, "Lỗi", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ResponseChung> call, Throwable t) {
                Toast.makeText(context, "Lỗi kết nối: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}
