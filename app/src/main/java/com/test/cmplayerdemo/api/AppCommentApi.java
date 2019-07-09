package com.test.cmplayerdemo.api;

import com.cm.rxretrofitlibrary.api.BaseApi;
import com.cm.rxretrofitlibrary.listener.HttpOnNextListener;
import com.test.cmplayerdemo.bean.AppCommentBean;
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

public class AppCommentApi extends BaseApi<AppCommentBean> {

    private String packageName ;

    public AppCommentApi(HttpOnNextListener listener, RxAppCompatActivity rxAppCompatActivity, String packageName) {
        super(listener, rxAppCompatActivity);
        setMothed("AppStore/app/comment/"+packageName);
        this.packageName = packageName ;
    }

    @Override
    public Observable getObservable(Retrofit retrofit) {
        HttpGetService httpGetService = retrofit.create(HttpGetService.class);
        return httpGetService.getAppCommentData(packageName);
    }

    @Override
    public AppCommentBean call(ResponseBody responseBody) {
        String string = "";
        try {
            string = responseBody.string();
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("string:"+string);
        return JsonParseUtils.parseAppCommentBean(string);
    }
}
