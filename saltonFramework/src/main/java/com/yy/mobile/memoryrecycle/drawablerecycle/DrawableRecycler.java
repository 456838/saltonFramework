package com.yy.mobile.memoryrecycle.drawablerecycle;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.drawable.AnimationDrawable;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.salton123.saltonframework.BuildConfig;
import com.salton123.util.log.MLog;
import com.yy.mobile.memoryrecycle.views.IImageRecycleView;
import com.yy.mobile.memoryrecycle.views.IRecycleView;


/**
 * Created by lulong on 2017/5/9.
 * Email:lulong@yy.com
 */
public class DrawableRecycler {
    private static final String TAG = "DrawableRecycler";
    public static final int INVALID_DRAWABLE_ID = -1;
    public static final int TAG_KEY_AUTO_RECYCLE_OFF = -1313130;
    public static final int TAG_KEY_VIEW_BG_DRAWABLE_ID = -1313131;
    public static final int TAG_KEY_IMAGEVIEW_SRC_DRAWABLE_ID = -1313132;
    public static final int TAG_KEY_ATTACH_COUNT_ID = -1313133;
    public static final int TAG_KEY_RECYCLE_FLAG_ID = -1313134;

    private static volatile boolean sSwitch = true; //默认开启
    private static boolean sDebug = false;

    public static void updateSwitch(boolean switchOn) {
        if (sSwitch == switchOn) {
            return;
        }

        sSwitch = switchOn;
        MLog.info(TAG, "updateSwitch :" + switchOn);
    }

    public static void closeAutoRecycle(View view) {
        if (!(view instanceof IRecycleView)) {
            return;
        }

        recoveryViewDrawable((IRecycleView) view);
        view.setTag(TAG_KEY_AUTO_RECYCLE_OFF, true);
    }

    public static void openAutoRecycle(View view) {
        if (!(view instanceof IRecycleView)) {
            return;
        }

        view.setTag(TAG_KEY_AUTO_RECYCLE_OFF, false);
        if (view.getVisibility() == View.GONE) {
            int size = recycleViewDrawable((IRecycleView) view);
            if (size > 0 && BuildConfig.DEBUG) {
                MLog.info(TAG, "recycle size by VIEW.GONE :" + size);
            }
        }
    }


    @SuppressLint("ResourceType")
    public static void onAttributesUpdated(Context context, IRecycleView view, AttributeSet attrs) {
        if (!sSwitch) {
            return;
        }

        if (!(view instanceof View) || view.closeAutoRecycleDrawables()) {
            return;
        }

        int[] attribute = null;
        boolean isImageView = false;
        if (view instanceof ImageView) {
            isImageView = true;
            attribute = new int[]{android.R.attr.background, android.R.attr.src};
        } else {
            attribute = new int[]{android.R.attr.background};
        }

        TypedArray array = context.obtainStyledAttributes(attrs, attribute);
        int bgDrawableId = INVALID_DRAWABLE_ID;
        int srcDrawableId = INVALID_DRAWABLE_ID;
        try {
            bgDrawableId = array.getResourceId(0, INVALID_DRAWABLE_ID);
            if (isImageView) {
                srcDrawableId = array.getResourceId(1, INVALID_DRAWABLE_ID);
            }

            if (sDebug) {
                if (bgDrawableId != INVALID_DRAWABLE_ID || srcDrawableId != INVALID_DRAWABLE_ID) {
                    MLog.debug(TAG, "onAttributesUpdated view " + "src " + srcDrawableId + " bg " + bgDrawableId + " " + view);
                }
            }

        } catch (Exception e) {
            MLog.error("DrawableRecycler", "onAttributesUpdated" + e.toString());
        }

        array.recycle();

        ((View) view).setTag(TAG_KEY_VIEW_BG_DRAWABLE_ID, bgDrawableId);
        if (isImageView) {
            ((View) view).setTag(TAG_KEY_IMAGEVIEW_SRC_DRAWABLE_ID, srcDrawableId);
        }

        if (((View) view).getVisibility() == View.GONE) {
            int size = 0;
            if (bgDrawableId != INVALID_DRAWABLE_ID || srcDrawableId != INVALID_DRAWABLE_ID) {
                size += recycleViewDrawable(view);
            }

            if (view instanceof ViewGroup) {
                size += recycleDrawablesByGone((ViewGroup) view);
            }

            if (size > 0 && sDebug) {
                MLog.info(TAG, "recycle size by VIEW.GONE :" + size);
            }
        }
    }

