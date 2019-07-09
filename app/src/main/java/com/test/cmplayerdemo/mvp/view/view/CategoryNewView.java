package com.test.cmplayerdemo.mvp.view.view;


import com.test.cmplayerdemo.base.mvpbase.BaseView;
import com.test.cmplayerdemo.bean.CategoryNewBean;

public interface CategoryNewView extends BaseView {
    void onCategoryNewDataSuccess(CategoryNewBean categoryNewBean);
    void onCategoryNewDataError(String msg);
}
