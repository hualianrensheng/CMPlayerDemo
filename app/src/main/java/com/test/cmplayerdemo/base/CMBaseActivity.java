package com.test.cmplayerdemo.base;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Window;

import com.bumptech.glide.Glide;
import com.bumptech.glide.util.Util;
import com.jaeger.library.StatusBarUtil;
import com.test.cmplayerdemo.R;
import com.test.cmplayerdemo.utils.AppActivityManager;
import com.trello.rxlifecycle.components.support.RxAppCompatActivity;

import butterknife.ButterKnife;

public abstract class CMBaseActivity extends RxAppCompatActivity {

    private static int mColor = Color.YELLOW;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        //设置沉浸式状态栏
        StatusBarUtil.setColor(this,mColor);
        //保持竖屏
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        //------777
        String add = "999999";


        AppActivityManager.getInstance().addActivity(this);

        initLayout();
        ButterKnife.bind(this) ;
        initView();
    }


    /**
     * @info 初始化布局
     */
    protected abstract void initLayout();

    /**
     * @info 初始化View
     */
    protected abstract void initView();


    protected abstract void showToast(String msg);

    @Override
    public void startActivity(Intent intent) {
        super.startActivity(intent);
        overridePendingTransition(R.anim.anim_slide_in_right,R.anim.anim_slide_out_left);
    }

    @Override
    public void startActivityForResult(Intent intent, int requestCode) {
        super.startActivityForResult(intent, requestCode);
        overridePendingTransition(R.anim.anim_slide_in_right,R.anim.anim_slide_out_left);
    }

    protected void openActivity(Class clazz){
        Intent intent = new Intent(this,clazz);
        startActivity(intent);
    }

    @Override
    public void finish() {
        super.finish();
        overridePendingTransition(R.anim.anim_slide_in_left,R.anim.anim_slide_out_right);
    }

    @Override
    protected void onDestroy() {
        AppActivityManager.getInstance().removeActivity(this);
        if(Util.isOnMainThread()) {
            Glide.get(this).clearMemory();
        }
        super.onDestroy();
    }
}
