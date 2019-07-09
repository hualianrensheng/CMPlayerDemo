package com.test.cmplayerdemo.api;

import com.cm.rxretrofitlibrary.api.BaseApi;
import com.cm.rxretrofitlibrary.listener.HttpOnNextListener;
import com.test.cmplayerdemo.bean.AppMoreRecommendBean;
import com.test.cmplayerdemo.utils.JsonParseUtils;
import com.trello.rxlifecycle.components.support.RxAppCompatActivity;


import java.io.IOException;

import okhttp3.ResponseBody;
import retrofit2.Retrofit;
import rx.Observable;


public class AppMoreRecommendApi extends BaseApi<AppMoreRecommendBean> {

    private String packageName ;
    private String type ;

    public AppMoreRecommendApi(HttpOnNextListener listener, RxAppCompatActivity rxAppCompatActivity, String type, String packageName) {
        super(listener, rxAppCompatActivity);
        setMothed("AppStore/app/"+type+"/"+packageName);
        this.packageName = packageName ;
        this.type = type ;
    }

    @Override
    public Observable getObservable(Retrofit retrofit) {
        HttpGetService httpGetService = retrofit.create(HttpGetService.class);
        return httpGetService.getAppMoreRecommendData(type,packageName);
    }

    @Override
    public AppMoreRecommendBean call(ResponseBody responseBody) {
        String string = "";
        try {
            string = responseBody.string();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return JsonParseUtils.parseAppMoreRecommendBean(string);
    }
}
