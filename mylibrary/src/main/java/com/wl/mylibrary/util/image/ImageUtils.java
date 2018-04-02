package com.wl.mylibrary.util.image;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.widget.ImageView;

/**
 * 图片工具类
 * Created by sks on 2016/4/9.
 */
public class ImageUtils {

    /**
     * 从本地加载图片
     * @param path
     * @param imageView
     * @return
     */
    public static Bitmap loadImageFromLocal(final String path,final ImageView imageView) {
        // 1、获得图片需要显示的大小
        ImageSizeUtils.ImageSize imageSize = ImageSizeUtils.getImageViewSize(imageView);
        // 2、压缩图片
        return getBitmapFromPath(path, imageSize.width, imageSize.height);
    }

    /**
     * 根据图片需要显示的宽和高对图片进行压缩
     * @param path
     * @param width
     * @param height
     * @return
     */
    public static Bitmap getBitmapFromPath(String path, int width, int height) {
        // 获得图片的宽和高，并不把图片加载到内存中
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeFile(path, options);
        options.inSampleSize = ImageUtils.caculateInSampleSize(options, width, height);
        // 使用获得到的InSampleSize再次解析图片
        options.inJustDecodeBounds = false;
        Bitmap bitmap = BitmapFactory.decodeFile(path, options);
        return bitmap;
    }

    /**
     * 根据需求的宽和高以及图片实际的宽和高计算SampleSize
     * @param options  里面存了实际的宽和高
     * @param reqWidth reqWidth和reqHeight就是我们之前得到的想要显示的大小
     * @param reqHeight reqWidth和reqHeight就是我们之前得到的想要显示的大小
     * @return
     */
    public static int caculateInSampleSize(BitmapFactory.Options options,
           int reqWidth, int reqHeight) {
        int width = options.outWidth;
        int height = options.outHeight;
        int inSampleSize = 1;
        if (width > reqWidth || height > reqHeight) {
            int widthRadio = Math.round(width * 1.0f / reqWidth);
            int heightRadio = Math.round(height * 1.0f / reqHeight);
            inSampleSize = Math.max(widthRadio, heightRadio);
        }
        return inSampleSize;
    }

    /**
     * Drawable转为Bitmap
     * @param d
     * @return
     */
    public static Bitmap drawableToBitmap(Drawable d) {
        return d == null ? null : ((BitmapDrawable)d).getBitmap();
    }

    /**
     * Bitmap转为Drawable
     * @param b
     * @return
     */
    public static Drawable bitmapToDrawable(Bitmap b) {
        return b == null ? null : new BitmapDrawable(b);
    }

    /**
     * 缩放图片
     * @param org
     * @param newWidth
     * @param newHeight
     * @return
     */
    public static Bitmap scaleImage(Bitmap org, int newWidth, int newHeight) {
        if (org == null) {
            return null;
        }
        float scaleWidth = (float) newWidth / org.getWidth();
        float scaleHeight = (float) newHeight / org.getHeight();
        Matrix matrix = new Matrix();
        matrix.postScale(scaleWidth, scaleHeight);
        return Bitmap.createBitmap(org, 0, 0, org.getWidth(), org.getHeight(), matrix, true);
    }

}
