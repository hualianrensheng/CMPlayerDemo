package com.test.cmplayerdemo.mvp.view.fragment;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import com.cm.recyclerviewlibrary.section.SectionRVAdapter;
import com.test.cmplayerdemo.R;
import com.test.cmplayerdemo.adapter.section.TopContactsSection;
import com.test.cmplayerdemo.adapter.top.TopTopWrapper;
import com.test.cmplayerdemo.base.CMBaseMVPFragment;
import com.test.cmplayerdemo.bean.AppBean;
import com.test.cmplayerdemo.bean.TopBean;
import com.test.cmplayerdemo.mvp.parsenter.impl.TopFragmentPresenterImpl;
import com.test.cmplayerdemo.mvp.view.view.TopFragmentView;
import com.test.cmplayerdemo.utils.UIUtils;
import com.test.cmplayerdemo.view.LoadingPager;
import com.test.cmplayerdemo.view.widget.ViewUpSearch;

import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

public class TopFragment extends CMBaseMVPFragment<TopFragmentPresenterImpl> implements TopFragmentView {

    @BindView(R.id.rv_top)
    RecyclerView rv ;
    @BindView(R.id.search)
    ViewUpSearch search ;

    private boolean isExpand = true ;

    private TopBean mTopBean ;

    @Inject
    public TopFragmentPresenterImpl mTopFragmentPresenter;

    @Override
    protected TopFragmentPresenterImpl initInjector() {
        mFragmentComponent.inject(this);
        return mTopFragmentPresenter;
    }


    @Override
    public void load() {
        mTopFragmentPresenter.getTopData(mCMBaseActivity);
    }

    @Override
    public View createSuccessView() {

        View view = UIUtils.inflate(R.layout.fragment_top) ;
        ButterKnife.bind(this,view) ;

        SectionRVAdapter sectionAdapter = new SectionRVAdapter(getContext());
        Map<String, List<AppBean>> appBeanMap = mTopBean.getAppBeanMap();
        Set<String> strings = appBeanMap.keySet();
        for(String name : strings){
            List<AppBean> appBeanList = appBeanMap.get(name);
            TopContactsSection section = new TopContactsSection(getContext(),name,appBeanList);

            sectionAdapter.addSection(section);
        }


        TopTopWrapper topTopWrapper = new TopTopWrapper(getContext(),sectionAdapter);
        topTopWrapper.addDataAll(mTopBean.getTopTopBeanList());

        rv.setAdapter(topTopWrapper);
        rv.setLayoutManager(new LinearLayoutManager(getContext()));

        rv.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                final int firstVisiblePosition = ((LinearLayoutManager)rv.getLayoutManager()).findFirstVisibleItemPosition();
                if(firstVisiblePosition == 0 && isExpand && dy > 0){
                    search.updateShow(!isExpand);
                    isExpand = false ;
                }else if(firstVisiblePosition == 0 && !isExpand && dy < 0){
                    search.updateShow(!isExpand);
                    isExpand = true ;
                }
            }
        });

        return view;
    }


    @Override
    public void onTopDataSuccess(TopBean topBean) {
        Log.e("==1==",topBean.toString());
        this.mTopBean = topBean ;
        setState(LoadingPager.LoadResuslt.success);
    }

    @Override
    public void onTopDataFaild(String msg) {
        setState(LoadingPager.LoadResuslt.error);
    }

    @Override
    public void showToast(String msg) {

    }
}
