package com.example.ltdd_suaxe;

import android.content.Context;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

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
        txtSoDienThoai.setText(donYeuCau.getSoDienThoai());
        txtDiaChi.setText(donYeuCau.getDiaChi());
        imgHinh.setImageResource(donYeuCau.getHinh());

        // Set sự kiện OnClick cho Button
        btnXacNhan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(btnXacNhan.getText().toString().equals("Xác nhận")){
                    // Thay đổi màu nền của Button khi nhấn
                    btnXacNhan.setBackgroundColor(context.getResources().getColor(R.color.white));
                    btnXacNhan.setText("ĐÃ XÁC NHẬN");
                }
                if(btnXacNhan.getText().toString().equals("Hoàn thành")){
                    // Thay đổi màu nền của Button khi nhấn
                    btnXacNhan.setBackgroundColor(context.getResources().getColor(R.color.white));
                    btnXacNhan.setText("ĐÃ HOÀN THÀNH");
                }
            }
        });


        return view;
    }
}
