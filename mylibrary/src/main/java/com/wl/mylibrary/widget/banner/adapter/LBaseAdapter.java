package com.wl.mylibrary.widget.banner.adapter;

import android.content.Context;
import android.view.View;

import com.wl.mylibrary.widget.banner.LMBanners;


/**
 * Created by luomin on 16/7/12.
 */
public interface LBaseAdapter<T> {
    View getView(LMBanners lBanners, Context context, int position, T data);
}
