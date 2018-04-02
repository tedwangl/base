package com.wl.mylibrary.util;

import android.app.Activity;
import android.content.Context;
import android.util.DisplayMetrics;
import android.view.WindowManager;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 核心工具类
 * 管理adctivity添加删除
 * Created by sks on 2016/4/9.
 */
public class CoreUtils {

    private static float sDensity;


    //Activity列表
    public static ArrayList<Activity> activityList = new ArrayList<Activity>();

    /**
     * 添加Activity到列表中
     * @param activity
     */
    public static void addAppActivity(Activity activity){
        if(!activityList.contains(activity)){
            activityList.add(activity);
        }
    }

    /**
     * 从Activity列表移除Activity
     * @param activity
     */
    public static void removeAppActivity(Activity activity){
        if(activityList.contains(activity)){
            activityList.remove(activity);
        }
    }

    /**
     * 退出应用程序
     * @param context
     */
    public static void exitApp(Context context){
        L.d("销毁Activity size:" + activityList.size());
        for (Activity ac : activityList) {
            if(!ac.isFinishing()){
                ac.finish();
            }
        }
        activityList.clear();
        android.os.Process.killProcess(android.os.Process.myPid());
    }

    /**
     * 清空List集合
     * @param list
     */
    public static void clearList(List<?> list){
        if(list!=null){
            list.clear();
        }
    }

    /**
     * 将sp值转换为px值，保证文字大小不变
     * @param context
     * @param spValue
     * @return
     */
    public static int spToPixel(Context context, float spValue) {
        final float fontScale = context.getResources().getDisplayMetrics().scaledDensity;
        return (int) (spValue * fontScale + 0.5f);
    }

    /**
     * dp转换为像�?
     * @param context
     * @param nDip
     * @return
     */
    public static int dipToPixel(Context context, int nDip) {
        if (sDensity == 0) {
            final WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
            DisplayMetrics dm = new DisplayMetrics();
            wm.getDefaultDisplay().getMetrics(dm);
            sDensity = dm.density;
        }
        return (int) (sDensity * nDip);
    }

    /**
     * 获取屏幕 �?�?
     * @param activity
     * @return 数组  0 为宽�?1 为高�?
     */
    public static int[] getDisplay(Activity activity){
        int[] display = new int[2];
        DisplayMetrics dm = new DisplayMetrics();
        activity.getWindowManager().getDefaultDisplay().getMetrics(dm);
        display[0] = dm.widthPixels;
        display[1] = dm.heightPixels;
        return display;
    }

    /**
     * 判断是否为字�?
     * @param fstrData
     * @return
     */
    public static boolean isEnglish(String fstrData){
        char   c   =   fstrData.charAt(0);
        if(((c>='a'&&c<='z')   ||   (c>='A'&&c<='Z'))){
            return   true;
        }else{
            return   false;
        }
    }

    public static boolean isEmpty(String str){
        if(str==null||str.trim().length()==0){
            return true;
        }
        return false;
    }

    public static boolean isChinese(String str) {
        Pattern p = Pattern.compile("^[\u4e00-\u9fa5]*$");
        Matcher m = p.matcher(str);
        if (m.matches()) {
            return true;
        } else {
            return false;
        }
    }

}
