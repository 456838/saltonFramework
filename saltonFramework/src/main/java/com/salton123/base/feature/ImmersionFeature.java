package com.salton123.base.feature;

import android.support.v7.app.AppCompatActivity;

import com.gyf.barlibrary.ImmersionBar;

/**
 * User: newSalton@outlook.com
 * Date: 2018/12/25 4:51 PM
 * ModifyTime: 4:51 PM
 * Description:
 */
public class ImmersionFeature implements IFeature {
    private ImmersionBar mImmersionBar;
    private AppCompatActivity mActivity;

    public ImmersionFeature(AppCompatActivity activity) {
        this.mActivity = activity;
    }

    @Override
    public void onBind() {
        mImmersionBar = getImmersionBar();
        mImmersionBar.init();
    }

    public ImmersionBar getImmersionBar() {
        return ImmersionBar.with(mActivity)
                .statusBarDarkFont(true)
                .transparentBar()
                .transparentNavigationBar();
    }

    @Override
    public void onUnBind() {
        if (mImmersionBar != null) {
            mImmersionBar.destroy();
        }
    }

}
