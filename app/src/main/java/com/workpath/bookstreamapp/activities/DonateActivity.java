package com.workpath.bookstreamapp.activities;

import android.app.AlertDialog;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.workpath.bookstreamapp.MainActivity;
import com.workpath.bookstreamapp.R;
import com.workpath.bookstreamapp.myapp.MyActivity;

/**
 * Created by JayChen on 2020/3/18.
 */

public class DonateActivity extends MyActivity {
    private Button mBtnSub;
    @Override
    protected int getContentLayoutId() {
        return R.layout.main_activity_donate;
    }

    @Override
    protected void initWidget() {
        mBtnSub = (Button) findViewById(R.id.btn_submit);
        mBtnSub.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(DonateActivity.this,"已修改,感谢您的捐赠！",Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    protected void initData() {

    }
}
