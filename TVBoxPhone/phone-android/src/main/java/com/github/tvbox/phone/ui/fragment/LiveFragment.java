package com.github.tvbox.phone.ui.fragment;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.github.tvbox.phone.R;
import com.github.tvbox.phone.adapter.LiveChannelAdapter;
import com.github.tvbox.phone.bean.LiveChannel;

import java.util.ArrayList;
import java.util.List;

public class LiveFragment extends Fragment {

    private RecyclerView rvChannels;
    private EditText etSearch;
    private LiveChannelAdapter channelAdapter;
    private List<LiveChannel> allChannels = new ArrayList<>();

    public static LiveFragment newInstance() {
        return new LiveFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_live, container, false);
        rvChannels = view.findViewById(R.id.rvChannels);
        etSearch = view.findViewById(R.id.etSearch);

        channelAdapter = new LiveChannelAdapter();
        rvChannels.setLayoutManager(new LinearLayoutManager(getContext()));
        rvChannels.setAdapter(channelAdapter);

        channelAdapter.setOnItemClickListener((channel, position) -> {
            if (getActivity() != null) {
                android.content.Intent intent = new android.content.Intent(getActivity(), com.github.tvbox.phone.ui.activity.PhonePlayActivity.class);
                intent.putExtra("channelName", channel.getName());
                intent.putExtra("channelUrl", channel.getUrl());
                startActivity(intent);
            }
        });

        etSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                filterChannels(s.toString());
            }
            @Override
            public void afterTextChanged(Editable s) {}
        });

        loadChannels();
        return view;
    }

    private void loadChannels() {
        String[] groupNames = {"央视", "卫视", "地方", "体育", "影视", "少儿"};
        String[][] channelNames = {
                {"CCTV-1 综合", "CCTV-2 财经", "CCTV-3 综艺", "CCTV-4 中文国际", "CCTV-5 体育", "CCTV-6 电影", "CCTV-7 国防军事", "CCTV-8 电视剧", "CCTV-9 纪录", "CCTV-10 科教", "CCTV-11 戏曲", "CCTV-12 社会与法", "CCTV-13 新闻", "CCTV-14 少儿", "CCTV-15 音乐"},
                {"湖南卫视", "浙江卫视", "江苏卫视", "东方卫视", "北京卫视", "广东卫视", "深圳卫视", "山东卫视", "四川卫视", "天津卫视", "安徽卫视", "河南卫视"},
                {"广州新闻", "深圳都市", "珠海一套", "佛山新闻", "东莞新闻", "中山综合"},
                {"CCTV-5 体育", "广东体育", "五星体育", "北京体育"},
                {"CHC动作电影", "CHC家庭影院", "风云剧场", "第一剧场"},
                {"金鹰卡通", "卡酷少儿", "嘉佳卡通", "少儿频道"}
        };
        int num = 1;
        for (int g = 0; g < groupNames.length; g++) {
            for (int c = 0; c < channelNames[g].length; c++) {
                LiveChannel channel = new LiveChannel();
                channel.setName(channelNames[g][c]);
                channel.setGroupName(groupNames[g]);
                channel.setChannelNum(num++);
                channel.setUrl("");
                allChannels.add(channel);
            }
        }
        channelAdapter.setDataList(allChannels);
    }

    private void filterChannels(String keyword) {
        if (keyword.isEmpty()) {
            channelAdapter.setDataList(allChannels);
            return;
        }
        List<LiveChannel> filtered = new ArrayList<>();
        for (LiveChannel ch : allChannels) {
            if (ch.getName().contains(keyword) || ch.getGroupName().contains(keyword)) {
                filtered.add(ch);
            }
        }
        channelAdapter.setDataList(filtered);
    }
}