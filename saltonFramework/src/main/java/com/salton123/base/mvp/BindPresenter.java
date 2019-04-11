package com.salton123.base.mvp;


public class BindPresenter<T extends IBaseView> implements IBasePresenter<T> {

    protected T mView;

    @Override
    public void attachView(T view) {
        this.mView = view;
    }

    @Override
    public void detachView() {
        this.mView = null;
    }


}
