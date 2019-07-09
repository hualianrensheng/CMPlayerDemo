package com.test.cmplayerdemo.di.module;

import android.app.Activity;
import android.content.Context;
import android.support.v4.app.Fragment;

import com.test.cmplayerdemo.di.scope.ContextLife;
import com.test.cmplayerdemo.di.scope.PreFragment;

import dagger.Module;
import dagger.Provides;

@Module
public class FragmentMoudle {

    public Fragment mFragment;

    public FragmentMoudle(Fragment fragment) {
        mFragment = fragment;
    }

    @Provides
    @PreFragment
    public Fragment provideFragment(){
        return mFragment;
    }

    @Provides
    @PreFragment
    public Activity provideFragmentActivity(){
        return mFragment.getActivity();
    }

    @Provides
    @PreFragment
    @ContextLife("Activity")
    public Context provideFragmentContext(){
        return mFragment.getContext();
    }
}
