package com.test.cmplayerdemo.mvp.parsenter.impl;

import com.test.cmplayerdemo.api.IGetDataDelegate;
import com.test.cmplayerdemo.base.CMBaseActivity;
import com.test.cmplayerdemo.base.mvpbase.BasePresenterImpl;
import com.test.cmplayerdemo.bean.AppRecommendBean;
import com.test.cmplayerdemo.mvp.interactor.AppRecommendInteractor;
import com.test.cmplayerdemo.mvp.parsenter.AppRecommendFragmentPresenter;
import com.test.cmplayerdemo.mvp.view.view.AppRecommendFragmentView;

import javax.inject.Inject;

/**
 * <p>Description:
 *
 * @author xzhang
 */

public class AppRecommendFragmentPresenterImpl extends BasePresenterImpl<AppRecommendFragmentView> implements AppRecommendFragmentPresenter {

    @Inject
    public AppRecommendInteractor appRecommendInteractor ;

    @Inject
    public AppRecommendFragmentPresenterImpl(){

    }

    @Override
    public void getAppRecommendData(CMBaseActivity activity, String packageName) {
        appRecommendInteractor.loadAppRecommend(activity, packageName, new IGetDataDelegate<AppRecommendBean>() {
            @Override
            public void getDataSuccess(AppRecommendBean appRecommendBean) {
                mPresenterView.onAppRecommendDataSuccess(appRecommendBean);
            }

            @Override
            public void getDataError(String errmsg) {
                mPresenterView.onAppRecommendDataError(errmsg);
            }
        });
    }
}
