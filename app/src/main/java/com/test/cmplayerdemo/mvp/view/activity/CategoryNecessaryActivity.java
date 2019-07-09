package com.test.cmplayerdemo.mvp.view.activity;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;


import com.cm.recyclerviewlibrary.adapter.CommonAdapter;
import com.cm.recyclerviewlibrary.adapter.MultiItemTypeAdapter;
import com.cm.recyclerviewlibrary.base.ViewHolder;
import com.test.cmplayerdemo.R;
import com.test.cmplayerdemo.adapter.top.CategoryNecessaryTopWrapper;
import com.test.cmplayerdemo.base.CMBaseMVPActivity;
import com.test.cmplayerdemo.bean.AppBean;
import com.test.cmplayerdemo.bean.CategoryNecessaryBean;
import com.test.cmplayerdemo.mvp.parsenter.impl.CategoryNecessaryPresenterImpl;
import com.test.cmplayerdemo.mvp.view.view.CategoryNecessaryView;

import javax.inject.Inject;

import butterknife.BindView;

public class CategoryNecessaryActivity extends CMBaseMVPActivity<CategoryNecessaryPresenterImpl> implements CategoryNecessaryView {

    @BindView(R.id.title_text)
    TextView title_text ;
//    @BindView(R.id.iv_search)
//    ImageView iv_search ;
    @BindView(R.id.rv)
    RecyclerView rv ;

    @Inject
    public CategoryNecessaryPresenterImpl categoryNecessaryPresenter ;


    @Override
    protected void initLayout() {
        setContentView(R.layout.activity_category_necessary);
    }

    @Override
    protected void initView() {
        title_text.setText("装机必备");
    }

    @Override
    protected void initData() {
        categoryNecessaryPresenter.getCategoryNecessaryData(this);
    }

    @Override
    protected CategoryNecessaryPresenterImpl initInjector() {
        mActivityComponent.inject(this);
        return categoryNecessaryPresenter;
    }

    @Override
    public void onCategoryNecessaryDataSuccess(final CategoryNecessaryBean categoryNecessaryBean) {
        rv.setLayoutManager(new LinearLayoutManager(this));
        CategoryNecessaryAdapter adapter = new CategoryNecessaryAdapter(this);
        adapter.addDataAll(categoryNecessaryBean.getAppBeanList());
        CategoryNecessaryTopWrapper categoryNecessaryTopWrapper = new CategoryNecessaryTopWrapper(this,adapter,categoryNecessaryBean.getHead());
        rv.setAdapter(categoryNecessaryTopWrapper);

        adapter.setOnItemClickListener(new MultiItemTypeAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, RecyclerView.ViewHolder holder, Object o, int position) {
                AppBean appBean = categoryNecessaryBean.getAppBeanList().get(position);
                Intent intent = new Intent(CategoryNecessaryActivity.this,AppDetailActivity.class);
                intent.putExtra("packageName",appBean.getPackageName());
                startActivity(intent);

            }

            @Override
            public boolean onItemLongClick(View view, RecyclerView.ViewHolder holder, Object o, int position) {
                return false;
            }
        });
    }

    @Override
    public void onCategoryNecessaryDataError(String msg) {

    }

    class CategoryNecessaryAdapter extends CommonAdapter<AppBean> {

        public CategoryNecessaryAdapter(Context context) {
            super(context, R.layout.applistitem_recommend);
        }
        @Override
        protected void convert(ViewHolder holder, AppBean appBean, int position) {
            holder.setText(R.id.appTitle,appBean.getName());
            holder.setText(R.id.app_size,appBean.getSizeDesc());
            holder.setText(R.id.app_des,appBean.getMemo());
            holder.setImageUrl(R.id.appicon,appBean.getIcon());
        }
    }
}
