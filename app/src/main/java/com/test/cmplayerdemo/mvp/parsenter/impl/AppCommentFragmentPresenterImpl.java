package com.test.cmplayerdemo.mvp.parsenter.impl;


import com.test.cmplayerdemo.api.IGetDataDelegate;
import com.test.cmplayerdemo.base.CMBaseActivity;
import com.test.cmplayerdemo.base.mvpbase.BasePresenterImpl;
import com.test.cmplayerdemo.bean.AppCommentBean;
import com.test.cmplayerdemo.mvp.interactor.AppCommentFragmentInteractor;
import com.test.cmplayerdemo.mvp.parsenter.AppCommentFragmentPresenter;
import com.test.cmplayerdemo.mvp.view.view.AppCommentFragmentView;

import javax.inject.Inject;

/**
 * <p>Description:
 *
 * @author xzhang
 */

public class AppCommentFragmentPresenterImpl extends BasePresenterImpl<AppCommentFragmentView> implements AppCommentFragmentPresenter {

    @Inject
    AppCommentFragmentInteractor appCommentFragmentInteractor ;

    @Inject
    public AppCommentFragmentPresenterImpl(){

    }

    @Override
    public void getAppCommentData(CMBaseActivity activity, String packageName) {
        appCommentFragmentInteractor.loadAppCommentData(activity, packageName, new IGetDataDelegate<AppCommentBean>() {
            @Override
            public void getDataSuccess(AppCommentBean appCommentBean) {
                mPresenterView.onAppCommentDataSuccess(appCommentBean);
            }

            @Override
            public void getDataError(String errmsg) {
                mPresenterView.onAppCommentDataError(errmsg);
            }
        });
    }
}
