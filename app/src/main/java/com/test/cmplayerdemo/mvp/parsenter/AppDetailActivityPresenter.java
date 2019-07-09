package com.test.cmplayerdemo.mvp.parsenter;

import com.test.cmplayerdemo.base.CMBaseActivity;
import com.test.cmplayerdemo.base.mvpbase.BasePresenter;
import com.test.cmplayerdemo.mvp.view.view.AppDetailActivityView;

public interface AppDetailActivityPresenter extends BasePresenter<AppDetailActivityView> {

    void getAppDetailData(CMBaseActivity activity,String packageName);
}
