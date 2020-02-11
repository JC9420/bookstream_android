package com.workpath.bookstreamapp.myapp;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.ColorRes;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;

import com.workpath.bookstreamapp.utils.StatusBarUtil;

import java.io.IOException;

/**
 * Created by JayChen on 2020/2/3.
 */

public abstract class MyFragment extends Fragment {

    private final String TAG = "MyFragment";
    protected View mRoot;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        //初始化参数
        initArgs(getArguments());
    }

    /**初始化相关参数，子类可以重写**/
    protected void initArgs(Bundle arguments) {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        if (mRoot == null) {
            int layoutId = getContentLayoutId();//获取布局Id进行绑定
            View root = inflater.inflate(layoutId, container, false);//转化成View
            if (root != null) {
                Log.d(TAG, "onCreateView" + "布局转化成功！！！");
            }
            try {
                initWidget(root);
            } catch (IOException e) {
                e.printStackTrace();
            }
            initData();
            mRoot = root;

        } else {
            if (mRoot.getParent() != null) {
                // 把当前Root从其父控件中移除
                ((ViewGroup) mRoot.getParent()).removeView(mRoot);
            }
        }
        return mRoot;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        /**调整状态栏为亮模式，这样状态栏的文字颜色就为深模式了。**/
        StatusBarUtil.statusBarLightMode(getActivity());
        // 当View创建完成后初始化数据
        RefreshData();
    }



    /**获取布局Id进行绑定**/
    protected abstract int getContentLayoutId();

    /**初始化控件**/
    protected abstract void initWidget(View view) throws IOException;

    /**初始化数据**/
    protected abstract void initData();


    /**View可见后刷新数据**/
    public void RefreshData(){

    }

    /**
     * 改变状态栏颜色
     * @param color
     */
    public void changeStatusBarColor(@ColorRes int color) {
        StatusBarUtil.setStatusBarColor(getActivity(), color);
    }


    /**
     * 隐藏状态栏
     */
    public void hiddenStatusBar() {
        getActivity().getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
    }

    /**
     * 设置状态栏为透明
     */
    public void transparencyBar(){
        StatusBarUtil.transparencyBar(getActivity());
    }


}