    //回收属性为Gone的视图
    private static int recycleDrawablesByGone(ViewGroup viewGroup) {
        if (!sSwitch) {
            return 0;
        }

        int recycleSize = 0;
        if (viewGroup == null || viewGroup.getVisibility() != View.GONE || !(viewGroup instanceof IRecycleView)) {
            return recycleSize;
        }

        int childSize = viewGroup.getChildCount();
        View child = null;
        for (int i = 0; i < childSize; i++) {
            child = viewGroup.getChildAt(i);
            if (child == null || child.getVisibility() != View.GONE || !(child instanceof IRecycleView)) {
                continue;
            } else if (child instanceof ViewGroup) {
                recycleSize += recycleViewDrawable((IRecycleView) child);
                recycleSize += recycleDrawablesByGone((ViewGroup) child);
            } else if (child instanceof IRecycleView) {
                recycleSize += recycleViewDrawable((IRecycleView) child);
            }
        }

        return recycleSize;
    }

    //恢复属性不为GONE的视图
    private static void recoveryDrawablesNotGone(ViewGroup viewGroup) {
        if (viewGroup == null || viewGroup.getVisibility() == View.GONE || !(viewGroup instanceof IRecycleView)) {
            return;
        }

        int childSize = viewGroup.getChildCount();
        View child = null;
        for (int i = 0; i < childSize; i++) {
            child = viewGroup.getChildAt(i);
            if (child == null || child.getVisibility() == View.GONE || !(child instanceof IRecycleView)) {
                continue;
            } else if (child instanceof ViewGroup) {
                recoveryViewDrawable((IRecycleView) child);
                recoveryDrawablesNotGone((ViewGroup) child);
            } else if (child instanceof IRecycleView) {
                recoveryViewDrawable((IRecycleView) child);
            }
        }
    }

    public static void recoveryViewDrawable(IRecycleView view) {
        if (!(view instanceof View)) {
            return;
        }

        View v = (View) view;

        Object recycled = v.getTag(TAG_KEY_RECYCLE_FLAG_ID);
        if (recycled instanceof Boolean && (Boolean) recycled) {
            v.setTag(TAG_KEY_RECYCLE_FLAG_ID, false);
            if (v instanceof ImageView && view instanceof IImageRecycleView) {
                if (((IImageRecycleView) view).getImageDrawableInner() == null) {
                    Object srcId = v.getTag(TAG_KEY_IMAGEVIEW_SRC_DRAWABLE_ID);
                    if (srcId != null && srcId instanceof Integer && ((Integer) srcId) != INVALID_DRAWABLE_ID) {
                        ((ImageView) v).setImageResource((Integer) srcId);
                        if (sDebug) {
                            MLog.info(TAG, "recovery image:" + Integer.toHexString((Integer) srcId) + " from view:" + view.hashCode());
                        }
                    }
                }
            }

            if (v instanceof TextView) {
                //todo:
            }

            if (v instanceof CompoundButton) {
                //todo:
            }

            if (view.getBackgroundInner() == null) {
                Object bgId = v.getTag(TAG_KEY_VIEW_BG_DRAWABLE_ID);
                if (bgId != null && bgId instanceof Integer && ((Integer) bgId) != INVALID_DRAWABLE_ID) {
                    v.setBackgroundResource((Integer) bgId);
                    if (sDebug) {
                        MLog.info(TAG, "recovery bg:" + Integer.toHexString((Integer) bgId) + " from view:" + view.hashCode());
                    }
                }
            }
        }
    }

    private static boolean autoRecycleClosed(IRecycleView view) {
        if (!(view instanceof View) || view.closeAutoRecycleDrawables()) {
            return true;
        }

        View v = (View) view;
        Object autoRecycleOff = v.getTag(TAG_KEY_AUTO_RECYCLE_OFF);
        if (autoRecycleOff != null && autoRecycleOff instanceof Boolean) {
            return ((Boolean) autoRecycleOff);
        } else {
            return false;
        }
    }


