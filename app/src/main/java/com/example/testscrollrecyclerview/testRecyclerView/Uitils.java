package com.example.testscrollrecyclerview.testRecyclerView;

import android.app.Activity;
import android.os.Build;
import android.util.DisplayMetrics;
import android.view.Display;

public class Uitils {
    static int mDeviceWidth = -1;
    public static void getDeviceWidth(Activity pActivity) {
        if( pActivity != null && mDeviceWidth == -1) {
            DisplayMetrics outMetrics = new DisplayMetrics();
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
                Display iDisplay = pActivity.getDisplay();
                iDisplay.getRealMetrics(outMetrics);
            } else {
                Display iDisplay = pActivity.getWindowManager().getDefaultDisplay();
                iDisplay.getMetrics(outMetrics);
            }
            mDeviceWidth = outMetrics.widthPixels;
        }
    }
}
