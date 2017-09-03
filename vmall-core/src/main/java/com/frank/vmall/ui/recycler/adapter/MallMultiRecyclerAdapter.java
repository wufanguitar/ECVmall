package com.frank.vmall.ui.recycler.adapter;

import android.support.v7.widget.GridLayoutManager;
import android.view.View;
import android.widget.ImageView;

import com.bigkoo.convenientbanner.ConvenientBanner;
import com.bigkoo.convenientbanner.listener.OnItemClickListener;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.chad.library.adapter.base.BaseMultiItemQuickAdapter;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.frank.vmall.R;
import com.frank.vmall.ui.banner.BannerCreator;
import com.frank.vmall.ui.recycler.DataConverter;
import com.frank.vmall.ui.recycler.ItemType;
import com.frank.vmall.ui.recycler.MallMultiFields;
import com.frank.vmall.ui.recycler.MallMultiItemEntity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/9/1 0001.
 */

public class MallMultiRecyclerAdapter extends BaseMultiItemQuickAdapter<
        MallMultiItemEntity, MallMultiViewHolder> implements
        BaseQuickAdapter.SpanSizeLookup,
        OnItemClickListener {

    // 确保初始化一次Banner，防止重复Item加载
    private boolean mIsInitBanner = false;
    // 设置图片加载策略
    private static final RequestOptions RECYCLER_OPTIONS =
            new RequestOptions()
                    .centerCrop()
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .dontAnimate();

    protected MallMultiRecyclerAdapter(List<MallMultiItemEntity> data) {
        super(data);
        init();
    }

    public static MallMultiRecyclerAdapter create(List<MallMultiItemEntity> data) {
        return new MallMultiRecyclerAdapter(data);
    }

    public static MallMultiRecyclerAdapter create(DataConverter converter) {
        return new MallMultiRecyclerAdapter(converter.convert());
    }

    private void init() {
        // 设置不同的item布局
        addItemType(ItemType.TEXT, R.layout.item_multiple_text);
        addItemType(ItemType.IMAGE, R.layout.item_multiple_image);
        addItemType(ItemType.IMAGE_TEXT, R.layout.item_multiple_image_text);
        addItemType(ItemType.BANNER, R.layout.item_multiple_banner);
        // 设置宽度监听
        setSpanSizeLookup(this);
        openLoadAnimation();
        // 多次执行动画
        isFirstOnly(false);
    }

    @Override
    protected MallMultiViewHolder createBaseViewHolder(View view) {
        return MallMultiViewHolder.create(view);
    }

    @Override
    protected void convert(MallMultiViewHolder holder, MallMultiItemEntity itemEntity) {
        final String text;
        final String imageUrl;
        final ArrayList<String> bannerImages;
        switch (holder.getItemViewType()) {
            case ItemType.TEXT:
                text = itemEntity.getField(MallMultiFields.TEXT);
                holder.setText(R.id.text_single, text);
                break;
            case ItemType.IMAGE:
                imageUrl = itemEntity.getField(MallMultiFields.IMAGE_URL);
                Glide.with(mContext)
                        .load(imageUrl)
                        .apply(RECYCLER_OPTIONS)
                        .into((ImageView) holder.getView(R.id.image_single));
                break;
            case ItemType.IMAGE_TEXT:
                text = itemEntity.getField(MallMultiFields.TEXT);
                imageUrl = itemEntity.getField(MallMultiFields.IMAGE_URL);
                Glide.with(mContext)
                        .load(imageUrl)
                        .apply(RECYCLER_OPTIONS)
                        .into((ImageView) holder.getView(R.id.image_multiple));
                holder.setText(R.id.text_multiple, text);
                break;
            case ItemType.BANNER:
                if (!mIsInitBanner) {
                    bannerImages = itemEntity.getField(MallMultiFields.BANNERS);
                    final ConvenientBanner<String> convenientBanner = holder.getView(R.id.banner_recycler_item);
                    BannerCreator.setDefault(convenientBanner, bannerImages, this);
                    mIsInitBanner = true;
                }
                break;
            default:
                break;
        }
    }

    @Override
    public int getSpanSize(GridLayoutManager gridLayoutManager, int position) {
        return getData().get(position).getField(MallMultiFields.SPAN_SIZE);
    }

    @Override
    public void onItemClick(int position) {

    }
}
