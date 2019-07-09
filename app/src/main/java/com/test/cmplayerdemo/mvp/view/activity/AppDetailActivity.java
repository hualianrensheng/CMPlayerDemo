package com.test.cmplayerdemo.mvp.view.activity;

import android.os.Bundle;
import android.os.Environment;
import android.os.SystemClock;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.text.format.Formatter;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.cm.rxretrofitlibrary.download.DownInfo;
import com.cm.rxretrofitlibrary.download.DownState;
import com.cm.rxretrofitlibrary.download.HttpDownManager;
import com.cm.rxretrofitlibrary.utils.DbDownUtil;
import com.test.cmplayerdemo.R;
import com.test.cmplayerdemo.adapter.AppDetailPagerAdapter;
import com.test.cmplayerdemo.base.CMBaseMVPActivity;
import com.test.cmplayerdemo.bean.AppDetailBean;
import com.test.cmplayerdemo.mvp.parsenter.impl.AppDetailPresenterImpl;
import com.test.cmplayerdemo.mvp.view.fragment.AppCommentFragment;
import com.test.cmplayerdemo.mvp.view.fragment.AppIntroductionFragment;
import com.test.cmplayerdemo.mvp.view.fragment.AppRecommendFragment;
import com.test.cmplayerdemo.mvp.view.view.AppDetailActivityView;
import com.test.cmplayerdemo.utils.AppInfoUtils;
import com.test.cmplayerdemo.utils.UIUtils;
import com.test.cmplayerdemo.view.widget.DetailShareButton;
import com.test.cmplayerdemo.view.widget.DownloadProgressButton;
import com.test.cmplayerdemo.view.widget.SubTabNavigator;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;

public class AppDetailActivity extends CMBaseMVPActivity<AppDetailPresenterImpl> implements AppDetailActivityView,HttpDownManager.DownloadObserver {

    private final static String TAG = "AppDetailActivity";



    @BindView(R.id.title_text)
    TextView title_text ;
    @BindView(R.id.iv_search)
    ImageView iv_search ;



    @BindView(R.id.detail_head_app_icon_imageview)
    ImageView detail_app_icon ;
    @BindView(R.id.detail_head_app_name_textview)
    TextView detail_app_name ;
    @BindView(R.id.detail_head_download_count_textview)
    TextView detail_app_download_count ;
    @BindView(R.id.detail_head_app_stars_ratingbar)
    RatingBar detail_app_stars ;
    @BindView(R.id.detail_head_label_layout_linearlayout)
    RelativeLayout detail_head_label_layout ;
    @BindView(R.id.detail_head_label_icon_layout_linearlayout)
    LinearLayout detail_head_label_icon_layout ;
    @BindView(R.id.detail_head_lable_folding_textview)
    TextView detail_head_lable_folding ;
    @BindView(R.id.detail_head_safe_icon_container_linearlayout)
    LinearLayout detail_head_safe_icon_container ;
    @BindView(R.id.detail_head_safe_icon_layout_linearlayout)
    LinearLayout detail_head_safe_icon_layout ;
    @BindView(R.id.subTab)
    SubTabNavigator subTabNavigator ;
    @BindView(R.id.vp)
    ViewPager mViewPager ;
    @BindView(R.id.appdetail_head)
    LinearLayout appdetail_head ;
    @BindView(R.id.detail_download_button)
    DownloadProgressButton detail_download_button ;
    @BindView(R.id.detail_download_share_button)
    DetailShareButton detail_download_share_button ;
    @BindView(R.id.detail_download_comment_button_linearlayout)
    LinearLayout detail_download_comment_button_linearlayout ;



    @Inject
    public AppDetailPresenterImpl appDetailPresenter;

    private String packageName ;

    private AppDetailBean appDetailBean ;
    protected boolean expand = false;

    private DbDownUtil dbUtil;
    private HttpDownManager manager;
    private DownInfo downInfo ;
    private File outputFile ;

    private List<Fragment> fragments = null ;

    public String getAppPackageName(){
        return packageName ;
    }

    @Override
    protected AppDetailPresenterImpl initInjector() {
        mActivityComponent.inject(this);
        return appDetailPresenter;
    }
    @Override
    public void onDownloadStateChanged(DownInfo info) {

    }

    @Override
    public void onDownloadProgressed(DownInfo info) {
        if(downInfo != null && info.getId() == downInfo.getId()) {
            detail_download_button.setProgress((int) (100 * info.getReadLength() / info.getCountLength()));
        }
    }



    @Override
    protected void initLayout() {
        setContentView(R.layout.activity_app_detail2);
    }

