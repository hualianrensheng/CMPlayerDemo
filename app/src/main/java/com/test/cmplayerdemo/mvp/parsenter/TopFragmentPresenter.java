package com.test.cmplayerdemo.mvp.parsenter;

import com.test.cmplayerdemo.base.CMBaseActivity;
import com.test.cmplayerdemo.base.mvpbase.BasePresenter;
import com.test.cmplayerdemo.mvp.view.view.TopFragmentView;

public interface TopFragmentPresenter extends BasePresenter<TopFragmentView> {

    void getTopData(CMBaseActivity activity);
}
