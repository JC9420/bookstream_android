package com.workpath.bookstreamapp.utils;

import android.content.Context;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.youth.banner.loader.ImageLoader;

/**
 * Created by cjw on 2019/4/18 0018.
 */

public class GlideImageLoader extends ImageLoader {

    @Override
    public void displayImage(Context context, Object path, ImageView imageView) {
        /**
         * 根据提供的path将图片加载到ImageViews上
         */
        Glide.with(context).load(path).into(imageView);
    }
}
