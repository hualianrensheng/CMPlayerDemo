package com.test.cmplayerdemo.base.mvpbase;

public interface BasePresenter<T extends BaseView> {

    void attach(T view);

    void detachView();
}
