package com.test.cmplayerdemo.mvp.view.view;


import com.test.cmplayerdemo.base.mvpbase.BaseView;

import java.util.List;

public interface CategorySubjectView extends BaseView {
    void onCategorySubjectDataSuccess(List<String> list) ;
    void onCategorySubjectDataError(String msg);
}
