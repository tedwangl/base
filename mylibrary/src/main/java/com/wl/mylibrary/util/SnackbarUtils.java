package com.wl.mylibrary.util;

import android.content.Context;
import android.support.design.widget.Snackbar;
import android.view.View;

import com.wl.mylibrary.R;


/**
 * Snackbar工具类
 * 显示提示通知，可以控制背景颜色
 */
public class SnackbarUtils {

    //颜色
    public static int colorResId = R.color.gray;

    /**
     * 显示提示消息
     * @param context 上下文对象
     * @param view
     * @param messageId 文本的id
     */
    public static void showMessage(Context context,View view, int messageId){
        final Snackbar snackbar = Snackbar.make(view, context.getString(messageId), Snackbar.LENGTH_LONG);
        //设置显示文件的颜色
        snackbar.getView().setBackgroundColor(context.getResources().getColor(colorResId));
        snackbar.show();
    }

    /**
     * 显示提示消息
     * @param context
     * @param view
     * @param message 文本的内容
     */
    public static void showMessage(Context context,View view, String message){
        final Snackbar snackbar = Snackbar.make(view, message, Snackbar.LENGTH_LONG);
        //设置显示文件的颜色
        snackbar.getView().setBackgroundColor(context.getResources().getColor(colorResId));
        snackbar.show();
    }

}
