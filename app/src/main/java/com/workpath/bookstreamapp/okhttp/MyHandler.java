package com.workpath.bookstreamapp.okhttp;

/**
 * Created by JayChen on 2020/2/10.
 */

public interface MyHandler {

    /**
     * 请求成功所滴哦用的方法
     */
    public void onSuccess(Object responseObj);


    /**
     * 请求失败所调用的方法
     */
    public void onError(Object responseObj);


}
