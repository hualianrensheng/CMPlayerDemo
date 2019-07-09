package com.test.cmplayerdemo.api;

import com.cm.rxretrofitlibrary.api.BaseApi;
import com.cm.rxretrofitlibrary.listener.HttpOnNextListener;
import com.test.cmplayerdemo.bean.RecommendBean;
import com.test.cmplayerdemo.utils.JsonParseUtils;
import com.trello.rxlifecycle.components.support.RxAppCompatActivity;

import java.io.IOException;

import okhttp3.ResponseBody;
import retrofit2.Retrofit;
import rx.Observable;

public class RecommendApi extends BaseApi<RecommendBean> {

    public RecommendApi(HttpOnNextListener listener, RxAppCompatActivity rxAppCompatActivity) {
        super(listener, rxAppCompatActivity);

        setCache(true);
        setMothed("AppStore/recommend");
    }

    @Override
    public Observable getObservable(Retrofit retrofit) {
        HttpGetService httpGetService = retrofit.create(HttpGetService.class);
        return httpGetService.getRecommendData();
    }

    @Override
    public RecommendBean call(ResponseBody responseBody) {

        String result = "";
        try {
            result = responseBody.string();
        } catch (IOException e) {
            e.printStackTrace();
        }


        return JsonParseUtils.parseRecommendBean(result);
    }
}
