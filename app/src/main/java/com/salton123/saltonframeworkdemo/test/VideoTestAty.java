package com.salton123.saltonframeworkdemo.test;

import android.os.Bundle;
import android.os.Message;
import android.util.Log;

import com.salton123.base.BaseSupportActivity;
import com.salton123.saltonframeworkdemo.R;
import com.salton123.saltonframeworkdemo.SaltonVideoView;
import com.salton123.saltonframeworkdemo.video.OnStateChangeListener;

import org.jetbrains.annotations.Nullable;

/**
 * User: newSalton@outlook.com
 * Date: 2018/5/11 下午11:20
 * ModifyTime: 下午11:20
 * Description:
 */
public class VideoTestAty extends BaseSupportActivity {
    private SaltonVideoView videoPlayer;

    @Override
    public void initViewAndData() {
        videoPlayer = f(R.id.videoPlayer);
        videoPlayer.setCover(R.mipmap.video_bg_fingerprint);
        videoPlayer.setUri();
        videoPlayer.setOnStateChangeListener(new OnStateChangeListener() {
            @Override
            public void onStateChange(Message message) {
                Log.i("aa", "[onStateChange] message.what=" + message.obj + ",obj=" + message.obj);
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
