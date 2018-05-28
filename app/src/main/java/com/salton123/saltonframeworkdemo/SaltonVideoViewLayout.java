package com.salton123.saltonframeworkdemo;

import android.content.Context;
import android.graphics.Color;
import android.media.MediaPlayer;
import android.os.Message;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.VideoView;

import com.salton123.saltonframeworkdemo.video.OnStateChangeListener;
import com.salton123.saltonframeworkdemo.video.StateType;
import com.salton123.saltonframeworkdemo.video.VideoObj;

import java.util.Timer;
import java.util.TimerTask;

/**
 * User: newSalton@outlook.com
 * Date: 2018/5/11 下午8:51
 * ModifyTime: 下午8:51
 * Description:
 */
public class SaltonVideoViewLayout extends FrameLayout
        implements MediaPlayer.OnErrorListener
        , MediaPlayer.OnInfoListener
        , MediaPlayer.OnPreparedListener
        , MediaPlayer.OnCompletionListener
        , MediaPlayer.OnBufferingUpdateListener {
    private final String TAG = "SaltonVideoView";

    public void setOnStateChangeListener(OnStateChangeListener onStateChangeListener) {
        mOnStateChangeListener = onStateChangeListener;
    }

    private OnStateChangeListener mOnStateChangeListener;

    public SaltonVideoViewLayout(Context context) {
        super(context);
        initView();
    }

    public SaltonVideoViewLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView();
    }

    public SaltonVideoViewLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView();
    }

    private VideoView mVideoView;
    private ImageView mCover;
    private FrameLayout mFlController;

    private void initView() {
        LayoutInflater.from(getContext()).inflate(R.layout.salton_video_view, this, true);
        mVideoView = findViewById(R.id.videoView);
        mFlController = findViewById(R.id.flController);
        mCover = findViewById(R.id.cover);
        mVideoView.setOnErrorListener(this);
        mVideoView.setOnInfoListener(this);
        mVideoView.setOnPreparedListener(this);
        mVideoView.setOnCompletionListener(this);
    }


    public void setCover(int resId) {
        mFlController.setVisibility(View.VISIBLE);
        mCover.setImageResource(resId);
    }

    public void setUri() {
        mVideoView.setVideoURI(UriProvider.getVideoPathUri("video_fingerprint.mp4"));
        mVideoView.start();
    }


    @Override
    public boolean onError(MediaPlayer mp, int what, int extra) {
        if (mOnStateChangeListener != null) {
            Message message = Message.obtain();
            message.what = StateType.STATE_ERROR;
            message.obj = new VideoObj(mp, what, extra);
            mOnStateChangeListener.onStateChange(message);
        }
        Log.e(TAG, "[onError] action=cancelUpdateProgressTimer ");
        cancelUpdateProgressTimer();
        return false;
    }

    @Override
    public boolean onInfo(MediaPlayer mp, int what, int extra) {
        if (mOnStateChangeListener != null) {
            Message message = Message.obtain();
            message.what = StateType.STATE_INFO;
            message.obj = new VideoObj(mp, what, extra);
            mOnStateChangeListener.onStateChange(message);
        }
        return false;
    }

    @Override
    public void onPrepared(MediaPlayer mp) {
        mp.setOnInfoListener(new MediaPlayer.OnInfoListener() {
            @Override
            public boolean onInfo(MediaPlayer mp, int what, int extra) {
                if (what == MediaPlayer.MEDIA_INFO_VIDEO_RENDERING_START) {
                    mVideoView.setBackgroundColor(Color.TRANSPARENT);
                    mFlController.setVisibility(View.GONE);
                    mCover.setBackgroundColor(Color.TRANSPARENT);
                }
                return true;
            }
        });
        if (mOnStateChangeListener != null) {
            Message message = Message.obtain();
            message.what = StateType.STATE_PREPARE;
            message.obj = new VideoObj(mp);
            mOnStateChangeListener.onStateChange(message);
        }
        Log.e(TAG, "[onError] action=startUpdateProgressTimer ");
        startUpdateProgressTimer();
    }

    @Override
    public void onCompletion(MediaPlayer mp) {
        if (mOnStateChangeListener != null) {
            Message message = Message.obtain();
            message.what = StateType.STATE_COMPLETE;
            message.obj = new VideoObj(mp);
            mOnStateChangeListener.onStateChange(message);
        }
        Log.e(TAG, "[onError] action=cancelUpdateProgressTimer ");
        cancelUpdateProgressTimer();
    }

    @Override
    public void onBufferingUpdate(MediaPlayer mp, int percent) {
        if (mOnStateChangeListener != null) {
            Message message = Message.obtain();
            message.what = StateType.STATE_BUFFERING;
            message.obj = new VideoObj(mp, percent);
            mOnStateChangeListener.onStateChange(message);
        }
    }

    private Timer mUpdateProgressTimer;
    private TimerTask mUpdateProgressTimerTask;

    private void startUpdateProgressTimer() {
        cancelUpdateProgressTimer();
        if (mUpdateProgressTimer == null) {
            mUpdateProgressTimer = new Timer();
        }
        if (mUpdateProgressTimerTask == null) {
            mUpdateProgressTimerTask = new TimerTask() {
                @Override
                public void run() {
                    Message message = Message.obtain();
                    message.what = StateType.STATE_PROGRESSING;
                    message.obj = new VideoObj(mVideoView.getCurrentPosition(), mVideoView.getDuration());
                    mOnStateChangeListener.onStateChange(message);
                }
            };
        }
        mUpdateProgressTimer.schedule(mUpdateProgressTimerTask, getInterval());
    }

    public int getInterval() {
        return interval;
    }

    public void setInterval(int interval) {
        this.interval = interval;
    }

    private int interval = 500;

    private void cancelUpdateProgressTimer() {
        if (mUpdateProgressTimer != null) {
            mUpdateProgressTimer.cancel();
            mUpdateProgressTimer = null;
        }
        if (mUpdateProgressTimerTask != null) {
            mUpdateProgressTimerTask.cancel();
            mUpdateProgressTimerTask = null;
        }
    }


    private void unInit() {
        if (mOnStateChangeListener != null) {
            mOnStateChangeListener = null;
        }
        cancelUpdateProgressTimer();
    }

}
