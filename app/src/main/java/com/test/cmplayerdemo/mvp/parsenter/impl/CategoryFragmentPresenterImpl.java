package com.test.cmplayerdemo.mvp.parsenter.impl;

import android.os.SystemClock;

import com.test.cmplayerdemo.api.IGetDataDelegate;
import com.test.cmplayerdemo.base.CMBaseActivity;
import com.test.cmplayerdemo.base.mvpbase.BasePresenterImpl;
import com.test.cmplayerdemo.bean.CategoryBean;
import com.test.cmplayerdemo.bean.RecommendBean;
import com.test.cmplayerdemo.mvp.interactor.CategoryInteractor;
import com.test.cmplayerdemo.mvp.parsenter.CategoryFragmentPresneter;
import com.test.cmplayerdemo.mvp.view.view.CategoryFragmentView;

import javax.inject.Inject;

public class CategoryFragmentPresenterImpl extends BasePresenterImpl<CategoryFragmentView> implements CategoryFragmentPresneter {

    @Inject
    CategoryInteractor mCategoryInteractor;

    @Inject
    public CategoryFragmentPresenterImpl() {
    }

    @Override
    public void getCategoryData(CMBaseActivity activity) {

        //网络请求，获取数据
        mCategoryInteractor.loadCategoryInteractor(activity, new IGetDataDelegate<CategoryBean>() {
            @Override
            public void getDataSuccess(CategoryBean categoryBean) {
                mPresenterView.onCategoryDataSuccess(categoryBean);
            }

            @Override
            public void getDataError(String errmsg) {
                mPresenterView.onCategoryDataFaild(errmsg);
            }
        });
    }
}
