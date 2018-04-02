package com.wl.mylibrary.widget.SmoothListView;

import android.app.Activity;
import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.wl.mylibrary.R;
import com.wl.mylibrary.bean.Country;
import com.wl.mylibrary.util.ScreenUtils;
import com.wl.mylibrary.util.T;
import com.wl.mylibrary.util.adapter.CusAdapter;
import com.wl.mylibrary.widget.GridViewForScroll;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by pc on 2017/9/4.
 */
public class HeadClassifyView extends HeaderViewInterface<List<Country>> {

    private ViewPager viewpager;
    private LinearLayout llIndexContainer;
    private List<GridViewForScroll> gridlist ;

    private final static int NUMCLASSIFY_OF_PAGER = 8;//每页有8个

    public HeadClassifyView(Activity context) {
        super(context);
        gridlist  = new ArrayList<>();
    }

    @Override
    protected void getView(List<Country> countries, ListView listView) {
        View view = mInflate.inflate(R.layout.header_ad_layout, listView, false);
        viewpager = (ViewPager) view.findViewById(R.id.vp_ad);
        llIndexContainer = (LinearLayout) view.findViewById(R.id.ll_index_container);
        dealWithTheView(countries);
        listView.addHeaderView(view);
    }

    private void dealWithTheView(List<Country> list) {
        gridlist.clear();
        int last = list.size()%NUMCLASSIFY_OF_PAGER;
        int pagercount = last==0?list.size()/NUMCLASSIFY_OF_PAGER:list.size()/NUMCLASSIFY_OF_PAGER+1;
        for (int i = 0; i < pagercount; i++) {
            List<Country> sublist ;
            if(((list.size()-i*8))/8>0){
                sublist = (list.subList(i*8,(i+1)*8));
            }else{
                sublist = (list.subList(i*8,list.size()));
            }
            gridlist.add(createImageView(sublist));
        }
        GridPagerAdapter photoAdapter = new GridPagerAdapter(gridlist);
        viewpager.setAdapter(photoAdapter);
        addIndicatorImageViews(pagercount);
        setViewPagerChangeListener(pagercount);
    }

    private GridViewForScroll createImageView(List<Country> countryList) {
        GridViewForScroll grid_of_classify = new GridViewForScroll(mContext,null);
        AbsListView.LayoutParams params = new AbsListView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        grid_of_classify.setLayoutParams(params);
        grid_of_classify.setNumColumns(4);
        GridAdapter adapter = new GridAdapter(mContext,countryList,R.layout.item_grid);
        grid_of_classify.setAdapter(adapter);
        return grid_of_classify;
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
        viewpager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageSelected(int position) {
                if (gridlist != null && gridlist.size() > 0) {
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


    class GridPagerAdapter extends PagerAdapter {

        private List<GridViewForScroll> gridlist; // GridView的集合
        private int count;

        public GridPagerAdapter( List<GridViewForScroll> gridlist) {
            super();
            this.gridlist = gridlist;
            if(gridlist != null && gridlist.size() > 0){
                count = gridlist.size();
            }
        }

        @Override
        public int getCount() {
            return gridlist.size();
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

            GridViewForScroll gridViewForScroll = gridlist.get(newPosition);
            container.removeView(gridViewForScroll);
            container.addView(gridViewForScroll);
            return gridViewForScroll;
        }
    }

    class GridAdapter extends CusAdapter<Country> {


        public GridAdapter(Context context, List<Country> list, int itemLayoutRes) {
            super(context, list, itemLayoutRes);
        }

        @Override
        public View getCustomView(int position, View itemView) {
            final Country entity = getItem(position);
            ImageView ivImage = (ImageView) itemView.findViewById(R.id.iv_image);
            TextView tvTitle = (TextView) itemView.findViewById(R.id.tv_title);
            TextView tvTips = (TextView) itemView.findViewById(R.id.tv_tips);
            tvTitle.setText(entity.getName());
            Glide.with(context).load(entity.getImg_url()).into(ivImage);
            if (TextUtils.isEmpty(entity.getTips())) {
                tvTips.setVisibility(View.INVISIBLE);
            } else {
                tvTips.setVisibility(View.VISIBLE);
                tvTips.setText(entity.getTips());
            }
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    T.showLong(context,entity.toString());
                }
            });
            return itemView;
        }
    }
}
