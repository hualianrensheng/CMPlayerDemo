package com.test.cmplayerdemo.mvp.view.view;

import com.test.cmplayerdemo.base.mvpbase.BaseView;
import com.test.cmplayerdemo.bean.RecommendBean;

public interface RecommendFragmnetView extends BaseView {

    void onRecommendDataSuccess(RecommendBean recommendBean);

    void onMoreRecommendDataSuccess(RecommendBean recommendBean);

    void onRecommendDataFaild(String msg);
}
