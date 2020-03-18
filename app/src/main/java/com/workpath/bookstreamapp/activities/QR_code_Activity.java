package com.workpath.bookstreamapp.activities;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.workpath.bookstreamapp.MainActivity;
import com.workpath.bookstreamapp.R;

import cn.bingoogolapple.qrcode.zxing.QRCodeEncoder;

/**
 * Created by phm on 2020/3/18.
 */

public class QR_code_Activity extends AppCompatActivity {

    private ImageView img;
    private Button btn_comeback;

    Handler handler = new Handler(Looper.getMainLooper()){
        @Override
        public void handleMessage(Message msg) {
            Bitmap v = (Bitmap) msg.obj; //强转
            // 将集成后的二维码设置到ImageView上进行展示
            img.setImageBitmap(v);
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.qr_code_activity);
        img = (ImageView) findViewById(R.id.myimg);
        btn_comeback=(Button)findViewById(R.id.button_comeback);
        setlistener();

        // 集成的代码必须在子线程里
        new Thread(new Runnable() {
            @Override
            public void run() {
                // 第一个也可以是图片的网址展示图片，也可以是网址进行跳转，如果是图片的话size大一些就可以了
                Bitmap bitmap = QRCodeEncoder.syncEncodeQRCode("这里写要Toast的", 50);
                Message message = handler.obtainMessage();

                message.obj = bitmap;
                handler.sendMessage(message);// 发送Handler
            }


        }).start();



    }

    public void setlistener(){
        ButtonClickListener listener =new ButtonClickListener();
        btn_comeback.setOnClickListener(listener);


    }
    class ButtonClickListener implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            Intent intent = null;
            switch (v.getId()){
                case R.id.button_comeback:
                    intent = new Intent(QR_code_Activity.this,MainActivity.class);
                    break;



            }
            startActivity(intent);
        }
    }




}

