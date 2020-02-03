package com.workpath.bookstreamapp.network;

import android.util.Log;

import com.workpath.bookstreamapp.okhttp.CommonOkHttpClient;
import com.workpath.bookstreamapp.okhttp.listener.DisposeDataHandle;
import com.workpath.bookstreamapp.okhttp.listener.DisposeDataListener;
import com.workpath.bookstreamapp.okhttp.request.CommonRequest;
import com.workpath.bookstreamapp.okhttp.request.RequestParams;

import java.io.File;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.Request;
import okhttp3.RequestBody;


/**
 * Created by meng on 2018/2/24.
 * @function 所有请求的中心
 */

public class RequestCenter {

    private final String TGA = "RequestCenter";

    //根据参数发送所有post请求
    public static void postRequest(String url, RequestParams params, DisposeDataListener listener, Class<?> clazz) {
        CommonOkHttpClient.post(CommonRequest.
                createPostRequest(url, params), new DisposeDataHandle(listener, clazz));

    }

    //根据参数发送所有post请求，直接拼接整体
    public static void getRequest(String url, RequestParams params, DisposeDataListener listener, Class<?> clazz) {
        CommonOkHttpClient.get(CommonRequest.
                createPostRequestO(url, params), new DisposeDataHandle(listener, clazz));

    }

    /**
     *
     * @param url  请求的路径
     * @param imagePath 图片的真实地址
     * @param username 要上传的标识
     * @return
     */
    public static Request createPostUpdataImage(String url, String imagePath, String username){
        File file = new File(imagePath);

        RequestBody image = RequestBody.create(MediaType.parse("image/jpg"), file);
        RequestBody requestBody = new MultipartBody.Builder()
                .setType(MultipartBody.FORM)
                .addFormDataPart("file", imagePath, image)
                .addFormDataPart("username",username)
                .build();

        Log.i("Request","ok");;

        if(url !=null){
            //返回我们的请求对象
            return new Request
                    .Builder()
                    .url(url)
                    .post(requestBody)
                    .build();
        }
        return null;
    }


    /**
     * 图片请求
     * @param url
     * @param imagePath
     * @param username
     * @param listener
     * @param clazz
     */
    public static void postRequestImage(String url, String imagePath, String username, DisposeDataListener listener, Class<?> clazz)
    {
        CommonOkHttpClient.get(RequestCenter.
                createPostUpdataImage(url, imagePath,username), new DisposeDataHandle(listener, clazz));
    }


/******************************************************************************************************************************************/
/**下面为实现数据接口的样例
 * 要保证moudel的样子要跟返回的数据形式上是一一对应的
 */
/******************************************************************************************************************************************/

    /**
     * 注册
     * @param phone
     * @param userName
     * @param passWord
     * @param listener
     */
//    public static void register(String phone, String userName, String passWord, DisposeDataListener listener){
//        RequestParams params = new RequestParams();
//        params.put("username", userName);
//        params.put("password", passWord);
//        params.put("userid",phone);
//        LogUtils.d("Register", "register: " + userName + "|" + passWord+"|"+phone);
//        RequestCenter.postRequest(HttpConstants.REGISTER_URL, params, listener, BaseUserModel.class);
//    }





    /**
     * 用户登陆请求
     *
     * @param listener
     * @param userName
     * @param passWord
     */
//     public static void login(String userName, String passWord, DisposeDataListener listener) {
//
//        RequestParams params = new RequestParams();
//        params.put("username", userName);
//        params.put("password", passWord);
//        LogUtils.d("Login", "login: " + userName + "|" + passWord);
//        RequestCenter.postRequest(HttpConstants.CHECK_URL, params, listener, BaseUserLoginModel.class);
//    }


    /**
     * 获取评论
     * @param courseid 要获得课程的id
     * @param listener
     */
//   public static void getCouseComment(int courseid,DisposeDataListener listener){
//       RequestParams params=new RequestParams();
//       params.put("courseid", String.valueOf(courseid));
//       RequestCenter.postRequest(HttpConstants.GET_COMMENT_URL,/**请求接口**/
//               params,/**请求参数**/
//               listener,/**回调接口**/
//               BaseCommentModel.class/**数据转化的目标Model**/
//       );
//   }


    /**上传评论
     * @param courseid
     * @param coursename
     * @param username
     * @param commentcontent
     * @param replycount
     * @param portrait
     * @param listener
     */
//    public static void postCouseComment(int courseid,
//                                        String coursename,
//                                        String username,
//                                        String commentcontent,
//                                        int replycount,
//                                        String portrait,
//                                        DisposeDataListener listener){
//        RequestParams params=new RequestParams();
//        params.put("courseid", String.valueOf(courseid).trim());
//        params.put("coursename",coursename);
//        params.put("username",username);
//        params.put("commentcontent",commentcontent);
//        params.put("replycount", String.valueOf(replycount).trim());
//        params.put("portrait",portrait);
//        RequestCenter.postRequest(HttpConstants.POST_COMMENT_URL,params,listener,BaseUserLoginModel.class);
//    }


    /**
     * 上传评论回复
     * @param replyid
     * @param replycontent
     * @param username
     * @param portrait
     * @param listener
     */
//    public static void postCommentReply(int replyid,
//                                        String replycontent,
//                                        String username,
//                                        String portrait,
//                                        DisposeDataListener listener){
//        RequestParams params=new RequestParams();
//        params.put("replyid", String.valueOf(String.valueOf(replyid)));
//        params.put("replycontent",replycontent);
//        params.put("username",username);
//        params.put("portrait",portrait);
//        RequestCenter.postRequest(HttpConstants.POST_REPLY_URL,params,listener,BaseUserLoginModel.class);
//    }


    /**
     * 获取评论回复
     * @param id
     * @param listener
     */
//    public static void getReplyComment(int id,DisposeDataListener listener){
//        RequestParams params=new RequestParams();
//        params.put("replyid", String.valueOf(id));
//        RequestCenter.postRequest(HttpConstants.GET_REPLY_URL,params,listener,BaseReplyModel.class);
//    }
}
