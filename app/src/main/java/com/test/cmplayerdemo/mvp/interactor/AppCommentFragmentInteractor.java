package com.test.cmplayerdemo.mvp.interactor;


import com.cm.rxretrofitlibrary.http.HttpManager;
import com.cm.rxretrofitlibrary.listener.HttpOnNextListener;
import com.test.cmplayerdemo.api.AppCommentApi;
import com.test.cmplayerdemo.api.IGetDataDelegate;
import com.test.cmplayerdemo.base.CMBaseActivity;
import com.test.cmplayerdemo.bean.AppCommentBean;
import com.test.cmplayerdemo.utils.JsonParseUtils;

import javax.inject.Inject;

/**
 * <p>Description:
 *
 * @author xzhang
 */

public class AppCommentFragmentInteractor {

    private IGetDataDelegate<AppCommentBean> mDelegate ;

    @Inject
    public AppCommentFragmentInteractor(){

    }

    public void loadAppCommentData(CMBaseActivity activity, String packageName, IGetDataDelegate<AppCommentBean> delegate){
        this.mDelegate = delegate ;
        AppCommentApi api = new AppCommentApi(httpListener,activity,packageName);
        HttpManager httpManager = HttpManager.getInstance();
        httpManager.doHttpDeal(api);
    }

    private HttpOnNextListener<AppCommentBean> httpListener = new HttpOnNextListener<AppCommentBean>() {
        @Override
        public void onNext(AppCommentBean appCommentBean) {
            mDelegate.getDataSuccess(appCommentBean);
        }

        @Override
        public void onCacheNext(String string) {
            super.onCacheNext(string);
            AppCommentBean appCommentBean = JsonParseUtils.parseAppCommentBean(string);
            mDelegate.getDataSuccess(appCommentBean);
        }

        @Override
        public void onError(Throwable e) {
            super.onError(e);
            mDelegate.getDataError(e.getMessage());
        }
    };
}
