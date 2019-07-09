package com.test.cmplayerdemo.mvp.interactor;


import com.cm.rxretrofitlibrary.http.HttpManager;
import com.cm.rxretrofitlibrary.listener.HttpOnNextListener;
import com.test.cmplayerdemo.api.CategoryToolApi;
import com.test.cmplayerdemo.api.IGetDataDelegate;
import com.test.cmplayerdemo.base.CMBaseActivity;
import com.test.cmplayerdemo.bean.CategoryToolBean;
import com.test.cmplayerdemo.utils.JsonParseUtils;

import javax.inject.Inject;


public class CategoryToolActivityInteractor {

    private IGetDataDelegate<CategoryToolBean> mDelegate ;

    @Inject
    public CategoryToolActivityInteractor(){

    }

    public void loadCategoryToolData(CMBaseActivity activity, IGetDataDelegate<CategoryToolBean> delegate){
        this.mDelegate = delegate ;

        CategoryToolApi categoryToolApi = new CategoryToolApi(httpListener,activity);
        HttpManager httpManager = HttpManager.getInstance();
        httpManager.doHttpDeal(categoryToolApi);
    }

    private HttpOnNextListener httpListener = new HttpOnNextListener<CategoryToolBean>() {
        @Override
        public void onNext(CategoryToolBean categoryToolBean) {
            mDelegate.getDataSuccess(categoryToolBean);
        }

        @Override
        public void onCacheNext(String string) {
            super.onCacheNext(string);
            CategoryToolBean categoryToolBean = JsonParseUtils.parseCategoryToolBean(string);
            mDelegate.getDataSuccess(categoryToolBean);
        }

        @Override
        public void onError(Throwable e) {
            super.onError(e);
            mDelegate.getDataError(e.getMessage());
        }
    };

}
