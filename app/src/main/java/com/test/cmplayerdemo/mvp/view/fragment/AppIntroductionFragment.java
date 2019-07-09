package com.test.cmplayerdemo.mvp.view.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.format.Formatter;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.test.cmplayerdemo.R;
import com.test.cmplayerdemo.base.CMBaseMVPFragment;
import com.test.cmplayerdemo.bean.AppIntroductionBean;
import com.test.cmplayerdemo.mvp.parsenter.impl.AppIntroductionFragmentPresenterImpl;
import com.test.cmplayerdemo.mvp.view.activity.AppDetailActivity;
import com.test.cmplayerdemo.mvp.view.activity.GalleryActivity;
import com.test.cmplayerdemo.mvp.view.view.AppIntroductionFragmentView;
import com.test.cmplayerdemo.utils.UIUtils;
import com.test.cmplayerdemo.view.LoadingPager;
import com.test.cmplayerdemo.view.MultipleStatusView;
import com.test.cmplayerdemo.view.widget.FlowLayout;
import com.test.cmplayerdemo.view.widget.FoldingTextView;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * <p>Description:
 *
 * @author xzhang
 */

public class AppIntroductionFragment extends CMBaseMVPFragment<AppIntroductionFragmentPresenterImpl> implements AppIntroductionFragmentView,View.OnClickListener {

    @BindView(R.id.app_detail_gallery_container_linearlayout)
    LinearLayout app_detail_gallery_container ;
    @BindView(R.id.detail_appinfo_tariff_textview)
    TextView appInfoTariff ;
    @BindView(R.id.detail_appinfo_size_textview)
    TextView appInfoSize ;
    @BindView(R.id.detail_appinfo_version_textview)
    TextView appInfoVersion ;
    @BindView(R.id.detail_appinfo_release_date_textview)
    TextView appInfoDate ;
    @BindView(R.id.appdetail_appinfo_developer_textview)
    TextView appInfoDeveloper ;
    @BindView(R.id.ll)
    LinearLayout appInfoDes ;
    @BindView(R.id.flowLayout)
    FlowLayout flowLayout ;

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        show();
    }

    @Inject
    public AppIntroductionFragmentPresenterImpl appIntroductionFragmentPresenter ;

    private AppIntroductionBean mAppIntroductionBean ;

    @Override
    public void onAppIntroductionDataSuccess(AppIntroductionBean appIntroductionBean) {
        mAppIntroductionBean = appIntroductionBean ;
        setState(LoadingPager.LoadResuslt.success);
    }

    @Override
    public void onAppIntroductionDataError(String msg) {
        setState(LoadingPager.LoadResuslt.error);
    }

    @Override
    protected AppIntroductionFragmentPresenterImpl initInjector() {
        mFragmentComponent.inject(this);
        return appIntroductionFragmentPresenter;
    }

    @Override
    public void onClick(View v) {
        int tag = (int) v.getTag();
        List<String> images = mAppIntroductionBean.getImagesList();
        Intent intent = new Intent(getContext(), GalleryActivity.class);
        intent.putExtra("tag",tag) ;
        intent.putStringArrayListExtra("urlList", (ArrayList<String>) images);
        getActivity().startActivity(intent) ;
    }

    public void initView() {
        for(int i = 0 ;i < mAppIntroductionBean.getImageCompressList().size() ; i ++){
            String url = mAppIntroductionBean.getImageCompressList().get(i);
            View screenView = View.inflate(getContext(),R.layout.appdetail_item_screen_image,null) ;
            ImageView screenImageView = (ImageView) screenView.findViewById(R.id.appdetail_screen_img_imageview);
            screenImageView.setContentDescription(screenImageView.getResources().getString(R.string.appdetail_screenshot));
            screenImageView.setScaleType(ImageView.ScaleType.FIT_XY);
            screenView.setOnClickListener(this);
            screenView.setTag(i);
            Glide.with(UIUtils.getContext()).load(url).into(screenImageView);
            app_detail_gallery_container.addView(screenView);
        }

        appInfoTariff.setText(mAppIntroductionBean.getAppInfoBean().getTariffDesc());
        appInfoSize.setText(Formatter.formatFileSize(getContext(),Long.parseLong(mAppIntroductionBean.getAppInfoBean().getSize())));
        appInfoDate.setText(mAppIntroductionBean.getAppInfoBean().getReleaseDate());
        appInfoVersion.setText(mAppIntroductionBean.getAppInfoBean().getVersion());
        appInfoDeveloper.setText(mAppIntroductionBean.getAppInfoBean().getDeveloper());

        for(int i = 0 ; i < mAppIntroductionBean.getAppDetailInfoBeanList().size() ; i ++){
            FoldingTextView foldingTextView = new FoldingTextView(getContext()) ;
            foldingTextView.setTitle(mAppIntroductionBean.getAppDetailInfoBeanList().get(i).getTitle());
            foldingTextView.setContent(mAppIntroductionBean.getAppDetailInfoBeanList().get(i).getBody());
            appInfoDes.addView(foldingTextView);
        }

        List<String> tagList = mAppIntroductionBean.getTagList();
        for(int i = 0 ; i < tagList.size() ; i ++){
            View labView = UIUtils.inflate(R.layout.appdetail_item_label_item) ;
            TextView tv = (TextView) labView.findViewById(R.id.appdetail_label_content_textview);
            tv.setText(tagList.get(i));
            flowLayout.addView(labView);
        }
    }

    @Override
    public View createSuccessView() {
        View view = UIUtils.inflate(R.layout.fragment_app_introduction) ;
        ButterKnife.bind(this,view) ;
        initView();
        return view;
    }

    @Override
    public void load() {
        appIntroductionFragmentPresenter.getAppIntroductionData(mCMBaseActivity,((AppDetailActivity)getActivity()).getAppPackageName());
    }
}
