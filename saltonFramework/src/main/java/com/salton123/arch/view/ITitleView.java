package com.salton123.arch.view;

import com.wuhenzhizao.titlebar.widget.CommonTitleBar;

/**
 * User: 巫金生(newSalton@outlook.com)
 * Date: 2017/7/12 15:22
 * Time: 15:22
 * Description:
 */
public interface ITitleView extends CommonTitleBar.OnTitleBarListener{

    void setLeftText(String leftText);

    void setRightText(String rightText);

    void setTitleText(String titleText);

    void setSubTitleText(String subTitleText);

}
