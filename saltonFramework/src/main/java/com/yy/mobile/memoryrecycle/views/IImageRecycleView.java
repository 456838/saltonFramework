package com.yy.mobile.memoryrecycle.views;

import android.graphics.drawable.Drawable;

/**
 * Created by lulong on 2017/5/9.
 * Email:lulong@yy.com
 */

public interface IImageRecycleView extends IRecycleView {
    void setImageDrawableToNull();
    Drawable getImageDrawableInner();
}
