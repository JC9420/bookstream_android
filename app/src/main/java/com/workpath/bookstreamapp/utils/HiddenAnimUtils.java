package com.workpath.bookstreamapp.utils;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ValueAnimator;
import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.LinearInterpolator;
import android.view.animation.RotateAnimation;

public class HiddenAnimUtils {

    /**
     * 用法:
     * int tag= HiddenAnimUtils.newInstance(getContext(),hidden_coprhsv,iv_comprehensive,172).toggle();
     * 单例模式
     * HiddenAnimUtils.newInstance()调用这个类
     * .toggle()调用隐藏布局的方法
     * 会返回一个值 0 表示执行隐藏过程 1 表示显示过程
     */

    private int mHeight;//伸展高度

    private View hideView,down;//需要展开隐藏的布局，开关控件

    private RotateAnimation animation;//旋转动画

    private int mTime;//动画时间

    private Context context;

    public static HiddenAnimUtils newInstance(Context context, View hideView , View down) {

        return new HiddenAnimUtils(context,hideView,down);
    }

    public static HiddenAnimUtils newInstance(Context context, int height) {

        return new HiddenAnimUtils(context,height);
    }

    /**
     * 构造器(可根据自己需要修改传参)
     * @param context 上下文
     * @param hideView 需要隐藏或显示的布局view
     * @param down 按钮开关的view
     * @param height 布局展开的高度(根据实际需要传)
     */

    public static HiddenAnimUtils newInstance(Context context, View hideView, View down, int height, int time){
        return new HiddenAnimUtils(context,hideView,down,height,time);
    }

    public static HiddenAnimUtils newInstance(Context context, View hideView , int mHeight, int mTime) {
        return new HiddenAnimUtils(context, hideView , mHeight, mTime);
    }

    private HiddenAnimUtils(Context context, View hideView, View down, int height, int mTime){
        this.hideView = hideView;
        this.down = down;
        this.context=context;
        /**
         * 此处是将dp转化成px
         */
        float mDensity = context.getResources().getDisplayMetrics().density;
        this.mHeight = (int) (mDensity * height + 0.5);//伸展高度
        this.mTime=mTime;
        this.context=context;
    }

    private HiddenAnimUtils(Context context, View hideView , int mHeight, int mTime){
        this.hideView = hideView;
        this.down = null;
        this.mHeight = mHeight;
        this.mTime=mTime;
        this.context=context;
    }

    private HiddenAnimUtils(Context context, View hideView, View down){
        this.context=context;
        this.down=down;
        this.hideView=hideView;
    }

    /**
     *
     * @param context 上下文
     * @param height 这个量的单位是dp
     */
    private HiddenAnimUtils(Context context, int height){

        float mDensity = context.getResources().getDisplayMetrics().density;
        this.mHeight = (int) (mDensity * height + 0.5);//伸展高度
    }

    /**
     * 开关
     */
    public int toggle(){
        if(down!=null){
            startAnimation();
        }
        if (View.VISIBLE == hideView.getVisibility()) {
            closeAnimate(hideView);//布局隐藏
            return 0;
        } else {
            openAnimate(hideView);//布局铺开
            return 1;
        }
    }

    /**
     * 这个函数是给控制按钮设置动画的
     * 开关旋转动画
     */
    public void startAnimation() {
        if(down!=null){
            if (View.VISIBLE == hideView.getVisibility()) {
                animation = new RotateAnimation(180, 0, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
            } else {
                animation = new RotateAnimation(0, 180, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
            }
            animation.setDuration(mTime);//设置动画持续时间
            animation.setInterpolator(new LinearInterpolator());
            animation.setRepeatMode(Animation.REVERSE);//设置反方向执行
            animation.setFillAfter(true);//动画执行完后是否停留在执行完的状态
            down.startAnimation(animation);
        }
    }

    private void startAnimationLone() {
        if(down!=null){
            if (View.VISIBLE == hideView.getVisibility()) {
                animation = new RotateAnimation(180, 0, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
            } else {
                animation = new RotateAnimation(0, 180, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
            }
            animation.setDuration(mTime);//设置动画持续时间
            animation.setInterpolator(new LinearInterpolator());
            animation.setRepeatMode(Animation.REVERSE);//设置反方向执行
            animation.setFillAfter(true);//动画执行完后是否停留在执行完的状态
            down.startAnimation(animation);
        }
    }

    /**
     * 打开隐藏布局
     * @param v
     */
    public void openAnimate(View v) {
        v.setVisibility(View.VISIBLE);
        ValueAnimator animator = createDropAnimator(v, 0, mHeight);
        animator.start();
    }

    /**
     * 关闭隐藏布局
     * @param view
     */
    public void closeAnimate(final View view) {
        int origHeight = view.getHeight();
        ValueAnimator animator = createDropAnimator(view, origHeight, 0);
        animator.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                view.setVisibility(View.GONE);
            }
        });
        animator.start();
    }

    /**
     * 创建动画
     * @param v
     * @param start
     * @param end
     * @return
     */
    public ValueAnimator createDropAnimator(final View v, int start, int end) {
        ValueAnimator animator = ValueAnimator.ofInt(start, end);
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {

            @Override
            public void onAnimationUpdate(ValueAnimator arg0) {
                int value = (int) arg0.getAnimatedValue();
                ViewGroup.LayoutParams layoutParams = v.getLayoutParams();
                layoutParams.height = value;
                v.setLayoutParams(layoutParams);
            }
        });
        return animator;
    }
}