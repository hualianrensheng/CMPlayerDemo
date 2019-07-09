package com.test.cmplayerdemo.mvp.view.fragment;

import android.content.Intent;
import android.view.View;
import android.widget.TextView;

import com.test.cmplayerdemo.R;
import com.test.cmplayerdemo.base.CMBaseMVPFragment;
import com.test.cmplayerdemo.mvp.parsenter.impl.AppManagerFragmentPresenterImpl;
import com.test.cmplayerdemo.mvp.view.activity.ApkManagementActivity;
import com.test.cmplayerdemo.mvp.view.activity.CleanCacheActivity;
import com.test.cmplayerdemo.mvp.view.activity.InstallAppInfoActivity;
import com.test.cmplayerdemo.mvp.view.view.AppManagerFragmentView;
import com.test.cmplayerdemo.utils.UIUtils;
import com.test.cmplayerdemo.view.LoadingPager;
import com.test.cmplayerdemo.view.widget.EnterLayout;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

public class AppManagerFragment extends CMBaseMVPFragment<AppManagerFragmentPresenterImpl> implements AppManagerFragmentView {

    @BindView(R.id.update_label_textview)
    TextView tvUpdateLabel ;
    @BindView(R.id.update_label_subtitle)
    TextView tvUpdateLabelSubtitle ;
    @BindView(R.id.install_manager_layout)
    EnterLayout installManager ;
    @BindView(R.id.apk_manager_layout)
    EnterLayout apkManager ;
    @BindView(R.id.system_manager_layout)
    EnterLayout systemManager ;
    @BindView(R.id.connect_computer)
    EnterLayout connect ;

    @Inject
    public AppManagerFragmentPresenterImpl mAppManagerFragmentPresenter;

    @Override
    protected AppManagerFragmentPresenterImpl initInjector() {

        mFragmentComponent.inject(this);
        return mAppManagerFragmentPresenter;
    }



    @Override
    public void load() {
//        mAppManagerFragmentPresenter.getAppManagerData();
        setState(LoadingPager.LoadResuslt.success);
    }

    @Override
    public View createSuccessView() {
        View view = UIUtils.inflate(R.layout.fragment_manager) ;
        ButterKnife.bind(this,view) ;

        tvUpdateLabel.setText(UIUtils.getString(R.string.update_manager_title));
        tvUpdateLabelSubtitle.setText(UIUtils.getString(R.string.update_manager_subtitle_version_new));
        installManager.setTitle(UIUtils.getString(R.string.install_manager_title_ex));
        installManager.setMemo(UIUtils.getString(R.string.install_manager_subtitle));
        apkManager.setTitle(UIUtils.getString(R.string.apk_management));
        apkManager.setMemo(UIUtils.getString(R.string.apkmanage_tips_modify));
        systemManager.setTitle(UIUtils.getString(R.string.system_manager_title));
        systemManager.setMemo(UIUtils.getString(R.string.system_manager_memo));
        connect.setTitle(UIUtils.getString(R.string.connect_pc));
        connect.setMemo(UIUtils.getString(R.string.manager_phone_by_pc));

        installManager.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mCMBaseActivity.startActivity(new Intent(getContext(),InstallAppInfoActivity.class));
            }
        });

        apkManager.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mCMBaseActivity.startActivity(new Intent(getContext(), ApkManagementActivity.class));
            }
        });

        systemManager.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mCMBaseActivity.startActivity(new Intent(getContext(), CleanCacheActivity.class));
            }
        });

        return view ;
    }

    @Override
    public void onAppManagerDataSuccess() {
        setState(LoadingPager.LoadResuslt.success);
    }

    @Override
    public void onAppManagerDataFaild() {
        setState(LoadingPager.LoadResuslt.error);
    }

    @Override
    public void showToast(String msg) {

    }
}
