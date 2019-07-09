package com.cm.recyclerviewlibrary.pullrefresh;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public abstract class BaseIndicator {
    public abstract View createView(LayoutInflater inflater, ViewGroup parent);
    public abstract void onAction();
    public abstract void onUnaction();
    public abstract void onRestore();
    public abstract void onLoading();
}
