package com.frank.vmall.ui.banner;

import com.ToxicBakery.viewpager.transforms.DefaultTransformer;
import com.bigkoo.convenientbanner.ConvenientBanner;
import com.bigkoo.convenientbanner.listener.OnItemClickListener;
import com.frank.vmall.R;

import java.util.ArrayList;

/**
 * Created by Administrator on 2017/9/1 0001.
 */

public class BannerCreator {
    public static void setDefault(ConvenientBanner convenientbanner,
                                  ArrayList<String> banners,
                                  OnItemClickListener clickListener) {
        convenientbanner
                .setPages(new HolderCreator(), banners)
                .setPageIndicator(new int[]{R.drawable.dot_normal, R.drawable.dot_focus})
                .setPageIndicatorAlign(ConvenientBanner.PageIndicatorAlign.CENTER_HORIZONTAL)
                .setOnItemClickListener(clickListener)
                .setPageTransformer(new DefaultTransformer())
                .startTurning(3000)
                .setCanLoop(true);
    }
}
