package com.test.cmplayerdemo.mvp.view.view;


import com.test.cmplayerdemo.base.mvpbase.BaseView;
import com.test.cmplayerdemo.bean.AppIntroductionBean;

/**
 * <p>Description:
 *
 * @author xzhang
 */

public interface AppIntroductionFragmentView extends BaseView {
    void onAppIntroductionDataSuccess(AppIntroductionBean appIntroductionBean);
    void onAppIntroductionDataError(String msg) ;
}
