package com.yy.mobile.memoryrecycle.views;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.widget.ImageView;

import com.yy.mobile.memoryrecycle.drawablerecycle.DrawableRecycler;

/**
 * Created by lulong on 2017/5/9.
 * Email:lulong@yy.com
 */

public class YYImageView extends ImageView implements IImageRecycleView {

    private boolean mIsAttachToWindow;

    public YYImageView(Context context) {
        super(context);
    }

    public YYImageView(Context context, AttributeSet attrs) {
        super(context, attrs);
        DrawableRecycler.onAttributesUpdated(context, this, attrs);
    }

    public YYImageView(Context context, AttributeSet attrs, int defStyle) {
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
    public void setImageDrawable(Drawable drawable) {
        super.setImageDrawable(drawable);
        DrawableRecycler.onImageUpdated(this, drawable);
    }

    @Override
    public void setImageResource(int resid) {
        super.setImageResource(resid);
        DrawableRecycler.onImageUpdated(this, resid);
    }

    @Override
    public Drawable getDrawable() {
        DrawableRecycler.onGetDrawable(this);
        Drawable drawable = super.getDrawable();
        DrawableRecycler.onGetDrawableEnd(this);
        return drawable;
    }

    @Override
    public Drawable getBackground() {
        DrawableRecycler.onGetBackground(this);
        Drawable drawable = super.getBackground();
        DrawableRecycler.onGetBackgroundEnd(this);
        return drawable;
    }

    @Override
    public void setImageDrawableToNull() {
        super.setImageDrawable(null);
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

    @Override
    public Drawable getImageDrawableInner() {
        return super.getDrawable();
    }
}
