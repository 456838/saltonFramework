package com.salton123.saltonframeworkdemo.floatingview;

import android.support.v4.app.FragmentActivity;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;

import com.salton123.saltonframeworkdemo.R;

/**
 * User: newSalton@outlook.com
 * Date: 2019/4/16 20:27
 * ModifyTime: 20:27
 * Description:
 */
public class FloatingDelegate {
    private FragmentActivity mActivity;

    public FloatingDelegate(FragmentActivity activity) {
        this.mActivity = activity;
    }

    public void onCreate() {
        View root = mActivity.findViewById(android.R.id.content);
        if (root instanceof FrameLayout) {
            FrameLayout content = (FrameLayout) root;
            final ImageView stackView = new ImageView(mActivity);
            stackView.setImageResource(R.drawable.fragmentation_ic_stack);
            FrameLayout.LayoutParams params = new FrameLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            params.gravity = Gravity.END;
            final int dp18 = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 18, mActivity.getResources().getDisplayMetrics());
            params.topMargin = dp18 * 7;
            params.rightMargin = dp18;
            stackView.setLayoutParams(params);
            content.addView(stackView);
            stackView.setOnTouchListener(new StackViewTouchListener(stackView, dp18 / 4));
            stackView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                }
            });
        }
    }

}
