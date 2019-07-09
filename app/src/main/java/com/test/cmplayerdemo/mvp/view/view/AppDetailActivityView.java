package com.test.cmplayerdemo.mvp.view.view;

import com.test.cmplayerdemo.base.mvpbase.BaseView;
import com.test.cmplayerdemo.bean.AppDetailBean;

public interface AppDetailActivityView extends BaseView {

    void onAppDetailDataSuccess(AppDetailBean appDetailBean);
    void onAppDetailDataError(String msg);
}
