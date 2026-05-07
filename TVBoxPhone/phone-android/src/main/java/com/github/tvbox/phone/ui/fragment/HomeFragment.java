package com.github.tvbox.phone.ui.fragment;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.github.tvbox.phone.R;
import com.github.tvbox.phone.adapter.SortAdapter;
import com.github.tvbox.phone.adapter.VodListAdapter;
import com.github.tvbox.phone.bean.MovieSort;
import com.github.tvbox.phone.bean.VodInfo;

import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends Fragment {

    private RecyclerView rvSort;
    private RecyclerView rvVod;
    private SortAdapter sortAdapter;
    private VodListAdapter vodListAdapter;

    public static HomeFragment newInstance() {
        return new HomeFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        rvSort = view.findViewById(R.id.rvSort);
        rvVod = view.findViewById(R.id.rvVod);

        sortAdapter = new SortAdapter();
        rvSort.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));
        rvSort.setAdapter(sortAdapter);

        vodListAdapter = new VodListAdapter();
        rvVod.setLayoutManager(new GridLayoutManager(getContext(), 3));
        rvVod.setAdapter(vodListAdapter);

        sortAdapter.setOnItemClickListener((sortData, position) -> loadVodList(position));
        vodListAdapter.setOnItemClickListener((vodInfo, position) -> {
            if (getActivity() != null) {
                android.content.Intent intent = new android.content.Intent(getActivity(), com.github.tvbox.phone.ui.activity.PhoneDetailActivity.class);
                intent.putExtra("vodName", vodInfo.getName());
                intent.putExtra("vodId", vodInfo.getId());
                startActivity(intent);
            }
        });

        loadSortList();
        loadVodList(0);

        return view;
    }

    private void loadSortList() {
        List<MovieSort.SortData> sortList = new ArrayList<>();
        String[] names = {"推荐", "电影", "电视剧", "综艺", "动漫", "纪录片", "体育", "少儿"};
        for (int i = 0; i < names.length; i++) {
            sortList.add(new MovieSort.SortData(String.valueOf(i + 1), names[i]));
        }
        sortAdapter.setDataList(sortList);
    }

    private void loadVodList(int sortIndex) {
        List<VodInfo> vodList = new ArrayList<>();
        String[][] vodData = {
                {"战狼2", "红海行动", "流浪地球", "哪吒之魔童降世", "我和我的祖国", "中国机长"},
                {"肖申克的救赎", "霸王别姬", "阿甘正传", "泰坦尼克号", "盗梦空间", "星际穿越"},
                {"庆余年", "长安十二时辰", "隐秘的角落", "沉默的真相", "三十而已", "清平乐"},
                {"奔跑吧", "极限挑战", "向往的生活", "中国好声音", "王牌对王牌", "快乐大本营"},
                {"斗罗大陆", "完美世界", "斗破苍穹", "一念永恒", "凡人修仙传", "灵笼"},
                {"舌尖上的中国", "地球脉动", "蓝色星球", "河西走廊", "大明宫", "故宫"},
                {"NBA精选", "英超集锦", "欧冠精华", "世界杯回顾", "中超集锦", "CBA精选"},
                {"熊出没", "喜羊羊", "大头儿子", "超级飞侠", "汪汪队", "小猪佩奇"}
        };
        String[] remarks = {"高清", "更新至24集", "4K", "1080P", "蓝光", "全集"};
        int idx = Math.min(sortIndex, vodData.length - 1);
        for (int i = 0; i < vodData[idx].length; i++) {
            VodInfo vod = new VodInfo();
            vod.setId(String.valueOf(i + 1));
            vod.setName(vodData[idx][i]);
            vod.setRemark(remarks[i % remarks.length]);
            vodList.add(vod);
        }
        vodListAdapter.setDataList(vodList);
    }
}