package com.test.cmplayerdemo.mvp.parsenter;

import com.test.cmplayerdemo.base.mvpbase.BasePresenter;
import com.test.cmplayerdemo.mvp.view.view.MyFragmentView;

public interface MyFragmentPresenter extends BasePresenter<MyFragmentView> {

    void getMyData();
}
