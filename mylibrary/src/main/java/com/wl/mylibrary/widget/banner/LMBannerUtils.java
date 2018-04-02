package com.wl.mylibrary.widget.banner;

import android.widget.LinearLayout;

import com.wl.mylibrary.R;
import com.wl.mylibrary.widget.banner.transformer.ParallaxTransformer;

/**
 * Created by pc on 2017/8/31.
 */
public class LMBannerUtils {

    public  static void initBanner(LMBanners banners){
        banners.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT));
        banners.isGuide(true);
        //lmBanners.setVertical(true);
        banners.setAutoPlay(false);
        banners.setCanLoop(false);
        banners.setScrollDurtion(800);
        banners.setIndicatorBottomPadding(30);
        banners.setIndicatorWidth(6);
//      lmBanners.setHoriZontalTransitionEffect(TransitionEffect.Default);
        banners.setHoriZontalCustomTransformer(new ParallaxTransformer(R.id.id_image));
        banners.setIndicatorPosition(LMBanners.IndicaTorPosition.BOTTOM_MID);

    }
}