    public static int recycleViewDrawable(IRecycleView view) {
        if (!sSwitch) {
            return 0;
        }

        int recycleSize = 0;
        if (!(view instanceof View) || view.closeAutoRecycleDrawables()) {
            return recycleSize;
        }

        View v = (View) view;
        Object autoRecycleOff = v.getTag(TAG_KEY_AUTO_RECYCLE_OFF);
        if (autoRecycleOff != null && autoRecycleOff instanceof Boolean && ((Boolean) autoRecycleOff)) {
            return recycleSize;
        }

        boolean recycle = false;
        if (v != null) {
            if (v instanceof ImageView && view instanceof IImageRecycleView) {
                Drawable srcDrawable = ((IImageRecycleView) view).getImageDrawableInner();
                if (srcDrawable != null) {
                    Object srcId = v.getTag(TAG_KEY_IMAGEVIEW_SRC_DRAWABLE_ID);
                    if (srcId != null && srcId instanceof Integer && ((Integer) srcId) != INVALID_DRAWABLE_ID) {
                        if (srcDrawable instanceof AnimationDrawable) {
                            if (((AnimationDrawable) srcDrawable).isRunning()) {
                                ((AnimationDrawable) srcDrawable).stop();
                            }
                        }

                        ((IImageRecycleView) view).setImageDrawableToNull();
                        if (sDebug) {
                            MLog.info(TAG, "recycle image:" + Integer.toHexString((Integer) srcId) + " from view:" + view.hashCode());
                            recycleSize += getBitmapSize(srcDrawable);
                        }

                        recycle = true;
                    }
                }
            }

            if (v instanceof TextView) {
                //todo:
            }

            if (v instanceof CompoundButton) {
                //todo:
            }

            Drawable bgDrawable = view.getBackgroundInner();

            if (bgDrawable != null) {
                Object bgId = v.getTag(TAG_KEY_VIEW_BG_DRAWABLE_ID);
                if (bgId != null && bgId instanceof Integer && ((Integer) bgId) != INVALID_DRAWABLE_ID) {
                    view.setBackgroundToNull();
                    if (sDebug) {
                        MLog.info(TAG, "recycle bg:" + Integer.toHexString((Integer) bgId) + " from view:" + view.hashCode());
                        recycleSize += getBitmapSize(bgDrawable);
                    }

                    recycle = true;
                }
            }

            if (recycle) {
                v.setTag(TAG_KEY_RECYCLE_FLAG_ID, recycle);
            }
        }

        return recycleSize;
    }

    private static boolean isAttachToWindow(IRecycleView view) {
        if (view instanceof View) {
            Object count = ((View) view).getTag(TAG_KEY_ATTACH_COUNT_ID);
            if (count != null && count instanceof Integer) {
                return ((Integer) count) > 0;
            }
        }

        return false;
    }


    public static void onVisibilityChanged(IRecycleView view, int visibility) {
        if (view == null) {
            return;
        }

        if (visibility == View.GONE) {
            if (isAttachToWindow(view)) {
                int size = recycleViewDrawable(view);
                if (view instanceof ViewGroup) {
                    size += recycleDrawablesByGone((ViewGroup) view);
                }

                if (size > 0 && sDebug) {
                    MLog.info(TAG, "recycle size by VIEW.GONE Changed:" + size);
                }
            }
        } else {
            if (view instanceof ViewGroup) {
                recoveryDrawablesNotGone((ViewGroup) view);
            }

            recoveryViewDrawable(view);
            if (sDebug) {
                MLog.info(TAG, "reccvery by visible");
            }
        }
    }

