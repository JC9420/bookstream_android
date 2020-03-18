package com.workpath.bookstreamapp.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;

import com.workpath.bookstreamapp.R;
import com.workpath.bookstreamapp.adapters.BuyGoodsAdapter;
import com.workpath.bookstreamapp.moudles.goods.BuyCoogsMoudle;
import com.workpath.bookstreamapp.myapp.MyActivity;
import com.workpath.bookstreamapp.myapp.MyAdapter;

import java.util.ArrayList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by JayChen on 2020/3/18.
 */

public class BuyActivity extends MyActivity implements View.OnClickListener{
    private ImageView mImageView;//返回键
    private RecyclerView mRecyclerView;//货物列表
    private List<BuyCoogsMoudle> dataList;

    @Override
    protected int getContentLayoutId() {
        return R.layout.main_activity_buy;
    }

    @Override
    protected boolean initArgs(Bundle bundle) {
        dataList = new ArrayList<>();
        dataList.add(new BuyCoogsMoudle(R.mipmap.book1,"书籍名称1","科学出版社",6,17.5,0));
        dataList.add(new BuyCoogsMoudle(R.mipmap.book2,"书籍名称2","科学出版社",3,6.6,1));
        dataList.add(new BuyCoogsMoudle(R.mipmap.book3,"书籍名称3","科学出版社",1,8.4,1));
        dataList.add(new BuyCoogsMoudle(R.mipmap.book4,"书籍名称4","科学出版社",2,5.5,0));
        dataList.add(new BuyCoogsMoudle(R.mipmap.book5,"书籍名称5","科学出版社",4,12.5,0));
        dataList.add(new BuyCoogsMoudle(R.mipmap.book6,"书籍名称6","科学出版社",1,10.5,1));
        dataList.add(new BuyCoogsMoudle(R.mipmap.tuijian1,"书籍名称7","科学出版社",1,9.5,0));
        dataList.add(new BuyCoogsMoudle(R.mipmap.tuijian2,"书籍名称8","科学出版社",3,7.5,1));
        dataList.add(new BuyCoogsMoudle(R.mipmap.tuijian3,"书籍名称9","科学出版社",5,14.5,0));

        return super.initArgs(bundle);
    }

    @Override
    protected void initWidget() {
        //透明状态栏
        transparencyBar();
        mImageView = (ImageView) findViewById(R.id.cv_back);
        mImageView.setOnClickListener(this);
        mRecyclerView = (RecyclerView) findViewById(R.id.rec_buy_list);
        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(getApplicationContext());
        mRecyclerView.setLayoutManager(linearLayoutManager);

    }

    @Override
    protected void initData() {

        BuyGoodsAdapter buyGoodsAdapter = new BuyGoodsAdapter(getApplicationContext(), dataList, new MyAdapter.AdapterListener<BuyCoogsMoudle>() {


            @Override
            public void onItemClick(MyAdapter.MyViewHolder holder, BuyCoogsMoudle data) {
                Intent intent = new Intent(BuyActivity.this,BuyDetailActivity.class);
                Bundle bundle = new Bundle();
                bundle.putSerializable("msg", data);
                intent.putExtras(bundle);
                startActivity(intent);
            }

            @Override
            public void onItemLongClick(MyAdapter.MyViewHolder holder, BuyCoogsMoudle data) {

            }

            @Override
            public Boolean setAddActionContinue() {
                return null;
            }

            @Override
            public void updataUI(Object object) {

            }
        });
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setItemViewCacheSize(20);
        mRecyclerView.setDrawingCacheEnabled(true);
        mRecyclerView.setDrawingCacheQuality(View.DRAWING_CACHE_QUALITY_HIGH);
        mRecyclerView.setAdapter(buyGoodsAdapter);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.cv_back:
                finish();
                break;
            default:
                break;
        }
    }
}
