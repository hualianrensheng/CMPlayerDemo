package com.test.cmplayerdemo.mvp.interactor;


import com.cm.rxretrofitlibrary.http.HttpManager;
import com.cm.rxretrofitlibrary.listener.HttpOnNextListener;
import com.test.cmplayerdemo.api.CategorySubcribeApi;
import com.test.cmplayerdemo.api.IGetDataDelegate;
import com.test.cmplayerdemo.base.CMBaseActivity;
import com.test.cmplayerdemo.bean.CategorySubscribeBean;
import com.test.cmplayerdemo.utils.JsonParseUtils;

import javax.inject.Inject;


public class CategorySubscribeInteractor {

    private IGetDataDelegate<CategorySubscribeBean> mDelegate ;

    @Inject
    public CategorySubscribeInteractor(){

    }

    public void loadCategorySubscribeData(CMBaseActivity activity, IGetDataDelegate<CategorySubscribeBean> delegate){
        this.mDelegate = delegate ;
        CategorySubcribeApi categorySubcribeApi = new CategorySubcribeApi(httpListener,activity);
        HttpManager httpManager = HttpManager.getInstance();
        httpManager.doHttpDeal(categorySubcribeApi);
    }

    private HttpOnNextListener httpListener = new HttpOnNextListener<CategorySubscribeBean>(){

        @Override
        public void onNext(CategorySubscribeBean categorySubscribeBean) {
            mDelegate.getDataSuccess(categorySubscribeBean);
        }

        @Override
        public void onCacheNext(String string) {
            super.onCacheNext(string);
            CategorySubscribeBean categorySubscribeBean = JsonParseUtils.parseCategorySubscribeBean(string);
            mDelegate.getDataSuccess(categorySubscribeBean);
        }

        @Override
        public void onError(Throwable e) {
            super.onError(e);
            mDelegate.getDataError(e.getMessage());
        }
    };
}
