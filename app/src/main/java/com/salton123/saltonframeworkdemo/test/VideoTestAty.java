package com.salton123.saltonframeworkdemo.test;

import android.os.Build;
import android.os.Bundle;
import android.os.Message;
import android.support.annotation.RequiresApi;
import android.util.Log;

import com.orhanobut.logger.AndroidLogAdapter;
import com.orhanobut.logger.Logger;
import com.salton123.base.BaseSupportActivity;
import com.salton123.saltonframeworkdemo.R;
import com.salton123.saltonframeworkdemo.SaltonVideoView;
import com.salton123.saltonframeworkdemo.UriProvider;
import com.salton123.saltonframeworkdemo.video.OnStateChangeListener;
import com.salton123.saltonframeworkdemo.video.VideoObj;
import com.salton123.util.MLog;

import org.jetbrains.annotations.Nullable;

/**
 * User: newSalton@outlook.com
 * Date: 2018/5/11 下午11:20
 * ModifyTime: 下午11:20
 * Description:
 */
public class VideoTestAty extends BaseSupportActivity {
    private SaltonVideoView videoPlayer;

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    public void initViewAndData() {
        MLog.init();

        videoPlayer = f(R.id.videoPlayer);
        videoPlayer.setCover(R.mipmap.video_bg_fingerprint);
        videoPlayer.setVideoURI(UriProvider.getVideoPathUri("smart_gallery.mp4"));
        videoPlayer.start();
        videoPlayer.setInterval(50);
        videoPlayer.setOnStateChangeListener(new OnStateChangeListener() {
            @Override
            public void onStateChange(Message message) {

                MLog.info("[onStateChange] message.what=" + message.what + ",obj=" + message.obj);
                Logger.i("[onStateChange] message.what=" + message.what + ",obj=" + message.obj);


                if (message.what == 3) {
                    VideoObj videoObj = (VideoObj) message.obj;
                    int currentPosition = (int) videoObj.mObjects[0];
                    int duration = (int) videoObj.mObjects[1];
                    MLog.info("aa", currentPosition + ":" + duration);
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
