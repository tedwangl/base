package com.wl.mylibrary.util;

import android.app.Activity;
import android.content.Context;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;

import java.io.ByteArrayOutputStream;
import java.util.Locale;

/**
 * Created by pc on 2016/4/19.
 */
public class MyUtils {

    private static Fragment mFragment;
    //获取设置变量
    public static Configuration getConfig(Context context){
        return context.getResources().getConfiguration();
    }
    //获取屏幕参数变量
    public static DisplayMetrics getDisplayMetrics(Context context){
        return context.getResources().getDisplayMetrics();
    }
    //设置语言
    public static void setLanguage(Context context,Locale locale){
        getConfig(context).locale = locale;
    }


    //显示和隐藏fragment   replace和add有区别 add不会重新实例化fragment调用oncreateVoew方法
    public static void switchContent(Fragment fragment,int resId,Context context){
        FragmentTransaction transaction = ((AppCompatActivity)context).getSupportFragmentManager().beginTransaction();
        if(mFragment!=null){//不是第一次加载
            if(fragment.isAdded()){
                transaction.show(fragment).hide(mFragment);
            }else{
                transaction.add(resId,fragment).hide(mFragment);
            }
            mFragment = fragment;
        }else{//第一次加载
            transaction.add(resId,fragment);
            mFragment = fragment;
        }
        transaction.commitAllowingStateLoss();
    }

    /**
     * 压缩图片的方法 默认压缩到小于100kb即可  质量压缩
     * @param image 要压缩的对象
     * @return 返回压缩好的图片
     */
    public static Bitmap compressImage(Bitmap image){
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        //质量压缩方法，这里100表示不压缩，把压缩后的数据保存到baos中
        image.compress(Bitmap.CompressFormat.JPEG,100,baos);
        //循环判断如果压缩后的图片是否大于100KB,大于100KB继续压缩
        int options = 100;
        while(baos.toByteArray().length/1024/1024>100){
            baos.reset();//重置baos
            //压缩图片为原来的一半
            image.compress(Bitmap.CompressFormat.JPEG,options,baos);
            options -= 10;//每次都为原来的90% 或者81%
        }
        return image;
    }

    /**
     * 大小压缩
     * @param srcPath
     * @return
     */
    public static Bitmap getImage(String srcPath){
        BitmapFactory.Options newoptions = new BitmapFactory.Options();
        //开始读入图片，此时把options.inJustDccodeBounds设置为true
        newoptions.inJustDecodeBounds = true;
        Bitmap image = BitmapFactory.decodeFile(srcPath, newoptions);
        newoptions.inJustDecodeBounds = false;
        int w = newoptions.outWidth;
        int h = newoptions.outHeight;
        float ww = 800f;
        float hh = 480f;
        int be = 1;
        //拿大的和固定的比，达到缩放过度。。。
        if(w>h && w>ww){
            be = (int)(w/ww);
        }else if(h>w && h>hh){
            be = (int)(h/hh);
        }
        if(be<=0){
            be=1;
        }
        newoptions.inSampleSize = be;
        image = BitmapFactory.decodeFile(srcPath,newoptions);
        return compressImage(image);
    }

}
