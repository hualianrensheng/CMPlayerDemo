package com.test.cmplayerdemo.mvp.view.view;


import com.test.cmplayerdemo.base.mvpbase.BaseView;
import com.test.cmplayerdemo.bean.CategoryNecessaryBean;

public interface CategoryNecessaryView extends BaseView {
    void onCategoryNecessaryDataSuccess(CategoryNecessaryBean categoryNecessaryBean);
    void onCategoryNecessaryDataError(String msg) ;
}
