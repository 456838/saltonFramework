package com.salton123.arch.mvp.presenter;

import android.content.Context;

import com.salton123.arch.model.BaseModel;


/**
 * Description: <BasePresenter><br>
 * Author:      mxdl<br>
 * Date:        2018/1/15<br>
 * Version:     V1.0.0<br>
 * Update:     <br>
 */
public abstract class BasePresenter<M extends BaseModel, V> {
    protected Context mContext;
    protected V mView;
    protected M mModel;

    public BasePresenter(Context context) {
        mContext = context;
    }

    public void attach(V view) {
        attachView(view);
        attachModel();
    }

    public void detach() {
        detachView();
        detachModel();
    }

    public void attachView(V view) {
        mView = view;
    }

    public void detachView() {
        mView = null;
    }

    public void attachModel() {
        mModel = initModel();
    }

    public void detachModel() {
        mModel.onCleared();
        mModel = null;
    }

    public abstract M initModel();

}
