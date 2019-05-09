package com.salton123.saltonframeworkdemo.service;

import android.content.Intent;
import android.os.IBinder;

import com.salton123.compat.service.ForegroundService;

/**
 * User: newSalton@outlook.com
 * Date: 2019/4/17 14:47
 * ModifyTime: 14:47
 * Description:
 */
public class TestService extends ForegroundService {

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {

        return super.onStartCommand(intent, flags, startId);

    }
}
