package com.test.cmplayerdemo.api;

import com.cm.rxretrofitlibrary.api.BaseApi;
import com.cm.rxretrofitlibrary.listener.HttpOnNextListener;
import com.test.cmplayerdemo.bean.CategoryBean;
import com.test.cmplayerdemo.utils.JsonParseUtils;
import com.trello.rxlifecycle.components.support.RxAppCompatActivity;

import java.io.IOException;

import okhttp3.ResponseBody;
import retrofit2.Retrofit;
import rx.Observable;

public class CategoryApi extends BaseApi<CategoryBean> {

    public CategoryApi(HttpOnNextListener listener, RxAppCompatActivity rxAppCompatActivity) {
        super(listener, rxAppCompatActivity);

        setMothed("AppStore/category");
    }

    @Override
    public Observable getObservable(Retrofit retrofit) {
        HttpGetService httpGetService = retrofit.create(HttpGetService.class);
        return httpGetService.getCategoryData();
    }

    @Override
    public CategoryBean call(ResponseBody responseBody) {
        String result = "";
        try {
            result = responseBody.string();
        } catch (IOException e) {
            e.printStackTrace();
        }


        return JsonParseUtils.parseCategoryBean(result);
    }
}
