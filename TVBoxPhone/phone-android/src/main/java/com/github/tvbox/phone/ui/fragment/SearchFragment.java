package com.github.tvbox.phone.ui.fragment;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import com.github.tvbox.phone.R;
import com.github.tvbox.phone.adapter.VodListAdapter;
import com.github.tvbox.phone.bean.VodInfo;
import com.google.android.material.chip.Chip;
import com.google.android.material.chip.ChipGroup;

import java.util.ArrayList;
import java.util.List;

public class SearchFragment extends Fragment {

    private EditText etSearch;
    private TextView tvSearchBtn;
    private ChipGroup chipGroup;
    private RecyclerView rvResults;
    private VodListAdapter vodListAdapter;

    public static SearchFragment newInstance() {
        return new SearchFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_search, container, false);
        etSearch = view.findViewById(R.id.etSearch);
        tvSearchBtn = view.findViewById(R.id.tvSearchBtn);
        chipGroup = view.findViewById(R.id.chipGroup);
        rvResults = view.findViewById(R.id.rvResults);

        vodListAdapter = new VodListAdapter();
        rvResults.setLayoutManager(new GridLayoutManager(getContext(), 3));
        rvResults.setAdapter(vodListAdapter);

        vodListAdapter.setOnItemClickListener((vodInfo, position) -> {
            if (getActivity() != null) {
                android.content.Intent intent = new android.content.Intent(getActivity(), com.github.tvbox.phone.ui.activity.PhoneDetailActivity.class);
                intent.putExtra("vodName", vodInfo.getName());
                intent.putExtra("vodId", vodInfo.getId());
                startActivity(intent);
            }
        });

        String[] hotWords = {"战狼", "庆余年", "流浪地球", "斗罗大陆", "隐秘的角落", "舌尖上的中国", "NBA", "哪吒"};
        for (String word : hotWords) {
            Chip chip = new Chip(getContext());
            chip.setText(word);
            chip.setTextColor(getResources().getColor(R.color.white));
            chip.setChipBackgroundColorResource(R.color.colorPrimary);
            chip.setOnClickListener(v -> {
                etSearch.setText(word);
                performSearch(word);
            });
            chipGroup.addView(chip);
        }

        tvSearchBtn.setOnClickListener(v -> {
            String keyword = etSearch.getText().toString().trim();
            if (!keyword.isEmpty()) {
                performSearch(keyword);
            }
        });

        return view;
    }

    private void performSearch(String keyword) {
        List<VodInfo> results = new ArrayList<>();
        String[] allVods = {
                "战狼2", "战狼1", "红海行动", "流浪地球", "流浪地球2",
                "庆余年", "庆余年2", "长安十二时辰", "隐秘的角落", "沉默的真相",
                "斗罗大陆", "完美世界", "斗破苍穹", "哪吒之魔童降世", "舌尖上的中国",
                "NBA精选", "英超集锦", "我和我的祖国", "中国机长", "肖申克的救赎"
        };
        String[] remarks = {"高清", "更新至24集", "4K", "1080P", "蓝光", "全集"};
        int idx = 0;
        for (String vod : allVods) {
            if (vod.contains(keyword)) {
                VodInfo info = new VodInfo();
                info.setId(String.valueOf(++idx));
                info.setName(vod);
                info.setRemark(remarks[idx % remarks.length]);
                results.add(info);
            }
        }
        if (results.isEmpty()) {
            for (int i = 0; i < 6; i++) {
                VodInfo info = new VodInfo();
                info.setId(String.valueOf(i + 1));
                info.setName(keyword + " - 结果" + (i + 1));
                info.setRemark(remarks[i % remarks.length]);
                results.add(info);
            }
        }
        vodListAdapter.setDataList(results);
    }
}