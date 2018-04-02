package com.wl.mylibrary.widget.banner.adapter.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;

import com.wl.mylibrary.R;
import com.wl.mylibrary.widget.banner.LMBanners;
import com.wl.mylibrary.widget.banner.adapter.LBaseAdapter;


/**
 * Created by luomin on 16/7/12.
 */
public class LocalImgAdapter implements LBaseAdapter<Integer> {
    private Context mContext;

    public LocalImgAdapter(Context context) {
        mContext=context;
    }

    @Override
    public View getView(final LMBanners lBanners, final Context context, int position, Integer data) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item, null);
        ImageView imageView = (ImageView) view.findViewById(R.id.id_image);
        imageView.setImageResource(data);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        return view;
    }


}
