package com.example.linj.myapplication.utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.view.WindowManager;

/**
 * Created by JLin.
 *
 * @date 2019/11/13
 * @describe
 */
public class NetworkUtils {
    /**
     * 检查网络是否可用
     *
     * @param context
     * @return
     */
    public static boolean isNetworkAvailable(Context context) {

        ConnectivityManager connectivity = (ConnectivityManager) context
                .getSystemService(Context.CONNECTIVITY_SERVICE);
        if (connectivity != null) {
            NetworkInfo info = connectivity.getActiveNetworkInfo();
            if (info != null && info.isConnected()) {
                // 当前网络是连接的
                // 当前所连接的网络可用
                return info.getState() == NetworkInfo.State.CONNECTED;
            }
        }
        return false;
    }
}
