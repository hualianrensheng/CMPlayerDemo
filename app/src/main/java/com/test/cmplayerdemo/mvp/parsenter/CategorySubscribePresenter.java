package com.test.cmplayerdemo.mvp.parsenter;


import com.test.cmplayerdemo.base.CMBaseActivity;
import com.test.cmplayerdemo.base.mvpbase.BasePresenter;
import com.test.cmplayerdemo.mvp.view.view.CategorySubscribeView;

public interface CategorySubscribePresenter extends BasePresenter<CategorySubscribeView> {
    void getCategorySubscribeData(CMBaseActivity activity);
}
