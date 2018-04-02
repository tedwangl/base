package com.wl.mylibrary.application;

import android.app.Activity;

import java.util.Stack;

/**
 * Created by wanglei on 2018/1/30.
 */

public class ActivityManger {

    private static volatile ActivityManger instance;

    private Stack<Activity> mActivityStack = new Stack<>();

    private ActivityManger(){

    }

    public static ActivityManger getInstance(){
        if(instance==null){
            synchronized (ActivityManger.class){
                instance = new ActivityManger();
            }
        }
        return instance;
    }

    public void addActicity(Activity act){
        mActivityStack.push(act);
    }

    public void removeActivity(Activity act){
        mActivityStack.remove(act);
    }

    public void killMyProcess(){
        int nCount = mActivityStack.size();
        for (int i = nCount - 1; i >= 0; i--) {
            Activity activity =  mActivityStack.get(i);
            activity.finish();
        }
        mActivityStack.clear();
        android.os.Process.killProcess(android.os.Process.myPid());
    }

}
