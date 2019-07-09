package com.test.cmplayerdemo.mvp.parsenter;

import com.test.cmplayerdemo.base.CMBaseActivity;
import com.test.cmplayerdemo.base.mvpbase.BasePresenter;
import com.test.cmplayerdemo.mvp.view.view.AppRecommendFragmentView;

/**
 * <p>Description:
 *
 * @author xzhang
 */

public interface AppRecommendFragmentPresenter extends BasePresenter<AppRecommendFragmentView> {
    void getAppRecommendData(CMBaseActivity activity, String packageName);
}
