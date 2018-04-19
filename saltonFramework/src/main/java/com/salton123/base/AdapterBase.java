package com.salton123.base;

import android.content.Context;
import android.view.LayoutInflater;
import android.widget.BaseAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * User: 巫金生(newSalton@outlook.com)
 * Date: 2016/1/19 10:11
 * Time: 10:11
 * Description:
 * Update:增加泛型支持
 */

public abstract class AdapterBase<T> extends BaseAdapter {

    private List<T> mList;
    private Context mContext;
    private LayoutInflater mLayoutInflater;

    public AdapterBase(Context pContext) {
        mContext = pContext;
        mList = new ArrayList();
        mLayoutInflater = LayoutInflater.from(mContext);
    }

    public LayoutInflater getLayoutInflater() {
        return mLayoutInflater;
    }

    public Context getContext() {
        return mContext;
    }

    public List<T> getList() {
        return mList;
    }

    public void addAll(List<T> p_List) {
        getList().clear();
        getList().addAll(p_List);
        notifyDataSetChanged();
    }

    public void addToList(List<T> p_List) {
        getList().addAll(p_List);
        notifyDataSetChanged();
    }

    public void addNotify(T p_Object) {
        getList().add(p_Object);
        notifyDataSetChanged();
    }

    public void add(T p_Object) {
        getList().add(p_Object);
    }

    @Override
    public int getCount() {
        return mList.size();
    }

    @Override
    public T getItem(int pPosition) {
        return mList.get(pPosition);
    }

    @Override
    public long getItemId(int pPosition) {
        return pPosition;
    }

}
