package com.test.cmplayerdemo.view.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ScrollView;

/**
 * Created by xzhang on 2017/5/25.
 */

public class ScroScrollView extends ScrollView {
    public ScroScrollView(Context context) {
        super(context);
    }

    public ScroScrollView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public ScroScrollView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public ScroScrollView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }


    private ScrollViewListener scrollViewListener = null;


    public interface ScrollViewListener {
        void onScrollChanged(ScroScrollView scrollView, int x, int y, int oldx, int oldy);
    }

    public void setScrollViewListener(ScrollViewListener scrollViewListener) {
        this.scrollViewListener = scrollViewListener;
    }

    @Override
    protected void onScrollChanged(int x, int y, int oldx, int oldy) {
        super.onScrollChanged(x, y, oldx, oldy);
        if (scrollViewListener != null) {
            scrollViewListener.onScrollChanged(this, x, y, oldx, oldy);
        }
    }
}
