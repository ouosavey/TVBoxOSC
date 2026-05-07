package com.github.tvbox.phone.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.github.tvbox.phone.R;
import com.github.tvbox.phone.bean.VodInfo;

import java.util.ArrayList;
import java.util.List;

public class VodListAdapter extends RecyclerView.Adapter<VodListAdapter.VodViewHolder> {

    private List<VodInfo> dataList = new ArrayList<>();
    private OnItemClickListener onItemClickListener;

    public interface OnItemClickListener {
        void onItemClick(VodInfo vodInfo, int position);
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.onItemClickListener = listener;
    }

    public void setDataList(List<VodInfo> list) {
        this.dataList = list != null ? list : new ArrayList<>();
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public VodViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_vod, parent, false);
        return new VodViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull VodViewHolder holder, int position) {
        VodInfo info = dataList.get(position);
        holder.tvName.setText(info.getName());
        holder.tvRemark.setText(info.getRemark());
        if (onItemClickListener != null) {
            holder.itemView.setOnClickListener(v -> onItemClickListener.onItemClick(info, position));
        }
    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }

    static class VodViewHolder extends RecyclerView.ViewHolder {
        ImageView ivPic;
        TextView tvName;
        TextView tvRemark;

        VodViewHolder(View view) {
            super(view);
            ivPic = view.findViewById(R.id.ivPic);
            tvName = view.findViewById(R.id.tvName);
            tvRemark = view.findViewById(R.id.tvRemark);
        }
    }
}