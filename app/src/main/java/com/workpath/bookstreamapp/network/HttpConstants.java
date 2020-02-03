package com.workpath.bookstreamapp.network;

/**
 * Created by jaychen on 2018/2/24.
 * @function 所有请求相关地址 样例
 */

public class HttpConstants {
    public static final String HOME_URL = "http://39.107.87.25/thinkphp/index.php/Home/Index/";
    public static final String PICTURE_URL = "http://39.107.87.25/cjw/";
    public static final String VIDEO_URL="http://39.107.87.25/cjw/";

    public static final String REGISTER_URL =HOME_URL+"cjw_register";// 注册请求
    public static final String CHECK_URL =HOME_URL+"cjw_check"; // 登陆请求
    public static final String TUIJIAN_URL=HOME_URL+"cjw_tuijian"; //推荐课程请求
    public static final String HOT_URL=HOME_URL+"cjw_hot";//热门课程请求
    public static final String COURSE_URL=HOME_URL+"cjw_course";//课程请求
    public static final String ADD_COURSE_URL=HOME_URL+"cjw_addcourse";//添加个人课程
    public static final String GET_COURSE_URL=HOME_URL+"cjw_getMyCourse";//获取我的课程
    public static final String DELETE_COURSE_URL=HOME_URL+"cjw_deleteCourse"; //删除我的课程
    public static final String POST_COMMENT_URL=HOME_URL+"cjw_postComment"; //上传课程评论
    public static final String GET_COMMENT_URL=HOME_URL+"cjw_getComment";//获取课程评论
    public static final String POST_REPLY_URL=HOME_URL+"cjw_postReply";//上传评论回复
    public static final String GET_REPLY_URL=HOME_URL+"cjw_getReply";  //获取评论回复
}
