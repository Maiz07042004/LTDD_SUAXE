package com.example.ltdd_suaxe.Adapter;

import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.CenterCrop;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.request.RequestOptions;
import com.example.ltdd_suaxe.DonYeuCau;
import com.example.ltdd_suaxe.Model.DonSuaChuaRequest;
import com.example.ltdd_suaxe.Model.DonSuaChua_Daxacnhan;
import com.example.ltdd_suaxe.R;
import com.example.ltdd_suaxe.dathang_user_tab.Tab_Fragment_User_DaXacNhan;

import java.util.List;

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
        txtNgayDat.setText(donDaXacNhan.getNgayDatDon().toString());
        txtTrangThai.setText(donDaXacNhan.getTrangThai());
        String imageUrl = donDaXacNhan.getHinhAnh();

        Glide.with(context)
                .load(imageUrl)
                .apply(RequestOptions.bitmapTransform(new CenterCrop()))  // Cắt ảnh sao cho phủ đầy ImageView
                .apply(RequestOptions.bitmapTransform(new RoundedCorners(20))) // Bo góc ảnh với bán kính 20
                .into(imgHinh);


        return view;
    }
}
