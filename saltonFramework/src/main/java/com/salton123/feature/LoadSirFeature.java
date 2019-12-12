package com.salton123.feature;

import android.view.View;

import com.kingja.loadsir.callback.Callback;
import com.kingja.loadsir.core.LoadService;
import com.kingja.loadsir.core.LoadSir;
import com.salton123.saltonframework.R;
import com.salton123.ui.base.IComponentLife;
import com.salton123.util.FP;

import java.util.List;

/**
 * User: newSalton@outlook.com
 * Date: 2019/12/11 17:18
 * ModifyTime: 17:18
 * Description:
 */
public class LoadSirFeature implements IFeature, Callback.OnReloadListener {
    public IComponentLife mIComponentLife;
    //状态页管理
    public LoadService mBaseLoadService;

    public LoadSirFeature(IComponentLife componentLife) {
        this.mIComponentLife = componentLife;
    }

    @Override
    public void onBind() {
        LoadSir.Builder builder = new LoadSir.Builder()
                .addCallback(getInitStatus())
                .addCallback(getEmptyStatus())
                .addCallback(getErrorStatus())
                .addCallback(getLoadingStatus())
                .setDefaultCallback(getInitStatus().getClass());
        if (!FP.empty(getExtraStatus())) {
            for (Callback callback : getExtraStatus()) {
                builder.addCallback(callback);
            }
        }
        mBaseLoadService = builder.build().register(mIComponentLife.getRootView()
                .findViewById(R.id.salton_id_content_layout), this);
        mBaseLoadService.showCallback(getInitStatus().getClass());
    }

    Callback getInitStatus() {
        return new BlankStatus();
    }

    Callback getLoadingStatus() {
        return new BlankStatus();
    }

    Callback getErrorStatus() {
        return new BlankStatus();
    }

    Callback getEmptyStatus() {
        return new BlankStatus();
    }

    @Override
    public void onUnBind() {

    }

    List<Callback> getExtraStatus() {
        return null;
    }

    @Override
    public void onReload(View v) {
        mBaseLoadService.showSuccess();
    }
}
