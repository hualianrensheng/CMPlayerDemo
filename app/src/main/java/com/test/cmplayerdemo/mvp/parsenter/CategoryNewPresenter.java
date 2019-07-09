package com.test.cmplayerdemo.mvp.parsenter;


import com.test.cmplayerdemo.base.CMBaseActivity;
import com.test.cmplayerdemo.base.mvpbase.BasePresenter;
import com.test.cmplayerdemo.mvp.view.view.CategoryNewView;

public interface CategoryNewPresenter extends BasePresenter<CategoryNewView> {
    void getCategoryNewData(CMBaseActivity activity);
}
