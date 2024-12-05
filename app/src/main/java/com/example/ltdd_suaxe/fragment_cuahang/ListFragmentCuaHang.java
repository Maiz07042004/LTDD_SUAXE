package com.example.ltdd_suaxe.fragment_cuahang;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.widget.ViewPager2;

import com.example.ltdd_suaxe.LichSuDat;
import com.example.ltdd_suaxe.LichSuDatAdapter;
import com.example.ltdd_suaxe.R;
import com.example.ltdd_suaxe.dathang_tab.ListViewPagerAdapterCuaHang;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class ListFragmentCuaHang extends Fragment {
    private TabLayout tabLayout;
    private ViewPager2 viewPager;
    private ListViewPagerAdapterCuaHang viewPagerAdapter;
    private View mView;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.fragment_list_cuahang,container,false);



        // Ánh xạ view
        tabLayout = mView.findViewById(R.id.tab_layout_cuaHang);
        viewPager = mView.findViewById(R.id.viewPager);

        // Tạo adapter cho ViewPager2
        viewPagerAdapter = new ListViewPagerAdapterCuaHang(getActivity());
        viewPager.setAdapter(viewPagerAdapter);
        // Liên kết TabLayout với ViewPager2
        new TabLayoutMediator(tabLayout, viewPager, new TabLayoutMediator.TabConfigurationStrategy() {
            @Override
            public void onConfigureTab(@NonNull TabLayout.Tab tab, int position) {
                switch (position) {
                    case 0:
                        tab.setText("Đơn yêu cầu");
                        break;
                    case 1:
                        tab.setText("Đã xác nhận");
                        break;
                    case 2:
                        tab.setText("Đã hoàn thành");
                        break;
                }
            }
        }).attach();
        return mView;
    }

}
