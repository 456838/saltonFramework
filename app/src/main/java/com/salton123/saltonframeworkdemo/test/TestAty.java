package com.salton123.saltonframeworkdemo.test;

import android.os.Bundle;

import com.hwangjr.rxbus.RxBus;
import com.salton123.base.BaseSupportActivity;
import com.salton123.saltonframeworkdemo.R;

/**
 * User: newSalton@outlook.com
 * Date: 2017/8/10 19:27
 * ModifyTime: 19:27
 * Description:
 */
public class TestAty extends BaseSupportActivity{
    @Override
    public int getLayout() {
        return R.layout.aty_test;
    }

    @Override
    public void initVariable(Bundle savedInstanceState) {
        RxBus.get().post(new RxData(1,"小牛之家"));
    }

    @Override
    public void initViewAndData() {

    }

    @Override
    public void initListener() {

    }
}
