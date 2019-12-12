package com.salton123.ui.base;

import android.os.Bundle;
import android.view.View;

import com.salton123.feature.BlackTitleFeature;
import com.salton123.feature.MultiStatusFeature;
import com.salton123.arch.view.IMultiStatusView;
import com.salton123.arch.view.ITitleView;
import com.wuhenzhizao.titlebar.widget.CommonTitleBar;

/**
 * User: newSalton@outlook.com
 * Date: 2019/12/12 15:59
 * ModifyTime: 15:59
 * Description:
 */
public abstract class BaseDialogFragment extends LifeDelegateDialogFragment implements IMultiStatusView, ITitleView {
    private BlackTitleFeature mBlackTitleFeature;
    private MultiStatusFeature mMultiStatusFeature;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBlackTitleFeature = getTitleFeature();
        mMultiStatusFeature = getMultiStatusFeature();
        addFeature(mBlackTitleFeature);
        addFeature(mMultiStatusFeature);
    }

    public MultiStatusFeature getMultiStatusFeature() {
        return new MultiStatusFeature(this);
    }

    public BlackTitleFeature getTitleFeature() {
        return new BlackTitleFeature(this) {
            @Override
            public void onClicked(View v, int action, String extra) {
                super.onClicked(v, action, extra);
                BaseDialogFragment.this.onClicked(v, action, extra);
            }
        };
    }

    @Override
    public void showInitLoadView(boolean show) {
        mMultiStatusFeature.showInitLoadView(show);
    }

    @Override
    public void showNoDataView(boolean show) {
        mMultiStatusFeature.showNoDataView(show);
    }

    @Override
    public void showTransLoadingView(boolean show) {
        mMultiStatusFeature.showTransLoadingView(show);
    }

    @Override
    public void showNetWorkErrView(boolean show) {
        mMultiStatusFeature.showNetWorkErrView(show);
    }

    @Override
    public void setLeftText(String leftText) {
        mBlackTitleFeature.setLeftText(leftText);
    }

    @Override
    public void setRightText(String rightText) {
        mBlackTitleFeature.setRightText(rightText);
    }

    @Override
    public void setTitleText(String titleText) {
        mBlackTitleFeature.setTitleText(titleText);
    }

    @Override
    public void setSubTitleText(String subTitleText) {
        mBlackTitleFeature.setSubTitleText(subTitleText);
    }

    @Override
    public void onClicked(View v, int action, String extra) {
        if (action == CommonTitleBar.ACTION_LEFT_BUTTON
                || action == CommonTitleBar.ACTION_LEFT_TEXT) {
            onBackClick();
        }
    }

    public void onBackClick() {
        dismissAllowingStateLoss();
    }
}
