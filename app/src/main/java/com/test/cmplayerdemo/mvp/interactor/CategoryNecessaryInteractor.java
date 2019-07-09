package com.test.cmplayerdemo.mvp.interactor;


import com.cm.rxretrofitlibrary.http.HttpManager;
import com.cm.rxretrofitlibrary.listener.HttpOnNextListener;
import com.test.cmplayerdemo.api.CategoryNecessaryApi;
import com.test.cmplayerdemo.api.IGetDataDelegate;
import com.test.cmplayerdemo.base.CMBaseActivity;
import com.test.cmplayerdemo.bean.CategoryNecessaryBean;
import com.test.cmplayerdemo.utils.JsonParseUtils;

import javax.inject.Inject;

/**
 * <p>Description:
 *
 * @author xzhang
 */

public class CategoryNecessaryInteractor {

    private IGetDataDelegate<CategoryNecessaryBean> mDelegate ;

    @Inject
    public CategoryNecessaryInteractor(){

    }

    public void loadCategoryNecessaryData(CMBaseActivity activity, IGetDataDelegate<CategoryNecessaryBean> delegate){
        this.mDelegate = delegate ;
        CategoryNecessaryApi categoryNecessaryApi = new CategoryNecessaryApi(httpListener,activity);
        HttpManager httpListener = HttpManager.getInstance();
        httpListener.doHttpDeal(categoryNecessaryApi);
    }

    private HttpOnNextListener<CategoryNecessaryBean> httpListener = new HttpOnNextListener<CategoryNecessaryBean>() {
        @Override
        public void onNext(CategoryNecessaryBean categoryNecessaryBean) {
            mDelegate.getDataSuccess(categoryNecessaryBean);
        }

        @Override
        public void onCacheNext(String string) {
            super.onCacheNext(string);
            CategoryNecessaryBean categoryNecessaryBean = JsonParseUtils.parseCategoryNecessaryBean(string);
            mDelegate.getDataSuccess(categoryNecessaryBean);
        }

        @Override
        public void onError(Throwable e) {
            super.onError(e);
            mDelegate.getDataError(e.getMessage());
        }
    };
}
