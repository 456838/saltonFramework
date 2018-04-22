package com.salton123.base;


import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public abstract class BaseRecycerViewAdapter<T, V extends RecyclerView.ViewHolder> extends RecyclerView.Adapter<V> {
    protected OnItemClickListener mClickListener;
    public static Context context;
    private List<T> mData = new ArrayList<>();
    public LayoutInflater inflater;

    public BaseRecycerViewAdapter(Context context) {
        this.context = context;
        inflater = LayoutInflater.from(context);
    }

    public void setOnItemClickListener(OnItemClickListener itemClickListener) {
        this.mClickListener = itemClickListener;
    }

    public List<T> getData() {
        return mData;
    }

    @Override
    public V onCreateViewHolder(ViewGroup parent, int viewType) {
        return getCreateViewHolder(parent, viewType);
    }

    @Override
    public void onBindViewHolder(V holder, int position) {
        getBindViewHolder(holder, position);
    }

    public abstract V getCreateViewHolder(ViewGroup parent, int viewType);

    public abstract void getBindViewHolder(V holder, int position);

    public void addAll(Collection<? extends T> collection) {
        addAll(mData.size(), collection);
    }

    public void addAll(int position, Collection<? extends T> collection) {
        mData.addAll(position, collection);
        notifyItemRangeInserted(position, collection.size());
    }

    public void clear() {
        mData.clear();
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public interface OnItemClickListener {
        void onItemClick(int position, View view, RecyclerView.ViewHolder vh);
    }
}

