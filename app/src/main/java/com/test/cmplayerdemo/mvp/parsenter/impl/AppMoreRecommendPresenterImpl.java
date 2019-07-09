package com.test.cmplayerdemo.mvp.parsenter.impl;


import com.test.cmplayerdemo.api.IGetDataDelegate;
import com.test.cmplayerdemo.base.CMBaseActivity;
import com.test.cmplayerdemo.base.mvpbase.BasePresenterImpl;
import com.test.cmplayerdemo.bean.AppMoreRecommendBean;
import com.test.cmplayerdemo.mvp.interactor.AppMoreRecommendInteractor;
import com.test.cmplayerdemo.mvp.parsenter.AppMoreRecommendPresenter;
import com.test.cmplayerdemo.mvp.view.view.AppMoreRecommendView;

import javax.inject.Inject;


public class AppMoreRecommendPresenterImpl extends BasePresenterImpl<AppMoreRecommendView> implements AppMoreRecommendPresenter {

    @Inject
    public AppMoreRecommendInteractor appMoreRecommendInteractor ;

    @Inject AppMoreRecommendPresenterImpl(){

    }

    @Override
    public void getAppMoreRecommendData(CMBaseActivity activity, String type, String packageName) {
        appMoreRecommendInteractor.loadAppMoreRecommend(activity, type, packageName, new IGetDataDelegate<AppMoreRecommendBean>() {
            @Override
            public void getDataSuccess(AppMoreRecommendBean appMoreRecommendBean) {
                mPresenterView.onAppMoreRecommendDataSuccess(appMoreRecommendBean);
            }

            @Override
            public void getDataError(String errmsg) {
                mPresenterView.onAppMoreRecommendDataError(errmsg);
            }
        });
    }
}
