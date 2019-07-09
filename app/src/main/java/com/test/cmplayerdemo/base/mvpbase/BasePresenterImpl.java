package com.test.cmplayerdemo.base.mvpbase;

public class BasePresenterImpl<T extends BaseView> implements BasePresenter<T> {

    protected T mPresenterView;

    @Override
    public void attach(T view) {
        this.mPresenterView = view;
    }

    @Override
    public void detachView() {
        this.mPresenterView = null;
    }
}
