package com.salton123.base.mvp.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.salton123.base.BaseSupportPopupFragment;
import com.salton123.base.mvp.presenter.BasePresenter;
import com.salton123.base.mvp.view.BaseView;


/**
 * User: 巫金生(newSalton@outlook.com)
 * Date: 2017/7/12 15:22
 * Time: 15:22
 * Description:
 */
public abstract class BaseSupportPresenterPopupFragment<T extends BasePresenter> extends BaseSupportPopupFragment implements BaseView {

    protected T mPresenter;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
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
