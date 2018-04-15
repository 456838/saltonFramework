package com.yy.mobile.ui.widget.button;

import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.util.AttributeSet;
import android.view.View;

import com.salton123.saltonframework.R;


/**
 * User: newSalton@outlook.com
 * Date: 2017/10/27 19:21
 * ModifyTime: 19:21
 * Description:
 */
public class TintButtonHelper {
    private View mView;
    private ColorStateList mImgTintList = null;
    private ColorStateList mBgTintList = null;
    private PorterDuff.Mode mImgTintMode = PorterDuff.Mode.MULTIPLY;
    private PorterDuff.Mode mBgTintMode = PorterDuff.Mode.MULTIPLY;
    private static float DEFAULT_IMG_PRESSED_ALPHA = 0.6f; // 默认0.6，UED建议值
    private float mImgPressedAlpha = DEFAULT_IMG_PRESSED_ALPHA;

    TintButtonHelper(View view) {
        mView = view;
    }

    void loadFromAttributes(AttributeSet attrs, int defStyleAttr) {
        if (attrs != null) {
            TypedArray a = mView.getContext().obtainStyledAttributes(attrs, R.styleable.salton_TintButton, defStyleAttr, 0);
            mImgTintList = a.getColorStateList(R.styleable.salton_TintButton_salton_imgTint);
            mBgTintList = a.getColorStateList(R.styleable.salton_TintButton_salton_bgTint);
            mImgPressedAlpha = a.getFloat(R.styleable.salton_TintButton_salton_imgPressedAlpha, DEFAULT_IMG_PRESSED_ALPHA);
            a.recycle();
        }
    }

    void setImgTintList(ColorStateList tint) {
        this.mImgTintList = tint;
    }

    ColorStateList getImgTintList() {
        return mImgTintList;
    }

    void setImgTintMode(PorterDuff.Mode mode) {
        this.mImgTintMode = mode;
    }

    PorterDuff.Mode getImgTintMode() {
        return mImgTintMode;
    }

    void setBgTintList(ColorStateList tint) {
        this.mBgTintList = tint;
    }

    ColorStateList getBgTintList() {
        return mBgTintList;
    }

    void setBgTintMode(PorterDuff.Mode mode) {
        this.mBgTintMode = mode;
    }

    PorterDuff.Mode getBgTintMode() {
        return mBgTintMode;
    }

    float getImgPressedAlpha() {
        return mImgPressedAlpha;
    }

    void setImgPressedAlpha(float imgPressedAlpha) {
        this.mImgPressedAlpha = imgPressedAlpha;
    }

    void applyImageDrawable(Drawable drawable) {
        if (drawable != null && !mView.isInEditMode()) {
            drawable = drawable.mutate();
            applyTint(drawable, mView.getDrawableState(), mImgTintList, mImgTintMode);
            applyImagePressedAlpha(drawable, mView.getDrawableState(), mImgPressedAlpha);
        }
    }

    void applyImageDrawable(Drawable[] drawables) {
        if (drawables != null && !mView.isInEditMode()) {
            for (Drawable drawable : drawables) {
                if (drawable != null) {
                    drawable = drawable.mutate();
                    applyTint(drawable, mView.getDrawableState(), mImgTintList, mImgTintMode);
                    applyImagePressedAlpha(drawable, mView.getDrawableState(), mImgPressedAlpha);
                }
            }
        }
    }

    void applyBackgroundDrawable(Drawable drawable) {
        if (drawable != null && !mView.isInEditMode()) {
            drawable = drawable.mutate();
            applyTint(drawable, mView.getDrawableState(), mBgTintList, mBgTintMode);
        }
    }

    private static void applyTint(Drawable drawable, int[] drawableState, ColorStateList tintList, PorterDuff.Mode tintMode) {
        if (drawable == null || drawableState == null) {
            return;
        }

        if (tintList == null) {
            drawable.clearColorFilter();
        } else {
            int DEFAULT_TINT_COLOR = Color.WHITE;
            int color = tintList.getColorForState(drawableState, DEFAULT_TINT_COLOR);
            drawable.setColorFilter(color, tintMode);
        }

        if (Build.VERSION.SDK_INT <= 23) {
            drawable.invalidateSelf();
        }
    }

    private static void applyImagePressedAlpha(Drawable drawable, int[] drawableState, float imgPressedAlpha) {
        if (drawable == null || drawableState == null) {
            return;
        }

        float finalAlpha = 1.0f;
        for (int s : drawableState) {
            if (s == android.R.attr.state_pressed) {
                finalAlpha = Math.max(0, Math.min(1, imgPressedAlpha));
                break;
            }
        }

        if (Build.VERSION.SDK_INT <= 19 || drawable.getAlpha() != finalAlpha) {
            drawable.setAlpha((int) (finalAlpha * 255));
        }
    }
}
