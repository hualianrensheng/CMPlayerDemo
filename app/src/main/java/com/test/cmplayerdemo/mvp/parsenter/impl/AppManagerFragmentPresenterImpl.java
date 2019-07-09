package com.test.cmplayerdemo.mvp.parsenter.impl;

import com.test.cmplayerdemo.base.mvpbase.BasePresenterImpl;
import com.test.cmplayerdemo.mvp.parsenter.AppManagerFragmentPresenter;
import com.test.cmplayerdemo.mvp.view.view.AppManagerFragmentView;

import javax.inject.Inject;

public class AppManagerFragmentPresenterImpl extends BasePresenterImpl<AppManagerFragmentView> implements AppManagerFragmentPresenter {

    @Inject
    public AppManagerFragmentPresenterImpl() {
    }

    @Override
    public void getAppManagerData() {
        //模拟网络请求，获取数据
//        new Thread(new Runnable() {
//            @Override
//            public void run() {
//                SystemClock.sleep(2000);
//                mPresenterView.onAppManagerDataFaild();
//            }
//        }).start();
    }
}
