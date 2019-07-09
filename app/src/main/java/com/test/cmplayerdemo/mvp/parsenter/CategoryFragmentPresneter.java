package com.test.cmplayerdemo.mvp.parsenter;

import com.test.cmplayerdemo.base.CMBaseActivity;
import com.test.cmplayerdemo.base.mvpbase.BasePresenter;
import com.test.cmplayerdemo.mvp.view.view.CategoryFragmentView;

public interface CategoryFragmentPresneter extends BasePresenter<CategoryFragmentView> {

    void getCategoryData(CMBaseActivity activity);
}
