package com.test.cmplayerdemo.mvp.view.activity;

import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;

import com.test.cmplayerdemo.R;
import com.test.cmplayerdemo.adapter.CategoryToolMultiAdapter;
import com.test.cmplayerdemo.adapter.top.RecommendTopWrapper;
import com.test.cmplayerdemo.base.CMBaseMVPActivity;
import com.test.cmplayerdemo.bean.CategoryToolBean;
import com.test.cmplayerdemo.mvp.parsenter.impl.CategoryToolActivityPresenterImpl;
import com.test.cmplayerdemo.mvp.view.view.CategoryToolActivityView;

import javax.inject.Inject;

import butterknife.BindView;

public class CategoryToolActivity extends CMBaseMVPActivity<CategoryToolActivityPresenterImpl> implements CategoryToolActivityView {

    @BindView(R.id.title_text)
    TextView title_text ;
//    @BindView(R.id.iv_search)
//    ImageView iv_search ;
    @BindView(R.id.rv)
    RecyclerView rv ;


    @Inject
    public CategoryToolActivityPresenterImpl categoryToolActivityPresenter ;

    private CategoryToolBean mCategoryToolBean ;

    @Override
    public void onCategoryToolDataSuccess(CategoryToolBean categoryToolBean) {
        this.mCategoryToolBean = categoryToolBean ;
        rv.setLayoutManager(new LinearLayoutManager(this));

        //主体adapter
        CategoryToolMultiAdapter adapter = new CategoryToolMultiAdapter(this);
        adapter.addDataAll(categoryToolBean.getCategoryToolAppBeanList());
        //头部轮播图
        RecommendTopWrapper topWrapper = new RecommendTopWrapper(this,adapter) ;
        topWrapper.addData(categoryToolBean.getBannerList());
        adapter.setAppItemListener(new CategoryToolMultiAdapter.AppItemListener() {
            @Override
            public void goAppDetail(String packageName) {
                Intent intent = new Intent(CategoryToolActivity.this, AppDetailActivity.class);
                intent.putExtra("packageName",packageName) ;
                startActivity(intent);
            }
        });
        rv.setAdapter(topWrapper);
    }

    @Override
    public void onCategoryToolError(String msg) {

    }

    @Override
    protected void initData() {
        categoryToolActivityPresenter.getCategoryToolData(this);
    }

    @Override
    protected CategoryToolActivityPresenterImpl initInjector() {
        mActivityComponent.inject(this);
        return categoryToolActivityPresenter;
    }

    @Override
    protected void initLayout() {
        setContentView(R.layout.activity_category_tool);
    }

    @Override
    protected void initView() {
        String name = getIntent().getStringExtra("name");
        title_text.setText(name);
    }
}
