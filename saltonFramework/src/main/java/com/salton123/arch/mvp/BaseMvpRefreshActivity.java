package com.salton123.arch.mvp;


import android.os.Bundle;
import android.support.annotation.Nullable;

import com.salton123.arch.model.BaseModel;
import com.salton123.arch.mvp.presenter.BaseRefreshPresenter;
import com.salton123.arch.mvp.view.BaseRefreshView;


public abstract class BaseMvpRefreshActivity<M extends BaseModel,
        V extends BaseRefreshView<T>,
        P extends BaseRefreshPresenter<M, V, T>,
        T> extends BaseMvpActivity<M, V, P> implements BaseRefreshView<T> {

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }
}
