package com.wl.mylibrary.application;

import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;

import com.squareup.leakcanary.LeakCanary;
import com.squareup.leakcanary.RefWatcher;
import com.wl.mylibrary.util.File.FileUtils;
import com.wl.mylibrary.util.File.SDCardManager;
import com.wl.mylibrary.util.L;

import java.io.File;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Locale;

/**
 * Created by pc on 2016/4/9.
 */
public class CoreApplication extends Application{

    public  static CoreApplication instance;
    public static RefWatcher refWatcher;
    //缓存目录
    public static String CACHE_DIR;;
    //图片目录
    public static String IMAGE_DIR;;
    //图片下载缓存目录
    public static String IMAGE_UPDATE_TEMP_DIR;
    //文件目录
    public static String FILE_DIR;
    //日志目录
    public static String LOG_DIR;
    //日志文件
    public static String LOG_FILE;

    public static String IMAGE_UPLOAD_TEMP;

    public static String USER_FILE;

    //控制日志状态（调试时打开，发布时关闭）
    public static final boolean IS_DEBUG = true;

    public static boolean isRight;

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        init();
    }

    /**
     * 初始化目录
     */
    private void init() {
        CACHE_DIR = SDCardManager.getCacheDir(this);
        L.i("缓存路径","CACHE_DIR");
        IMAGE_DIR = CACHE_DIR + "image" + File.separator;
        IMAGE_UPDATE_TEMP_DIR = CACHE_DIR + "imageupdatetemp" + File.separator;
        LOG_DIR = CACHE_DIR + "log" + File.separator;
        FILE_DIR = CACHE_DIR + "file" + File.separator;
        LOG_FILE = CACHE_DIR + "cache.log";
        USER_FILE = CACHE_DIR + "user.txt";
        FileUtils.checkDir(IMAGE_DIR);
        FileUtils.checkDir(IMAGE_UPDATE_TEMP_DIR);
        FileUtils.checkDir(LOG_DIR);
        FileUtils.checkDir(FILE_DIR);
        FileUtils.checkDir(LOG_FILE);
        FileUtils.checkDir(USER_FILE);
        if (LeakCanary.isInAnalyzerProcess(this)) {
            return;
        }
        refWatcher = LeakCanary.install(this);
    }

    public static RefWatcher getRefWatcher(Context context) {
        CoreApplication application = (CoreApplication) context.getApplicationContext();
        return CoreApplication.refWatcher;
    }

}
