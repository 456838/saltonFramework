package com.salton123.util;

import android.support.annotation.IdRes;
import android.view.View;

public class ViewUtils {

    private ViewUtils() {
        throw new AssertionError();
    }


    /**
     * 查找View
     *
     * @param itemView 控件父类
     * @param resId    控件id
     * @return
     */
    public static <T extends View> T f(View itemView, @IdRes int resId) {
        return (T) itemView.findViewById(resId);
    }
}
