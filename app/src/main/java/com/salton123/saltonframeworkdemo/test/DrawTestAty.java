package com.salton123.saltonframeworkdemo.test;

import android.os.Bundle;
import android.util.Log;

import com.salton123.base.BaseSupportActivity;
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
public class DrawTestAty extends BaseSupportActivity {
    @Override
    public int getLayout() {
        return R.layout.draw_test;
    }

    @Override
    public void initVariable(@Nullable Bundle savedInstanceState) {

    }

    @Override
    public void initViewAndData() {
        Log.e("aa", "hello initViewAndData");
        FragmentDelegate.Companion.newInstance(TestPopupComp.class)
                .show(getSupportFragmentManager()
                        , "TestPopupComp");
    }
}
