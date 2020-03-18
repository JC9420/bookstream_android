package com.workpath.bookstreamapp.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.AppCompatImageView;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.workpath.bookstreamapp.R;
import com.workpath.bookstreamapp.moudles.goods.BuyCoogsMoudle;
import com.workpath.bookstreamapp.myapp.MyActivity;

/**
 * Created by JayChen on 2020/3/18.
 */

public class BuyDetailActivity extends MyActivity {
    private BuyCoogsMoudle data;
    private ImageView mImageView ,back;
    private TextView bookName,bookFrom,bookOrder,bookPrice;
    private Button bookAlong,buy;
    @Override
    protected int getContentLayoutId() {
        return R.layout.main_activity_buy_detail;
    }

    @Override
    protected boolean initArgs(Bundle bundle) {
        Intent intent = this.getIntent();
        data=(BuyCoogsMoudle)intent.getSerializableExtra("msg");
        return super.initArgs(bundle);
    }

    @Override
    protected void initWidget() {
        back = (ImageView) findViewById(R.id.img_back);
        mImageView = (ImageView) findViewById(R.id.iv_show_book);
        bookName = (TextView) findViewById(R.id.tv_book_title);
        bookFrom = (TextView) findViewById(R.id.tv_book_from);
        bookOrder = (TextView) findViewById(R.id.tv_book_order);
        bookPrice = (TextView) findViewById(R.id.tv_book_price);
        bookAlong = (Button) findViewById(R.id.btn_book_along);
        buy = (Button) findViewById(R.id.btn_buy);

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    @Override
    protected void initData() {

        mImageView.setBackgroundResource(data.getImageId());
        bookName.setText(data.getBookName());
        bookFrom.setText(data.getFromName());
        bookOrder.setText("第"+data.getOrder()+"版");
        bookPrice.setText(""+data.getPrice());
        if(data.getWhoGet()==0) bookAlong.setText("平台");
        else bookAlong.setText("私人");
        if(data.getWhoGet()!=0) buy.setText("获取卖家");
        else buy.setText("我想买");
    }
}
