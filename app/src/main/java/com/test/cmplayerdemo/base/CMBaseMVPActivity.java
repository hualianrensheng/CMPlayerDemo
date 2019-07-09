package com.test.cmplayerdemo.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.Toast;

import com.test.cmplayerdemo.base.mvpbase.BasePresenter;
import com.test.cmplayerdemo.base.mvpbase.BaseView;
import com.test.cmplayerdemo.di.component.ActivityComponent;
import com.test.cmplayerdemo.di.component.DaggerActivityComponent;
import com.test.cmplayerdemo.di.module.ActivityMoudle;


public abstract class CMBaseMVPActivity<T extends BasePresenter> extends CMBaseActivity implements BaseView {


    protected ActivityComponent mActivityComponent;

    protected T mPresenter;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        initActivityComponent();
        mPresenter = initInjector();
        mPresenter.attach(this);

        initData();
    }

    protected abstract void initData();

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if(mPresenter != null){
            mPresenter.detachView();
        }
    }

    public void initActivityComponent(){
        mActivityComponent = DaggerActivityComponent
                .builder()
                .activityMoudle(new ActivityMoudle(this))
                .appComponent(((CMBaseApplication) getApplication()).getAppComponent())
                .build();

    }

    @Override
    protected void showToast(String msg) {
        Toast.makeText(this,msg,Toast.LENGTH_LONG).show();
    }

    /**
     * @info 完成并返回注入的对象Prensenter对象
     * @return
     */
    protected abstract T initInjector();
}
