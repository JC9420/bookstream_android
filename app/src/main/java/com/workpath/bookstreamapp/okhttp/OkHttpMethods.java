package com.workpath.bookstreamapp.okhttp;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.util.Log;

import com.alibaba.fastjson.JSON;
import com.workpath.bookstreamapp.utils.LogUtils;

import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.CollectionUtils;
import okhttp3.*;
import org.springframework.http.HttpMethod;
import java.io.IOException;
import java.util.Map;
import java.util.concurrent.TimeUnit;



/**
 * Created by JayChen on 2020/2/10.
 */

public class OkHttpMethods {

    private static final Logger log = LoggerFactory.getLogger(OkHttpMethods.class);

    private static final String HTTP_JSON = "application/json; charset=utf-8";
    private static final String HTTP_FORM = "application/x-www-form-urlencoded; charset=utf-8";

    private static final OkHttpClient okHttpClient = new OkHttpClient.Builder()
            .connectTimeout(120, TimeUnit.SECONDS)
            .readTimeout(120, TimeUnit.SECONDS)
            .writeTimeout(120, TimeUnit.SECONDS)
            .build();


    /**
     * 与服务器返回的字段的一一对应关系
     */
    protected static final String RESULT_CODE = "ecode"; // 有返回则对于http请求来说是成功的，但还有可能是业务逻辑上的错误
    protected static final int RESULT_CODE_VALUE = 0;
    protected static final int RESULT_CODE_ERROR = 1;
    protected static final int NOT_BACK = 100;
    protected static final String BACK_MSG = "emsg";
    private final static int NETWORK_ERROR = -1;
    private final static String EMPTY_MSG = "";
    private final static String TAG = "OkHttpMethods";
    protected final String COOKIE_STORE = "Set-Cookie"; // decide the server it


    /**
     * 自定义异常
     */
    //protected final int NETWORK_ERROR = -1; // the network relative error
    protected static final int JSON_ERROR = -2; // the JSON relative error
    protected static final int OTHER_ERROR = -3; // the unknow error
    protected final int WEB_ERROR = 1; //请求失败

    /**
     * 同步get请求
     * 对于小文档，响应体上的string()方法非常方便和高效。
     * 但是，如果响应主体很大(大于1 MB)，则应避免string()，
     * 因为它会将整个文档加载到内存中。在这种情况下，将主体处理为流。
     *
     * @param url 请求接口
     * @return
     */
    public static String httpGet(String url) {
        if (url == null || "".equals(url)) {
            log.error("url为null!");
            return "";
        }

        Request.Builder builder = new Request.Builder();
        Request request = builder.get().url(url).build();
        try {
            Response response = okHttpClient.newCall(request).execute();
            if (response.code() == 200) {
                log.info("http GET 请求成功; [url={}]", url);
                return response.body().string();
            } else {
                log.warn("Http GET 请求失败; [errorCode = {} , url={}]", response.code(), url);
            }
        } catch (IOException e) {
            throw new RuntimeException("同步http GET 请求失败,url:" + url, e);
        }
        return null;
    }

