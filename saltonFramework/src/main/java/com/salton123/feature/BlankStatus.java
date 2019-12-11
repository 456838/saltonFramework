package com.salton123.feature;

import com.kingja.loadsir.callback.Callback;
import com.salton123.saltonframework.R;

/**
 * User: newSalton@outlook.com
 * Date: 2019/12/11 18:00
 * ModifyTime: 18:00
 * Description:
 */
public class BlankStatus extends Callback {
    @Override
    protected int onCreateView() {
        return R.layout.salton_view_no_data;
    }
}
