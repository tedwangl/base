package com.wl.mylibrary.widget.SmoothListView;

import android.app.Activity;
import android.content.Context;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.wl.mylibrary.R;
import com.wl.mylibrary.bean.Country;
import com.wl.mylibrary.util.adapter.CusAdapter;
import com.wl.mylibrary.widget.GridViewForScroll;

import java.util.List;

/**
 * Created by sunfusheng on 16/4/20.
 */
public class HeaderCountryViewView extends HeaderViewInterface<List<Country>> {

    public GridViewForScroll gvCountry;
    public HeaderCountryViewView(Activity context) {
        super(context);
    }

    @Override
    protected void getView(List<Country> list, ListView listView) {
        View view = mInflate.inflate(R.layout.grid_of_cover, listView, false);
        gvCountry = (GridViewForScroll) view.findViewById(R.id.gv_country);
        dealWithTheView(list);
        listView.addHeaderView(view);
    }

    private void dealWithTheView(List<Country> list) {
        int size = list.size();

        if (size <= 4) {
            gvCountry.setNumColumns(size);
        } else if (size == 6) {
            gvCountry.setNumColumns(3);
        } else if (size == 8) {
            gvCountry.setNumColumns(4);
        } else {
            gvCountry.setNumColumns(4);
        }
        HeaderChannelAdapter adapter = new HeaderChannelAdapter(mContext, list,R.layout.item_grid);
        gvCountry.setAdapter(adapter);
    }

    class HeaderChannelAdapter extends CusAdapter<Country> {


        public HeaderChannelAdapter(Context context, List<Country> list, int itemLayoutRes) {
            super(context, list, itemLayoutRes);
        }

        @Override
        public View getCustomView(int position, View itemView) {
            Country entity = getItem(position);
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
            return itemView;
        }
    }

}
