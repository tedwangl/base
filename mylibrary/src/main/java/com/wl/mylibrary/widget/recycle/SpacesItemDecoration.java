package com.wl.mylibrary.widget.recycle;

import android.graphics.Rect;
import android.support.v7.widget.RecyclerView;
import android.view.View;

public class SpacesItemDecoration extends RecyclerView.ItemDecoration {

    private int left,top,right,bottom;

    public SpacesItemDecoration(int left,int top,int right,int bottom) {
        this.left=left;
        this.top = top;
        this.right = right;
        this.bottom = bottom;
    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        outRect.left=left;
        outRect.right=right;
        outRect.bottom=bottom;
        outRect.top=top;
    }
}