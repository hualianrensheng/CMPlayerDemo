package com.test.cmplayerdemo.mvp.parsenter.impl;

import com.test.cmplayerdemo.api.IGetDataDelegate;
import com.test.cmplayerdemo.base.CMBaseActivity;
import com.test.cmplayerdemo.base.mvpbase.BasePresenterImpl;
import com.test.cmplayerdemo.bean.AppIntroductionBean;
import com.test.cmplayerdemo.mvp.interactor.AppIntroductionIntroductor;
import com.test.cmplayerdemo.mvp.parsenter.AppIntroductionFragmentPresenter;
import com.test.cmplayerdemo.mvp.view.view.AppIntroductionFragmentView;

import javax.inject.Inject;

/**
 * <p>Description:
 *
 * @author xzhang
 */

public class AppIntroductionFragmentPresenterImpl extends BasePresenterImpl<AppIntroductionFragmentView> implements AppIntroductionFragmentPresenter {

    @Inject
    AppIntroductionIntroductor appIntroductionIntroductor ;

    @Inject
    public AppIntroductionFragmentPresenterImpl(){

    }

    @Override
    public void getAppIntroductionData(CMBaseActivity activity, String packageName) {
        appIntroductionIntroductor.loadAppIntroductino(activity, packageName, new IGetDataDelegate<AppIntroductionBean>() {
            @Override
            public void getDataSuccess(AppIntroductionBean appIntroductionBean) {
                mPresenterView.onAppIntroductionDataSuccess(appIntroductionBean);
            }

            @Override
            public void getDataError(String errmsg) {
                mPresenterView.onAppIntroductionDataError(errmsg);
            }
        });
    }
}
