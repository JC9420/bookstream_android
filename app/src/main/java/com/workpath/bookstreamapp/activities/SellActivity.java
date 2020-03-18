package com.workpath.bookstreamapp.activities;

import com.workpath.bookstreamapp.R;
import com.workpath.bookstreamapp.myapp.MyActivity;

/**
 * Created by phm on 2020/3/18.
 */

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import com.bumptech.glide.Glide;
import com.jph.takephoto.model.TResult;
import com.workpath.bookstreamapp.utils.StatusBarUtil;

import java.io.File;
import android.widget.Toast;

public class SellActivity extends BaseTakePhotoActivity {
    private ImageButton image_add;
    private Button button_sell;

    @Override
    protected int initView(Bundle savedInstanceState) {
        return R.layout.main_activity_sell;
    }

    @Override
    protected void initActionBar() {
        StatusBarUtil.transparencyBar(this);
        image_add = (ImageButton) findViewById(R.id.btn_add_image);
        button_sell=(Button)findViewById(R.id.btn_release);
        image_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //当控件点击的时候将控件传入即可
                //就这一行即可实现拍照功能 是不是很吊的样子
                onImgeViewClicked(image_add);

            }


        });
        button_sell.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = null;

                switch (v.getId()) {
                    case R.id.btn_release:
                        Toast.makeText(v.getContext(), "您已成功申请交易", Toast.LENGTH_SHORT).show();
                        intent = new Intent(SellActivity.this,QR_code_Activity.class);

                        break;

                }
                startActivity(intent);
            }

        });

    }

    @Override
    protected void initDate() {

    }

}

