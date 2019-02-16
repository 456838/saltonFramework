package com.salton123.view.adapter.abslistview;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.salton123.view.adapter.base.ItemViewDelegate;
import com.salton123.view.adapter.base.ItemViewDelegateManager;
import com.salton123.view.adapter.base.ViewHolder;

import java.util.ArrayList;
import java.util.List;

public class MultiLvAdapter<T> extends BaseAdapter {
    protected Context mContext;
    protected List<T> mDatas = new ArrayList<>();

    private ItemViewDelegateManager mItemViewDelegateManager;


    public MultiLvAdapter(Context context) {
        this.mContext = context;
        mItemViewDelegateManager = new ItemViewDelegateManager();
    }

    public MultiLvAdapter addItemViewDelegate(ItemViewDelegate<T> itemViewDelegate) {
        mItemViewDelegateManager.addDelegate(itemViewDelegate);
        return this;
    }

    private boolean useItemViewDelegateManager() {
        return mItemViewDelegateManager.getItemViewDelegateCount() > 0;
    }

    @Override
    public int getViewTypeCount() {
        if (useItemViewDelegateManager()) {
            return mItemViewDelegateManager.getItemViewDelegateCount();
        }
        return super.getViewTypeCount();
    }

    @Override
    public int getItemViewType(int position) {
        if (useItemViewDelegateManager()) {
            int viewType = mItemViewDelegateManager.getItemViewType(mDatas.get(position), position);
            return viewType;
        }
        return super.getItemViewType(position);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        int viewType = mItemViewDelegateManager.getItemViewType(mDatas.get(position), position);
        ItemViewDelegate itemViewDelegate = mItemViewDelegateManager.getItemViewDelegate(viewType);
        int layoutId = itemViewDelegate.getItemViewLayoutId();
        ViewHolder viewHolder = null;
        if (convertView == null) {
            View itemView = LayoutInflater.from(mContext).inflate(layoutId, parent,
                    false);
            viewHolder = new ViewHolder(mContext, itemView);
            onViewHolderCreated(viewHolder, viewHolder.getConvertView());
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }


        convert(viewHolder, getItem(position), position);
        return viewHolder.getConvertView();
    }

    protected void convert(@NonNull ViewHolder viewHolder, @NonNull T item, int position) {
        mItemViewDelegateManager.convert(viewHolder, item, position);
    }

    public void onViewHolderCreated(ViewHolder holder, View itemView) {
    }

    @Override
    public int getCount() {
        return mDatas.size();
    }

    @Override
    public T getItem(int position) {
        return mDatas.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }


    public List<T> getDatas() {
        return mDatas;
    }

    public void setDatas(List<T> datas) {
        this.mDatas = datas;
    }

    public void addAll(List<T> p_List) {
        mDatas.clear();
        mDatas.addAll(p_List);
        notifyDataSetChanged();
    }

    public void addToList(List<T> p_List) {
        mDatas.addAll(p_List);
        notifyDataSetChanged();
    }

    public void addNotify(T p_Object) {
        mDatas.add(p_Object);
        notifyDataSetChanged();
    }

    public void add(T item) {
        mDatas.add(item);
    }

}
