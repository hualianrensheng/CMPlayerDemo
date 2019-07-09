package com.test.cmplayerdemo.adapter.top;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.GridView;

import com.cm.recyclerviewlibrary.wrapper.HeaderAndFooterWrapper;
import com.test.cmplayerdemo.R;
import com.test.cmplayerdemo.bean.TopBean;
import com.test.cmplayerdemo.utils.UIUtils;

import java.util.List;

public class TopTopWrapper extends HeaderAndFooterWrapper {

    private Context mContext ;
    private View headerView ;
    private List<TopBean.TopTopBean> topBeanList ;

    private GridView gv_title_grid ;


    public TopTopWrapper(Context context , RecyclerView.Adapter adapter) {
        super(adapter);
        this.mContext = context ;
        headerView = UIUtils.inflate(R.layout.header_top) ;
        gv_title_grid = (GridView) headerView.findViewById(R.id.gv_title_grid);
        addHeaderView(headerView);
    }

    public void addTopView() {
        addHeaderView(headerView);
    }

    public void deleteTopView() {
        deleteHeaderView(headerView);
    }

    public void addDataAll(List<TopBean.TopTopBean> list) {
        this.topBeanList = list ;

        TopSubAdapter adapter = new TopSubAdapter(mContext,topBeanList);
        gv_title_grid.setNumColumns(topBeanList.size());
        gv_title_grid.setAdapter(adapter);

    }

    public void clearData() {
        if(topBeanList != null)
            topBeanList.clear();
    }
}
