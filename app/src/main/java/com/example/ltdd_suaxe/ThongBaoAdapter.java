package com.example.ltdd_suaxe;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

public class ThongBaoAdapter extends BaseAdapter {

    private Context context;
    private List<ThongBao> notifications;

    public ThongBaoAdapter(Context context, List<ThongBao> notifications) {
        this.context = context;
        this.notifications = notifications;
    }

    @Override
    public int getCount() {
        return notifications.size();
    }

    @Override
    public Object getItem(int position) {
        return notifications.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(android.R.layout.simple_list_item_2, parent, false);
        }

        ThongBao notification = notifications.get(position);

        TextView titleTextView = convertView.findViewById(android.R.id.text1);
        TextView contentTextView = convertView.findViewById(android.R.id.text2);

        titleTextView.setText(notification.getTitle());
        contentTextView.setText(notification.getContent());
        // Tùy chỉnh giao diện: làm tiêu đề lớn hơn và đậm hơn
        titleTextView.setTextSize(18); // Tăng kích thước font cho tiêu đề
        titleTextView.setTextColor(Color.BLACK); // Đặt màu chữ tiêu đề
        titleTextView.setTypeface(null, android.graphics.Typeface.BOLD); // Đặt chữ đậm

        contentTextView.setTextSize(14); // Tăng kích thước font cho nội dung phụ
        contentTextView.setTextColor(Color.GRAY); // Đặt màu chữ cho nội dung phụ

        return convertView;
    }
}
