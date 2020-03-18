package com.workpath.bookstreamapp.activities;

import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;

import com.bumptech.glide.Glide;
import com.jph.takephoto.app.TakePhoto;
import com.jph.takephoto.app.TakePhotoActivity;
import com.jph.takephoto.compress.CompressConfig;
import com.jph.takephoto.model.TResult;
import com.workpath.bookstreamapp.R;

import java.io.File;

//继承自拍照的activity
public abstract class BaseTakePhotoActivity extends TakePhotoActivity {
    public static final String TAG=BaseTakePhotoActivity.class.getSimpleName();
    private File file;
    private PopupWindow popupWindow;
    private ImageView imageView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(initView(savedInstanceState));
        initActionBar();
        initDate();
    }

    protected abstract int initView(Bundle savedInstanceState);

    protected abstract void initActionBar();

    protected abstract void initDate();
    //打开相机
    public void openCamera() {
        file = new File(Environment.getExternalStorageDirectory(), "/temp/" + System.currentTimeMillis() + ".jpg");
        if (!file.getParentFile().exists()) file.getParentFile().mkdirs();
        Uri imageUri = Uri.fromFile(file);
        configCompress(getTakePhoto(), imageUri);

    }
    //相册选取
    public void openGallery() {
        file = new File(Environment.getExternalStorageDirectory(), "/temp/" + System.currentTimeMillis() + ".jpg");
        if (!file.getParentFile().exists()) file.getParentFile().mkdirs();
        Uri imageUri = Uri.fromFile(file);
        //configCompress(getTakePhoto(), imageUri);
        CompressConfig compressConfig = new CompressConfig.Builder().setMaxSize(50 * 1024).setMaxPixel(800).create();
        getTakePhoto().onEnableCompress(compressConfig, false);
        getTakePhoto().onPickFromGallery();


    }
    //显示选择方式的弹窗
    public void showPopWindow() {
        Log.e(TAG, "showPopWindow:------------- "+ 1);
        final View popView = LayoutInflater.from(this).inflate(
                R.layout.activity_select_pic, null);
        popView.setFocusable(true);
        popView.setFocusableInTouchMode(true);
        Button btn1 = (Button)popView.findViewById(R.id.btn_select_pic);
        Button btn2 = (Button)popView.findViewById(R.id.btn_take_photo);
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openGallery();
                popupWindow.dismiss();
            }
        });
        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openCamera();
                popupWindow.dismiss();
            }
        });

        popupWindow = new PopupWindow(popView,
                LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT);
        popupWindow.setFocusable(true);
        popupWindow.showAtLocation(popView, Gravity.BOTTOM | Gravity.LEFT, 0,
                0);
        Log.e(TAG, "showPopWindow:------------- "+ popupWindow+popView);

    }

    public void configCompress(TakePhoto takePhoto, Uri imageUri) {
        CompressConfig compressConfig = new CompressConfig.Builder().setMaxSize(50 * 1024).setMaxPixel(800).create();
        takePhoto.onEnableCompress(compressConfig, false);
        takePhoto.onPickFromCapture(imageUri);
    }

    @Override
    public void takeSuccess(TResult result) {
        super.takeSuccess(result);
        file = new File(result.getImage().getCompressPath());
        Glide.with(this).load(file).into(imageView);

    }
    //dang当控件点击的时候
    public void  onImgeViewClicked(ImageView imageView){
        this.imageView=imageView;
        showPopWindow();

    }
    @Override
    public void takeCancel() {
        super.takeCancel();
    }

    @Override
    public void takeFail(TResult result, String msg) {
        super.takeFail(result, msg);
    }

}

