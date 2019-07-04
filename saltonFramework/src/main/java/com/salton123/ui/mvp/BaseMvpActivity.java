package com.salton123.ui.mvp;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.salton123.ui.base.BaseActivity;


/**
 * User: newSalton@outlook.com
 * Date: 2017/9/8 21:44
 * ModifyTime: 21:44
 * Description:
 */
public abstract class BaseMvpActivity<T extends BasePresenter>
        extends BaseActivity implements BaseView {
    public T mPresenter;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        mPresenter = getPresenter();
        if (mPresenter != null) {
            mPresenter.attachView(this);
        }
        super.onCreate(savedInstanceState);
    }

    public abstract T getPresenter();

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (mPresenter != null) {
            mPresenter.detachView();
        }
    }
}
