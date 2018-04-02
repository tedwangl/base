package com.wl.mylibrary.util.image;

import android.graphics.BitmapFactory.Options;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.ViewGroup.LayoutParams;
import android.widget.ImageView;

import java.lang.reflect.Field;

/**
 * ImageView宽高计算工具类
 * @author ymx
 */
public class ImageSizeUtils {

    /**
     * 根据ImageView获适当的压缩的宽和高
     * @param imageView
     * @return
     */
    public static ImageSize getImageViewSize(ImageView imageView) {
        ImageSize imageSize = new ImageSize();
        DisplayMetrics displayMetrics = imageView.getContext().
                getResources().getDisplayMetrics();
        LayoutParams lp = imageView.getLayoutParams();
        // 获取imageview的实际宽度
        int width = imageView.getWidth();
        if(lp !=null ){
            if (width <= 0) {
                // 获取imageview在layout中声明的宽度
                width = lp.width;
            }
        }
        if (width <= 0) {
            // 检查最大值
            //width = imageView.getMaxWidth();
            width = getImageViewFieldValue(imageView, "mMaxWidth");
        }
        if (width <= 0) {
            //屏幕宽
            width = displayMetrics.widthPixels;
        }

        // 获取imageview的实际高度
        int height = imageView.getHeight();
        if(lp !=null ){
            if (height <= 0) {
                // 获取imageview在layout中声明的宽度
                height = lp.height;
            }
        }
        if (height <= 0) {
            // 检查最大值
            height = getImageViewFieldValue(imageView, "mMaxHeight");
        }
        if (height <= 0) {
            //屏幕高
            height = displayMetrics.heightPixels;
        }
        imageSize.width = width;
        imageSize.height = height;

        return imageSize;
    }

    /**
     * 通过反射获取imageview的某个属性值
     * @param object
     * @param fieldName
     * @return
     */
    private static int getImageViewFieldValue(Object object, String fieldName) {
        int value = 0;
        try {
            Field field = ImageView.class.getDeclaredField(fieldName);
            field.setAccessible(true);
            int fieldValue = field.getInt(object);
            if (fieldValue > 0 && fieldValue < Integer.MAX_VALUE) {
                value = fieldValue;
            }
        } catch (Exception e) {

        }
        return value;
    }

    public static class ImageSize {
        int width;
        int height;
    }

}
