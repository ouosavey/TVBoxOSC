package com.github.tvbox.phone.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.github.tvbox.phone.R;
import com.github.tvbox.phone.bean.MovieSort;

import java.util.ArrayList;
import java.util.List;

public class SortAdapter extends RecyclerView.Adapter<SortAdapter.SortViewHolder> {

    private List<MovieSort.SortData> dataList = new ArrayList<>();
    private int selectedPosition = 0;
    private OnItemClickListener onItemClickListener;

    public interface OnItemClickListener {
        void onItemClick(MovieSort.SortData sortData, int position);
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.onItemClickListener = listener;
    }

    public void setDataList(List<MovieSort.SortData> list) {
        this.dataList = list != null ? list : new ArrayList<>();
        notifyDataSetChanged();
    }

    public void setSelectedPosition(int position) {
        int oldPos = selectedPosition;
        selectedPosition = position;
        notifyItemChanged(oldPos);
        notifyItemChanged(position);
    }

    @NonNull
    @Override
    public SortViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_sort, parent, false);
        return new SortViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SortViewHolder holder, int position) {
        MovieSort.SortData data = dataList.get(position);
        holder.tvName.setText(data.name);
        holder.tvName.setSelected(position == selectedPosition);
        holder.tvName.setTextColor(position == selectedPosition
                ? holder.itemView.getContext().getResources().getColor(R.color.colorAccent)
                : holder.itemView.getContext().getResources().getColor(R.color.white));
        if (onItemClickListener != null) {
            holder.itemView.setOnClickListener(v -> {
                setSelectedPosition(position);
                onItemClickListener.onItemClick(data, position);
            });
        }
    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }

    static class SortViewHolder extends RecyclerView.ViewHolder {
        TextView tvName;

        SortViewHolder(View view) {
            super(view);
            tvName = view.findViewById(R.id.tvName);
        }
    }
}