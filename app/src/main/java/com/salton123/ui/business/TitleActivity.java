package com.salton123.ui.business;

import android.os.Bundle;
import android.view.View;
import android.view.ViewStub;

import com.hjq.bar.TitleBar;
import com.salton123.saltonframeworkdemo.R;
import com.salton123.ui.base.BaseActivity;

/**
 * User: newSalton@outlook.com
 * Date: 2019/5/20 11:38
 * ModifyTime: 11:38
 * Description:
 */
public abstract class TitleActivity extends BaseActivity {
    ViewStub stub;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (stub != null) {
            stub.inflate();
        }
    }
}
