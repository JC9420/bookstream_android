package com.workpath.bookstreamapp;

import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.view.View;

import com.workpath.bookstreamapp.myapp.MyFragment;

/**
 * Created by JayChen on 2020/2/3.
 */


public class ThreeFragment extends MyFragment{

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

        FloatingActionButton fab = (FloatingActionButton) view.findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "这是第三部分", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

    }

    @Override
    protected void initData() {

    }
}
