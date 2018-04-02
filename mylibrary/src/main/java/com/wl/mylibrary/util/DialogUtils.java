package com.wl.mylibrary.util;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;

/**
 * Created by pc on 2016/4/11.
 */
public class DialogUtils {

    public static void showDialog(Context context,
                int resSetId,int resId,int resNoId ,DialogInterface.OnClickListener listener1,
                                  DialogInterface.OnClickListener listener2,boolean iswhat){
        new AlertDialog.Builder(context).setPositiveButton(context.getString(resId),listener1)
                .setNegativeButton(context.getString(resNoId),listener2).setMessage(resSetId).show();
    }
    public static void showDialog(Context context,
                int resSetId,int resId,int resNoId ,DialogInterface.OnClickListener listener1,
                                  DialogInterface.OnClickListener listener2){
        new AlertDialog.Builder(context).setPositiveButton(context.getString(resId),listener1)
                .setNegativeButton(context.getString(resNoId),listener2).setMessage(context.getString(resId)).show();
    }
    public static void showDialog(Context context,
                String resSetId,String resId,String resNoId ,DialogInterface.OnClickListener listener1,
                                  DialogInterface.OnClickListener listener2,boolean iswhat){
        new AlertDialog.Builder(context).setPositiveButton(resId,listener1)
                .setNegativeButton(resNoId,listener2).setMessage(resSetId).show();
    }
    public static void showDialog(Context context,
                String resSetId,String resId,String resNoId ,DialogInterface.OnClickListener listener1,
                                  DialogInterface.OnClickListener listener2){
        new AlertDialog.Builder(context).setPositiveButton(resId,listener1)
                .setNegativeButton(resNoId,listener2).setMessage(resSetId).show();
    }

}
