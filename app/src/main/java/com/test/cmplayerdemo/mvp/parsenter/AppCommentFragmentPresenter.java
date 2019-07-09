package com.test.cmplayerdemo.mvp.parsenter;

import com.test.cmplayerdemo.base.CMBaseActivity;
import com.test.cmplayerdemo.base.mvpbase.BasePresenter;
import com.test.cmplayerdemo.mvp.view.view.AppCommentFragmentView;

/**
 * <p>Description:
 *
 * @author xzhang
 */

public interface AppCommentFragmentPresenter extends BasePresenter<AppCommentFragmentView> {
    void getAppCommentData(CMBaseActivity activity, String packageName);
}
