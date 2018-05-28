package com.salton123.saltonframeworkdemo;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.widget.VideoView;

/**
 * User: newSalton@outlook.com
 * Date: 2018/5/16 下午6:25
 * ModifyTime: 下午6:25
 * Description:
 */
public class DrawTestView extends VideoView {
    public DrawTestView(Context context) {
        super(context);
    }

    public DrawTestView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public DrawTestView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public void onDrawForeground(Canvas canvas) {
        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.mipmap.video_bg_fingerprint);
        canvas.drawBitmap(bitmap, 0, 0, new Paint());
        Log.e("aa", "onDrawForeground");
        super.onDrawForeground(canvas);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.mipmap.video_bg_fingerprint);
        canvas.drawBitmap(bitmap, 0, 0, new Paint());
        Log.e("aa", "onDraw");
        // drawColor(Color.BLACK);  // 纯黑
    }
}
