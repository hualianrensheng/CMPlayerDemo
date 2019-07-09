package com.test.cmplayerdemo.adapter;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;

import com.bumptech.glide.util.Util;
import com.cm.recyclerviewlibrary.adapter.CommonAdapter;
import com.cm.recyclerviewlibrary.adapter.MultiItemTypeAdapter;
import com.cm.recyclerviewlibrary.base.ItemViewDelegate;
import com.cm.recyclerviewlibrary.base.ViewHolder;
import com.test.cmplayerdemo.R;
import com.test.cmplayerdemo.bean.AppBean;
import com.test.cmplayerdemo.bean.RecommendBean;

import java.util.List;

public class RecommendAdapter extends MultiItemTypeAdapter<RecommendBean.RecommendAppBean> {

    private Context mContext;

    private AppItemListener appItemListener;

    public void setAppItemListener(AppItemListener listener){
        this.appItemListener = listener;
    }

    public interface AppItemListener{
        void goAppDetaail(String packageName);
    }

    public RecommendAdapter(Context context, List<RecommendBean.RecommendAppBean> datas){
        super(context,datas);

        this.mContext = context;
        addItemViewDelegate(new AppDelegate());
        addItemViewDelegate(new AdDelegate());
    }

    /**
     * @info App列表的条目
     */
    public  class AppDelegate implements ItemViewDelegate<RecommendBean.RecommendAppBean>{
        @Override
        public int getItemViewLayoutId() {
            return R.layout.item_applist_horizontal;
        }

        @Override
        public boolean isForViewType(RecommendBean.RecommendAppBean item, int position) {
            return item.getType() == 0;
        }

        @Override
        public void convert(ViewHolder holder, final RecommendBean.RecommendAppBean recommendAppBean, int position) {
            holder.setText(R.id.tv_item_title,recommendAppBean.getTitle());
            RecyclerView rv = holder.getView(R.id.rv_applist_item);
            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(mContext) ;
            linearLayoutManager.setOrientation(LinearLayout.HORIZONTAL);
            rv.setLayoutManager(linearLayoutManager);
            AppItemAdapter adapter = new AppItemAdapter(mContext);
            adapter.addDataAll(recommendAppBean.getAppList());
            rv.setAdapter(adapter);

            adapter.setOnItemClickListener(new OnItemClickListener() {
                @Override
                public void onItemClick(View view, RecyclerView.ViewHolder holder, Object o, int position) {
                    if(appItemListener != null){
                        appItemListener.goAppDetaail(recommendAppBean.getAppList().get(position).getPackageName());


                    }
                }

                @Override
                public boolean onItemLongClick(View view, RecyclerView.ViewHolder holder, Object o, int position) {
                    return false;
                }
            });
        }
    }

    /**
     * @info 两张图片的广告条目
     */
    public class AdDelegate implements ItemViewDelegate<RecommendBean.RecommendAppBean>{
        @Override
        public int getItemViewLayoutId() {
            return R.layout.item_ad;
        }

        @Override
        public boolean isForViewType(RecommendBean.RecommendAppBean item, int position) {
            return item.getType() == 1;
        }

        @Override
        public void convert(ViewHolder holder, RecommendBean.RecommendAppBean recommendAppBean, int position) {
            holder.setImageUrl(R.id.iv_ad1,recommendAppBean.getIconList().get(0));
            holder.setImageUrl(R.id.iv_ad2,recommendAppBean.getIconList().get(1));
        }
    }

    public class AppItemAdapter extends CommonAdapter<AppBean> {

        public AppItemAdapter(Context context) {
            super(context, R.layout.item_app);
        }

        @Override
        protected void convert(ViewHolder holder, AppBean appBean, int position) {
            if(Util.isOnMainThread()) {
                holder.setImageUrl(R.id.iv_app_icon, appBean.getIcon());
            }
            holder.setText(R.id.tv_app_name,appBean.getName()) ;
            holder.setText(R.id.tv_app_size,appBean.getSizeDesc());
        }
    }
}
