package com.wl.mylibrary.widget;

import android.content.Context;
import android.support.v4.widget.SwipeRefreshLayout;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewConfiguration;
import android.widget.AbsListView;
import android.widget.ListView;

import com.wl.mylibrary.R;

/**
 * 自定义控件：下拉刷新,上拉加载
 * 作为listview的父容器，可以实现上拉刷新和下拉刷新
 * Created by sks on 2016/4/9.
 */
public class RefreshLayout extends SwipeRefreshLayout implements AbsListView.OnScrollListener {

    //最小滑动距离
    private int mTouchSlop;

    //ListView的加载中footer
    private View mListViewFooter;

    //按下时的y坐标
    private int mYDown;

    //抬起时的y坐标, 与mYDown一起用于滑动到底部时判断是上拉还是下拉
    private int mLastY;

    //是否完成分页加载
    private boolean iscompleteLoading=false;

    //上拉监听器, 到了最底部的上拉加载操作 这是一个接口
    private OnLoadListener mOnLoadListener;

    //是否在加载中
    private boolean isLoading = false;

    /**
     * listview实例
     */
    private ListView mListView;

    public RefreshLayout(Context context) {
        super(context);
    }

    public RefreshLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        //系统指定的一个高度
        mTouchSlop = ViewConfiguration.get(context).getScaledTouchSlop();
        //设置脚视图
        mListViewFooter = LayoutInflater.from(context).inflate(R.layout.core_listview_footer, null, false);
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
        // 初始化ListView对象
        if (mListView == null) {
            getListView();
        }
    }

    /**
     * 获取ListView对象
     */
    private void getListView() {
        int childs = getChildCount();
        if (childs > 0) {
            View childView = getChildAt(0);
            if (childView instanceof ListView) {
                mListView = (ListView) childView;
                // 设置滚动监听器给ListView, 使得滚动的情况下也可以自动加载
                mListView.setOnScrollListener(this);
            }
        }
    }

    //分发事件监听器
    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        int action = ev.getAction();
        switch (action){
            case MotionEvent.ACTION_DOWN: //按下
                        mYDown = (int)ev.getRawY();
                        break;
                    case MotionEvent.ACTION_MOVE: //移动
                        mLastY = (int)ev.getRawY();
                        break;
                    case MotionEvent.ACTION_UP: //抬起
                        if(canLoad()){//加载数据 判断是否为有效的操作
                            loadData();
            }
            break;
        }
        return super.dispatchTouchEvent(ev);
    }

    /**
     * 是否可加载数据 满足3个条件
     * @return
     */
    private boolean canLoad() {//上拉了且不在加载中且当前已经在底部
        return isPullUp() && !isLoading && isBottom();
    }

    /**
     * 判定是否到了底部
     * @return
     */
    private boolean isBottom(){
        if(mListView!=null && mListView.getAdapter()!=null){

            //可见的底部位置是否是否是已经加载的数据的最后一条
            return mListView.getLastVisiblePosition() == (mListView.getAdapter().getCount()-1);
        }
        return false;
    }

    /**
     * 判定是否上拉
     * @return
     */
    private boolean isPullUp(){//按下位置减去松手位置>指定高度 判定上拉
       return (mYDown - mLastY) >= mTouchSlop;
    }

    /**
        * 加载数据
                */
        private void loadData(){
            if(iscompleteLoading){   //完成加载，不操作
                return;
            }
            if(mOnLoadListener!=null){
                setLoading(true);
                mOnLoadListener.onPageLoad();
            }
    }

    /**
     * 是否显示分页加载,true显示,
     * @param loading
     */
    public void setLoading(boolean loading) {
        this.isLoading = loading;
        if(isLoading){
            mListView.addFooterView(mListViewFooter);
        }else{
            mListView.removeFooterView(mListViewFooter);
            mYDown = 0;
            mLastY = 0;
        }
    }

    /**
     * 完成分页加载
     */
    public void completePageData() {
        setLoading(false);
        iscompleteLoading=true;
        mListViewFooter.setVisibility(View.GONE);
    }

    @Override
    public void onScrollStateChanged(AbsListView view, int scrollState) {

    }

    @Override
    public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
        // 滚动时到了最底部也可以加载更多
        if (canLoad()) {
            loadData();
        }
    }

    public void setOnLoadListener(OnLoadListener loadListener) {
        //setColorSchemeColors(getColorPrimary());//设置颜色
        mOnLoadListener = loadListener;
    }


    /*//获取颜色
    public int getColorPrimary(){
        return AttrUtils.getValueOfColorAttr(getContext(),
                R.styleable.Theme, R.styleable.Theme_colorPrimary);
    }*/

    /**
     * 加载更多的监听器,加载下一页
     * @author mrsimple
     */
    public interface OnLoadListener {
        void onPageLoad();
    }

}
