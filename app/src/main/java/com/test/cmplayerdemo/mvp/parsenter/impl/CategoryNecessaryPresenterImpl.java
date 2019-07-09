package com.test.cmplayerdemo.mvp.parsenter.impl;


import com.test.cmplayerdemo.api.IGetDataDelegate;
import com.test.cmplayerdemo.base.CMBaseActivity;
import com.test.cmplayerdemo.base.mvpbase.BasePresenterImpl;
import com.test.cmplayerdemo.bean.CategoryNecessaryBean;
import com.test.cmplayerdemo.mvp.interactor.CategoryNecessaryInteractor;
import com.test.cmplayerdemo.mvp.parsenter.CategoryNecessaryPresenter;
import com.test.cmplayerdemo.mvp.view.view.CategoryNecessaryView;

import javax.inject.Inject;


public class CategoryNecessaryPresenterImpl extends BasePresenterImpl<CategoryNecessaryView> implements CategoryNecessaryPresenter {


    @Inject
    public CategoryNecessaryInteractor categoryNecessaryInteractor ;

    @Inject
    public CategoryNecessaryPresenterImpl(){

    }

    @Override
    public void getCategoryNecessaryData(CMBaseActivity activity) {
        categoryNecessaryInteractor.loadCategoryNecessaryData(activity, new IGetDataDelegate<CategoryNecessaryBean>() {
            @Override
            public void getDataSuccess(CategoryNecessaryBean categoryNecessaryBean) {
                mPresenterView.onCategoryNecessaryDataSuccess(categoryNecessaryBean);
            }

            @Override
            public void getDataError(String errmsg) {
                mPresenterView.onCategoryNecessaryDataError(errmsg);
            }
        });
    }
}
