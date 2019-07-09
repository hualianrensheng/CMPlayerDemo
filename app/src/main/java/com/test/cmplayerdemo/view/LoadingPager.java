package com.test.cmplayerdemo.view;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;
import android.widget.FrameLayout;

import com.test.cmplayerdemo.R;
import com.test.cmplayerdemo.utils.UIUtils;

public abstract class LoadingPager extends FrameLayout {


    //默认状态
    private final static int STATE_UNKOWN = 0;
    //加载中状态
    private final static int STATE_LOADING = 1;
    //加载失败状态
    private final static int STATE_ERROR = 2;
    //加载成功状态
    private final static int STATE_SUCCESS = 3;
    //加载空状态
    private final static int STATE_EMPTY = 4;

    //当前状态
    private int state = STATE_UNKOWN;


    private View loadingView;
    private View errorView;
    private View emptyView;
    private View successView;

    public LoadingPager(@NonNull Context context) {
        super(context);
        init();
    }

    public LoadingPager(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public LoadingPager(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    public LoadingPager(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init();
    }


    private void init() {
        if(loadingView == null){
            loadingView = createLoadingView();
            this.addView(loadingView,new FrameLayout.LayoutParams(FrameLayout.LayoutParams.MATCH_PARENT,FrameLayout.LayoutParams.MATCH_PARENT));
        }

        if(errorView == null){
            errorView = createErrorView();
            this.addView(errorView,new FrameLayout.LayoutParams(FrameLayout.LayoutParams.MATCH_PARENT,FrameLayout.LayoutParams.MATCH_PARENT));
        }

        if(emptyView == null){
            emptyView = createEmptyView();
            this.addView(emptyView,new FrameLayout.LayoutParams(FrameLayout.LayoutParams.MATCH_PARENT,FrameLayout.LayoutParams.MATCH_PARENT));
        }

        showPage();
    }

    private void showPage() {

        if(loadingView != null){
            loadingView.setVisibility(state == STATE_UNKOWN || state == STATE_LOADING ? View.VISIBLE : View.GONE);
        }
        if(errorView != null){
            errorView.setVisibility(state == STATE_ERROR ? View.VISIBLE : View.GONE);
        }
        if(emptyView != null){
            emptyView.setVisibility(state == STATE_EMPTY ? View.VISIBLE : View.GONE);
        }
        if(state == STATE_SUCCESS && successView == null){
            successView = createSuccessView();
            this.addView(successView,new FrameLayout.LayoutParams(FrameLayout.LayoutParams.MATCH_PARENT,FrameLayout.LayoutParams.MATCH_PARENT));
        }

    }

    /**
     * @info 请求网络，修改状态
     */
    public void show() {
        if(state == STATE_UNKOWN || state == STATE_ERROR || state == STATE_EMPTY){
            state = STATE_LOADING;
            load();
        }
        showPage();
    }

    public abstract void load();

    public void setState(LoadResuslt result){
        state = result.getValue();
        UIUtils.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                showPage();
            }
        });
    }

    public enum LoadResuslt{
        error(STATE_ERROR),success(STATE_SUCCESS),empty(STATE_EMPTY);
        int value;
        LoadResuslt(int value){
            this.value = value;
        }
        public int getValue(){
            return value;
        }
    }


    private View createLoadingView() {
        return UIUtils.inflate(R.layout.loading_page);
    }


    private View createErrorView() {
        View view = UIUtils.inflate(R.layout.loading_error_page);
        view.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                show();
            }
        });
        return view;
    }

    private View createEmptyView() {
        return UIUtils.inflate(R.layout.loading_page_empty);
    }

    public abstract View createSuccessView();


}
