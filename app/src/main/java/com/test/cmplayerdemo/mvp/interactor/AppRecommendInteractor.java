package com.test.cmplayerdemo.mvp.interactor;

import com.cm.rxretrofitlibrary.http.HttpManager;
import com.cm.rxretrofitlibrary.listener.HttpOnNextListener;
import com.test.cmplayerdemo.api.AppRecommendApi;
import com.test.cmplayerdemo.api.IGetDataDelegate;
import com.test.cmplayerdemo.base.CMBaseActivity;
import com.test.cmplayerdemo.bean.AppRecommendBean;
import com.test.cmplayerdemo.utils.JsonParseUtils;


import javax.inject.Inject;

/**
 * <p>Description:
 *
 * @author xzhang
 */

public class AppRecommendInteractor {

    private IGetDataDelegate<AppRecommendBean> mDelegate ;

    @Inject
    public AppRecommendInteractor(){

    }

    public void loadAppRecommend(CMBaseActivity activity, String packageName, IGetDataDelegate<AppRecommendBean> delegate){
        this.mDelegate = delegate ;
        AppRecommendApi api = new AppRecommendApi(httpListener,activity,packageName);
        HttpManager httpListener = HttpManager.getInstance() ;
        httpListener.doHttpDeal(api);
    }

    private HttpOnNextListener<AppRecommendBean> httpListener = new HttpOnNextListener<AppRecommendBean>() {
        @Override
        public void onNext(AppRecommendBean appRecommendBean) {
            mDelegate.getDataSuccess(appRecommendBean);
        }

        @Override
        public void onCacheNext(String string) {
            super.onCacheNext(string);
            AppRecommendBean appRecommendBean = JsonParseUtils.parseAppRecommendBean(string);
            mDelegate.getDataSuccess(appRecommendBean);
        }

        @Override
        public void onError(Throwable e) {
            super.onError(e);
            mDelegate.getDataError(e.getMessage());
        }
    };
}
