package com.wl.mylibrary.util;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;

import com.wl.mylibrary.R;
import com.wl.mylibrary.util.network.NetWorkUtils;

import java.io.Serializable;
import java.util.Objects;

/**
 * Intent工具类
 * Created by sks on 2016/4/9.
 */
public class IntentUtils {

    /**
     * 启动Activity
     * @param context
     * @param cls
     */
    public static void startActivity(Context context,Class<?> cls){
        context.startActivity(new Intent(context, cls));
    }

    /**
     * 启动Activity
     * @param context
     * @param intent
     */
    public static void startActivity(Context context,Intent intent) {
        context.startActivity(intent);
    }

    /**
     * 启动Activity
     * @param context
     * @param cls
     * @param intent
     */
    public static void startActivity(Context context,Class<?> cls,Intent intent){
        context.startActivity(intent.setClass(context, cls));
    }

    /**
     * 启动Activity(带返回值)
     * @param activity
     * @param intent
     * @param requestCode
     */
    public static void startActivityForResult(Activity activity,Intent intent,int requestCode){
        activity.startActivityForResult(intent, requestCode);
    }

    /**
     * 启动Activity前判断网络
     * @param context
     * @param cls
     */
    public static void startActivityNetWork(Context context,Class<?> cls){
        if(!NetWorkUtils.NETWORK){
            T.showShort(context, context.getString(R.string.not_network));
            return;
        }
        startActivity(context, cls);
    }

    /**
     * 启动Activity前判断网络
     * @param context
     * @param intent
     */
    public static void startActivityNetWork(Context context,Intent intent){
        if(!NetWorkUtils.NETWORK){
            T.showShort(context, context.getString(R.string.not_network));
            return;
        }
        startActivity(context, intent);
    }

    /**
     * 启动Activity前判断网络
     * @param context
     * @param intent
     */
    public static void startActivityNetWork(Context context,Class<?> cls,Intent intent){
        if(!NetWorkUtils.NETWORK){
            T.showShort(context, context.getString(R.string.not_network));
            return;
        }
        startActivity(context, cls, intent);
    }

    /**
     * 启动Activity前判断网络(带返回值)
     * @param activity
     * @param intent
     * @param requestCode
     */
    public static void startActivityForResultNetWork(Activity activity,Intent intent,int requestCode){
        if(!NetWorkUtils.NETWORK){
            T.showShort(activity, activity.getString(R.string.not_network));
            return;
        }
        startActivityForResult(activity, intent, requestCode);
    }

    /**
     * 返回主页
     * @param activity
     * @param cls
     */
    public static void goHome(Activity activity,Class<?> cls){
        Intent intent=new Intent();
        intent.setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
        startActivity(activity, cls, intent);
        activity.finish();
    }

    public static void startActivityWithData(Context context,Class<?> cls,String key,Serializable t){
        Intent intent = new Intent(context,cls);
        intent.putExtra(key,t);
        context.startActivity(intent);
    }

}
