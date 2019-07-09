package com.test.cmplayerdemo.mvp.parsenter.impl;

import com.test.cmplayerdemo.api.IGetDataDelegate;
import com.test.cmplayerdemo.base.CMBaseActivity;
import com.test.cmplayerdemo.base.mvpbase.BasePresenterImpl;
import com.test.cmplayerdemo.bean.CategoryToolBean;
import com.test.cmplayerdemo.mvp.interactor.CategoryToolActivityInteractor;
import com.test.cmplayerdemo.mvp.parsenter.CategoryToolActivityPresenter;
import com.test.cmplayerdemo.mvp.view.view.CategoryToolActivityView;

import javax.inject.Inject;


public class CategoryToolActivityPresenterImpl extends BasePresenterImpl<CategoryToolActivityView> implements CategoryToolActivityPresenter {

    @Inject
    public CategoryToolActivityInteractor categoryToolActivityInteractor ;

    @Inject
    public CategoryToolActivityPresenterImpl(){

    }

    @Override
    public void getCategoryToolData(CMBaseActivity activity) {
        categoryToolActivityInteractor.loadCategoryToolData(activity, new IGetDataDelegate<CategoryToolBean>() {
            @Override
            public void getDataSuccess(CategoryToolBean categoryToolBean) {
                mPresenterView.onCategoryToolDataSuccess(categoryToolBean);
            }

            @Override
            public void getDataError(String errmsg) {
                mPresenterView.onCategoryToolError(errmsg);
            }
        });
    }
}
