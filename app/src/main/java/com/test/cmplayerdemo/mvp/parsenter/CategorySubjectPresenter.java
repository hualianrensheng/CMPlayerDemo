package com.test.cmplayerdemo.mvp.parsenter;

import com.test.cmplayerdemo.base.CMBaseActivity;
import com.test.cmplayerdemo.base.mvpbase.BasePresenter;
import com.test.cmplayerdemo.mvp.view.view.CategorySubjectView;

public interface CategorySubjectPresenter extends BasePresenter<CategorySubjectView> {
    void getCategorySubjectData(CMBaseActivity activity) ;
}
