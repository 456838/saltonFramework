package com.yy.mobile.memoryrecycle.views;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;

import com.yy.mobile.memoryrecycle.drawablerecycle.DrawableRecycler;

/**
 * Created by lulong on 2017/5/9.
 * Email:lulong@yy.com
 */

public class YYRelativeLayout extends RelativeLayout implements IRecycleView {

    private boolean mIsAttachToWindow;

    public YYRelativeLayout(Context context) {
        super(context);
    }

    public YYRelativeLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        DrawableRecycler.onAttributesUpdated(context, this, attrs);
    }

    public YYRelativeLayout(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        DrawableRecycler.onAttributesUpdated(context, this, attrs);
    }

    @Override
    public void setVisibility(int visibility) {
        super.setVisibility(visibility);
        DrawableRecycler.onVisibilityChanged(this, visibility);
    }

    @Override
    protected void onAttachedToWindow() {
        mIsAttachToWindow = true;
        super.onAttachedToWindow();
        DrawableRecycler.onAttachedToWindow(this);
    }

    @Override
    protected void onDetachedFromWindow() {
        mIsAttachToWindow = false;
        super.onDetachedFromWindow();
        DrawableRecycler.onDetachedFromWindow(this);
    }

    @Override
    public void setBackgroundDrawable(Drawable background) {
        super.setBackgroundDrawable(background);
        DrawableRecycler.onBackgroundUpdated(this, background);
    }

    @Override
    public void setBackgroundResource(int resid) {
        super.setBackgroundResource(resid);
        DrawableRecycler.onBackgroundUpdated(this, resid);
    }


    @Override
    public Drawable getBackground() {
        DrawableRecycler.onGetBackground(this);
        Drawable drawable = super.getBackground();
        DrawableRecycler.onGetBackgroundEnd(this);
        return drawable;
    }


    @Override
    public void setBackgroundToNull() {
        super.setBackgroundDrawable(null);
    }

    @Override
    public Drawable getBackgroundInner() {
        return super.getBackground();
    }

    @Override
    public boolean isAttachToWindow() {
        return mIsAttachToWindow;
    }

    @Override
    public boolean closeAutoRecycleDrawables() {
        return false;
    }
}
