package com.test.cmplayerdemo.mvp.parsenter;

import com.test.cmplayerdemo.base.CMBaseActivity;
import com.test.cmplayerdemo.base.mvpbase.BasePresenter;
import com.test.cmplayerdemo.mvp.view.view.RecommendFragmnetView;

public interface RecommendFragmentPresenter extends BasePresenter<RecommendFragmnetView> {

    /**
     * @info获取推荐数据
     * @param activity
     */
    void getRecommendData(CMBaseActivity activity);


    /**
     * @info获取更多数据
     * @param activity
     */
    void getMoreRecommendData(CMBaseActivity activity);
}
