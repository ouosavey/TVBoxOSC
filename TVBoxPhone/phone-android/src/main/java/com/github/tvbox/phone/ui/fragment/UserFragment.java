package com.github.tvbox.phone.ui.fragment;

import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.github.tvbox.phone.R;

public class UserFragment extends Fragment {

    public static UserFragment newInstance() {
        return new UserFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_user, container, false);

        TextView tvHistory = view.findViewById(R.id.tvHistory);
        TextView tvCollect = view.findViewById(R.id.tvCollect);
        TextView tvSetting = view.findViewById(R.id.tvSetting);
        TextView tvAbout = view.findViewById(R.id.tvAbout);

        tvHistory.setOnClickListener(v -> showToast("播放历史"));
        tvCollect.setOnClickListener(v -> showToast("我的收藏"));
        tvSetting.setOnClickListener(v -> showToast("设置"));
        tvAbout.setOnClickListener(v -> showToast("TVBox Phone v1.0\n基于TVBoxOSC开发的竖屏手机版"));

        return view;
    }

    private void showToast(String msg) {
        if (getContext() != null) {
            Toast.makeText(getContext(), msg, Toast.LENGTH_SHORT).show();
        }
    }
}