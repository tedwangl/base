package com.wl.mylibrary.util;

import android.content.Context;
import android.content.res.TypedArray;

/**
 * 属性工具类
 * @author wl
 */
public class AttrUtils {

    /**
     * 获取属性颜色值
     * @param context
     * @param attrs
     * @param attrValue
     * @return
     */
    public static int getValueOfColorAttr(Context context,int[] attrs,int attrValue){
            TypedArray a = context.obtainStyledAttributes(attrs);
            int color=a.getColor(attrValue, 0);
            return color;
    }

}


