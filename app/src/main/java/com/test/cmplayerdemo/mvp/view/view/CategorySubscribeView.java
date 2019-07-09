package com.test.cmplayerdemo.mvp.view.view;


import com.test.cmplayerdemo.base.mvpbase.BaseView;
import com.test.cmplayerdemo.bean.CategorySubscribeBean;

public interface CategorySubscribeView extends BaseView {
    void onCategorySubscribeDataSuccess(CategorySubscribeBean categorySubscribeBean) ;
    void onCategorySubscribeDataError(String msg);
}
