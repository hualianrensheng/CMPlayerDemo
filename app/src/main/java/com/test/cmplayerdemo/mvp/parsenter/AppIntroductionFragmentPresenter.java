package com.test.cmplayerdemo.mvp.parsenter;

import com.test.cmplayerdemo.base.CMBaseActivity;
import com.test.cmplayerdemo.base.mvpbase.BasePresenter;
import com.test.cmplayerdemo.mvp.view.view.AppIntroductionFragmentView;

/**
 * <p>Description:
 *
 * @author xzhang
 */

public interface AppIntroductionFragmentPresenter extends BasePresenter<AppIntroductionFragmentView> {
    void getAppIntroductionData(CMBaseActivity activity, String packageName) ;
}
