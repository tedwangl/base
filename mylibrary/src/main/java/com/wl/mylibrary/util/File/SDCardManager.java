package com.wl.mylibrary.util.File;

import android.content.Context;
import android.os.Environment;
import android.os.StatFs;
import java.io.File;

/**
 * SD卡管理类
 * 通过这个类能够的到内存和内存卡的缓存目录，判断手机有无内存卡，
 * 内存，外存，可用没存，外存大小
 * @author ymx
 */
public class SDCardManager {

    /**
     * 判断SD卡是否可用
     * @return
     */
    public static boolean isExistSD(){
        return Environment.MEDIA_MOUNTED.equals(Environment.getExternalStorageState());
    }

    /**
     * 获取SD卡根路径
     * @return
     */
    public static String getSDCardPath(){
        return Environment.getExternalStorageDirectory().getAbsolutePath()
            + File.separator;
    }

    /**
     * 获取手机内部空间大小
     * StatFs保存一个单元数和单元字节数，totalBlocks * blockSize
     * @return
     */
    public static long getTotalInternalMemorySize() {
        //Gets the Android data directory
        File path = Environment.getDataDirectory();
        StatFs stat = new StatFs(path.getPath());
        //每个block 占字节数
        long blockSize = stat.getBlockSize();
        //block总数
        long totalBlocks = stat.getBlockCount();
        return totalBlocks * blockSize;
    }

    /**
     * 获取缓存文件目录
     * 有sd卡就返回sd卡的缓存目录，没有就返回手机没存的缓存目录
     */
    public static String getCacheDir(Context context){
        if(isExistSD()){
            return getSDCacheDir(context) + File.separator;
        }else{
            return context.getCacheDir().getAbsolutePath()+ File.separator;
        }
    }

    /**
     * 获取手机内部可用空间大小
     * StatFs里保存可用空间大小相关的参数
     * @return
     */
    public static long getAvailableInternalSize() {
        File path = Environment.getDataDirectory();
        StatFs stat = new StatFs(path.getPath());
        long blockSize = stat.getBlockSize();
        long availableBlocks = stat.getAvailableBlocks();
        return availableBlocks * blockSize;
    }

    /**
     * 获取SD卡缓存目录
     * @param context
     * @return
     */
    public static String getSDCacheDir(Context context){
        if(isExistSD()){
            return context.getExternalCacheDir().getAbsolutePath();
        }
        return null;
    }

    /**
     * 获取SD卡大小
     * @return
     */
    public static long getSDCardSize() {
        if (isExistSD()) {
            StatFs stat = new StatFs(getSDCardPath());
            long blockSize = stat.getBlockSize();
            long totalBlocks = stat.getBlockCount();
            return totalBlocks * blockSize;
        }
        return 0;
    }

    /**
     * 获取SD卡可用空间大小,单位byte
     * @return
     */
    public static long getAvailableSDCardSize() {
        if (isExistSD()) {
            StatFs stat = new StatFs(getSDCardPath());
            long blockSize = stat.getBlockSize();
            long availableBlocks = stat.getAvailableBlocks();
            return availableBlocks * blockSize;
        }
        return 0;
    }

}
