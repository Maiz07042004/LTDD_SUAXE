package com.example.ltdd_suaxe.dathang_tab;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.example.ltdd_suaxe.fragment_cuahang.HomeFragmentCuaHang;
import com.example.ltdd_suaxe.fragment_cuahang.ListFragmentCuaHang;
import com.example.ltdd_suaxe.fragment_cuahang.PersonFragmentCuaHang;
import com.example.ltdd_suaxe.fragment_cuahang.SettingsFragmentCuaHang;

public class ListViewPagerAdapterCuaHang extends FragmentStateAdapter {

    public ListViewPagerAdapterCuaHang(@NonNull FragmentActivity fragmentActivity) {
        super(fragmentActivity);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        switch (position){
            case 0:
                return new Tab_Fragment_DonYeuCau();
            case 1:
                return new Tab_Fragment_DonXacNhan();
            case 2:
                return new Tab_Fragment_DaHoanThanh();
            default:
                return new Tab_Fragment_DonYeuCau();
        }
    }

    @Override
    public int getItemCount() {
        return 3;
    }


}
