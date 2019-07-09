package com.test.cmplayerdemo.mvp.parsenter;


import com.test.cmplayerdemo.base.CMBaseActivity;
import com.test.cmplayerdemo.base.mvpbase.BasePresenter;
import com.test.cmplayerdemo.mvp.view.view.AppMoreRecommendView;

public interface AppMoreRecommendPresenter extends BasePresenter<AppMoreRecommendView> {
    void getAppMoreRecommendData(CMBaseActivity activity, String type, String packageName) ;
}
