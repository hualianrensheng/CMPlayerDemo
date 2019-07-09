package com.test.cmplayerdemo.mvp.view.view;


import com.test.cmplayerdemo.base.mvpbase.BaseView;
import com.test.cmplayerdemo.bean.AppMoreRecommendBean;

public interface AppMoreRecommendView extends BaseView {
    void onAppMoreRecommendDataSuccess(AppMoreRecommendBean appMoreRecommendBean) ;
    void onAppMoreRecommendDataError(String msg) ;
}
