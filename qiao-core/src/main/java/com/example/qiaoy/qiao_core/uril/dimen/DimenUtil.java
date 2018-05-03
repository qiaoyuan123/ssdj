package com.example.qiaoy.qiao_core.uril.dimen;

import android.content.res.Resources;
import android.util.DisplayMetrics;

import com.example.qiaoy.qiao_core.app.Core;

public class DimenUtil {

    public static int getScreenWidth() {
        final Resources resources = Core.getApplication().getResources();
        final DisplayMetrics dm = resources.getDisplayMetrics();
        return dm.widthPixels;
    }

    public static int getScreenHeight() {
        final Resources resources = Core.getApplication().getResources();
        final DisplayMetrics dm = resources.getDisplayMetrics();
        return dm.heightPixels;
    }
}