    @Override
    protected void initView() {
        iv_search.setVisibility(View.VISIBLE);
        title_text.setText(getResources().getString(R.string.title_activity_app_detail));
    }
    @Override
    protected void initData(){
        packageName = getIntent().getStringExtra("packageName");
        manager= HttpDownManager.getInstance();
        manager.registerObserver(this);
        dbUtil= DbDownUtil.getInstance();
        downInfo = dbUtil.queryDownBy(packageName.hashCode());
        if(downInfo == null) {
            outputFile = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS), packageName + ".apk");
        }
        appDetailPresenter.getAppDetailData(this,packageName);
    }

    @Override
    public void onAppDetailDataSuccess(AppDetailBean appDetailBean) {
        this.appDetailBean = appDetailBean ;
        Log.i(TAG,appDetailBean.getName());
        setDetailHead();
    }

    private void setDetailHead(){
        Glide.with(UIUtils.getContext()).load(appDetailBean.getIcoUrl()).into(detail_app_icon) ;
        Log.e(TAG,appDetailBean.getIcoUrl());
        detail_app_name.setText(appDetailBean.getName());
        detail_app_download_count.setText(appDetailBean.getIntro());
        detail_app_stars.setRating(Float.parseFloat(appDetailBean.getStars())) ;

        setLabel();
        setSafeLabel();
        setSubTab();
        setDetailDown();

        detail_head_label_layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(expand){
                    expand = false ;
                    detail_head_safe_icon_layout.setVisibility(View.GONE);
                    detail_head_lable_folding.setBackgroundResource(R.drawable.ic_public_arrow_down);
                }else {
                    expand = true ;
                    detail_head_safe_icon_layout.setVisibility(View.VISIBLE);
                    detail_head_lable_folding.setBackgroundResource(R.drawable.ic_public_arrow_up);
                }
            }
        });
    }

    private void setLabel(){
        for(AppDetailBean.LabelName labelNamesBean : appDetailBean.getLabelNameList()){
            View labelView = UIUtils.inflate(R.layout.appdetail_item_head_label_item);
            TextView label = (TextView) labelView.findViewById(R.id.appdetail_head_label_textview);
            label.setText(labelNamesBean.getName());
            if(labelNamesBean.getType() == 1){
                label.setTextColor(getResources().getColor(R.color.app_not_safe_textcolor));
            }
            detail_head_label_icon_layout.addView(labelView);
        }
    }

    private void setSafeLabel(){
        for(AppDetailBean.SafeLabel safeLabelsBean : appDetailBean.getSafeLabelList()){
            View safeLabelView = UIUtils.inflate(R.layout.appdetail_item_head_safe_item);
            TextView safe_checker = (TextView) safeLabelView.findViewById(R.id.safe_checker_textview);
            TextView safe_checker_label = (TextView) safeLabelView.findViewById(R.id.safe_checker_label);
            ImageView detail_head_app_icon = (ImageView) safeLabelView.findViewById(R.id.detail_head_app_icon_imageview);
            TextView detail_safe_desc_textview = (TextView) safeLabelView.findViewById(R.id.detail_safe_desc_textview);

            safe_checker.setText(safeLabelsBean.getDetectorName());
            safe_checker_label.setText(safeLabelsBean.getDetectorDesc());
            Glide.with(UIUtils.getContext()).load(safeLabelsBean.getUrl()).into(detail_head_app_icon);
            detail_safe_desc_textview.setText(safeLabelsBean.getName());

            detail_head_safe_icon_container.addView(safeLabelView);
        }
    }

    private void setSubTab(){
        fragments = new ArrayList<>() ;
        AppDetailPagerAdapter appDetailPagerAdapter = new AppDetailPagerAdapter(getSupportFragmentManager()) ;
        AppIntroductionFragment appIntroductionFragment = new AppIntroductionFragment() ;
        AppCommentFragment appCommentFragment = new AppCommentFragment() ;
        AppRecommendFragment appRecommendFragment = new AppRecommendFragment() ;

        fragments.add(appIntroductionFragment);
        fragments.add(appCommentFragment);
        fragments.add(appRecommendFragment);

        appDetailPagerAdapter.setFragments(fragments);
        mViewPager.setAdapter(appDetailPagerAdapter);
        mViewPager.setOnPageChangeListener(subTabNavigator);
        subTabNavigator.setViewPager(mViewPager);

        subTabNavigator.setLeftText(appDetailBean.getTabInfoList().get(0));
        subTabNavigator.setNoneText(appDetailBean.getTabInfoList().get(1));
        subTabNavigator.setRightText(appDetailBean.getTabInfoList().get(2));
    }

    private void setDetailDown(){
        if(downInfo == null) {
            detail_download_button.setStartText("安装 " + Formatter.formatFileSize(UIUtils.getContext(),
                    Long.parseLong(appDetailBean.getSize())));
        }else{
            if(downInfo.getState() == DownState.DOWN){
                detail_download_button.setState(DownloadProgressButton.STATUS_PROGRESS_BAR_DOWNLOADING);
                manager.startDown(downInfo);
            }else if(downInfo.getState() == DownState.PAUSE){
                detail_download_button.setState(DownloadProgressButton.STATUS_PROGRESS_BAR_PAUSE);
            }else if(downInfo.getState() == DownState.FINISH){
                detail_download_button.setState(DownloadProgressButton.STATUS_PROGRESS_BAR_BEGIN);
            }
            detail_download_button.setProgress((int) (100 * downInfo.getReadLength()/downInfo.getCountLength()));
        }

        detail_download_button.setStateChangeListener(new DownloadProgressButton.StateChangeListener() {
            @Override
            public void onPauseTask() {
                manager.pause(downInfo);
            }

            @Override
            public void onFinishTask() {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        SystemClock.sleep(3000);
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                AppInfoUtils.install(downInfo.getSavePath());
                                if(dbUtil != null && downInfo != null)
                                    dbUtil.update(downInfo);
                            }
                        });
                    }
                }).start();

            }

            @Override
            public void onLoadingTask() {
                detail_download_button.setMax(100);

                if(downInfo == null){
                    downInfo = new DownInfo(appDetailBean.getDownloadUrl());
                    downInfo.setId((long) packageName.hashCode());
                    downInfo.setState(DownState.START);
                    downInfo.setSavePath(outputFile.getAbsolutePath());
                    dbUtil.save(downInfo);

                }
                if(downInfo.getState()!= DownState.FINISH){
                    manager.startDown(downInfo);
                }
            }
        });
    }

    @Override
    public void onAppDetailDataError(String msg) {

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if(manager != null){
            manager.unRegisterObserver(this);
            if(downInfo != null)
                dbUtil.update(downInfo);
        }
    }
}
