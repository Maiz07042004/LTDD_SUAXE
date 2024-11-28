package com.example.ltdd_suaxe.dathang_user_tab;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

import com.example.ltdd_suaxe.R;

public class Tab_Fragment_User_DaHoanThanh extends Fragment {
    public Tab_Fragment_User_DaHoanThanh() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_tab_user_dahoanthanh, container, false);
    }
}
