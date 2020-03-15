package com.workpath.bookstreamapp;

import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.CenterCrop;
import com.workpath.bookstreamapp.activities.ItemView;
import com.workpath.bookstreamapp.myapp.MyFragment;

import jp.wasabeef.glide.transformations.BlurTransformation;
import jp.wasabeef.glide.transformations.CropCircleTransformation;

/**
 * Created by JayChen on 2020/2/3.
 */


public class ThreeFragment extends MyFragment{

    private ImageView mHBack;
    private ImageView mHHead;
    private ImageView mUserLine;
    private TextView mUserName;
    private TextView mUserVal;

    private ItemView mNickName;
    private ItemView mSex;

    private ItemView mDindan;
    private ItemView mXiaoxi;
    private ItemView mZuji;

    private ItemView mSignName;
    private ItemView mPass;
    private ItemView mPhone;
    private ItemView mAbout;

    @Override
    public void RefreshData() {
        super.RefreshData();
    }

    @Override
    protected int getContentLayoutId() {
        return R.layout.three_fragment_layout;
    }

    @Override
    protected void initWidget(View view) {

        //顶部头像控件
        mHBack = (ImageView) view.findViewById(R.id.h_back);
        mHHead = (ImageView) view.findViewById(R.id.h_head);
        mUserLine = (ImageView) view.findViewById(R.id.user_line);
        mUserName = (TextView) view.findViewById(R.id.user_name);
        mUserVal = (TextView) view.findViewById(R.id.user_val);
        //下面item控件
        mNickName = (ItemView) view.findViewById(R.id.nickName);
        mSex = (ItemView) view.findViewById(R.id.sex);
        mDindan = (ItemView) view.findViewById(R.id.dingdan);
        mXiaoxi = (ItemView) view.findViewById(R.id.xiaoxi);
        mZuji = (ItemView) view.findViewById(R.id.zuji);
        mSignName = (ItemView) view.findViewById(R.id.signName);
        mPass = (ItemView) view.findViewById(R.id.pass);
        mPhone = (ItemView) view.findViewById(R.id.phone);
        mAbout = (ItemView) view.findViewById(R.id.about);

    }


    @Override
    protected void initData() {

        //设置背景磨砂效果
        Glide.with(this).load(R.drawable.head)
                .bitmapTransform(new BlurTransformation(getContext(), 25), new CenterCrop(getContext()))
                .into(mHBack);
        //设置圆形图像
        Glide.with(this).load(R.drawable.head)
                .bitmapTransform(new CropCircleTransformation(getContext()))
                .into(mHHead);

        //设置用户名整个item的点击事件
        mNickName.setItemClickListener(new ItemView.itemClickListener() {
            @Override
            public void itemClick(String text) {
                Toast.makeText(getContext(), text, Toast.LENGTH_SHORT).show();
            }
        });
        mSex.setItemClickListener(new ItemView.itemClickListener() {
            @Override
            public void itemClick(String text) {
                Toast.makeText(getContext(),text,Toast.LENGTH_SHORT).show();
            }
        });
        mDindan.setItemClickListener(new ItemView.itemClickListener() {
            @Override
            public void itemClick(String text) {

            }
        });
        mXiaoxi.setItemClickListener(new ItemView.itemClickListener() {
            @Override
            public void itemClick(String text) {

            }
        });
        mZuji.setItemClickListener(new ItemView.itemClickListener() {
            @Override
            public void itemClick(String text) {

            }
        });


        mSignName.setItemClickListener(new ItemView.itemClickListener() {
            @Override
            public void itemClick(String text) {
                Toast.makeText(getContext(),text,Toast.LENGTH_SHORT).show();
            }
        });
        mPass.setItemClickListener(new ItemView.itemClickListener() {
            @Override
            public void itemClick(String text) {
                Toast.makeText(getContext(),"已修改",Toast.LENGTH_SHORT).show();
            }
        });
        mPhone.setItemClickListener(new ItemView.itemClickListener() {
            @Override
            public void itemClick(String text) {
                Toast.makeText(getContext(),"已修改",Toast.LENGTH_SHORT).show();
            }
        });
        mAbout.setItemClickListener(new ItemView.itemClickListener() {
            @Override
            public void itemClick(String text) {
                Toast.makeText(getContext(),"已修改",Toast.LENGTH_SHORT).show();
            }
        });
    }



}
