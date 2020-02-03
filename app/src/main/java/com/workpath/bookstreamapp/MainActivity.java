package com.workpath.bookstreamapp;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.FrameLayout;

import com.ittianyu.bottomnavigationviewex.BottomNavigationViewEx;
import com.workpath.bookstreamapp.myapp.MyActivity;
import com.workpath.bookstreamapp.utils.NavHelper;

public class MainActivity extends MyActivity implements NavHelper.OnTabChangedListener<Integer>,NavHelper.OnTabReselectListener<Integer>,BottomNavigationViewEx.OnNavigationItemSelectedListener {

    private BottomNavigationViewEx bottomNavigationViewEx;
    private NavHelper<Integer> navHelper;
    private FrameLayout container;

    @Override
    protected int getContentLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected void initWidget() {
        transparencyBar();//设置状态栏的颜色为透明
        bottomNavigationViewEx= (BottomNavigationViewEx) findViewById(R.id.btnv);
        bottomNavigationViewEx.setOnNavigationItemSelectedListener(this);
        container= (FrameLayout) findViewById(R.id.lay_container);
        //设置动画效果
        bottomNavigationViewEx.enableAnimation(true);
        bottomNavigationViewEx.enableShiftingMode(false);
        bottomNavigationViewEx.enableItemShiftingMode(true);
        //设置图标和文字的大小
        bottomNavigationViewEx.setIconSize(24,24);
        bottomNavigationViewEx.setTextSize(12);
        //获取帮助类
        navHelper = new NavHelper<>(MainActivity.this,
                R.id.lay_container,
                getSupportFragmentManager(),
                MainActivity.this,
                MainActivity.this,
                null);
        //添加导航分页
        navHelper.add(R.id.navigation_home,new NavHelper.Tab<Integer>(OneFragment.class,R.string.btnv_one))
                .add(R.id.navigation_course,new NavHelper.Tab<Integer>(TwoFragment.class,R.string.btnv_two))
                .add(R.id.navigation_me,new NavHelper.Tab<Integer>(ThreeFragment.class,R.string.btnv_three));

    }

    @Override
    protected void initData() {
        Menu menu = bottomNavigationViewEx.getMenu();
        menu.performIdentifierAction(R.id.navigation_home,0);
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    /**
     * NavHelper类继承的的抽象方法
     * @param newTab
     * @param oldTab
     */
    @Override
    public void onTabChanged(NavHelper.Tab<Integer> newTab, NavHelper.Tab<Integer> oldTab) {
        if(newTab.getFragment() instanceof OneFragment){
            changeNavigationColor(0);
        }else if(newTab.getFragment() instanceof TwoFragment){
            changeNavigationColor(1);
        }else{
            changeNavigationColor(2);
        }
    }

    /**
     * NavHelper类继承的的抽象方法
     * @param tab
     */
    @Override
    public void notifyTabReselect(NavHelper.Tab<Integer> tab) {
        Log.d("notifyTabReselect",tab.getFragment().toString());
        if(tab.getFragment() instanceof OneFragment){

            OneFragment oneFragment = (OneFragment)tab.getFragment();
            oneFragment.RefreshData();

        }else if(tab.getFragment() instanceof TwoFragment){

            TwoFragment twoFragment = (TwoFragment)tab.getFragment();
            twoFragment.RefreshData();

        }else{
            ThreeFragment threeFragment = (ThreeFragment)tab.getFragment();
            threeFragment.RefreshData();
        }

    }


    private void changeNavigationColor(int position){
        switch (position){
            case 0:
                bottomNavigationViewEx.setIconTintList(0, ColorStateList.valueOf(Color.parseColor("#3F51B5")));
                bottomNavigationViewEx.setIconTintList(1, ColorStateList.valueOf(Color.parseColor("#000000")));
                bottomNavigationViewEx.setIconTintList(2, ColorStateList.valueOf(Color.parseColor("#000000")));
                // R.color.grey_300 000000
                bottomNavigationViewEx.setTextTintList(0, ColorStateList.valueOf(Color.parseColor("#3F51B5")));
                bottomNavigationViewEx.setTextTintList(1, ColorStateList.valueOf(Color.parseColor("#000000")));
                bottomNavigationViewEx.setTextTintList(2, ColorStateList.valueOf(Color.parseColor("#000000")));
                break;
            case 1:
                bottomNavigationViewEx.setIconTintList(0, ColorStateList.valueOf(Color.parseColor("#000000")));
                bottomNavigationViewEx.setIconTintList(1, ColorStateList.valueOf(Color.parseColor("#3F51B5")));
                bottomNavigationViewEx.setIconTintList(2, ColorStateList.valueOf(Color.parseColor("#000000")));

                bottomNavigationViewEx.setTextTintList(0, ColorStateList.valueOf(Color.parseColor("#000000")));
                bottomNavigationViewEx.setTextTintList(1, ColorStateList.valueOf(Color.parseColor("#3F51B5")));
                bottomNavigationViewEx.setTextTintList(2, ColorStateList.valueOf(Color.parseColor("#000000")));
                break;
            case 2:
                bottomNavigationViewEx.setIconTintList(0, ColorStateList.valueOf(Color.parseColor("#000000")));
                bottomNavigationViewEx.setIconTintList(1, ColorStateList.valueOf(Color.parseColor("#000000")));
                bottomNavigationViewEx.setIconTintList(2, ColorStateList.valueOf(Color.parseColor("#3F51B5")));


                bottomNavigationViewEx.setTextTintList(0, ColorStateList.valueOf(Color.parseColor("#000000")));
                bottomNavigationViewEx.setTextTintList(1, ColorStateList.valueOf(Color.parseColor("#000000")));
                bottomNavigationViewEx.setTextTintList(2, ColorStateList.valueOf(Color.parseColor("#3F51B5")));
                break;
            case 3:
                bottomNavigationViewEx.setIconTintList(0, ColorStateList.valueOf(Color.parseColor("#000000")));
                bottomNavigationViewEx.setIconTintList(1, ColorStateList.valueOf(Color.parseColor("#000000")));
                bottomNavigationViewEx.setIconTintList(2, ColorStateList.valueOf(Color.parseColor("#000000")));


                bottomNavigationViewEx.setTextTintList(0, ColorStateList.valueOf(Color.parseColor("#757575")));
                bottomNavigationViewEx.setTextTintList(1, ColorStateList.valueOf(Color.parseColor("#757575")));
                bottomNavigationViewEx.setTextTintList(2, ColorStateList.valueOf(Color.parseColor("#757575")));
                break;

        }
    }


    /**
     * BottomNavigationViewEx.OnNavigationItemSelectedListener
     * @param item
     * @return
     */
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        return navHelper.performClickMenu(item.getItemId());
    }
}
