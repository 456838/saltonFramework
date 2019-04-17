package com.salton123.saltonframeworkdemo.floatingview;

import android.app.Activity;
import android.app.Application;
import android.os.Bundle;
import android.support.v4.view.ViewCompat;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

/**
 * User: newSalton@outlook.com
 * Date: 2019/4/16 16:27
 * ModifyTime: 16:27
 * Description:
 */
public enum FloatingViewManager {
    INSTANCE;
    private View mCurrentView;
    private FrameLayout mFlContainer;
    private Application mApplication;

    public void init(Application application) {
        mApplication = application;
        mApplication.registerActivityLifecycleCallbacks(new Application.ActivityLifecycleCallbacks() {
            @Override
            public void onActivityCreated(Activity activity, Bundle savedInstanceState) {
                attach(activity);
            }

            @Override
            public void onActivityStarted(Activity activity) {

            }

            @Override
            public void onActivityResumed(Activity activity) {

            }

            @Override
            public void onActivityPaused(Activity activity) {
                detach(activity);
            }

            @Override
            public void onActivityStopped(Activity activity) {

            }

            @Override
            public void onActivitySaveInstanceState(Activity activity, Bundle outState) {

            }

            @Override
            public void onActivityDestroyed(Activity activity) {

            }
        });
    }

    public View getCurrentView() {
        return mCurrentView;
    }

    public FloatingViewManager setCurrentView(View currentView) {
        mCurrentView = currentView;
        currentView.setLayoutParams(getDefaultParams());
        mCurrentView.setOnTouchListener(new StackViewTouchListener(mCurrentView, 0));
        return this;
    }

    /**
     * default params
     *
     * @return
     */
    public FrameLayout.LayoutParams getDefaultParams() {
        FrameLayout.LayoutParams params = new FrameLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT);
        params.gravity = Gravity.CENTER_HORIZONTAL;
        return params;
    }

    /**
     * attach to the activity
     *
     * @param activity
     * @return
     */
    public FloatingViewManager attach(Activity activity) {
        attach(getActivityRoot(activity));
        return this;
    }

    /**
     * attach to the activity
     *
     * @param container
     * @return
     */
    public FloatingViewManager attach(FrameLayout container) {
        if (container == null || mCurrentView == null) {
            mFlContainer = container;
            return this;
        }
        if (mCurrentView.getParent() == container) {
            return this;
        }
        // if (mFlContainer != null && mCurrentView.getParent() == mFlContainer) {
        //     mFlContainer.removeView(mCurrentView);
        // }
        mFlContainer = container;
        container.addView(mCurrentView);
        return this;
    }

    /**
     * detach from the activity
     *
     * @param activity
     * @return
     */
    public FloatingViewManager detach(Activity activity) {
        detach(getActivityRoot(activity));
        return this;
    }

    /**
     * detach from the activity
     *
     * @param container
     * @return
     */
    public FloatingViewManager detach(FrameLayout container) {
        if (mCurrentView != null && container != null && ViewCompat.isAttachedToWindow(mCurrentView)) {
            container.removeView(mCurrentView);
        }
        if (mFlContainer == container) {
            mFlContainer = null;
        }
        return this;
    }

    /**
     * get main frame from aty
     *
     * @param activity
     * @return
     */
    private FrameLayout getActivityRoot(Activity activity) {
        if (activity == null) {
            return null;
        }
        try {
            return (FrameLayout) activity.getWindow().getDecorView().findViewById(android.R.id.content);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
