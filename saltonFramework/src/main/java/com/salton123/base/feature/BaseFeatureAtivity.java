package com.salton123.base.feature;

import android.os.Bundle;

import com.salton123.base.BaseSupportActivity;

import java.util.ArrayList;
import java.util.List;

public abstract class BaseFeatureAtivity extends BaseSupportActivity {
    private List<IFeature> mFeatures = new ArrayList<>();

    public void addFeature(IFeature feature) {
        this.mFeatures.add(feature);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        for (IFeature item : mFeatures) {
            item.onBind();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        for (IFeature item : mFeatures) {
            item.onUnBind();
        }
    }
}