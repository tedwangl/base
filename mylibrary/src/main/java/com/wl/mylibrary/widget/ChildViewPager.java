package com.wl.mylibrary.widget;

import android.content.Context;
import android.support.v4.view.ViewPager;  
import android.util.AttributeSet;  
import android.util.Log;  
import android.view.MotionEvent;  
/** 
 * @Package com.ml.news.ui.view 
 * @ClassName: ChildViewPager 
 * @Description: 解决viewpager内嵌viewpager滑动冲突问题 
 * @author malong 
 * @date Mar 26, 2015 8:56:24 PM 
 */  
public class ChildViewPager extends ViewPager {  
  
    public ChildViewPager(Context context, AttributeSet attrs) {  
        super(context, attrs);  
    }  
  
    public ChildViewPager(Context context) {  
        super(context);  
    }  
  
    // 滑动距离及坐标 归还父控件焦点  
    private float xDistance, yDistance, xLast, yLast,mLeft;  
  
    @Override  
    public boolean dispatchTouchEvent(MotionEvent ev) {  
        getParent().requestDisallowInterceptTouchEvent(true);  
        switch (ev.getAction()) {  
            case MotionEvent.ACTION_DOWN:  
                Log.d("touch", "ACTION_DOWN");  
                xDistance = yDistance = 0f;  
                xLast = ev.getX();  
                yLast = ev.getY();  
                mLeft = ev.getX();  
                break;  
            case MotionEvent.ACTION_MOVE:  
                final float curX = ev.getX();  
                final float curY = ev.getY();  
  
                xDistance += Math.abs(curX - xLast);  
                yDistance += Math.abs(curY - yLast);  
                xLast = curX;  
                yLast = curY;  
                if (xDistance < yDistance) {//父布局做
                    getParent().requestDisallowInterceptTouchEvent(false);  
                } else {//我自己做
                    getParent().requestDisallowInterceptTouchEvent(true);  
                }  
                break;  
            case MotionEvent.ACTION_UP:  
            case MotionEvent.ACTION_CANCEL:  
                break;  
        }  
        return super.dispatchTouchEvent(ev);  
    }  
}  