package com.workpath.bookstreamapp;

import android.Manifest;
import android.os.Environment;

/**设置一些常用的静态常量
 * Created by JayChen on 2020/2/3.
 */

public class Constant {
    /**
     * 权限常量相关
     */
    public static final int HARDWEAR_CAMERA_CODE = 0x01;
    public static final String[] HARDWEAR_CAMERA_PERMISSION = new String[]{Manifest.permission.CAMERA};

    /**登陆相关常量**/
    public static final String PORTRAIT_URL="portrait";

    /**课程相关常量**/
    public static final String COURSE="course";

    /**评论相关常量**/
    public static final String COMMENT="course";


    /**
     * 整个应用文件下载保存路径
     * 自行添加对应路径
     */
    public static String APP_PHOTO_DIR = Environment.
            getExternalStorageDirectory().getAbsolutePath().
            concat("/bookstream_business/photo/");
}
