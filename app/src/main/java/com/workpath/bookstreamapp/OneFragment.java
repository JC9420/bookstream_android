package com.workpath.bookstreamapp;
import android.os.Build;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.RequiresApi;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.workpath.bookstreamapp.moudles.user.AllUserBean;
import com.workpath.bookstreamapp.myapp.MyFragment;
import com.workpath.bookstreamapp.network.RequestCenter;
import com.workpath.bookstreamapp.okhttp.MyHandler;

import java.io.IOException;


/**
 * Created by JayChen on 2020/2/3.
 */


public class OneFragment extends MyFragment{

    private FloatingActionButton fab;
    private Button buttonGetMsg;
    private TextView textViewViewMsg;

    //测试数据
    private AllUserBean allUserBean;

    @Override
    protected int getContentLayoutId() {
        return R.layout.one_fragment_layout;
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    protected void initWidget(View view) throws IOException {

        fab = (FloatingActionButton) view.findViewById(R.id.fab);
        buttonGetMsg= (Button) view.findViewById(R.id.include_one).findViewById(R.id.get_msg);
        textViewViewMsg= (TextView) view.findViewById(R.id.include_one).findViewById(R.id.view_msg);

    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    protected void initData() {

        try {
            RequestCenter.get_users_msg(new MyHandler() {
                @Override
                public void onSuccess(Object responseObj) {
                    allUserBean = (AllUserBean) responseObj;
                }

                @Override
                public void onError(Object responseObj) {
                    Log.d("chen","测试失败！！！");
                }
            });
        } catch (IOException e) {
            e.printStackTrace();
        }

        //网络访问测试
//        Handler myHandler = new Handler(){
//            @Override
//            public void handleMessage(Message msg) {
//                switch (msg.what){
//                    case 0:
//                        textViewViewMsg.setText((String) msg.obj);
//                        break;
//                    default:
//                        break;
//                }
//            }
//        };
//
//        buttonGetMsg.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                new Thread(){
//                    @Override
//                    public void run() {
//                        super.run();
//                        String temp = OkHttpMethods.httpGet("http://192.168.124.7:8080/users/all");
//                        Message msg=Message.obtain();
//
//                        msg.what=0;
//
//                        msg.obj=temp;
//
//                        myHandler.sendMessage(msg);
//                    }
//                }.start();
//            }
//        });
    }

    @Override
    public void RefreshData() {

        super.RefreshData();

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "这是第一部分", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });


        buttonGetMsg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                textViewViewMsg.setText(allUserBean.getData().toString());
            }
        });


    }


}
