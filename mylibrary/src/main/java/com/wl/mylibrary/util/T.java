package com.wl.mylibrary.util;

import android.app.Activity;
import android.content.Context;
import android.widget.Toast;

/**
 * Toast提示管理类
 * Created by sks on 2016/4/9.
 */
public class T {

    /**
     * 短时间显示Toast
     * @param context
     * @param message
     */
    public static void showShort(Context context, int message) {
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
    }

    /**
     * 短时间显示Toast
     * @param context
     * @param message
     */
    public static void showShort(Context context, CharSequence message) {
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
    }

    /**
     * 长时间显示Toast
     * @param context
     * @param message
     */
    public static void showLong(Context context, int message) {
        Toast.makeText(context, message, Toast.LENGTH_LONG).show();
    }

    /**
     * 长时间显示Toast
     * @param context
     * @param message
     */
    public static void showLong(Context context, CharSequence message) {
        Toast.makeText(context, message, Toast.LENGTH_LONG).show();
    }

    /**
     * 在非UI线程中显示Toast消息
     * @param activity
     * @param message
     */
    public static void showOnUi(final Activity activity,final String message){
        activity.runOnUiThread(new Runnable() {
            public void run() {
                Toast.makeText(activity, message, Toast.LENGTH_SHORT).show();
            }
        });
    }

    public static void showShort(Context context) {
        Toast.makeText(context, "测试用的", Toast.LENGTH_SHORT).show();
    }
}
