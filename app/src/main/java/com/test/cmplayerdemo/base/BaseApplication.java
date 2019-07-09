package com.test.cmplayerdemo.base;

import com.cm.recyclerviewlibrary.App;

public class BaseApplication extends App {

    private static BaseApplication context;

    @Override
    public void onCreate() {
        super.onCreate();

        context = this;
    }

    public static BaseApplication getContext(){
        return context;
    }
}
