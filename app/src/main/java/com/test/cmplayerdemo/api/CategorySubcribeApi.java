package com.test.cmplayerdemo.api;

import com.cm.rxretrofitlibrary.api.BaseApi;
import com.cm.rxretrofitlibrary.listener.HttpOnNextListener;
import com.test.cmplayerdemo.bean.CategorySubscribeBean;
import com.test.cmplayerdemo.utils.JsonParseUtils;
import com.trello.rxlifecycle.components.support.RxAppCompatActivity;

import java.io.IOException;

import okhttp3.ResponseBody;
import retrofit2.Retrofit;
import rx.Observable;

/**
 * <p>Description:
 *
 * @author xzhang
 */

public class CategorySubcribeApi extends BaseApi<CategorySubscribeBean> {
    public CategorySubcribeApi(HttpOnNextListener listener, RxAppCompatActivity rxAppCompatActivity) {
        super(listener, rxAppCompatActivity);

        setMothed("AppStore/categorydata/subscribe");

    }

    @Override
    public Observable getObservable(Retrofit retrofit) {
        HttpGetService httpGetService = retrofit.create(HttpGetService.class);
        return httpGetService.getCategorySubscribeData();
    }

    @Override
    public CategorySubscribeBean call(ResponseBody responseBody) {
        String string = "";
        try {
            string = responseBody.string();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return JsonParseUtils.parseCategorySubscribeBean(string);
    }
}
