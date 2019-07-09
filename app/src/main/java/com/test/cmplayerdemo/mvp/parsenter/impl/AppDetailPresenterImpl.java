package com.test.cmplayerdemo.mvp.parsenter.impl;

import com.test.cmplayerdemo.api.IGetDataDelegate;
import com.test.cmplayerdemo.base.CMBaseActivity;
import com.test.cmplayerdemo.base.mvpbase.BasePresenterImpl;
import com.test.cmplayerdemo.bean.AppDetailBean;
import com.test.cmplayerdemo.mvp.interactor.AppDetailInteractor;
import com.test.cmplayerdemo.mvp.parsenter.AppDetailActivityPresenter;
import com.test.cmplayerdemo.mvp.view.view.AppDetailActivityView;

import javax.inject.Inject;

public class AppDetailPresenterImpl extends BasePresenterImpl<AppDetailActivityView> implements AppDetailActivityPresenter {

    @Inject
    AppDetailInteractor appDetailInteractor ;

    @Inject
    public AppDetailPresenterImpl(){

    }

    @Override
    public void getAppDetailData(CMBaseActivity activity,String packageName) {
        appDetailInteractor.loadAppDetailData(activity, packageName, new IGetDataDelegate<AppDetailBean>() {
            @Override
            public void getDataSuccess(AppDetailBean appDetailBean) {
                mPresenterView.onAppDetailDataSuccess(appDetailBean);
            }

            @Override
            public void getDataError(String errmsg) {
                mPresenterView.onAppDetailDataError(errmsg);
            }
        });
    }
}
