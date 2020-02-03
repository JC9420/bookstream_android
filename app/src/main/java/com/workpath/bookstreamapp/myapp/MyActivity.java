package com.workpath.bookstreamapp.myapp;

import android.os.Bundle;
import android.support.annotation.ColorRes;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.WindowManager;

import com.workpath.bookstreamapp.utils.StatusBarUtil;

/**
 * Created by JayChen on 2020/2/3.
 */

public abstract class MyActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //初始化界面未开始调用之前使用的窗口
        initWindow();

        if(initArgs(getIntent().getExtras())){//参数初始化成功
            int layoutId=getContentLayoutId();
            setContentView(layoutId);/**绑定布局Id**/
            initWidget();/**初始化控件**/
            initData();/**初始化数据**/
            StatusBarUtil.statusBarLightMode(this);/**调整状态栏为亮模式，这样状态栏的文字颜色就为深模式了。**/
        }else{
            finish();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    /**
     * 初始化窗口，为非必需实现的方法
     */
    protected void initWindow(){}


    /**
     * 初始化参数，若无初始化参数则默认返回真
     * 此为非必需方法
     * @return 初始化成功则返回真，否则返回假的
     */
    protected boolean initArgs(Bundle bundle){

        return true;
    }

    /**
     * 绑定布局Id
     * @return 返回值为Id
     */
    protected abstract int getContentLayoutId();

    /**
     * 初始化控件
     */
    protected abstract void initWidget();

    /**
     * 初始化数据
     */
    protected abstract void initData();



    //一些控制状态栏的方法
    /**
     * 改变状态栏颜色
     *
     * @param color
     */
    public void changeStatusBarColor(@ColorRes int color) {
        StatusBarUtil.setStatusBarColor(this, color);
    }


    /**
     * 隐藏状态栏
     */
    public void hiddenStatusBar() {
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
    }


    /**
     * 设置状态栏为透明
     */
    public void transparencyBar(){
        StatusBarUtil.transparencyBar(this);
    }


    @Override
    public boolean onSupportNavigateUp() {
        //当点击界面导航返回时候回调的方法
        finish();
        return super.onSupportNavigateUp();
    }

    //手机的返回键
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }

}
