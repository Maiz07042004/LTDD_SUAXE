package com.example.ltdd_suaxe;

import android.content.Context;
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
import com.example.ltdd_suaxe.Model.CuaHang;

import java.util.List;

public class nCuaHangDaLuu_Adapter extends BaseAdapter {
    private Context context;
    private int layout;
    private List<CuaHang> ncuaHangDaLuuList;

    public nCuaHangDaLuu_Adapter(Context context, int layout, List<CuaHang> ncuaHangDaLuuList) {
        this.context = context;
        this.layout = layout;
        this.ncuaHangDaLuuList = ncuaHangDaLuuList;
    }

    @Override
    public int getCount() {
        return ncuaHangDaLuuList.size();
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
        view = inflater.inflate(layout,null);

        //ánh xa view
        TextView txtTen = (TextView) view.findViewById(R.id.ten_cuahang);
        TextView txtLike = (TextView) view.findViewById(R.id.like_cuahang);
        ImageView imgHinh =(ImageView) view.findViewById(R.id.imageCuahang);



        //gán giá trị
        CuaHang nCuaHangDaLuu = ncuaHangDaLuuList.get(i);
        txtTen.setText(nCuaHangDaLuu.getTenCuaHang());
        txtLike.setText(String.valueOf(nCuaHangDaLuu.getLike()));
        String imageUrl = nCuaHangDaLuu.getHinhAnh();

        Glide.with(context)
                .load(imageUrl)
                .apply(RequestOptions.bitmapTransform(new CenterCrop()))  // Cắt ảnh sao cho phủ đầy ImageView
                .apply(RequestOptions.bitmapTransform(new RoundedCorners(20))) // Bo góc ảnh với bán kính 20
                .into(imgHinh);  // Hiển thị ảnh trong ImageView

        return view;
    }
}
