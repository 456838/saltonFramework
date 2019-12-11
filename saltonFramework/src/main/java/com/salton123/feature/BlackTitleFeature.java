package com.salton123.feature;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.view.AsyncLayoutInflater;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;

import com.salton123.saltonframework.R;
import com.salton123.ui.base.IComponentLife;
import com.wuhenzhizao.titlebar.widget.CommonTitleBar;

import me.yokeyword.fragmentation.SupportFragment;

/**
 * User: newSalton@outlook.com
 * Date: 2019/12/11 11:33
 * ModifyTime: 11:33
 * Description:
 */
public class BlackTitleFeature implements IFeature, CommonTitleBar.OnTitleBarListener {
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
                        if (mCommonTitleBar.getLeftTextView() != null && !TextUtils.isEmpty(getLeftText())) {
                            mCommonTitleBar.getLeftTextView().setText(getLeftText());
                        }
                        if (mCommonTitleBar.getCenterTextView() != null && !TextUtils.isEmpty(getTitle())) {
                            mCommonTitleBar.getCenterTextView().setText(getTitle());
                        }
                        if (mCommonTitleBar.getCenterSubTextView() != null && !TextUtils.isEmpty(getSubTitle())) {
                            mCommonTitleBar.getCenterSubTextView().setText(getSubTitle());
                        }
                        if (mCommonTitleBar.getRightTextView() != null && !TextUtils.isEmpty(getRightText())) {
                            mCommonTitleBar.getRightTextView().setText(getRightText());
                        }
                        mIComponentLife.asynTitleBar(view);
                        mCommonTitleBar.setListener(BlackTitleFeature.this);
                    }
                });
    }

    @Override
    public void onUnBind() {

    }

    public void onBackClick() {
        if (mIComponentLife instanceof Activity) {
            mIComponentLife.activity().onBackPressed();
        } else if (mIComponentLife instanceof SupportFragment) {
            ((SupportFragment) mIComponentLife).onBackPressedSupport();
        }
    }

    public String getLeftText() {
        return "";
    }

    public String getRightText() {
        return "";
    }

    public String getTitle() {
        return "";
    }

    public String getSubTitle() {
        return "";
    }


    @Override
    public void onClicked(View v, int action, String extra) {
        if (action == CommonTitleBar.ACTION_LEFT_BUTTON
                || action == CommonTitleBar.ACTION_LEFT_TEXT) {
            onBackClick();
        }
    }
}
