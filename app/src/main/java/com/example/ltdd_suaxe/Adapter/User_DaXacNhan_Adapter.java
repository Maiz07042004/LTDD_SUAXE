package com.example.ltdd_suaxe.Adapter;

import android.content.Context;
import android.content.Intent;
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
import com.example.ltdd_suaxe.Model.DonSuaChua_Daxacnhan;
import com.example.ltdd_suaxe.Model.ResponseChung;
import com.example.ltdd_suaxe.R;

import com.example.ltdd_suaxe.nChiTietDatHang_AcTiViTy;

import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class User_DaXacNhan_Adapter extends BaseAdapter {

    private Context context;
    private int layout;
    private List<DonSuaChua_Daxacnhan> daxacnhanList;

    public User_DaXacNhan_Adapter(Context context, int layout, List<DonSuaChua_Daxacnhan> daxacnhanList) {
        this.context = context;
        this.layout = layout;
        this.daxacnhanList = daxacnhanList;
    }

    @Override
    public int getCount() {
        return daxacnhanList.size();
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
        TextView txtTen=(TextView) view.findViewById(R.id.ten_cuahang);
        TextView txtDichVu=(TextView) view.findViewById(R.id.dichvu);
        TextView txtNgayDat=(TextView) view.findViewById(R.id.ngaydat);
        TextView txtTrangThai=(TextView) view.findViewById(R.id.trangthai);
        ImageView imgHinh=(ImageView) view.findViewById(R.id.imgCuaHang);

        //        Gán giá trị
        DonSuaChua_Daxacnhan donDaXacNhan=daxacnhanList.get(i);

        txtTen.setText(donDaXacNhan.getTenCuaHang());
        String dichVuString = TextUtils.join(", ", donDaXacNhan.getDichVu());
        txtDichVu.setText(dichVuString);

        // Tạo một SimpleDateFormat với định dạng bạn muốn
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        txtNgayDat.setText(dateFormat.format(donDaXacNhan.getNgayDatDon()));
        switch (donDaXacNhan.getTrangThai()){
            case "ChoXacNhan":
                txtTrangThai.setText("Chờ xác nhận");
                break;
            case "DaXacNhan":
                txtTrangThai.setText("Đã xác nhận");
                break;
            case "DaHoanThanh":
                txtTrangThai.setText("Đã hoàn thành");
                break;
            case "DaHuy":
                txtTrangThai.setText("Đã huỷ");
                break;
            default:
                break;
        }

        String imageUrl = donDaXacNhan.getHinhAnh();;
        Glide.with(context)
                .load(imageUrl)
                .apply(RequestOptions.bitmapTransform(new CenterCrop()))  // Cắt ảnh sao cho phủ đầy ImageView
                .apply(RequestOptions.bitmapTransform(new RoundedCorners(20))) // Bo góc ảnh với bán kính 20
                .into(imgHinh);


        // Thêm sự kiện onClick cho mỗi item
        view.setOnClickListener(v -> {
            Intent intent = new Intent(context, nChiTietDatHang_AcTiViTy.class);
            intent.putExtra("IdDonHang", donDaXacNhan.get_id());
            context.startActivity(intent);
        });


        return view;
    }

}
