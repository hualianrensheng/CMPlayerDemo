package com.test.cmplayerdemo.mvp.parsenter.impl;

import android.os.SystemClock;

import com.test.cmplayerdemo.api.IGetDataDelegate;
import com.test.cmplayerdemo.base.CMBaseActivity;
import com.test.cmplayerdemo.base.mvpbase.BasePresenter;
import com.test.cmplayerdemo.base.mvpbase.BasePresenterImpl;
import com.test.cmplayerdemo.bean.RecommendBean;
import com.test.cmplayerdemo.bean.TopBean;
import com.test.cmplayerdemo.mvp.interactor.TopInteractor;
import com.test.cmplayerdemo.mvp.parsenter.TopFragmentPresenter;
import com.test.cmplayerdemo.mvp.view.view.TopFragmentView;

import javax.inject.Inject;

public class TopFragmentPresenterImpl extends BasePresenterImpl<TopFragmentView> implements TopFragmentPresenter {

    @Inject
    TopInteractor mTopInteractor;

    @Inject
    public TopFragmentPresenterImpl() {
    }

    @Override
    public void getTopData(CMBaseActivity activity) {
        //网络请求，获取数据
        mTopInteractor.loadTopData(activity, new IGetDataDelegate<TopBean>() {
            @Override
            public void getDataSuccess(TopBean topBean) {
                mPresenterView.onTopDataSuccess(topBean);
            }

            @Override
            public void getDataError(String errmsg) {
                mPresenterView.onTopDataFaild(errmsg);
            }
        });
    }
}
