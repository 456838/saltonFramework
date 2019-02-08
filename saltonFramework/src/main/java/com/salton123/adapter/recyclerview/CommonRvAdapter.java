package com.salton123.adapter.recyclerview;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;

import com.salton123.adapter.base.ItemViewDelegate;
import com.salton123.adapter.base.ViewHolder;

public abstract class CommonRvAdapter<T> extends MultiRvAdapter<T> {
    protected Context mContext;
    protected int mLayoutId;
    protected LayoutInflater mInflater;

    public CommonRvAdapter(final Context context, final int layoutId) {
        super(context);
        mContext = context;
        mInflater = LayoutInflater.from(context);
        mLayoutId = layoutId;

        addItemViewDelegate(new ItemViewDelegate<T>() {
            @Override
            public int getItemViewLayoutId() {
                return layoutId;
            }

            @Override
            public boolean isForViewType(T item, int position) {
                return true;
            }

            @Override
            public void convert(ViewHolder holder, T t, int position) {
                CommonRvAdapter.this.convert(holder, t, position);
            }
        });
    }

    protected abstract void convert(@NonNull ViewHolder holder, @NonNull T entity, int position);


}
