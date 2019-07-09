package com.test.cmplayerdemo.mvp.view.view;


import com.test.cmplayerdemo.base.mvpbase.BaseView;
import com.test.cmplayerdemo.bean.CategoryToolBean;

public interface CategoryToolActivityView extends BaseView {
    void onCategoryToolDataSuccess(CategoryToolBean categoryToolBean);
    void onCategoryToolError(String msg);
}
