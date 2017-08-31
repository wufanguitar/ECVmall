package com.frank.vmall.utils.dimen;

import android.content.res.Resources;
import android.util.DisplayMetrics;

import com.frank.vmall.app.Mall;

/**
 * Created by Administrator on 2017/8/29 0029.
 */

public class DimenUtil {

    public static int getScreenWidth() {
        final Resources resources = Mall.getApplicationContext().getResources();
        final DisplayMetrics dm = resources.getDisplayMetrics();
        return dm.widthPixels;
    }

    public static int getScreenHeight() {
        final Resources resources = Mall.getApplicationContext().getResources();
        final DisplayMetrics dm = resources.getDisplayMetrics();
        return dm.heightPixels;
    }
}
