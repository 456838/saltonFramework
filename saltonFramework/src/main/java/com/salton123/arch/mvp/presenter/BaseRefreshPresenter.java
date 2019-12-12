package com.salton123.arch.mvp.presenter;

import android.content.Context;

import com.salton123.arch.model.BaseModel;
import com.salton123.arch.mvp.view.BaseRefreshView;

/**
 * Description: <BaseRefreshPresenter><br>
 * Author:      mxdl<br>
 * Date:        2018/2/26<br>
 * Version:     V1.0.0<br>
 * Update:     <br>
 */
public abstract class BaseRefreshPresenter<M extends BaseModel,
        V extends BaseRefreshView<T>,
        T> extends BasePresenter<M,V> implements BaseRefreshContract.Presenter{
    public BaseRefreshPresenter(Context context) {
        super(context);
    }
}
