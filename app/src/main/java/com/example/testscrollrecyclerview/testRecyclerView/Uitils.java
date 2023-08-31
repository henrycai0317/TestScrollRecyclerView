package com.example.testscrollrecyclerview.testRecyclerView;

import android.app.Activity;
import android.os.Build;
import android.util.DisplayMetrics;
import android.view.Display;

public class Uitils {

    public static int getDeviceWidth(Activity pActivity) {
        if( pActivity != null) {
            DisplayMetrics outMetrics = new DisplayMetrics();
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
                Display iDisplay = pActivity.getDisplay();
                iDisplay.getRealMetrics(outMetrics);
            } else {
                Display iDisplay = pActivity.getWindowManager().getDefaultDisplay();
                iDisplay.getMetrics(outMetrics);
            }

            return outMetrics.widthPixels;
        } else {
            return 0;
        }
    }
}
