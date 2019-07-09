package com.test.cmplayerdemo.mvp.view.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import com.cm.recyclerviewlibrary.pullrefresh.PullToRefreshView;
import com.test.cmplayerdemo.R;
import com.test.cmplayerdemo.adapter.RecommendAdapter;
import com.test.cmplayerdemo.adapter.top.RecommendTopWrapper;
import com.test.cmplayerdemo.base.CMBaseMVPFragment;
import com.test.cmplayerdemo.bean.RecommendBean;
import com.test.cmplayerdemo.mvp.parsenter.impl.RecommendFragmentPresenterImpl;
import com.test.cmplayerdemo.mvp.view.activity.AppDetailActivity;
import com.test.cmplayerdemo.mvp.view.view.RecommendFragmnetView;
import com.test.cmplayerdemo.utils.UIUtils;
import com.test.cmplayerdemo.view.LoadingPager;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

public class RecommendFragment extends CMBaseMVPFragment<RecommendFragmentPresenterImpl> implements RecommendFragmnetView {

    @Inject
    public RecommendFragmentPresenterImpl mRecommendFragmentPresenter;

    @BindView(R.id.rv_recommend)
    RecyclerView rv;

    @BindView(R.id.ptr)
    PullToRefreshView mPullToRefreshView;


    private List<RecommendBean.RecommendAppBean> mRecommendAppBeanList = new ArrayList<>();

    private RecommendBean recommendBean;
    private View mView;
    private RecommendAdapter mAdapter;
    private RecommendTopWrapper mTopWrapper;

    @Override
    protected RecommendFragmentPresenterImpl initInjector() {
        mFragmentComponent.inject(this);
        return mRecommendFragmentPresenter;
    }


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        show();
    }

    @Override
    public void load() {

        mRecommendFragmentPresenter.getRecommendData(mCMBaseActivity);

    }

    @Override
    public View createSuccessView() {
        mView = UIUtils.inflate(R.layout.fragment_recommend);
        ButterKnife.bind(this, mView);

        rv.setLayoutManager(new LinearLayoutManager(getContext()));

        mAdapter = new RecommendAdapter(getContext(),recommendBean.getRecommendAppBeanList());


        mTopWrapper = new RecommendTopWrapper(getContext(), mAdapter);
        mTopWrapper.addData(recommendBean.getBannerList());

        rv.setAdapter(mTopWrapper);

        //禁用下拉刷新
        mPullToRefreshView.setPullDownEnable(false);

        mPullToRefreshView.setListener(new PullToRefreshView.OnRefreshListener() {
            @Override
            public void onRefresh() {
                //下拉刷新
            }

            @Override
            public void onLoadMore() {
                //上拉加载更多
                mRecommendFragmentPresenter.getMoreRecommendData(mCMBaseActivity);

            }
        });

        mAdapter.setAppItemListener(new RecommendAdapter.AppItemListener() {
            @Override
            public void goAppDetaail(String packageName) {
                Intent intent = new Intent(mCMBaseActivity, AppDetailActivity.class);
                intent.putExtra("packageName",packageName) ;
                mCMBaseActivity.startActivity(intent);
            }
        });

        return mView;

    }


    @Override
    public void onRecommendDataSuccess(RecommendBean recommendBean) {
        Log.e("====",recommendBean.toString());
        this.recommendBean = recommendBean;
        setState(LoadingPager.LoadResuslt.success);
    }

    @Override
    public void onMoreRecommendDataSuccess(RecommendBean recommendBean) {
        mRecommendAppBeanList.addAll(recommendBean.getRecommendAppBeanList());
        mAdapter.clearData();
        mAdapter.addDataAll(mRecommendAppBeanList);
        mTopWrapper.notifyDataSetChanged();
        mPullToRefreshView.onFinishLoading();
        setState(LoadingPager.LoadResuslt.success);
    }

    @Override
    public void onRecommendDataFaild(String msg) {
        setState(LoadingPager.LoadResuslt.error);
    }
}
