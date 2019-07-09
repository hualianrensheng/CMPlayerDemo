package com.test.cmplayerdemo.base;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.test.cmplayerdemo.view.LoadingPager;

public abstract class BaseFragment extends Fragment {



    private LoadingPager mLoadingPager;

    protected CMBaseActivity mCMBaseActivity;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mCMBaseActivity = (CMBaseActivity) getActivity();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        if(mLoadingPager == null){

            mLoadingPager = new LoadingPager(getContext()) {
                @Override
                public void load() {
                    BaseFragment.this.load();
                }

                @Override
                public View createSuccessView() {
                    return BaseFragment.this.createSuccessView();
                }
            };


        }

        return mLoadingPager;
    }

    public void show(){
        if(mLoadingPager != null){
            mLoadingPager.show();
        }
    }

    public void setState(LoadingPager.LoadResuslt resuslt){
        if(mLoadingPager != null){
            mLoadingPager.setState(resuslt);
        }
    }


    public abstract void load();


    public abstract View createSuccessView();


    public abstract void showToast(String msg);

}
