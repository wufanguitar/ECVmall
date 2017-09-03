package com.frank.vmall.ec.main.sort.content;

import android.support.v7.widget.AppCompatImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.chad.library.adapter.base.BaseSectionQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.frank.vmall.ec.R;

import java.util.List;

/**
 * Created by Administrator on 2017/9/2 0002.
 */

public class SectionAdapter extends BaseSectionQuickAdapter<SectionBean, BaseViewHolder> {
    private static final RequestOptions OPTIONS = new RequestOptions()
            .diskCacheStrategy(DiskCacheStrategy.ALL)
            .dontAnimate();


    public SectionAdapter(int layoutResId, int sectionHeadResId, List<SectionBean> data) {
        super(layoutResId, sectionHeadResId, data);
    }

    @Override
    protected void convertHead(BaseViewHolder helper, SectionBean item) {
        helper.setText(R.id.header, item.header);
        helper.setVisible(R.id.more, item.isMore());
        helper.addOnClickListener(R.id.more);
    }

    @Override
    protected void convert(BaseViewHolder helper, SectionBean item) {
        final SectionContentItemEntity entity = item.t;
        final String thumb = entity.getGoodsThumb();
        final String name = entity.getGoodsName();
        final int goodsId = entity.getGoodsId();
        helper.setText(R.id.tv, name);
        final AppCompatImageView goodsImageView = helper.getView(R.id.iv);
        Glide.with(mContext)
                .load(thumb)
                .apply(OPTIONS)
                .into(goodsImageView);
    }
}
