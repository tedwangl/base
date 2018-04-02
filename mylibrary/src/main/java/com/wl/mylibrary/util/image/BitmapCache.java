package com.wl.mylibrary.util.image;

import android.graphics.Bitmap;
import android.support.v4.util.LruCache;

/**
 * 图片内存缓存
 */
public class BitmapCache {

    //图片缓存之内存缓存技术LruCache，当存储Image的大小大于LruCache设定的值，系统自动释放内存
    private LruCache<String, Bitmap> mLruCache;

    public BitmapCache(){
        // 获取我们应用的最大可用内存
        int maxMemory = (int) Runtime.getRuntime().maxMemory();
        int cacheMemory = maxMemory/8;
        mLruCache = new LruCache<String,Bitmap>(cacheMemory){
            @Override
            protected int sizeOf(String key, Bitmap value) {
                return value.getRowBytes() * value.getHeight();
            }
        };
    }

    /**
     * 将Bitmap加入LruCache
     * @param path
     * @param bm
     */
    public void addToCache(String path, Bitmap bm) {
        if (getFromCache(path) == null) {
            if (bm != null){
                mLruCache.put(path, bm);
            }
        }
    }

    /**
     * 从LruCache中获取Bitmap
     * @param key
     * @return
     */
    public Bitmap getFromCache(String key) {
        return mLruCache.get(key);
    }

}
