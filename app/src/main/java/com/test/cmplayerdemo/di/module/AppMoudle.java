package com.test.cmplayerdemo.di.module;

import android.content.Context;

import com.test.cmplayerdemo.base.CMBaseApplication;
import com.test.cmplayerdemo.di.scope.ContextLife;
import com.test.cmplayerdemo.di.scope.PreApp;

import dagger.Module;
import dagger.Provides;

@Module
public class AppMoudle {

    public CMBaseApplication mCMBaseApplication;

    public AppMoudle(CMBaseApplication cmBaseApplication){
        this.mCMBaseApplication = cmBaseApplication;
    }

    @Provides
    @PreApp
    @ContextLife("Application")
    public Context provideAppContext(){
        return mCMBaseApplication.getApplicationContext();
    }
}
