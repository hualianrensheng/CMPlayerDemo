package com.test.cmplayerdemo.mvp.parsenter.impl;

import com.test.cmplayerdemo.api.IGetDataDelegate;
import com.test.cmplayerdemo.base.CMBaseActivity;
import com.test.cmplayerdemo.base.mvpbase.BasePresenterImpl;
import com.test.cmplayerdemo.mvp.interactor.CategorySubjectInteractor;
import com.test.cmplayerdemo.mvp.parsenter.CategorySubjectPresenter;
import com.test.cmplayerdemo.mvp.view.view.CategorySubjectView;

import java.util.List;

import javax.inject.Inject;


public class CategorySubjectPresenterImpl extends BasePresenterImpl<CategorySubjectView> implements CategorySubjectPresenter {


    @Inject
    CategorySubjectInteractor categorySubjectInteractor ;

    @Inject
    public CategorySubjectPresenterImpl(){

    }

    @Override
    public void getCategorySubjectData(CMBaseActivity activity) {
        categorySubjectInteractor.loadCategorySubjectData(activity, new IGetDataDelegate<List<String>>() {
            @Override
            public void getDataSuccess(List<String> list) {
                mPresenterView.onCategorySubjectDataSuccess(list);
            }

            @Override
            public void getDataError(String errmsg) {
                mPresenterView.onCategorySubjectDataError(errmsg);
            }
        });
    }
}
