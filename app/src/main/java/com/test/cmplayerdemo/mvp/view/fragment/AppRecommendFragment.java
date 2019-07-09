package com.test.cmplayerdemo.mvp.view.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;


import com.cm.recyclerviewlibrary.section.SectionRVAdapter;
import com.test.cmplayerdemo.R;
import com.test.cmplayerdemo.adapter.section.AppRecommendHotSection;
import com.test.cmplayerdemo.adapter.section.AppRecommendPopularSection;
import com.test.cmplayerdemo.adapter.section.AppRecommendTasteSection;
import com.test.cmplayerdemo.base.CMBaseMVPFragment;
import com.test.cmplayerdemo.bean.AppRecommendBean;
import com.test.cmplayerdemo.mvp.parsenter.impl.AppRecommendFragmentPresenterImpl;
import com.test.cmplayerdemo.mvp.view.activity.AppDetailActivity;
import com.test.cmplayerdemo.mvp.view.view.AppRecommendFragmentView;
import com.test.cmplayerdemo.utils.UIUtils;
import com.test.cmplayerdemo.view.LoadingPager;
import com.test.cmplayerdemo.view.MultipleStatusView;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * <p>Description:
 *
 * @author xzhang
 */

public class AppRecommendFragment extends CMBaseMVPFragment<AppRecommendFragmentPresenterImpl> implements AppRecommendFragmentView {

    @BindView(R.id.rv)
    RecyclerView rv ;

    @Inject
    AppRecommendFragmentPresenterImpl appRecommendFragmentPresenter ;

    private String packageName ;

    private AppRecommendBean appRecommendBean ;

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        packageName = ((AppDetailActivity)getActivity()).getAppPackageName();

        show();
    }

    @Override
    public void onAppRecommendDataSuccess(AppRecommendBean appRecommendBean) {
        this.appRecommendBean = appRecommendBean ;
        setState(LoadingPager.LoadResuslt.success);
    }

    @Override
    public void onAppRecommendDataError(String msg) {
        setState(LoadingPager.LoadResuslt.error);
    }

    @Override
    protected AppRecommendFragmentPresenterImpl initInjector() {
        mFragmentComponent.inject(this);
        return appRecommendFragmentPresenter;
    }

    public void initView() {
        SectionRVAdapter adapter = new SectionRVAdapter(getContext());
        adapter.addSection(new AppRecommendPopularSection(getContext(),"流行应用",appRecommendBean.getPopularAppBeanList(),packageName));
        adapter.addSection(new AppRecommendTasteSection(getContext(),"兴趣相近的用户也安装了",appRecommendBean.getTasteAppBeanList(),packageName));
        adapter.addSection(new AppRecommendHotSection(getContext(),"本周热议的社区应用",appRecommendBean.getHotAppBeanList(),packageName));
        rv.setAdapter(adapter);
        rv.setLayoutManager(new LinearLayoutManager(getContext()));
    }

    @Override
    public View createSuccessView() {
        View view = UIUtils.inflate(R.layout.fragment_app_recommend);
        ButterKnife.bind(this,view);

        initView();


        return view;
    }

    @Override
    public void load() {
        appRecommendFragmentPresenter.getAppRecommendData(mCMBaseActivity,packageName);
    }
}
