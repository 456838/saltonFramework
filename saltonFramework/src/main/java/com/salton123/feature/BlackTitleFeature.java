package com.salton123.feature;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.view.AsyncLayoutInflater;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;

import com.salton123.saltonframework.R;
import com.salton123.ui.base.IComponentLife;
import com.salton123.arch.view.ITitleView;
import com.wuhenzhizao.titlebar.widget.CommonTitleBar;

/**
 * User: newSalton@outlook.com
 * Date: 2019/12/11 11:33
 * ModifyTime: 11:33
 * Description:
 */
public class BlackTitleFeature implements IFeature, ITitleView {
    public IComponentLife mIComponentLife;
    private CommonTitleBar mCommonTitleBar;

    public BlackTitleFeature(IComponentLife componentLife) {
        this.mIComponentLife = componentLife;
    }

    @Override
    public void onBind() {
        new AsyncLayoutInflater(mIComponentLife.activity()).inflate(
                R.layout.salton_commond_title_bar_style_black,
                null,
                new AsyncLayoutInflater.OnInflateFinishedListener() {
                    @Override
                    public void onInflateFinished(@NonNull View view, int i, @Nullable ViewGroup viewGroup) {
                        mCommonTitleBar = (CommonTitleBar) view;
                        mIComponentLife.asynTitleBar(view);
                        mCommonTitleBar.setListener(BlackTitleFeature.this);
                    }
                });
    }

    @Override
    public void onUnBind() {

    }

    @Override
    public void setLeftText(String leftText) {
        if (mCommonTitleBar.getLeftTextView() != null && !TextUtils.isEmpty(leftText)) {
            mCommonTitleBar.getLeftTextView().setText(leftText);
        }

    }

    @Override
    public void setRightText(String rightText) {
        if (mCommonTitleBar.getRightTextView() != null && !TextUtils.isEmpty(rightText)) {
            mCommonTitleBar.getRightTextView().setText(rightText);
        }
    }

    @Override
    public void setTitleText(String titleText) {
        if (mCommonTitleBar.getCenterTextView() != null && !TextUtils.isEmpty(titleText)) {
            mCommonTitleBar.getCenterTextView().setText(titleText);
        }
    }

    @Override
    public void setSubTitleText(String subTitleText) {
        if (mCommonTitleBar.getCenterSubTextView() != null && !TextUtils.isEmpty(subTitleText)) {
            mCommonTitleBar.getCenterSubTextView().setText(subTitleText);
        }
    }

    @Override
    public void onClicked(View v, int action, String extra) {

    }
}
