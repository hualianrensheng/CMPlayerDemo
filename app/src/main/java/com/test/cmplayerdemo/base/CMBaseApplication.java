package com.test.cmplayerdemo.base;

import android.os.Handler;

import com.cm.rxretrofitlibrary.RxRetrofitApp;
import com.test.cmplayerdemo.di.component.AppComponent;
import com.test.cmplayerdemo.di.component.DaggerAppComponent;
import com.test.cmplayerdemo.di.module.AppMoudle;

public class CMBaseApplication extends BaseApplication {

    private static int mMainThreadId;
    private static Handler mHandler;

    private AppComponent mAppComponent;

    @Override
    public void onCreate() {
        super.onCreate();

        RxRetrofitApp.init(this,true);

        mHandler = new Handler();

        initApplicationComponent();
    }

    private void initApplicationComponent() {
        mAppComponent = DaggerAppComponent.builder().appMoudle(new AppMoudle(this)).build();
    }

    public AppComponent getAppComponent(){
        return mAppComponent;
    }

    public static int getMainThreadId(){
        return mMainThreadId;
    }

    public static Handler getHandler(){
        return mHandler;
    }
}
