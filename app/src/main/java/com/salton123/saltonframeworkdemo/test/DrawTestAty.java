package com.salton123.saltonframeworkdemo.test;

import android.os.Bundle;
import android.util.Log;

import com.gyf.barlibrary.BarHide;
import com.gyf.barlibrary.ImmersionBar;
import com.salton123.base.ActivityBase;
import com.salton123.base.FragmentDelegate;
import com.salton123.saltonframeworkdemo.R;
import com.salton123.saltonframeworkdemo.ui.fm.TestPopupComp;

import org.jetbrains.annotations.Nullable;

/**
 * User: newSalton@outlook.com
 * Date: 2018/5/16 下午6:24
 * ModifyTime: 下午6:24
 * Description:
 */
public class DrawTestAty extends ActivityBase {
    ImmersionBar mImmersionBar;

    @Override
    public int getLayout() {
        return R.layout.draw_test;
    }

    @Override
    public void initVariable(@Nullable Bundle savedInstanceState) {
        mImmersionBar = ImmersionBar.with(this)
                .statusBarDarkFont(true)
                .hideBar(BarHide.FLAG_HIDE_NAVIGATION_BAR)
                .transparentBar().transparentNavigationBar();
        mImmersionBar.init();
    }

    @Override
    public void initViewAndData() {
        Log.e("aa", "hello initViewAndData");
        FragmentDelegate.Companion.newInstance(TestPopupComp.class)
                .show(getSupportFragmentManager()
                        , "TestPopupComp");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mImmersionBar != null) {
            mImmersionBar.destroy();
        }
    }
}
