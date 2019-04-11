package com.salton123.base.mvp.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.salton123.base.BaseActivity;
import com.salton123.base.mvp.IBasePresenter;
import com.salton123.base.mvp.IBaseView;


/**
 * User: newSalton@outlook.com
 * Date: 2017/9/8 21:44
 * ModifyTime: 21:44
 * Description:
 */
public abstract class BaseMvpActivity<T extends IBasePresenter> extends BaseActivity implements IBaseView {
    protected T mPresenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mPresenter.attachView(this);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (mPresenter != null) {
            mPresenter.detachView();
        }
    }
}