    public static void onDetachedFromWindow(IRecycleView view) {
        if (view instanceof View) {
            View theView = ((View) view);
            int countInt = 0;
            Object count = theView.getTag(TAG_KEY_ATTACH_COUNT_ID);
            if (count != null && count instanceof Integer) {
                countInt = (Integer) count;
                if (countInt > 0) {
                    countInt--;
                } else {
                    countInt = 0;
                }
            }

            if (countInt == 0) {
                int size = recycleViewDrawable(view);
                if (size > 0 && sDebug) {
                    MLog.info(TAG, "recycle size by Detached :" + size);
                }

                theView.setTag(TAG_KEY_ATTACH_COUNT_ID, countInt);
            } else {
                theView.setTag(TAG_KEY_ATTACH_COUNT_ID, countInt);
                if (sDebug) {
                    MLog.debug("DrawableRecycler", "onDetachedFromWindow but attach count is not 0");
                }
            }

        } else {
            int size = recycleViewDrawable(view);
            if (size > 0 && sDebug) {
                MLog.info(TAG, "recycle size by Detached :" + size);
            }
        }
    }

    public static void onAttachedToWindow(IRecycleView view) {
        if (view instanceof View) {
            View theView = ((View) view);
            int countInt = 0;
            Object count = theView.getTag(TAG_KEY_ATTACH_COUNT_ID);
            if (count != null && count instanceof Integer) {
                countInt = (Integer) count;
                if (countInt < 0) {
                    countInt = 0;
                }
            }

            countInt++;
            theView.setTag(TAG_KEY_ATTACH_COUNT_ID, countInt);

            if (theView.getVisibility() == View.GONE) {
                //recycleViewDrawable(view);
            } else {
                recoveryViewDrawable(view);
            }
        } else {
            recoveryViewDrawable(view);
        }
    }

    public static void onBackgroundUpdated(IRecycleView view, Drawable drawable) {
        if (!(view instanceof View)) {
            return;
        }

        ((View) view).setTag(TAG_KEY_VIEW_BG_DRAWABLE_ID, INVALID_DRAWABLE_ID);
    }

    public static void onBackgroundUpdated(IRecycleView view, int resID) {
        if (!(view instanceof View)) {
            return;
        }

        ((View) view).setTag(TAG_KEY_VIEW_BG_DRAWABLE_ID, resID);
    }

    public static void onImageUpdated(IRecycleView view, Drawable drawable) {
        if (!(view instanceof ImageView)) {
            return;
        }

        ((View) view).setTag(TAG_KEY_IMAGEVIEW_SRC_DRAWABLE_ID, INVALID_DRAWABLE_ID);
    }

    public static void onImageUpdated(IRecycleView view, int resID) {
        if (!(view instanceof ImageView)) {
            return;
        }

        ((View) view).setTag(TAG_KEY_IMAGEVIEW_SRC_DRAWABLE_ID, resID);
    }


    public static void onGetDrawable(IRecycleView view) {
        if (!(view instanceof ImageView)) {
            return;
        }

        recoveryViewDrawable(view);
    }

    public static void onGetDrawableEnd(IRecycleView view) {
        if (!(view instanceof ImageView)) {
            return;
        }
        if (autoRecycleClosed(view)) {
            return;
        }

        if (((ImageView) view).getVisibility() == View.GONE) {
            recycleViewDrawable(view);
        }
    }

    public static void onGetBackground(IRecycleView view) {
        if (Build.VERSION.SDK_INT < 24) {
            if (!(view instanceof View)) {
                return;
            }

            recoveryViewDrawable(view);
        }

    }

    public static void onGetBackgroundEnd(IRecycleView view) {
        if (Build.VERSION.SDK_INT < 24) {
            if (!(view instanceof View)) {
                return;
            }

            if (autoRecycleClosed(view)) {
                return;
            }

            if (((View) view).getVisibility() == View.GONE) {
                recycleViewDrawable(view);
            }
        }
    }


    private static final int DEFAULT_SUPPOSE_SIZE = 10 * 1024;

    private static int getBitmapSize(Drawable value) {
        Bitmap bitmap = null;
        if (value instanceof BitmapDrawable) {
            bitmap = ((BitmapDrawable) value).getBitmap();
        }
        if (bitmap != null) {
            if (bitmap == null) return DEFAULT_SUPPOSE_SIZE;

            if (Build.VERSION.SDK_INT > Build.VERSION_CODES.HONEYCOMB_MR1) {
                return bitmap.getByteCount();
            }
            // Pre HC-MR1
            return bitmap.getRowBytes() * bitmap.getHeight();
        } else {
            return DEFAULT_SUPPOSE_SIZE;
        }
    }
}
