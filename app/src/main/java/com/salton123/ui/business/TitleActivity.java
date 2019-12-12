package com.salton123.ui.business;

import android.os.Bundle;
import android.view.ViewStub;

import com.salton123.ui.base.LifeDelegateActivity;

/**
 * User: newSalton@outlook.com
 * Date: 2019/5/20 11:38
 * ModifyTime: 11:38
 * Description:
 */
public abstract class TitleActivity extends LifeDelegateActivity {
    ViewStub stub;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (stub != null) {
            stub.inflate();
        }
    }
}
