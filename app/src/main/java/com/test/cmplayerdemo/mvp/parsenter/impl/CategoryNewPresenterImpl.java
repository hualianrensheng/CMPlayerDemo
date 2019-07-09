package com.test.cmplayerdemo.mvp.parsenter.impl;

import com.test.cmplayerdemo.api.IGetDataDelegate;
import com.test.cmplayerdemo.base.CMBaseActivity;
import com.test.cmplayerdemo.base.mvpbase.BasePresenterImpl;
import com.test.cmplayerdemo.bean.CategoryNewBean;
import com.test.cmplayerdemo.mvp.interactor.CategoryNewInteractor;
import com.test.cmplayerdemo.mvp.parsenter.CategoryNewPresenter;
import com.test.cmplayerdemo.mvp.view.view.CategoryNewView;

import javax.inject.Inject;


public class CategoryNewPresenterImpl extends BasePresenterImpl<CategoryNewView> implements CategoryNewPresenter {

    @Inject
    public CategoryNewInteractor categoryNewInteractor ;

    @Inject
    public CategoryNewPresenterImpl(){

    }

    @Override
    public void getCategoryNewData(CMBaseActivity activity) {
        categoryNewInteractor.loadCategoryNewData(activity, new IGetDataDelegate<CategoryNewBean>() {
            @Override
            public void getDataSuccess(CategoryNewBean categoryNewBean) {
                mPresenterView.onCategoryNewDataSuccess(categoryNewBean);
            }

            @Override
            public void getDataError(String errmsg) {
                mPresenterView.onCategoryNewDataError(errmsg);
            }
        });
    }
}
