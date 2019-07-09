package com.test.cmplayerdemo.api;

import com.cm.rxretrofitlibrary.api.BaseApi;
import com.cm.rxretrofitlibrary.listener.HttpOnNextListener;
import com.test.cmplayerdemo.bean.AppDetailBean;
import com.test.cmplayerdemo.utils.JsonParseUtils;
import com.trello.rxlifecycle.components.support.RxAppCompatActivity;

import java.io.IOException;

import okhttp3.ResponseBody;
import retrofit2.Retrofit;
import rx.Observable;

public class AppDetailApi extends BaseApi<AppDetailBean> {

    private String packageName ;

    public AppDetailApi(HttpOnNextListener listener, RxAppCompatActivity rxAppCompatActivity, String packageName) {
        super(listener, rxAppCompatActivity);
//        setMothed("app/introduce/"+packageName);
        this.packageName = packageName ;
    }

    @Override
    public Observable getObservable(Retrofit retrofit) {
        HttpGetService httpGetService = retrofit.create(HttpGetService.class);
        return httpGetService.getAppDetailData(packageName);
    }

    @Override
    public AppDetailBean call(ResponseBody responseBody) {
        String string = "";
        try {
            string = responseBody.string();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return JsonParseUtils.parseAppDetailBean(string);
    }
}
