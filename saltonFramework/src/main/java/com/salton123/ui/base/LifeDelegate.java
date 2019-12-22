package com.salton123.ui.base;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.AsyncLayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.salton123.log.XLog;
import com.salton123.saltonframework.R;

/**
 * User: newSalton@outlook.com
 * Date: 2019/12/11 9:29
 * ModifyTime: 9:29
 * Description:
 */
public class LifeDelegate implements IComponentLife {
    private IComponentLife mComponentLife;
    private Activity mHostActivity;
    private LinearLayout rootView;
    private LinearLayout titleLayout;

    public LifeDelegate(IComponentLife componentLife) {
        this.mComponentLife = componentLife;
    }

    @Override
    public int getLayout() {
        return mComponentLife.getLayout();
    }

    @Override
    public View getRootView() {
        if (rootView == null) {
            rootView = new LinearLayout(activity());
            rootView.setId(R.id.salton_id_top_layout);
            rootView.setOrientation(LinearLayout.VERTICAL);
            rootView.setLayoutParams(new FrameLayout.LayoutParams(
                    FrameLayout.LayoutParams.MATCH_PARENT,
                    FrameLayout.LayoutParams.MATCH_PARENT));
            FrameLayout contentLayout = new FrameLayout(activity());
            contentLayout.setId(R.id.salton_id_content_layout);
            contentLayout.setLayoutParams(new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.MATCH_PARENT,
                    LinearLayout.LayoutParams.MATCH_PARENT));
            rootView.addView(contentLayout);
            new AsyncLayoutInflater(activity()).inflate(
                    mComponentLife.getLayout(),
                    contentLayout,
                    new AsyncLayoutInflater.OnInflateFinishedListener() {
                        @Override
                        public void onInflateFinished(
                                @NonNull View view,
                                int i,
                                @Nullable ViewGroup viewGroup) {
                            contentLayout.addView(view);
                            mComponentLife.initViewAndData();
                            mComponentLife.initListener();
                        }
                    });
        }
        return rootView;
    }

    @Override
    public void asynTitleBar(View titleBarView) {
        if (titleBarView == null) {
            if (titleLayout != null) {  //title布局已经初始化过，清空标题布局
                titleLayout.removeAllViews();
            }
        } else {
            if (titleLayout == null) {
                titleLayout = new LinearLayout(activity());
                titleLayout.setId(R.id.salton_id_title_layout);
                titleLayout.setOrientation(LinearLayout.VERTICAL);
                rootView.addView(titleLayout, 0);
                titleLayout.addView(titleBarView);
                titleLayout.setLayoutParams(new LinearLayout.LayoutParams(
                        LinearLayout.LayoutParams.MATCH_PARENT,
                        LinearLayout.LayoutParams.WRAP_CONTENT));
            } else {
                titleLayout.removeAllViews();
                titleLayout.addView(titleBarView);
            }
        }
    }
    
    @Override
    public Activity activity() {
        if (mComponentLife instanceof Fragment) {
            mHostActivity = ((Fragment) mComponentLife).getActivity();
        } else if (mComponentLife instanceof Activity) {
            mHostActivity = (Activity) mComponentLife;
        } else {
            throw new RuntimeException("instance must Fragment or Activity");
        }
        return mHostActivity;
    }

    @Override
    public <T extends View> T f(int resId) {
        return rootView.findViewById(resId);
    }

    @Override
    public void initVariable(@Nullable Bundle savedInstanceState) {
        mComponentLife.initVariable(savedInstanceState);
    }

    @Override
    public void initViewAndData() {
        mComponentLife.initViewAndData();
    }

    @Override
    public void initListener() {
        mComponentLife.initListener();
    }

    @Override
    public void longToast(String toast) {
        if (activity() != null) {
            Toast.makeText(activity(), toast, Toast.LENGTH_LONG).show();
        }
    }

    @Override
    public void shortToast(String toast) {
        if (activity() != null) {
            Toast.makeText(activity(), toast, Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void log(String msg) {
        XLog.i(getClass(), msg);
    }

    @Override
    public void openActivity(Class<?> clz, Bundle bundle) {
        if (activity() != null) {
            Intent intent = new Intent(activity(), clz);
            if (bundle != null) {
                intent.putExtras(bundle);
            }
            activity().startActivity(intent);
        }
    }

    @Override
    public void openActivityForResult(Class<?> clz, Bundle bundle, int requestCode) {
        if (activity() != null) {
            Intent intent = new Intent(activity(), clz);
            if (bundle != null) {
                intent.putExtras(bundle);
            }
            activity().startActivityForResult(intent, requestCode);
        }
    }

    @Override
    public void setListener(int... ids) {
        for (int id : ids) {
            f(id).setOnClickListener(this);
        }
    }

    @Override
    public void setListener(View... views) {
        for (View view : views) {
            view.setOnClickListener(this);
        }
    }

    @Override
    public void show(View... views) {
        for (View view : views) {
            view.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void hide(View... views) {
        for (View view : views) {
            view.setVisibility(View.GONE);
        }
    }


    @Override
    public void onClick(View v) {
        mComponentLife.onClick(v);
    }
}
