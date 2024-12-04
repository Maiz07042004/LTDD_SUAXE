package com.example.ltdd_suaxe;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.example.ltdd_suaxe.fragment_user.HomeFragmentUser;
import com.example.ltdd_suaxe.fragment_user.ListFragmentUser;
import com.example.ltdd_suaxe.fragment_user.PersonFragmentUser;
import com.example.ltdd_suaxe.fragment_user.SettingsFragmentUser;

public class ViewPagerAdapterUser extends FragmentStateAdapter {

    public ViewPagerAdapterUser(@NonNull FragmentActivity fragmentActivity) {
        super(fragmentActivity);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        switch (position){
            case 0:
                return new HomeFragmentUser();
            case 1:
                return new ListFragmentUser();
            case 2:
                return new PersonFragmentUser();
            case 3:
                return new SettingsFragmentUser();
            default:
                return new HomeFragmentUser();
        }
    }

    @Override
    public int getItemCount() {
        return 4;
    }
}
