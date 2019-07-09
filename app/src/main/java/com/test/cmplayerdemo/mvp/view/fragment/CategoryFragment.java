package com.test.cmplayerdemo.mvp.view.fragment;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import com.cm.recyclerviewlibrary.section.SectionRVAdapter;
import com.test.cmplayerdemo.R;
import com.test.cmplayerdemo.adapter.section.CategoryContactsSection;
import com.test.cmplayerdemo.adapter.top.CategoryTopWrapper;
import com.test.cmplayerdemo.base.CMBaseMVPFragment;
import com.test.cmplayerdemo.bean.CategoryBean;
import com.test.cmplayerdemo.mvp.parsenter.impl.CategoryFragmentPresenterImpl;
import com.test.cmplayerdemo.mvp.view.activity.CategoryNecessaryActivity;
import com.test.cmplayerdemo.mvp.view.activity.CategoryNewActivity;
import com.test.cmplayerdemo.mvp.view.activity.CategorySubjectActivity;
import com.test.cmplayerdemo.mvp.view.activity.CategorySubscribeActivity;
import com.test.cmplayerdemo.mvp.view.activity.CategoryToolActivity;
import com.test.cmplayerdemo.mvp.view.activity.HomeActivity;
import com.test.cmplayerdemo.mvp.view.view.CategoryFragmentView;
import com.test.cmplayerdemo.utils.UIUtils;
import com.test.cmplayerdemo.view.LoadingPager;
import com.test.cmplayerdemo.view.widget.ViewUpSearch;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

public class CategoryFragment extends CMBaseMVPFragment<CategoryFragmentPresenterImpl> implements CategoryFragmentView {

    @BindView(R.id.rv_categary)
    RecyclerView mRecyclerView;

    @BindView(R.id.search)
    ViewUpSearch search ;

    private CategoryBean mCategoryBean;

    @Inject
    public CategoryFragmentPresenterImpl mCategoryFragmentPresneter;

    private boolean isExpand = true ;

    @Override
    protected CategoryFragmentPresenterImpl initInjector() {
        mFragmentComponent.inject(this);
        return mCategoryFragmentPresneter;
    }


    @Override
    public void load() {
        mCategoryFragmentPresneter.getCategoryData(mCMBaseActivity);

    }

    @Override
    public View createSuccessView() {

        View view = UIUtils.inflate(R.layout.fragment_category);
        ButterKnife.bind(this,view);

        initView();

        return view;
    }

    public void initView() {
        SectionRVAdapter adapter = new SectionRVAdapter(getContext()) ;
        CategoryContactsSection categoryContactsSection = new CategoryContactsSection(getContext(),mCategoryBean.getTitle(),mCategoryBean.getCategoryDataBeanList());
        adapter.addSection(categoryContactsSection);

        CategoryTopWrapper categoryTopWrapper = new CategoryTopWrapper(getContext(),adapter);
        categoryTopWrapper.addDataAll(mCategoryBean.getCategoryTopBeanList());
        mRecyclerView.setAdapter(categoryTopWrapper);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        mRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                final int firstVisiblePosition = ((LinearLayoutManager)mRecyclerView.getLayoutManager()).findFirstVisibleItemPosition();
                if(firstVisiblePosition == 0 && isExpand && dy > 0){
                    search.updateShow(!isExpand);
                    isExpand = false ;
                }else if(firstVisiblePosition == 0 && !isExpand && dy < 0){
                    search.updateShow(!isExpand);
                    isExpand = true ;
                }
            }
        });

        categoryContactsSection.setOnItemClickListener(new CategoryContactsSection.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                Intent intent = new Intent(getContext(),CategoryToolActivity.class) ;
                intent.putExtra("name",mCategoryBean.getCategoryDataBeanList().get(position).getName());
                ((HomeActivity)getActivity()).startActivity(intent);

            }
        });

        categoryTopWrapper.setOnItemClickListener(new CategoryTopWrapper.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                if(position == 0){
                    mCMBaseActivity.startActivity(new Intent(mCMBaseActivity, CategorySubscribeActivity.class));
                }else if(position == 1){
                    mCMBaseActivity.startActivity(new Intent(mCMBaseActivity,CategoryNecessaryActivity.class));
                }else if(position == 2){
                    mCMBaseActivity.startActivity(new Intent(mCMBaseActivity,CategoryNewActivity.class));
                }else {
                    mCMBaseActivity.startActivity(new Intent(mCMBaseActivity,CategorySubjectActivity.class));
                }
            }
        });
    }


    @Override
    public void onCategoryDataSuccess(CategoryBean categoryBean) {
        Log.e("===",categoryBean.toString());
        this.mCategoryBean = categoryBean;
        setState(LoadingPager.LoadResuslt.success);
    }

    @Override
    public void onCategoryDataFaild(String msg) {
        setState(LoadingPager.LoadResuslt.error);
    }

    @Override
    public void showToast(String msg) {

    }
}
