package com.yy.saltonframework.callback;

/**
 * User: 巫金生(newSalton@outlook.com)
 * Date: 2015/12/22 12:19
 * Time: 12:19
 * Description:
 */

public interface HttpResponseHandler<T> {
    void onSuccess(T responseData);
    void onFailure(String failedReason);
}
