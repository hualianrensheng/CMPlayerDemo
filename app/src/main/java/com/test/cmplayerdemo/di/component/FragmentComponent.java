package com.test.cmplayerdemo.di.component;

import android.app.Activity;
import android.content.Context;

import com.test.cmplayerdemo.di.module.FragmentMoudle;
import com.test.cmplayerdemo.di.scope.ContextLife;
import com.test.cmplayerdemo.di.scope.PreFragment;
import com.test.cmplayerdemo.mvp.view.fragment.AppCommentFragment;
import com.test.cmplayerdemo.mvp.view.fragment.AppIntroductionFragment;
import com.test.cmplayerdemo.mvp.view.fragment.AppManagerFragment;
import com.test.cmplayerdemo.mvp.view.fragment.AppRecommendFragment;
import com.test.cmplayerdemo.mvp.view.fragment.CategoryFragment;
import com.test.cmplayerdemo.mvp.view.fragment.MyFragment;
import com.test.cmplayerdemo.mvp.view.fragment.RecommendFragment;
import com.test.cmplayerdemo.mvp.view.fragment.TopFragment;

import dagger.Component;

@PreFragment
@Component(modules = FragmentMoudle.class,dependencies = AppComponent.class)
public interface FragmentComponent {

    @ContextLife("Activity")
    Context getActivityContext();

    @ContextLife("Application")
    Context getApplicationContext();

    Activity getActivity();

    void inject(CategoryFragment categoryFragment);
    void inject(MyFragment myFragment);
    void inject(AppManagerFragment appManagerFragment);
    void inject(TopFragment topFragment);
    void inject(RecommendFragment recommendFragment);

    void inject(AppCommentFragment appCommentFragment);
    void inject(AppIntroductionFragment appIntroductionFragment);
    void inject(AppRecommendFragment appRecommendFragment);
}
