package com.salton123.view.adapter.base;


import android.support.annotation.NonNull;

public interface ItemViewDelegate<T>
{

    int getItemViewLayoutId();

    boolean isForViewType(@NonNull T item, int position);

    void convert(@NonNull ViewHolder holder,@NonNull T t, int position);

}
