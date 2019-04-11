package com.salton123.base.mvp.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.salton123.base.BaseDialogFragment;
import com.salton123.base.mvp.IBasePresenter;
import com.salton123.base.mvp.IBaseView;


/**
 * User: 巫金生(newSalton@outlook.com)
 * Date: 2017/7/1215:22
 * Time: 15:22
 * Description:
 */
public abstract class BaseMvpDialogFragment<T extends IBasePresenter> extends BaseDialogFragment implements IBaseView {

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
