package com.test.cmplayerdemo.mvp.parsenter.impl;

import android.os.SystemClock;

import com.test.cmplayerdemo.base.mvpbase.BasePresenterImpl;
import com.test.cmplayerdemo.mvp.parsenter.MyFragmentPresenter;
import com.test.cmplayerdemo.mvp.view.view.MyFragmentView;

import javax.inject.Inject;

public class MyFragmentPresenterImpl extends BasePresenterImpl<MyFragmentView> implements MyFragmentPresenter {

    @Inject
    public MyFragmentPresenterImpl() {
    }

    @Override
    public void getMyData() {
        //模拟网络请求，获取数据
        new Thread(new Runnable() {
            @Override
            public void run() {
                SystemClock.sleep(2000);
                mPresenterView.onMyDataFaild();
            }
        }).start();
    }
}
