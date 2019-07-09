package com.test.cmplayerdemo.mvp.parsenter.impl;

import com.test.cmplayerdemo.api.IGetDataDelegate;
import com.test.cmplayerdemo.base.CMBaseActivity;
import com.test.cmplayerdemo.base.mvpbase.BasePresenterImpl;
import com.test.cmplayerdemo.bean.RecommendBean;
import com.test.cmplayerdemo.mvp.interactor.RecommendInteractor;
import com.test.cmplayerdemo.mvp.parsenter.RecommendFragmentPresenter;
import com.test.cmplayerdemo.mvp.view.view.RecommendFragmnetView;

import javax.inject.Inject;

public class RecommendFragmentPresenterImpl extends BasePresenterImpl<RecommendFragmnetView> implements RecommendFragmentPresenter {


    @Inject
    RecommendInteractor mRecommendInteractor;

    @Inject
    public RecommendFragmentPresenterImpl() {
    }

    @Override
    public void getRecommendData(CMBaseActivity activity) {
        //网络请求，获取数据
        mRecommendInteractor.loadRecommendData(activity, new IGetDataDelegate<RecommendBean>() {
            @Override
            public void getDataSuccess(RecommendBean recommendBean) {
                mPresenterView.onRecommendDataSuccess(recommendBean);
            }

            @Override
            public void getDataError(String errmsg) {
                mPresenterView.onRecommendDataFaild(errmsg);
            }
        });
    }

    @Override
    public void getMoreRecommendData(CMBaseActivity activity) {
        //网络请求，获取更多数据
        mRecommendInteractor.loadRecommendData(activity, new IGetDataDelegate<RecommendBean>() {
            @Override
            public void getDataSuccess(RecommendBean recommendBean) {
                mPresenterView.onMoreRecommendDataSuccess(recommendBean);
            }

            @Override
            public void getDataError(String errmsg) {
                mPresenterView.onRecommendDataFaild(errmsg);
            }
        });
    }

}
