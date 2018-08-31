package com.salton123.log;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.orhanobut.logger.FormatStrategy;
import com.orhanobut.logger.LogAdapter;
import com.salton123.util.AppUtils;


/**
 * User: newSalton@outlook.com
 * Date: 2018/6/15 下午4:06
 * ModifyTime: 下午4:06
 * Description:
 */
public class SaltonLogAdapter implements LogAdapter {
    @NonNull
    private final FormatStrategy formatStrategy;

    @Override
    public boolean isLoggable(int priority, @Nullable String tag) {
        return true;
    }

    public SaltonLogAdapter(@NonNull SaltonFormatStrategy formatStrategy) {
        this.formatStrategy = AppUtils.checkNotNull(formatStrategy);
    }

    @Override
    public void log(int priority, @Nullable String tag, @NonNull String message) {
        formatStrategy.log(priority, tag, message);
    }


}