    /**
     * 异步get请求
     * 对于小文档，响应体上的string()方法非常方便和高效。
     * 但是，如果响应主体很大(大于1 MB)，则应避免string()，
     * 因为它会将整个文档加载到内存中。在这种情况下，将主体处理为流。
     * @param url 请求接口
     * @param clazz 转化目标类
     * @param handler 回调接口
     */
    public static void  AsyncHttpGet(String url,Class<?> clazz,MyHandler handler) {
        if (url == null || "".equals(url)) {
            Log.d(TAG,"url为null!");
        }

        Request.Builder builder = new Request.Builder();
        Request request = builder.get().url(url).build();
        Call call = okHttpClient.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Log.d("chen","访问失败");
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                HandResponse(response.body().string(),clazz,handler);
            }
        });

    }


    /**
     * 同步get请求
     * @param url
     * @param headers
     * @return
     */
    @RequiresApi(api = Build.VERSION_CODES.N)
    public static String httpGet(String url, Map<String, String> headers) {
        if (CollectionUtils.isEmpty(headers)) {
            return httpGet(url);
        }

        Request.Builder builder = new Request.Builder();
        headers.forEach((String key, String value) -> builder.header(key, value));
        Request request = builder.get().url(url).build();
        try {
            Response response = okHttpClient.newCall(request).execute();
            if (response.code() == 200) {
                log.info("http GET 请求成功; [url={}]", url);
                return response.body().string();
            } else {
                log.warn("Http GET 请求失败; [errorxxCode = {} , url={}]", response.code(), url);
            }
        } catch (IOException e) {
            throw new RuntimeException("同步http GET 请求失败,url:" + url, e);
        }
        return null;
    }


    /**
     * 异步get请求
     * @param url 请求接口
     * @param headers 请求携带参数
     * @param clazz 转化类
     * @param handler 回调接口
     */
    @RequiresApi(api = Build.VERSION_CODES.N)
    public static void AsyncHttpGet(String url, Map<String, String> headers,Class<?> clazz,MyHandler handler) {
        if (CollectionUtils.isEmpty(headers)) {
            AsyncHttpGet(url,clazz,handler);
        }

        Request.Builder builder = new Request.Builder();
        headers.forEach((String key, String value) -> builder.header(key, value));
        Request request = builder.get().url(url).build();
        Call call = okHttpClient.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Log.d("chen","访问失败");
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                HandResponse(response.body().string(),clazz,handler);
            }
        });
    }

    /**
     * 同步 POST调用 无Header
     *
     * @param url
     * @param json
     * @return
     */
    public static String httpPostJson(String url, String json) {
        if (url == null || "".equals(url)) {
            log.error("url为null!");
            return "";
        }

        MediaType JSON = MediaType.parse(HTTP_JSON);
        RequestBody body = RequestBody.create(JSON, json);
        Request.Builder requestBuilder = new Request.Builder().url(url);
        Request request = requestBuilder.post(body).build();
        try {
            Response response = okHttpClient.newCall(request).execute();
            if (response.code() == 200) {
                log.info("http Post 请求成功; [url={}, requestContent={}]", url, json);
                return response.body().string();
            } else {
                log.warn("Http POST 请求失败; [ errorCode = {}, url={}, param={}]", response.code(), url, json);
            }
        } catch (IOException e) {
            throw new RuntimeException("同步http请求失败,url:" + url, e);
        }
        return null;
    }


    /**
     * 异步 POST调用 无Header
     *
     * @param url
     * @param json
     * @return
     */
    public static void AsyncHttpPostJson(String url, String json,Class<?> clazz,MyHandler handler) {
        if (url == null || "".equals(url)) {
            Log.d(TAG,"url为null!");
        }

        MediaType JSON = MediaType.parse(HTTP_JSON);
        RequestBody body = RequestBody.create(JSON, json);
        Request.Builder requestBuilder = new Request.Builder().url(url);
        Request request = requestBuilder.post(body).build();
        Call call = okHttpClient.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Log.d("chen","访问失败");
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                HandResponse(response.body().string(),clazz,handler);
            }
        });
    }

    /**
     * 同步 POST调用 有Header
     *
     * @param url
     * @param headers
     * @param json
     * @return
     */
    @RequiresApi(api = Build.VERSION_CODES.N)
    public static String httpPostJson(String url, Map<String, String> headers, String json) {
        if (CollectionUtils.isEmpty(headers)) {
            httpPostJson(url, json);
        }

        MediaType JSON = MediaType.parse(HTTP_JSON);
        RequestBody body = RequestBody.create(JSON, json);
        Request.Builder requestBuilder = new Request.Builder().url(url);
        headers.forEach((k, v) -> requestBuilder.addHeader(k, v));
        Request request = requestBuilder.post(body).build();
        try {
            Response response = okHttpClient.newCall(request).execute();
            if (response.code() == 200) {
                log.info("http Post 请求成功; [url={}, requestContent={}]", url, json);
                return response.body().string();
            } else {
                log.warn("Http POST 请求失败; [ errorCode = {}, url={}, param={}]", response.code(), url, json);
            }
        } catch (IOException e) {
            throw new RuntimeException("同步http请求失败,url:" + url, e);
        }
        return null;
    }
    /**
     * 异步 POST调用 有Header
     *
     * @param url
     * @param headers
     * @param json
     * @return
     */
    @RequiresApi(api = Build.VERSION_CODES.N)
    public static void AsyncHttpPostJson(String url, Map<String, String> headers, String json,Class<?> clazz,MyHandler handler) {
        if (CollectionUtils.isEmpty(headers)) {
            AsyncHttpPostJson(url, json,clazz,handler);
        }

        MediaType JSON = MediaType.parse(HTTP_JSON);
        RequestBody body = RequestBody.create(JSON, json);
        Request.Builder requestBuilder = new Request.Builder().url(url);
        headers.forEach((k, v) -> requestBuilder.addHeader(k, v));
        Request request = requestBuilder.post(body).build();
        Call call = okHttpClient.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Log.d("chen","访问失败");
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                HandResponse(response.body().string(),clazz,handler);
            }
        });
    }
    /**
     * 同步提交表单
     * @param url
     * @param content
     * @param headers
     * @return
     */
    @RequiresApi(api = Build.VERSION_CODES.N)
    public static String postDataByForm(String url, String content, Map<String, String> headers) {
        MediaType JSON = MediaType.parse(HTTP_FORM);
        RequestBody body = RequestBody.create(JSON, content);

        Request.Builder requestBuilder = new Request.Builder().url(url);
        if (headers != null && headers.size() > 0) {
            headers.forEach((k, v) -> requestBuilder.addHeader(k, v));
        }
        Request request = requestBuilder
                .post(body)
                .build();

        Response response = null;
        try {
            response = okHttpClient.newCall(request).execute();
            if (response.code() == 200) {
                Log.d(TAG,"postUrl="+ url+
                        "\nrequestContent="+content.toString()+
                        "\nresponseCode="+ response.code()
                        );
                return response.body().string();
            } else {
                Log.d(TAG,"postUrl="+ url+
                        "\ncontent="+content.toString()
                );
            }
        } catch (IOException e) {
            log.error("Http Post Form请求失败,[url={}, param={}]", url, content, e);
            throw new RuntimeException("Http Post Form请求失败,url:" + url);
        }
        return null;
    }


    /**
     * 异步提交表单
     * @param url
     * @param content
     * @param headers
     * @return
     */
    @RequiresApi(api = Build.VERSION_CODES.N)
    public static void AsyncPostDataByForm(String url, String content, Map<String, String> headers,Class<?> clazz,MyHandler handler) {
        MediaType JSON = MediaType.parse(HTTP_FORM);
        RequestBody body = RequestBody.create(content, JSON);

        Request.Builder requestBuilder = new Request.Builder().url(url);
        if (headers != null && headers.size() > 0) {
            headers.forEach((k, v) -> requestBuilder.addHeader(k, v));
        }
        Request request = requestBuilder
                .post(body)
                .build();
        Call call = okHttpClient.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Log.d("chen","访问失败");
                Log.d(TAG,"异步http:"+ url+
                        "\n+请求失败,[url="+url+
                        ", param="+headers.toString()
                        +"]");
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                HandResponse(response.body().string(),clazz,handler);
            }
        });
    }

    /**
     * 异步Http调用参考模板：Get、Post、Put
     * 需要异步调用的接口一般情况下你需要定制一个专门的Http方法
     *
     * @param httpMethod 请求方式GET、POST等等
     * @param url  请求接口
     * @param content 为post的上传内容
     * @param headers 为请求所携带的参数
     * @return
     */
    @RequiresApi(api = Build.VERSION_CODES.N)
    public static void AsyncHttpByJson(HttpMethod httpMethod, String url, Map<String, String> headers, String content,Class<?> clazz,MyHandler handler) {
        MediaType JSON = MediaType.parse(HTTP_JSON);
        RequestBody body = RequestBody.create(content, JSON);

        Request.Builder requestBuilder = new Request.Builder()
                .url(url);

        if (!CollectionUtils.isEmpty(headers)) {
            headers.forEach((key, value) -> requestBuilder.header(key, value));
        }

        switch (httpMethod) {
            case GET:
                requestBuilder.get();
                break;
            case POST:
                requestBuilder.post(body);
                break;
            default:
        }

        Request request = requestBuilder.build();
        Call call = okHttpClient.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

                Log.d(TAG,"异步http:"+ url+
                        "\n+请求失败,[url="+url+
                        ", param="+headers.toString()
                        +"]");
                throw new RuntimeException("异步http请求失败,url:" + url);
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                HandResponse(response.body().string(),clazz,handler);
            }
        });
    }

    private static void HandResponse(Object responseObj,Class<?> clazz,MyHandler handler){
        Log.d(TAG,"进入HandResponse");
        if (responseObj == null || responseObj.toString().trim().equals("")) {
            handler.onError(new OkHttpException(NETWORK_ERROR, EMPTY_MSG));
            LogUtils.i(TAG,"handleResponse error 返回结果为空");
            return;
        }

        try {
            /**
             * 协议确定后看这里如何修改
             */

            LogUtils.i(TAG,"对应的json1---"+responseObj.toString());

            JSONObject result = new JSONObject(responseObj.toString());

            LogUtils.i(TAG,"对应的json2"+result);
            //尝试解析json
            if(result.has(RESULT_CODE)){
                LogUtils.i(TAG,"返回的json格式正确尝试解析");
                //判断是否正常响应
                if(result.getInt(RESULT_CODE) == RESULT_CODE_VALUE){


                    LogUtils.i(TAG,"返回码正确开始解析");
                    //不需要解析
                    if(clazz == null){
                        LogUtils.i(TAG,"不需要吧json解析成实体类");

                        if(result.length() == 2){
                            LogUtils.i(TAG,"特殊请求，不需要返回数据");
                            handler.onSuccess(new NotCallBackData((Integer) result.get(RESULT_CODE),result.get(BACK_MSG)));

                        }

                        handler.onSuccess(responseObj);
                    }else{//需要解析 144---156
                        //将我们的json转为我们的实体对象
                        LogUtils.i(TAG,"尝试将json转化为实体类");
                        Object obj = com.alibaba.fastjson.JSON.parseObject(result.toString(),clazz);
                        if(obj !=null){
                            //成功的转化成我们的实体对象
                            LogUtils.i(TAG,"成功的转化成我们的实体对象");
                            handler.onSuccess(obj);
                        }else {
                            LogUtils.i(TAG,"解析异常");
                            handler.onError(new OkHttpException(JSON_ERROR,EMPTY_MSG));
                        }
                    }//end else
                }else if(result.getInt(RESULT_CODE) == RESULT_CODE_ERROR) {
                    LogUtils.i(TAG,"ecode为1，请求失败");
                    handler.onError(new OkHttpException((Integer) result.get(RESULT_CODE),result.get(BACK_MSG)));
                }else if(result.getInt(RESULT_CODE) == NOT_BACK){
                    //请求还是成功的
                    LogUtils.i(TAG,"ecode为"+NOT_BACK+"  "+"不需要返回解析数据");
                }
            }//end if
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}

