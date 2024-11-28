package com.example.ltdd_suaxe;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.request.RequestOptions;
import com.example.ltdd_suaxe.Model.Quan;

import java.util.List;

public class QuanAdapter  extends BaseAdapter {

    private Context context;
    private int layout;
    private List<Quan> quanList;

    public QuanAdapter(Context context, int layout, List<Quan> quanList) {
        this.context = context;
        this.layout = layout;
        this.quanList = quanList;
    }

    @Override
    public int getCount() {
        return quanList.size();
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
        TextView txtTen=(TextView) view.findViewById(R.id.ten_quan);
        TextView txtMoTa=(TextView) view.findViewById(R.id.mota_quan);
        ImageView imgHinh=(ImageView) view.findViewById(R.id.imageView_quan);

//        Gán giá trị
        Quan quan=quanList.get(i);

        txtTen.setText(quan.getTenQuan());
        txtMoTa.setText(quan.getMoTa());
        String imageUrl = quan.getHinhAnh();

        Glide.with(context)
                .load(imageUrl)
                .apply(RequestOptions.bitmapTransform(new RoundedCorners(20))) // 20 là bán kính bo góc// Tải ảnh từ URL
                .into(imgHinh);  // Hiển thị ảnh trong ImageView

        return view;

    }
}
