package me.jansv.challenge.util;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.Log;


public final class Utils {

    public static boolean isNetworkAvailable(Context context) {
        return isConnectedAs(context, ConnectivityManager.TYPE_WIFI) || isConnectedAs(context, ConnectivityManager.TYPE_MOBILE);
    }

    private static boolean isConnectedAs(Context context, int type) {
        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        if (null != cm) {
            NetworkInfo info = cm.getNetworkInfo(type);
            return info != null && info.isAvailable() && info.isConnected();
        }
        return false;
    }

    public static final void loge(String tag, String msg) {
        Log.e(Thread.currentThread().getName() + "\t" + tag, msg);
    }

    public static final void loge(String tag, String msg, Throwable throwable) {
        Log.e(Thread.currentThread().getName() + "\t" + tag, msg, throwable);
    }
}