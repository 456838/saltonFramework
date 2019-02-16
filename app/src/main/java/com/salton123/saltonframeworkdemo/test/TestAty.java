package com.salton123.saltonframeworkdemo.test;

import android.os.Bundle;

import com.salton123.base.BaseSupportActivity;
import com.salton123.saltonframeworkdemo.R;
import com.salton123.util.EventFactory;

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
        EventFactory.sendEvent(new RxData(1,"小牛之家"));
    }

    @Override
    public void initViewAndData() {

    }

    @Override
    public void initListener() {

    }
}
