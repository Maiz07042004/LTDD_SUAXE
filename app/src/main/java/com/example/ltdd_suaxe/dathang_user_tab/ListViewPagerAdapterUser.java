package com.example.ltdd_suaxe.dathang_user_tab;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.adapter.FragmentStateAdapter;

public class ListViewPagerAdapterUser extends FragmentStateAdapter {
    public ListViewPagerAdapterUser(@NonNull Fragment fragment) {
        super(fragment);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        switch (position) {
            case 0:
                return new Tab_Fragment_User_ChoXacNhan();
            case 1:
                return new Tab_Fragment_User_DaXacNhan();
            case 2:
                return new Tab_Fragment_User_DaHoanThanh();
            case 3:
                return new Tab_Fragment_User_DaHuy();
            case 4:
                return new Tab_Fragment_User_LichSuDat();
            default:
                return new Tab_Fragment_User_ChoXacNhan();
        }
    }

    @Override
    public int getItemCount() {
        return 5;  // Số lượng tab (5 tab trong trường hợp này)
    }
}
