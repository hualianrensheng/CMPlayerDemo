package com.test.cmplayerdemo.mvp.interactor;

import com.cm.rxretrofitlibrary.http.HttpManager;
import com.cm.rxretrofitlibrary.listener.HttpOnNextListener;
import com.test.cmplayerdemo.api.IGetDataDelegate;
import com.test.cmplayerdemo.api.TopApi;
import com.test.cmplayerdemo.base.CMBaseActivity;
import com.test.cmplayerdemo.bean.TopBean;
import com.test.cmplayerdemo.utils.JsonParseUtils;

import javax.inject.Inject;

public class TopInteractor {

    private IGetDataDelegate<TopBean> mDelegate;

    @Inject
    public TopInteractor() {
    }

    public void loadTopData(CMBaseActivity activity,IGetDataDelegate<TopBean> mTopDataDelegate){
        this.mDelegate = mTopDataDelegate;

        TopApi topApi = new TopApi(mTopBeanHttpOnNextListener,activity);
        HttpManager instance = HttpManager.getInstance();
        instance.doHttpDeal(topApi);
    }

    private HttpOnNextListener<TopBean> mTopBeanHttpOnNextListener = new HttpOnNextListener<TopBean>() {
        @Override
        public void onNext(TopBean topBean) {
            mDelegate.getDataSuccess(topBean);
        }

        @Override
        public void onCacheNext(String string) {
            super.onCacheNext(string);
            mDelegate.getDataSuccess(JsonParseUtils.parseTopBean(string));
        }

        @Override
        public void onError(Throwable e) {
            super.onError(e);
            mDelegate.getDataError(e.toString());
        }
    };
}
