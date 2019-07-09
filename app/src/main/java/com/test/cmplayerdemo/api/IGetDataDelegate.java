package com.test.cmplayerdemo.api;

public interface IGetDataDelegate<T> {

    void getDataSuccess(T t);
    void getDataError(String errmsg);
}
