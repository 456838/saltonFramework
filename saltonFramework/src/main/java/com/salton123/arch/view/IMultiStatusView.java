package com.salton123.arch.view;

/**
 * User: 巫金生(newSalton@outlook.com)
 * Date: 2017/7/12 15:22
 * Time: 15:22
 * Description:
 */
public interface IMultiStatusView {
    void showInitLoadView(boolean show);
    void showNoDataView(boolean show);
    void showTransLoadingView(boolean show);
    void showNetWorkErrView(boolean show);
}
