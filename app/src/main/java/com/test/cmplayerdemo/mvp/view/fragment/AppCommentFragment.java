package com.test.cmplayerdemo.mvp.view.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.cm.recyclerviewlibrary.section.SectionRVAdapter;
import com.test.cmplayerdemo.R;
import com.test.cmplayerdemo.adapter.section.AppCommentContactsSection;
import com.test.cmplayerdemo.adapter.top.AppCommentTopWrapper;
import com.test.cmplayerdemo.base.CMBaseMVPFragment;
import com.test.cmplayerdemo.bean.AppCommentBean;
import com.test.cmplayerdemo.mvp.parsenter.impl.AppCommentFragmentPresenterImpl;
import com.test.cmplayerdemo.mvp.view.activity.AppDetailActivity;
import com.test.cmplayerdemo.mvp.view.view.AppCommentFragmentView;
import com.test.cmplayerdemo.utils.UIUtils;
import com.test.cmplayerdemo.view.LoadingPager;
import com.test.cmplayerdemo.view.MultipleStatusView;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;


public class AppCommentFragment extends CMBaseMVPFragment<AppCommentFragmentPresenterImpl> implements AppCommentFragmentView {

    @BindView(R.id.rv)
    RecyclerView rv ;

    @Inject
    AppCommentFragmentPresenterImpl appCommentFragmentPresenter ;

    private String packageName ;
    private AppCommentBean appCommentBean ;
    private List<AppCommentBean.CommentsBean> hotList = new ArrayList<>();
    private List<AppCommentBean.CommentsBean> list = new ArrayList<>();

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        packageName = ((AppDetailActivity)getActivity()).getAppPackageName();
        show();
    }


    @Override
    public void onAppCommentDataSuccess(AppCommentBean appCommentBean) {
        this.appCommentBean = appCommentBean ;
        setState(LoadingPager.LoadResuslt.success);
    }

    @Override
    public void onAppCommentDataError(String msg) {
        setState(LoadingPager.LoadResuslt.error);
    }

    @Override
    protected AppCommentFragmentPresenterImpl initInjector() {
        mFragmentComponent.inject(this);
        return appCommentFragmentPresenter;
    }

    public void initView() {
        for(AppCommentBean.CommentsBean commentsBean : appCommentBean.getComments()){
            //type为1是精彩评论
            if(commentsBean.getCommentType().equals("1")){
                hotList.add(commentsBean);
            }else{
                list.add(commentsBean);
            }
        }

        SectionRVAdapter sectionAdapter = new SectionRVAdapter(getContext());

        if(hotList.size() > 0)
            sectionAdapter.addSection(new AppCommentContactsSection(getContext(),"精彩评论",hotList));
        if(list.size() > 0)
            sectionAdapter.addSection(new AppCommentContactsSection(getContext(),"全部评论",list));

        AppCommentTopWrapper appCommentTopWrapper = new AppCommentTopWrapper(getContext(),sectionAdapter);
        appCommentTopWrapper.addDataAll(appCommentBean);
        rv.setAdapter(appCommentTopWrapper);
        rv.setLayoutManager(new LinearLayoutManager(getContext()));
    }

    @Override
    public View createSuccessView() {
        View view = UIUtils.inflate(R.layout.fragment_app_comment) ;
        ButterKnife.bind(this,view) ;
        initView();





        return view;
    }

    @Override
    public void load() {
        appCommentFragmentPresenter.getAppCommentData(mCMBaseActivity,packageName);
    }
}
