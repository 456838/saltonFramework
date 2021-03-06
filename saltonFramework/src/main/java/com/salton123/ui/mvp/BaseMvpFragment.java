package com.salton123.ui.mvp;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.salton123.ui.base.BaseFragment;

/**
 * User: 巫金生(newSalton@outlook.com)
 * Date: 2017/7/12 15:22
 * Time: 15:22
 * Description:
 */
public abstract class BaseMvpFragment<T extends BasePresenter> extends BaseFragment implements BaseView {

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
