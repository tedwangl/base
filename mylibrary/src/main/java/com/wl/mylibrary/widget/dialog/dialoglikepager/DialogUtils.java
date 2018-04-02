package com.wl.mylibrary.widget.dialog.dialoglikepager;


import android.app.FragmentManager;

/**
 * Created by pc on 2017/8/30.
 */
public class DialogUtils {

    public static void onShowAppCompatAlertDialog(FragmentManager manager) {
        ExampleDialogFragment.newInstance(ExampleDialogFragment.Type.APPCOMPAT).show(manager, "alert");
    }

    public static void onShowAppCompatListDialog(FragmentManager manager) {
        ExampleDialogFragment.newInstance(ExampleDialogFragment.Type.APPCOMPAT_LIST).show(manager, "list");
    }

    public static void onShowDefaultAlertDialog(FragmentManager manager) {
        ExampleDialogFragment.newInstance(ExampleDialogFragment.Type.DEFAULT).show(manager, "alert");
    }

    public static void onShowDefaultListDialog(FragmentManager manager) {
        ExampleDialogFragment.newInstance(ExampleDialogFragment.Type.DEFAULT_LIST).show(manager, "list");
    }

    public static void onShowProgressDialog(FragmentManager manager) {
        ExampleDialogFragment.newInstance(ExampleDialogFragment.Type.PROGRESS).show(manager, "progress");
    }

    public static void onShowDateDialog(FragmentManager manager) {
        ExampleDialogFragment.newInstance(ExampleDialogFragment.Type.DATE).show(manager, "date");
    }

    public static void onShowTimeDialog(FragmentManager manager) {
        ExampleDialogFragment.newInstance(ExampleDialogFragment.Type.TIME).show(manager, "time");
    }

    public static void onShowCustomDialog(FragmentManager manager) {
        ExampleDialogFragment.newInstance(ExampleDialogFragment.Type.CUSTOM).show(manager, "custom");
    }
}
