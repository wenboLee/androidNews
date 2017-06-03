package cn.wenbo_lee.androidnews.utils;

import android.util.Log;

/**
 * Created by Administrator on 2017/4/23.
 */

public class TimeUtils {

    private static final String TAG = "TimeUtils";

    public static boolean diff5Time(long nowTime, long fiveTime) {
        Log.e(TAG, "nowTime : " + nowTime + "  fiveTime : " + fiveTime);
        long diff = ((fiveTime - nowTime) / 1000) / 60;
        if (diff > 5)
            return true;
        return false;
    }
}
