package com.salton123.saltonframeworkdemo.test;

import android.os.Build;
import android.os.Bundle;
import android.os.Message;
import android.support.annotation.RequiresApi;
import android.util.Log;

import com.salton123.base.BaseSupportActivity;
import com.salton123.saltonframeworkdemo.R;
import com.salton123.saltonframeworkdemo.SaltonVideoView;
import com.salton123.saltonframeworkdemo.UriProvider;
import com.salton123.saltonframeworkdemo.video.OnStateChangeListener;
import com.salton123.saltonframeworkdemo.video.VideoObj;
import com.salton123.util.MLog;

import org.jetbrains.annotations.Nullable;

import static com.salton123.saltonframeworkdemo.video.StateType.STATE_PROGRESSING;

/**
 * User: newSalton@outlook.com
 * Date: 2018/5/11 下午11:20
 * ModifyTime: 下午11:20
 * Description:
 */
public class VideoTestAty extends BaseSupportActivity {
    private SaltonVideoView videoPlayer;
    private static final String TAG = "VideoTestAty";

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    public void initViewAndData() {
        MLog.init();

        videoPlayer = f(R.id.videoPlayer);
        videoPlayer.setCover(R.mipmap.video_bg_fingerprint);
        videoPlayer.setVideoURI(UriProvider.getVideoPathUri("video_fingerprint.mp4"));
        videoPlayer.start();
        videoPlayer.setOnStateChangeListener(new OnStateChangeListener() {
            @Override
            public void onStateChange(Message message) {
                if (message.what == STATE_PROGRESSING) {
                    VideoObj videoObj = (VideoObj) message.obj;
                    int currentPosition = (int) videoObj.mObjects[0];
                    int duration = (int) videoObj.mObjects[1];
                    Log.i(TAG, currentPosition + ":" + duration);
                }
            }
        });
    }

    @Override
    public void initVariable(@Nullable Bundle savedInstanceState) {

    }

    @Override
    public int getLayout() {
        return R.layout.aty_test_video;
    }
}
