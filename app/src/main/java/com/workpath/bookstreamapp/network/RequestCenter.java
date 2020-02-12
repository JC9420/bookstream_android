package com.workpath.bookstreamapp.network;

import android.os.Build;
import android.support.annotation.RequiresApi;

import com.workpath.bookstreamapp.moudles.user.AllUserBean;
import com.workpath.bookstreamapp.okhttp.MyHandler;
import com.workpath.bookstreamapp.okhttp.OkHttpMethods;


import java.io.IOException;

/**
 * Created by JayChen on 2020/2/6.
 */

public class RequestCenter {


    /**
     * 获取所有的用户信息
     * @param handler
     * @throws IOException
     */
    @RequiresApi(api = Build.VERSION_CODES.N)
    public static void getUsersMsg(MyHandler handler) throws IOException {

        //OkHttpMethods.httpGet("http://www.baidu.com",AllUserBean.class,handler);
        OkHttpMethods.AsyncHttpGet(HttpConstants.ALL_USER_MSG,AllUserBean.class,handler);
    }

}
