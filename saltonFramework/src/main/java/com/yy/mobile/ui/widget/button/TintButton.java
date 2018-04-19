package com.yy.mobile.ui.widget.button;

import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;

/**
 * User: newSalton@outlook.com
 * Date: 2017/10/27 19:20
 * ModifyTime: 19:20
 * Description:
 */
public class TintButton extends android.support.v7.widget.AppCompatButton {

    TintButtonHelper tintButtonHelper;

    /**
     * 支持染色的按钮
     *
     * @param context context
     */
    public TintButton(Context context) {
        this(context, null);
    }

    /**
     * 支持染色的按钮
     *
     * @param context context
     * @param attrs   attrs
     */
    public TintButton(Context context, AttributeSet attrs) {
        this(context, attrs, android.R.attr.buttonStyle);
    }

    /**
     * 支持染色的按钮
     *
     * @param context      context
     * @param attrs        attrs
     * @param defStyleAttr defStyleAttr
     */
    public TintButton(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        tintButtonHelper = new TintButtonHelper(this);
        tintButtonHelper.loadFromAttributes(attrs, defStyleAttr);
    }

    /**
     * 设置图标染色表，根据不同的状态应用对应的颜色对图标进行染色
     *
     * @param tint 状态-颜色映射表
     */
    public void setImgTintList(ColorStateList tint) {
        tintButtonHelper.setImgTintList(tint);
        tintButtonHelper.applyImageDrawable(getCompoundDrawables());
    }

    /**
     * 获取图标染色表
     *
     * @return 状态-颜色映射表
     */
    public ColorStateList getImgTintList() {
        return tintButtonHelper.getImgTintList();
    }

    /**
     * 设置背景染色列表，根据不同的状态应用对应的颜色对背景进行染色
     *
     * @param tint 状态-颜色映射
     */
    public void setBgTintList(ColorStateList tint) {
        tintButtonHelper.setBgTintList(tint);
        tintButtonHelper.applyBackgroundDrawable(getBackground());
    }

    /**
     * 获得背景染色表
     *
     * @return 背景染色表
     */
    public ColorStateList getBgTintList() {
        return tintButtonHelper.getBgTintList();
    }

    /**
     * 设置图标染色模式，默认是 PorterDuff.Mode.MULTIPLY
     *
     * @param mode 图标染色模式
     */
    public void setImgTintMode(PorterDuff.Mode mode) {
        tintButtonHelper.setImgTintMode(mode);
        tintButtonHelper.applyImageDrawable(getCompoundDrawables());
    }

    /**
     * 获取图标染色模式
     *
     * @return 图标染色模式
     */
    public PorterDuff.Mode getImgTintMode() {
        return tintButtonHelper.getImgTintMode();
    }

    /**
     * 设置背景染色模式，默认是 PorterDuff.Mode.MULTIPLY
     *
     * @param mode 背景染色模式
     */
    public void setBgTintMode(PorterDuff.Mode mode) {
        tintButtonHelper.setBgTintMode(mode);
    }

    /**
     * 获取背景染色模式
     *
     * @return 背景染色模式
     */
    public PorterDuff.Mode getBgTintMode() {
        return tintButtonHelper.getBgTintMode();
    }

    /**
     * 设置按下状态时图标的透明度，默认透明度0.6(UED建议值)
     *
     * @param imgPressedAlpha 透明度，范围0.0(全透明)~1.0(完全不透明)
     */
    public void setImgPressedAlpha(float imgPressedAlpha) {
        tintButtonHelper.setImgPressedAlpha(imgPressedAlpha);
        tintButtonHelper.applyImageDrawable(getCompoundDrawables());
    }

    /**
     * 获取按下状态时图标的透明度
     *
     * @return 透明度，范围0.0(全透明)~1.0(完全不透明)
     */
    public float getImgPressedAlpha() {
        return tintButtonHelper.getImgPressedAlpha();
    }

    @Override
    protected void drawableStateChanged() {
        super.drawableStateChanged();
        tintButtonHelper.applyBackgroundDrawable(getBackgroundInner());
        tintButtonHelper.applyImageDrawable(getCompoundDrawables());
    }

    public Drawable getBackgroundInner() {
        return super.getBackground();
    }
}
