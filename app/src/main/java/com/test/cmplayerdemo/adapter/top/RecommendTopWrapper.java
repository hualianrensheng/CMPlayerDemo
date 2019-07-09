package com.test.cmplayerdemo.adapter.top;

import android.content.Context;
import android.support.v7.widget.RecyclerView;

import com.cm.recyclerviewlibrary.wrapper.HeaderAndFooterWrapper;
import com.test.cmplayerdemo.banner.RecommendController;

import java.util.List;

public class RecommendTopWrapper extends HeaderAndFooterWrapper {

    private Context mContext;
    private RecommendController mController;

    public RecommendTopWrapper(Context context, RecyclerView.Adapter adapter) {
        super(adapter);
        this.mContext = context;

        mController = new RecommendController(mContext);
        addHeaderView(mController.getContentView());
    }

    public void addData(List<String> urls){
        if(mController != null){
            mController.setData(urls);
        }
    }

}
