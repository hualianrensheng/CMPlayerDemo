package com.test.cmplayerdemo.mvp.interactor;

import com.cm.rxretrofitlibrary.http.HttpManager;
import com.cm.rxretrofitlibrary.listener.HttpOnNextListener;
import com.test.cmplayerdemo.api.CategoryApi;
import com.test.cmplayerdemo.api.IGetDataDelegate;
import com.test.cmplayerdemo.base.CMBaseActivity;
import com.test.cmplayerdemo.bean.CategoryBean;
import com.test.cmplayerdemo.utils.JsonParseUtils;

import javax.inject.Inject;

public class CategoryInteractor {

    private IGetDataDelegate<CategoryBean> mCategoryBeanIGetDataDelegate;

    @Inject
    public CategoryInteractor() {
    }

    public void loadCategoryInteractor(CMBaseActivity activity, IGetDataDelegate<CategoryBean> dataDelegate){
        this.mCategoryBeanIGetDataDelegate = dataDelegate;

        CategoryApi categoryApi = new CategoryApi(mListener,activity);
        HttpManager instance = HttpManager.getInstance();
        instance.doHttpDeal(categoryApi);

    }

    private HttpOnNextListener<CategoryBean> mListener = new HttpOnNextListener<CategoryBean>() {
        @Override
        public void onNext(CategoryBean categoryBean) {
            mCategoryBeanIGetDataDelegate.getDataSuccess(categoryBean);
        }

        @Override
        public void onCacheNext(String string) {
            super.onCacheNext(string);
            CategoryBean categoryBean = JsonParseUtils.parseCategoryBean(string);
            mCategoryBeanIGetDataDelegate.getDataSuccess(categoryBean);
        }

        @Override
        public void onError(Throwable e) {
            super.onError(e);
            mCategoryBeanIGetDataDelegate.getDataError(e.toString());
        }
    };
}
