package com.test.cmplayerdemo.mvp.interactor;



import com.cm.rxretrofitlibrary.http.HttpManager;
import com.cm.rxretrofitlibrary.listener.HttpOnNextListener;
import com.test.cmplayerdemo.api.AppIntroductionApi;
import com.test.cmplayerdemo.api.IGetDataDelegate;
import com.test.cmplayerdemo.base.CMBaseActivity;
import com.test.cmplayerdemo.bean.AppIntroductionBean;
import com.test.cmplayerdemo.utils.JsonParseUtils;

import javax.inject.Inject;

/**
 * <p>Description:
 *
 * @author xzhang
 */

public class AppIntroductionIntroductor {

    private IGetDataDelegate<AppIntroductionBean> mDelegate ;

    @Inject
    public AppIntroductionIntroductor(){

    }

    public void loadAppIntroductino(CMBaseActivity activity, String packageName, IGetDataDelegate<AppIntroductionBean> delegate){
        this.mDelegate = delegate ;
        AppIntroductionApi api = new AppIntroductionApi(httpListener,activity,packageName);
        HttpManager httpManager = HttpManager.getInstance() ;
        httpManager.doHttpDeal(api);
    }

    private HttpOnNextListener<AppIntroductionBean> httpListener = new HttpOnNextListener<AppIntroductionBean>() {
        @Override
        public void onNext(AppIntroductionBean appIntroductionBean) {
            mDelegate.getDataSuccess(appIntroductionBean);

        }

        @Override
        public void onCacheNext(String string) {
            super.onCacheNext(string);
            AppIntroductionBean appIntroductionBean = JsonParseUtils.parseAppIntroductionBean(string);
            mDelegate.getDataSuccess(appIntroductionBean);
        }

        @Override
        public void onError(Throwable e) {
            super.onError(e);
            mDelegate.getDataError(e.getMessage());
        }
    };
}
