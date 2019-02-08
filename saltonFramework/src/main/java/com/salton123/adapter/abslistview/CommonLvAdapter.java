package com.salton123.adapter.abslistview;

import android.content.Context;
import android.support.annotation.NonNull;

import com.salton123.adapter.base.ItemViewDelegate;
import com.salton123.adapter.base.ViewHolder;

public abstract class CommonLvAdapter<T> extends MultiLvAdapter<T> {

    public CommonLvAdapter(Context context, final int layoutId) {
        super(context);

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
            public void convert(@NonNull ViewHolder holder,@NonNull T t, int position) {
                CommonLvAdapter.this.convert(holder, t, position);
            }
        });
    }

    protected abstract void convert(ViewHolder viewHolder, T item, int position);

}
