package com.workpath.bookstreamapp;

import android.view.View;

import com.workpath.bookstreamapp.activities.ItemView;
import com.workpath.bookstreamapp.myapp.MyFragment;
import com.workpath.bookstreamapp.utils.GlideImageLoader;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.Transformer;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by JayChen on 2020/2/3.
 */


public class TwoFragment extends MyFragment{

    private Banner mBanner;
    private List<Integer> images;

    private ItemView mItemView1;
    private ItemView mItemView2;
    private ItemView mItemView3;

    @Override
    public void RefreshData() {
        super.RefreshData();
    }

    @Override
    protected int getContentLayoutId() {
        return R.layout.two_fragment_layout;
    }

    @Override
    protected void initWidget(View view) {

        mBanner = (Banner) view.findViewById(R.id.banner_adv);
        images = new ArrayList<>();
        images.add(R.mipmap.tuijian1);
        images.add(R.mipmap.tuijian2);
        images.add(R.mipmap.tuijian3);

        mItemView1 = (ItemView) view.findViewById(R.id.shijiantixing);
        mItemView1.setItemClickListener(new ItemView.itemClickListener() {
            @Override
            public void itemClick(String text) {

            }
        });

        mItemView2 = (ItemView) view.findViewById(R.id.dongtai);
        mItemView2.setItemClickListener(new ItemView.itemClickListener() {
            @Override
            public void itemClick(String text) {

            }
        });

        mItemView3 = (ItemView) view.findViewById(R.id.news);
        mItemView3.setItemClickListener(new ItemView.itemClickListener() {
            @Override
            public void itemClick(String text) {

            }
        });


    }

    @Override
    protected void initData() {

        mBanner.setBannerStyle(BannerConfig.NUM_INDICATOR)
                .setImageLoader(new GlideImageLoader())
                .setImages(images)
                .setBannerAnimation(Transformer.DepthPage)
                .isAutoPlay(true)
                .setDelayTime(3000)
                .setIndicatorGravity(BannerConfig.CENTER)
                .start();
        mBanner.start();//开始渲染
    }
}
