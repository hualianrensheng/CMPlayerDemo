package com.test.cmplayerdemo.di.module;

import android.app.Activity;
import android.content.Context;

import com.test.cmplayerdemo.di.scope.ContextLife;
import com.test.cmplayerdemo.di.scope.PreActivity;

import dagger.Module;
import dagger.Provides;

@Module
public class ActivityMoudle {

    public Activity mActivity;

    public ActivityMoudle(Activity activity) {
        mActivity = activity;
    }

    @Provides
    @PreActivity
    public Activity provideActivity(){
        return mActivity;
    }

    @Provides
    @PreActivity
    @ContextLife("Activity")
    public Context provideActivityContext(){
        return mActivity;
    }
}
