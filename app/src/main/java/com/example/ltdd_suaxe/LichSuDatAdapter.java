package com.example.ltdd_suaxe;

import android.content.Context;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class LichSuDatAdapter extends BaseAdapter {
    private Context context;
    private int layout;
    private List<LichSuDat> lichSuDatList;

    public LichSuDatAdapter(Context context, int layout, List<LichSuDat> lichSuDatList) {
        this.context = context;
        this.layout = layout;
        this.lichSuDatList = lichSuDatList;
    }

    @Override
    public int getCount() {
        return lichSuDatList.size();
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
        LichSuDat lichSuDat=lichSuDatList.get(i);

        txtTen.setText(lichSuDat.getTenCuaHang());
        String dichVuString = TextUtils.join(", ", lichSuDat.getDichVu());
        txtDichVu.setText(dichVuString);
        txtNgayDat.setText(lichSuDat.getNgayDat().toString());
        txtTrangThai.setText(lichSuDat.getTrangThai());
        imgHinh.setImageResource(lichSuDat.getHinh());

        return view;
    }
}
