package com.test.cmplayerdemo.mvp.view.view;

import com.test.cmplayerdemo.base.mvpbase.BaseView;
import com.test.cmplayerdemo.bean.AppRecommendBean;

/**
 * <p>Description:
 *
 * @author xzhang
 */

public interface AppRecommendFragmentView extends BaseView {
    void onAppRecommendDataSuccess(AppRecommendBean appRecommendBean);
    void onAppRecommendDataError(String msg);
}
