package com.salton123.app

/**
 * User: newSalton@outlook.com
 * Date: 2018/9/24 上午11:18
 * ModifyTime: 上午11:18
 * Description:
 */
interface IFutureTaskPriority {
    fun highPriority(): Boolean
    fun mediumPriority(): Boolean
    fun lowPriority(): Boolean
}