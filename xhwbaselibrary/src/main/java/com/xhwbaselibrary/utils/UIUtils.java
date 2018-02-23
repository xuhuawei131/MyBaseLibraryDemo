package com.xhwbaselibrary.utils;

import android.content.Context;

/**
 * Created by Administrator on 2018/2/17 0017.
 */

public class UIUtils {

    public static int convertDpToPixel(Context context, int dp) {
        float density = context.getResources().getDisplayMetrics().density;
        return Math.round((float) dp * density);
    }
}
