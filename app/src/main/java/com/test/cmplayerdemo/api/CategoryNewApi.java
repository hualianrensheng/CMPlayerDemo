package com.test.cmplayerdemo.api;

import com.cm.rxretrofitlibrary.api.BaseApi;
import com.cm.rxretrofitlibrary.listener.HttpOnNextListener;
import com.test.cmplayerdemo.bean.CategoryNewBean;
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

public class CategoryNewApi extends BaseApi<CategoryNewBean> {
    public CategoryNewApi(HttpOnNextListener listener, RxAppCompatActivity rxAppCompatActivity) {
        super(listener, rxAppCompatActivity);
        setMothed("AppStore/categorydata/new");
    }

    @Override
    public Observable getObservable(Retrofit retrofit) {
        HttpGetService httpGetService = retrofit.create(HttpGetService.class);
        return httpGetService.getCategoryNewData();
    }

    @Override
    public CategoryNewBean call(ResponseBody responseBody) {
        String string = "";
        try {
            string = responseBody.string();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return JsonParseUtils.parseCategoryNewBean(string);
    }
}
