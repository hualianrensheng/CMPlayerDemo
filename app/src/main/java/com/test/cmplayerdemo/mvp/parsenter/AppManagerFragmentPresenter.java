package com.test.cmplayerdemo.mvp.parsenter;

import com.test.cmplayerdemo.base.mvpbase.BasePresenter;
import com.test.cmplayerdemo.mvp.view.view.AppManagerFragmentView;

public interface AppManagerFragmentPresenter extends BasePresenter<AppManagerFragmentView> {


    /**
     * 获取App管理页面数据
     */
    void getAppManagerData();
}
