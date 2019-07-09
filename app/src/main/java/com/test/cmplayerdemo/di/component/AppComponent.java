package com.test.cmplayerdemo.di.component;


import android.content.Context;

import com.test.cmplayerdemo.di.module.AppMoudle;
import com.test.cmplayerdemo.di.scope.ContextLife;
import com.test.cmplayerdemo.di.scope.PreApp;

import dagger.Component;

@PreApp
@Component(modules = AppMoudle.class)
public interface AppComponent {

    @ContextLife("Application")
    Context getApplicationContext();
}
