package com.example.ltdd_suaxe;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.example.ltdd_suaxe.fragment_cuahang.HomeFragmentCuaHang;
import com.example.ltdd_suaxe.fragment_cuahang.ListFragmentCuaHang;
import com.example.ltdd_suaxe.fragment_cuahang.PersonFragmentCuaHang;
import com.example.ltdd_suaxe.fragment_cuahang.SettingsFragmentCuaHang;
import com.example.ltdd_suaxe.fragment_user.HomeFragmentUser;
import com.example.ltdd_suaxe.fragment_user.ListFragmentUser;
import com.example.ltdd_suaxe.fragment_user.PersonFragmentUser;
import com.example.ltdd_suaxe.fragment_user.SettingsFragmentUser;

public class ViewPagerAdapterCuaHang extends FragmentStateAdapter {

    public ViewPagerAdapterCuaHang(@NonNull FragmentActivity fragmentActivity) {
        super(fragmentActivity);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        switch (position){
            case 0:
                return new HomeFragmentCuaHang();
            case 1:
                return new ListFragmentCuaHang();
            case 2:
                return new PersonFragmentCuaHang();
            case 3:
                return new SettingsFragmentCuaHang();
            default:
                return new HomeFragmentCuaHang();
        }
    }

    @Override
    public int getItemCount() {
        return 4;
    }
}
