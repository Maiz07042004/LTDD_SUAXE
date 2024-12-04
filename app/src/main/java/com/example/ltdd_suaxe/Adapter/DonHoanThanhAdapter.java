package com.example.ltdd_suaxe.Adapter;

import android.content.Context;
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
import com.example.ltdd_suaxe.Model.DonYeuCau;
import com.example.ltdd_suaxe.R;

import java.text.SimpleDateFormat;
import java.util.List;

public class DonHoanThanhAdapter extends BaseAdapter {
    private Context context;
    private int layout;
    private List<DonYeuCau> donYeuCauList;

    public DonHoanThanhAdapter(Context context, int layout, List<DonYeuCau> donYeuCauList) {
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
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        view = inflater.inflate(layout, null);

//        Ánh xạ view
        TextView txtTen = (TextView) view.findViewById(R.id.ten_khachhang);
        TextView txtDichVu = (TextView) view.findViewById(R.id.dichvu);
        TextView txtSoDienThoai = (TextView) view.findViewById(R.id.sdt);
        TextView txtDiaChi = (TextView) view.findViewById(R.id.diachi);
        ImageView imgHinh = (ImageView) view.findViewById(R.id.imgKhachHang);
        TextView txtThoiGian = (TextView) view.findViewById((R.id.thoigian));

//        Gán giá trị
        DonYeuCau donYeuCau = donYeuCauList.get(i);

        txtTen.setText(donYeuCau.getTenKhachHang());
        String dichVuString = TextUtils.join(", ", donYeuCau.getDichVu());
        txtDichVu.setText(dichVuString);
        txtSoDienThoai.setText(donYeuCau.getSDT());
        txtDiaChi.setText(donYeuCau.getDiaChi());
        // Tạo một SimpleDateFormat với định dạng bạn muốn
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        txtThoiGian.setText(dateFormat.format(donYeuCau.getNgayDatDon()));

        String imageUrl = donYeuCau.getHinhAnh();
        ;
        Glide.with(context)
                .load(imageUrl)
                .apply(RequestOptions.bitmapTransform(new CenterCrop()))  // Cắt ảnh sao cho phủ đầy ImageView
                .apply(RequestOptions.bitmapTransform(new RoundedCorners(20))) // Bo góc ảnh với bán kính 20
                .into(imgHinh);
        return view;
    }
}
