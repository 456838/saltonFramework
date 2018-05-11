package com.salton123.saltonframeworkdemo.video;

import android.support.annotation.IntDef;

/**
 * User: newSalton@outlook.com
 * Date: 2018/5/11 下午10:34
 * ModifyTime: 下午10:34
 * Description:
 */
@IntDef({StateType.STATE_PREPARE
        , StateType.STATE_BUFFERING
        , StateType.STATE_INFO
        , StateType.STATE_PROGRESSING
        , StateType.STATE_ERROR
        , StateType.STATE_COMPLETE})
public @interface StateType {
    int STATE_PREPARE = 0;
    int STATE_BUFFERING = 1;
    int STATE_INFO = 2;
    int STATE_PROGRESSING = 3;
    int STATE_ERROR = 4;
    int STATE_COMPLETE = 5;
}
