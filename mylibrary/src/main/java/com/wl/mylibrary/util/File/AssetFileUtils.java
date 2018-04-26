package com.wl.mylibrary.util.File;

import android.content.Context;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * Created by wanglei on 2018/4/26.
 */

public class AssetFileUtils {

    public static String readJsonFromAsset(Context context,String resName){
        StringBuilder sb = new StringBuilder();
        InputStream is = null;
        try {
            is = context.getClass().getClassLoader().getResourceAsStream("assets/"+resName);
            BufferedReader reader = new BufferedReader(new InputStreamReader(is));
            String s;
            while ((s=reader.readLine())!=null){
                sb.append(s);
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return sb.toString();
    }

}
