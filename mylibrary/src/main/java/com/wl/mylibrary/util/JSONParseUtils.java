package com.wl.mylibrary.util;

import android.util.Log;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * JSON解析工具类
 * Created by sks on 2016/4/9.
 */
public class JSONParseUtils {

    /**
     * 解析对象 通过返回的直接拿到对象
     * @param json
     * @param clazz
     * @return
     */
    public static <T> T parseObject(String json, Class<T> clazz) {
        if(TextUtils.isEmpty(json)){
            return null;
        }
        return new Gson().fromJson(json, clazz);
    }

    /**
     * 解析数组 通过json直接获取对象的数组
     * @param json
     * @param clazz
     * @return
     */
    public static <T> List<T> parseArray(String json, Class<T[]> clazz) {
        Gson gson = new Gson();
        T[] array = gson.fromJson(json, clazz);
        Log.e("tags","jsonToList");
        return Arrays.asList(array);
    }

}
