package com.example.ltdd_suaxe;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class nCuaHangDaLuu_Adapter extends BaseAdapter {
    private Context context;
    private int layout;
    private List<nCuaHangDaLuu> ncuaHangDaLuuList;

    public nCuaHangDaLuu_Adapter(Context context, int layout, List<nCuaHangDaLuu> ncuaHangDaLuuList) {
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
        ImageView imgLuu = (ImageView) view.findViewById(R.id.luu_icon);

        //gán giá trị
        nCuaHangDaLuu nCuaHangDaLuu = ncuaHangDaLuuList.get(i);
        txtTen.setText(nCuaHangDaLuu.getTen());
        txtLike.setText(String.valueOf(nCuaHangDaLuu.getLike()));
        imgHinh.setImageResource(nCuaHangDaLuu.getHinh());
        imgLuu.setImageResource(nCuaHangDaLuu.getLuu());

        return view;
    }
}
