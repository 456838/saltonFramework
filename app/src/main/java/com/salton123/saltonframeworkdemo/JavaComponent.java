package com.salton123.saltonframeworkdemo;

import android.os.Bundle;

import com.salton123.base.BaseSupportFragment;

import org.jetbrains.annotations.Nullable;

/**
 * User: newSalton@outlook.com
 * Date: 2018/4/19 18:17
 * ModifyTime: 18:17
 * Description:
 */
public class JavaComponent extends BaseSupportFragment {
    @Override
    public int getLayout() {
        return R.layout.cp_java;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void initVariable(@Nullable Bundle savedInstanceState) {

    }

    @Override
    public void initViewAndData() {
    }
}
