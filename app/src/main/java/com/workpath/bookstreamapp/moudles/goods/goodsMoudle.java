package com.workpath.bookstreamapp.moudles.goods;

import android.content.Context;

import com.workpath.bookstreamapp.moudles.Base;

/**
 * Created by JayChen on 2020/3/10.
 */

public class goodsMoudle extends Base{

    Context context;//传递上下文对象
    int Id;//传递图片Id

    public goodsMoudle(Context context, int id) {
        this.context = context;
        Id = id;
    }

    public Context getContext() {
        return context;
    }

    public void setContext(Context context) {
        this.context = context;
    }

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }
}
