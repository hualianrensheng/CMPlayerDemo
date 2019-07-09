package com.test.cmplayerdemo.mvp.view.activity;

import android.graphics.drawable.AnimationDrawable;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;


import com.test.cmplayerdemo.R;
import com.test.cmplayerdemo.base.CMBaseActivity;

import butterknife.BindView;

public class ApkManagementActivity extends CMBaseActivity {

    @BindView(R.id.title_text)
    TextView title_text ;
    @BindView(R.id.progressImg)
    ImageView progressImg ;
    @BindView(R.id.nodata_localpkg_refresh)
    TextView nodata_localpkg_refresh ;

    @Override
    protected void initLayout() {
        setContentView(R.layout.activity_apk_management);
    }

    @Override
    protected void initView() {

        title_text.setText("安装包管理");

        nodata_localpkg_refresh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                progressImg.setVisibility(View.VISIBLE);
                AnimationDrawable drawable = (AnimationDrawable) progressImg.getDrawable();
                drawable.start();
            }
        });



    }

    @Override
    protected void showToast(String msg) {

    }
}
