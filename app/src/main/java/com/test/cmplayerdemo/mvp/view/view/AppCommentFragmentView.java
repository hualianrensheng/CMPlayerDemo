package com.test.cmplayerdemo.mvp.view.view;


import com.test.cmplayerdemo.base.mvpbase.BaseView;
import com.test.cmplayerdemo.bean.AppCommentBean;

/**
 * <p>Description:
 *
 * @author xzhang
 */

public interface AppCommentFragmentView extends BaseView {
    void onAppCommentDataSuccess(AppCommentBean appCommentBean);
    void onAppCommentDataError(String msg) ;
}
