package com.wl.mylibrary.widget.banner.adapter.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.wl.mylibrary.R;
import com.wl.mylibrary.widget.banner.LMBanners;
import com.wl.mylibrary.widget.banner.adapter.LBaseAdapter;


/**
 * Created by luomin on 16/7/12.
 */
public class UrlImgAdapter implements LBaseAdapter<String> {
    private Context mContext;

    public UrlImgAdapter(Context context) {
        mContext=context;
    }



    @Override
    public View getView(final LMBanners lBanners, final Context context, int position, String data) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item, null);
        ImageView imageView = (ImageView) view.findViewById(R.id.id_image);
        Glide.with(context).load(data).into(imageView);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                        TransSearchActivity.this.startActivity(new Intent(TransSearchActivity.this,SeconedAc.class));
            }
        });
        return view;
    }



}
