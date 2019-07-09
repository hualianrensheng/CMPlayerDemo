package com.test.cmplayerdemo.mvp.view.view;

import com.test.cmplayerdemo.base.mvpbase.BaseView;
import com.test.cmplayerdemo.bean.TopBean;

public interface TopFragmentView extends BaseView {

    void onTopDataSuccess(TopBean topBean);

    void onTopDataFaild(String msg);
}
