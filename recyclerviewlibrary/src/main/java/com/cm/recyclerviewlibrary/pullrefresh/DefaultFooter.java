package com.cm.recyclerviewlibrary.pullrefresh;

import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.cm.recyclerviewlibrary.R;
import com.pnikosis.materialishprogress.ProgressWheel;


public class DefaultFooter extends BaseIndicator {
    private TextView mStringIndicator;
    private ProgressWheel progress_header_wheell;
    private int default_rim_color;

    @Override
    public View createView(LayoutInflater inflater, ViewGroup parent) {
        ViewGroup v = (ViewGroup) inflater.inflate(R.layout.prj_ptr_footer_default, parent, true);
        View child = v.getChildAt(v.getChildCount() - 1);
        mStringIndicator = (TextView) child.findViewById(R.id.tv_footer);
        progress_header_wheell = (ProgressWheel) v.findViewById(R.id.progress_footer_wheell);
        default_rim_color = progress_header_wheell.getRimColor();
        return child;
    }

    @Override
    public void onAction() {
        mStringIndicator.setText("放开加载更多");
    }

    @Override
    public void onUnaction() {
        mStringIndicator.setText("上拉加载更多");
    }

    @Override
    public void onRestore() {
        mStringIndicator.setText("上拉加载更多");
        progress_header_wheell.setRimColor(default_rim_color);
        progress_header_wheell.stopSpinning();
    }

    @Override
    public void onLoading() {
        mStringIndicator.setText("加载中...");
        progress_header_wheell.setRimColor(Color.parseColor("#00000000"));
        progress_header_wheell.spin();
    }
}
