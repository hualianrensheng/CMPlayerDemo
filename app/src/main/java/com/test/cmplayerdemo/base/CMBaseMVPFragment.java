package com.test.cmplayerdemo.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.Toast;

import com.test.cmplayerdemo.base.mvpbase.BasePresenter;
import com.test.cmplayerdemo.base.mvpbase.BaseView;
import com.test.cmplayerdemo.di.component.DaggerFragmentComponent;
import com.test.cmplayerdemo.di.component.FragmentComponent;
import com.test.cmplayerdemo.di.module.FragmentMoudle;

public abstract class CMBaseMVPFragment<T extends BasePresenter> extends BaseFragment implements BaseView {


    protected FragmentComponent mFragmentComponent;
    protected T mPresenter;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        initFragmentComponent();
        mPresenter = initInjector();
        mPresenter.attach(this);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if(mPresenter != null){
            mPresenter.detachView();
        }
    }

    private void initFragmentComponent(){
        mFragmentComponent = DaggerFragmentComponent.builder()
                .fragmentMoudle(new FragmentMoudle(this))
                .appComponent(((CMBaseApplication) getActivity().getApplication()).getAppComponent())
                .build();

    }

    protected abstract T initInjector();

    @Override
    public void showToast(String msg) {
        Toast.makeText(getContext(),msg,Toast.LENGTH_LONG).show();
    }
}
