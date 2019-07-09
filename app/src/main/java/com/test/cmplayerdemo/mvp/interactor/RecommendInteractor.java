package com.test.cmplayerdemo.mvp.interactor;

import com.cm.rxretrofitlibrary.http.HttpManager;
import com.cm.rxretrofitlibrary.listener.HttpOnNextListener;
import com.test.cmplayerdemo.api.IGetDataDelegate;
import com.test.cmplayerdemo.api.RecommendApi;
import com.test.cmplayerdemo.base.CMBaseActivity;
import com.test.cmplayerdemo.bean.RecommendBean;
import com.test.cmplayerdemo.utils.JsonParseUtils;

import javax.inject.Inject;

public class RecommendInteractor {

    private IGetDataDelegate<RecommendBean> mDelegate;

    @Inject
    public RecommendInteractor() {
    }

    public void loadRecommendData(CMBaseActivity activity,IGetDataDelegate<RecommendBean> dataDelegate){

        this.mDelegate = dataDelegate;
        RecommendApi recommendApi = new RecommendApi(httpListener,activity);
        HttpManager httpManager = HttpManager.getInstance();
        httpManager.doHttpDeal(recommendApi);

    }

    private HttpOnNextListener<RecommendBean> httpListener = new HttpOnNextListener<RecommendBean>() {


        @Override
        public void onNext(RecommendBean recommendBean) {
            mDelegate.getDataSuccess(recommendBean);
        }

        @Override
        public void onCacheNext(String string) {
            super.onCacheNext(string);
            RecommendBean recommendBean = JsonParseUtils.parseRecommendBean(string);
            mDelegate.getDataSuccess(recommendBean);
        }

        @Override
        public void onError(Throwable e) {
            super.onError(e);
            mDelegate.getDataError(e.getMessage());
        }
    };
}
