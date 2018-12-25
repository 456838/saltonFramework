package com.salton123.util;

import android.support.annotation.IdRes;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.GridView;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.RelativeLayout.LayoutParams;
import android.widget.TextView;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

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
