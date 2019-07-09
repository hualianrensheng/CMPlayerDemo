package com.test.cmplayerdemo.mvp.view.activity;

import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.widget.LinearLayout;

import com.test.cmplayerdemo.FixPagerAdapter;
import com.test.cmplayerdemo.R;
import com.test.cmplayerdemo.base.CMBaseActivity;
import com.test.cmplayerdemo.factory.FragmentFactory;
import com.test.cmplayerdemo.base.BaseFragment;

import java.util.ArrayList;
import java.util.List;

public class HomeActivity extends CMBaseActivity {

    private FixPagerAdapter fixPagerAdapter;
    private String[] titles = {"推荐", "分类", "排行", "管理", "我的"};
    private List<Fragment> fragments;

    private TabLayout tabLayout;
    private ViewPager mainViewPager;
    private LinearLayout statusBar;

    @Override
    protected void showToast(String msg) {

    }

    @Override
    protected void initLayout() {
        setContentView(R.layout.activity_home);

        tabLayout = (TabLayout) findViewById(R.id.tab_layout);
        mainViewPager = (ViewPager) findViewById(R.id.main_viewpager);
        statusBar = (LinearLayout) findViewById(R.id.status_bar);
    }

    @Override
    protected void initView() {
        initViewPagerFragment();
    }

    private void initViewPagerFragment() {

        fixPagerAdapter = new FixPagerAdapter(getSupportFragmentManager());
        fragments = new ArrayList<>();

        for (int i = 0; i < titles.length; i++) {

            fragments.add(FragmentFactory.createFragment(i));
        }

        fixPagerAdapter.setTitles(titles);
        fixPagerAdapter.setFragments(fragments);


        mainViewPager.setAdapter(fixPagerAdapter);

        tabLayout.setupWithViewPager(mainViewPager);
        tabLayout.setTabMode(TabLayout.MODE_FIXED);

        mainViewPager.addOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                super.onPageScrolled(position, positionOffset, positionOffsetPixels);

                BaseFragment fragment = (BaseFragment) FragmentFactory.createFragment(position);
                fragment.show();
            }
        });
    }
}
