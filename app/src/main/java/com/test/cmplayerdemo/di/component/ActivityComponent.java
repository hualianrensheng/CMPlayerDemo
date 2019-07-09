package com.test.cmplayerdemo.di.component;

import android.app.Activity;
import android.content.Context;

import com.test.cmplayerdemo.di.module.ActivityMoudle;
import com.test.cmplayerdemo.di.scope.ContextLife;
import com.test.cmplayerdemo.di.scope.PreActivity;
import com.test.cmplayerdemo.mvp.view.activity.AppDetailActivity;
import com.test.cmplayerdemo.mvp.view.activity.AppMoreRecommendActivity;
import com.test.cmplayerdemo.mvp.view.activity.CategoryNecessaryActivity;
import com.test.cmplayerdemo.mvp.view.activity.CategoryNewActivity;
import com.test.cmplayerdemo.mvp.view.activity.CategorySubjectActivity;
import com.test.cmplayerdemo.mvp.view.activity.CategorySubscribeActivity;
import com.test.cmplayerdemo.mvp.view.activity.CategoryToolActivity;
import com.test.cmplayerdemo.mvp.view.activity.GalleryActivity;
import com.test.cmplayerdemo.mvp.view.activity.HomeActivity;

import dagger.Component;


@PreActivity
@Component(modules = ActivityMoudle.class,dependencies = AppComponent.class)
public interface ActivityComponent {

    @ContextLife("Activity")
    Context getActivityContext();

    @ContextLife("Application")
    Context getApplicationContext();

    Activity getActivity();


    void inject(HomeActivity homeActivity);
    void inject(AppDetailActivity appDetailActivity);
    void inject(AppMoreRecommendActivity appMoreRecommendActivity);

    void inject(CategoryToolActivity activity);
    void inject(CategorySubscribeActivity activity);
    void inject(CategoryNecessaryActivity activity);
    void inject(CategoryNewActivity activity);
    void inject(CategorySubjectActivity activity);
}
