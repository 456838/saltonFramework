package com.salton123.base.mvp;

/**
 * User: 巫金生(newSalton@outlook.com)
 * Date: 2017/7/12 11:06
 * Time: 11:06
 * Description:
 */
public interface IBasePresenter<T extends IBaseView>{

    void attachView(T view);

    void detachView();
}
