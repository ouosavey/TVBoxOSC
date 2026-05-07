package com.github.tvbox.phone.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.github.tvbox.phone.R;
import com.github.tvbox.phone.bean.LiveChannel;

import java.util.ArrayList;
import java.util.List;

public class LiveChannelAdapter extends RecyclerView.Adapter<LiveChannelAdapter.ChannelViewHolder> {

    private List<LiveChannel> dataList = new ArrayList<>();
    private OnItemClickListener onItemClickListener;

    public interface OnItemClickListener {
        void onItemClick(LiveChannel channel, int position);
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.onItemClickListener = listener;
    }

    public void setDataList(List<LiveChannel> list) {
        this.dataList = list != null ? list : new ArrayList<>();
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ChannelViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_live_channel, parent, false);
        return new ChannelViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ChannelViewHolder holder, int position) {
        LiveChannel channel = dataList.get(position);
        holder.tvNum.setText(String.valueOf(channel.getChannelNum()));
        holder.tvName.setText(channel.getName());
        holder.tvGroup.setText(channel.getGroupName());
        if (onItemClickListener != null) {
            holder.itemView.setOnClickListener(v -> onItemClickListener.onItemClick(channel, position));
        }
    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }

    static class ChannelViewHolder extends RecyclerView.ViewHolder {
        TextView tvNum;
        TextView tvName;
        TextView tvGroup;

        ChannelViewHolder(View view) {
            super(view);
            tvNum = view.findViewById(R.id.tvNum);
            tvName = view.findViewById(R.id.tvName);
            tvGroup = view.findViewById(R.id.tvGroup);
        }
    }
}