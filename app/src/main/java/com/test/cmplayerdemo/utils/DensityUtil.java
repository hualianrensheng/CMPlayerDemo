package com.test.cmplayerdemo.utils;

import android.content.Context;
import android.util.DisplayMetrics;
import android.view.WindowManager;

public final class DensityUtil {

    private static DisplayMetrics displayMetrics;

    public static int getDensity(Context context, int density) {
        if (displayMetrics == null) {
            displayMetrics = new DisplayMetrics();
            if (context != null)
                ((WindowManager) context.getSystemService(Context.WINDOW_SERVICE)).getDefaultDisplay().getMetrics(displayMetrics);
        }
        return (int) (density * displayMetrics.density);
    }


}
