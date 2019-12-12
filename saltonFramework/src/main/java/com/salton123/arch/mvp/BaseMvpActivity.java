package com.salton123.arch.mvp;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.salton123.arch.mvp.presenter.BasePresenter;
import com.salton123.arch.model.BaseModel;
import com.salton123.ui.base.BaseActivity;

/**
 * Description: <BaseMvpActivity><br>
 * Author:      mxdl<br>
 * Date:        2018/1/16<br>
 * Version:     V1.0.0<br>
 * Update:     <br>
 */
public abstract class BaseMvpActivity<M extends BaseModel, V, P extends BasePresenter<M, V>> extends BaseActivity {
    protected P mPresenter;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        mPresenter = initPresenter();
        if (mPresenter != null) {
            mPresenter.attach((V) this);
        }
        super.onCreate(savedInstanceState);

    }

    public abstract P initPresenter();

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (mPresenter != null) {
            mPresenter.detach();
        }
    }
}
