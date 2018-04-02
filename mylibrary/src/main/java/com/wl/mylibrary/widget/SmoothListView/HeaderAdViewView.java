package com.wl.mylibrary.widget.SmoothListView;

import android.app.Activity;
import android.os.Handler;
import android.os.Message;
import android.os.SystemClock;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;

import com.bumptech.glide.Glide;
import com.wl.mylibrary.R;
import com.wl.mylibrary.util.ScreenUtils;

import java.util.ArrayList;
import java.util.List;

public class HeaderAdViewView extends HeaderViewInterface<List<String>> {

    ViewPager vpAd;
    LinearLayout llIndexContainer;

    private static final int TYPE_CHANGE_AD = 0;
    private Thread mThread;
    private List<ImageView> ivList;
    private boolean isStopThread = false;

    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (msg.what == TYPE_CHANGE_AD) {
                vpAd.setCurrentItem(vpAd.getCurrentItem() + 1);
            }
        }
    };

    public HeaderAdViewView(Activity context) {
        super(context);
        ivList = new ArrayList<>();
    }

    @Override
    protected void getView(List<String> list, ListView listView) {
        View view = mInflate.inflate(R.layout.header_ad_layout, listView, false);
        vpAd = (ViewPager) view.findViewById(R.id.vp_ad);
        llIndexContainer = (LinearLayout) view.findViewById(R.id.ll_index_container);
        dealWithTheView(list);
        listView.addHeaderView(view);
    }

    private void dealWithTheView(List<String> list) {
        ivList.clear();
        int size = list.size();
        for (int i = 0; i < size; i++) {
            ivList.add(createImageView(list.get(i)));
        }
        HeaderAdAdapter photoAdapter = new HeaderAdAdapter(ivList);
        vpAd.setAdapter(photoAdapter);
        addIndicatorImageViews(size);
        setViewPagerChangeListener(size);
        startADRotate();
    }

    // 创建要显示的ImageView
    private ImageView createImageView(String url) {
        ImageView imageView = new ImageView(mContext);
        AbsListView.LayoutParams params = new AbsListView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        imageView.setLayoutParams(params);
        imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
        Glide.with(mContext).load(url).into(imageView);
        return imageView;
    }

    // 添加指示图标
    private void addIndicatorImageViews(int size) {
        llIndexContainer.removeAllViews();
        for (int i = 0; i < size; i++) {
            ImageView iv = new ImageView(mContext);
            LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(ScreenUtils.dip2px(mContext, 5), ScreenUtils.dip2px(mContext, 5));
            if (i != 0) {
                lp.leftMargin = ScreenUtils.dip2px(mContext, 7);
            }
            iv.setLayoutParams(lp);
            iv.setBackgroundResource(R.drawable.xml_round_orange_grey_sel);
            iv.setEnabled(false);
            if (i == 0) {
                iv.setEnabled(true);
            }
            llIndexContainer.addView(iv);
        }
    }

    // 为ViewPager设置监听器
    private void setViewPagerChangeListener(final int size) {
        vpAd.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageSelected(int position) {
                if (ivList != null && ivList.size() > 0) {
                    int newPosition = position % size;
                    for (int i = 0; i < size; i++) {
                        llIndexContainer.getChildAt(i).setEnabled(false);
                        if (i == newPosition) {
                            llIndexContainer.getChildAt(i).setEnabled(true);
                        }
                    }
                }
            }

            @Override
            public void onPageScrolled(int position, float arg1, int arg2) {
            }

            @Override
            public void onPageScrollStateChanged(int arg0) {
            }
        });
    }

    // 启动循环广告的线程
    public void startADRotate() {
        // 一个广告的时候不用转
        if (ivList == null || ivList.size() <= 1) {
            return;
        }
        if (mThread == null) {
            mThread = new Thread(new Runnable() {

                @Override
                public void run() {
                    // 当没离开该页面时一直转
                    while (!isStopThread) {
                        // 每隔5秒转一次
                        SystemClock.sleep(5000);
                        // 在主线程更新界面
                        mHandler.sendEmptyMessage(TYPE_CHANGE_AD);
                    }
                }
            });
            mThread.start();
        }
    }

    // 停止循环广告的线程，清空消息队列
    public void stopADRotate() {
        isStopThread = true;
        if (mHandler != null && mHandler.hasMessages(TYPE_CHANGE_AD)) {
            mHandler.removeMessages(TYPE_CHANGE_AD);
        }
    }

    class HeaderAdAdapter extends PagerAdapter {

        private List<ImageView> ivList; // ImageView的集合
        private int count = 1; // 广告的数量

        public HeaderAdAdapter( List<ImageView> ivList) {
            super();
            this.ivList = ivList;
            if(ivList != null && ivList.size() > 0){
                count = ivList.size();
            }
        }

        @Override
        public int getCount() {
            if (count == 1) {
                return 1;
            } else {
                return Integer.MAX_VALUE;
            }
        }

        @Override
        public boolean isViewFromObject(View arg0, Object arg1) {
            return arg0 == arg1;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            int newPosition = position % count;
            // 先移除在添加，更新图片在container中的位置（把iv放至container末尾）
            ImageView iv = ivList.get(newPosition);
            container.removeView(iv);
            container.addView(iv);
            return iv;
        }
    }

}
