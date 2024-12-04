package com.example.ltdd_suaxe;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.CenterCrop;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.request.RequestOptions;
import com.example.ltdd_suaxe.API.APIService;
import com.example.ltdd_suaxe.API.RetrofitApp;
import com.example.ltdd_suaxe.Model.CuaHang;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class nCuaHangDaLuu_Adapter extends BaseAdapter {
    private Context context;
    private int layout;
    private List<CuaHang> ncuaHangDaLuuList;
    private String userId;

    public nCuaHangDaLuu_Adapter(Context context, int layout, List<CuaHang> ncuaHangDaLuuList, String userId) {
        this.context = context;
        this.layout = layout;
        this.ncuaHangDaLuuList = ncuaHangDaLuuList;
        this.userId = userId;
    }

    @Override
    public int getCount() {
        return ncuaHangDaLuuList.size();
    }

    @Override
    public Object getItem(int position) {
        return ncuaHangDaLuuList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View view, ViewGroup viewGroup) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        view = inflater.inflate(layout, null);

        // ánh xạ view
        TextView txtTen = view.findViewById(R.id.ten_cuahang);
        TextView txtLike = view.findViewById(R.id.like_cuahang);
        ImageView imgHinh = view.findViewById(R.id.imageCuahang);
        ImageView iconLuu = view.findViewById(R.id.luu_icon);

        // gán giá trị
        CuaHang nCuaHangDaLuu = ncuaHangDaLuuList.get(position);
        txtTen.setText(nCuaHangDaLuu.getTenCuaHang());
        txtLike.setText(String.valueOf(nCuaHangDaLuu.getLike()));
        String imageUrl = nCuaHangDaLuu.getHinhAnh();

        Glide.with(context)
                .load(imageUrl)
                .apply(RequestOptions.bitmapTransform(new CenterCrop()))
                .apply(RequestOptions.bitmapTransform(new RoundedCorners(20)))
                .into(imgHinh);

        // Set sự kiện OnClick cho Button
        iconLuu.setOnClickListener(v -> {
            String cuaHangId = nCuaHangDaLuu.get_id();
            deleteCuaHangDaLuu(cuaHangId);
        });

        return view;
    }

    private void deleteCuaHangDaLuu(String cuaHangId) {
        // Khởi tạo ApiService
        APIService apiService = RetrofitApp.getRetrofitInstance().create(APIService.class);

        // Tạo một map chứa cuaHangId
        Map<String, String> body = new HashMap<>();
        body.put("cuaHangId", cuaHangId);

        // Gọi API để xóa cửa hàng
        Call<ResponseBody> call = apiService.deleteCuaHangDaLuu(userId, body);
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if (response.isSuccessful()) {
                    // Cập nhật lại danh sách sau khi xóa
                    ncuaHangDaLuuList.removeIf(cuaHang -> cuaHang.get_id().equals(cuaHangId));
                    notifyDataSetChanged();  // Cập nhật giao diện

                    Toast.makeText(context, "Đã xoá thành công", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(context, "Bạn chưa lưu cửa hàng này", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Toast.makeText(context, "Lỗi kết nối: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}

