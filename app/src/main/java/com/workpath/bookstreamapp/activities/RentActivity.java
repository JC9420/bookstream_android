package com.workpath.bookstreamapp.activities;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.workpath.bookstreamapp.R;
import com.workpath.bookstreamapp.adapters.RentGoodsAdapter;
import com.workpath.bookstreamapp.moudles.goods.BuyCoogsMoudle;
import com.workpath.bookstreamapp.myapp.MyActivity;
import com.workpath.bookstreamapp.myapp.MyAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by JayChen on 2020/3/18.
 */

public class RentActivity extends MyActivity implements View.OnClickListener{

    private ImageView mImageView;//返回键
    private RecyclerView mRecyclerView;//货物列表
    private List<BuyCoogsMoudle> dataList;
    private Button rent;

    @Override
    protected int getContentLayoutId() {
        return R.layout.main_activity_rent;
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

        rent = (Button) findViewById(R.id.btn_book_rent);

    }

    @Override
    protected void initData() {

        RentGoodsAdapter rentGoodsAdapter = new RentGoodsAdapter(RentActivity.this, dataList, new MyAdapter.AdapterListener<BuyCoogsMoudle>() {


            @Override
            public void onItemClick(MyAdapter.MyViewHolder holder, BuyCoogsMoudle data) {

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
        mRecyclerView.setAdapter(rentGoodsAdapter);

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
