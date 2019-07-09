package com.test.cmplayerdemo.mvp.parsenter;


import com.test.cmplayerdemo.base.CMBaseActivity;
import com.test.cmplayerdemo.base.mvpbase.BasePresenter;
import com.test.cmplayerdemo.mvp.view.view.CategoryNecessaryView;

public interface CategoryNecessaryPresenter extends BasePresenter<CategoryNecessaryView> {
    void getCategoryNecessaryData(CMBaseActivity activity) ;
}
