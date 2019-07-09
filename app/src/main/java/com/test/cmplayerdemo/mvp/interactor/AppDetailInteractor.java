package com.test.cmplayerdemo.mvp.interactor;

import com.cm.rxretrofitlibrary.http.HttpManager;
import com.cm.rxretrofitlibrary.listener.HttpOnNextListener;
import com.test.cmplayerdemo.api.AppDetailApi;
import com.test.cmplayerdemo.api.IGetDataDelegate;
import com.test.cmplayerdemo.base.CMBaseActivity;
import com.test.cmplayerdemo.bean.AppDetailBean;
import com.test.cmplayerdemo.utils.JsonParseUtils;

import javax.inject.Inject;

public class AppDetailInteractor {
    private IGetDataDelegate<AppDetailBean> mDelegate ;

    @Inject
    public AppDetailInteractor(){

    }

    public void loadAppDetailData(CMBaseActivity activity, String packageName, IGetDataDelegate<AppDetailBean> delegate){
        this.mDelegate = delegate ;
        AppDetailApi appDetailApi = new AppDetailApi(httpListener,activity,packageName);
        HttpManager httpManager = HttpManager.getInstance();
        httpManager.doHttpDeal(appDetailApi);
    }

    private HttpOnNextListener<AppDetailBean> httpListener = new HttpOnNextListener<AppDetailBean>() {
        @Override
        public void onNext(AppDetailBean appDetailBean) {
            mDelegate.getDataSuccess(appDetailBean);
        }

        @Override
        public void onCacheNext(String string) {
            super.onCacheNext(string);
            AppDetailBean appDetailBean = JsonParseUtils.parseAppDetailBean(string);
            mDelegate.getDataSuccess(appDetailBean);
        }

        @Override
        public void onError(Throwable e) {
            super.onError(e);
            mDelegate.getDataError(e.getMessage());
        }
    };
}
