package com.test.cmplayerdemo.mvp.view.view;

import com.test.cmplayerdemo.base.mvpbase.BaseView;
import com.test.cmplayerdemo.bean.CategoryBean;

public interface CategoryFragmentView extends BaseView {

    void onCategoryDataSuccess(CategoryBean categoryBean);

    void onCategoryDataFaild(String msg);
}
