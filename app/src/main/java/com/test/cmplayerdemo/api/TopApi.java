package com.test.cmplayerdemo.api;

import com.cm.rxretrofitlibrary.api.BaseApi;
import com.cm.rxretrofitlibrary.listener.HttpOnNextListener;
import com.test.cmplayerdemo.bean.TopBean;
import com.test.cmplayerdemo.utils.JsonParseUtils;
import com.trello.rxlifecycle.components.support.RxAppCompatActivity;

import java.io.IOException;

import okhttp3.ResponseBody;
import retrofit2.Retrofit;
import rx.Observable;

public class TopApi extends BaseApi<TopBean> {

    public TopApi(HttpOnNextListener listener, RxAppCompatActivity rxAppCompatActivity) {
        super(listener, rxAppCompatActivity);

        setCache(true);

        setMothed("AppStore/top");
    }

    @Override
    public Observable getObservable(Retrofit retrofit) {
        HttpGetService httpGetService = retrofit.create(HttpGetService.class);
        return httpGetService.getTopData();
    }

    @Override
    public TopBean call(ResponseBody responseBody) {

        String result = "";
        try {
            result = responseBody.string();
        } catch (IOException e) {
            e.printStackTrace();
        }


        return JsonParseUtils.parseTopBean(result);
    }
}
