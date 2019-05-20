package com.salton123.ui.base;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.salton123.feature.IFeature;

import java.util.ArrayList;
import java.util.List;

import me.yokeyword.fragmentation.SupportFragment;

/**
 * User: newSalton@outlook.com
 * Date: 2019/2/16 19:01
 * ModifyTime: 19:01
 * Description:
 */
public abstract class BaseFragment extends SupportFragment implements IComponentLife {
    private FragmentDelegate mLifeDelegate;
    private List<IFeature> mFeatures = new ArrayList<>();

    public void addFeature(IFeature feature) {
        this.mFeatures.add(feature);
    }

    @Override
    public void onAttach(final Context context) {
        super.onAttach(context);
        mLifeDelegate = new FragmentDelegate(this) {
            @Override
            Activity activity() {
                return (Activity) context;
            }
        };
    }

    @Override
    public void onAttach(final Activity activity) {
        super.onAttach(activity);
        mLifeDelegate = new FragmentDelegate(this) {
            @Override
            Activity activity() {
                return activity;
            }
        };
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        mLifeDelegate.onCreate(savedInstanceState);
        super.onCreate(savedInstanceState);
        for (IFeature item : mFeatures) {
            item.onBind();
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return mLifeDelegate.onCreateView();
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mLifeDelegate.onViewCreated();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        for (IFeature item : mFeatures) {
            item.onUnBind();
        }
    }

    @Override
    public View getRootView() {
        return mLifeDelegate.getRootView();
    }

    @Override
    public View getTitleBar() {
        return null;
    }

    @Override
    public Activity activity() {
        return mLifeDelegate.activity();
    }

    @Override
    public LayoutInflater inflater() {
        return mLifeDelegate.inflater();
    }

    @Override
    public <T extends View> T f(int resId) {
        return mLifeDelegate.f(resId);
    }

    @Override
    public void initListener() {

    }

    @Override
    public void longToast(String toast) {
        mLifeDelegate.longToast(toast);
    }

    @Override
    public void shortToast(String toast) {
        mLifeDelegate.shortToast(toast);
    }

    @Override
    public void log(String msg) {
        mLifeDelegate.log(msg);
    }

    @Override
    public void openActivity(Class<?> clz, Bundle bundle) {
        mLifeDelegate.openActivity(clz, bundle);
    }

    @Override
    public void openActivityForResult(Class<?> clz, Bundle bundle, int requestCode) {
        mLifeDelegate.openActivityForResult(clz, bundle, requestCode);
    }

    public void openActivity(Class<?> clz) {
        mLifeDelegate.openActivity(clz, new Bundle());
    }


    public void openActivityForResult(Class<?> clz, int requestCode) {
        mLifeDelegate.openActivityForResult(clz, new Bundle(), requestCode);
    }

    @Override
    public void setListener(int... ids) {
        mLifeDelegate.setListener(ids);
    }

    @Override
    public void setListener(View... views) {
        mLifeDelegate.setListener(views);
    }

    @Override
    public void show(View... views) {
        mLifeDelegate.show(views);
    }

    @Override
    public void hide(View... views) {
        mLifeDelegate.hide(views);
    }

    @Override
    public void onClick(View v) {

    }

}
