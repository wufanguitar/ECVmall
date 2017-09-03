package com.frank.vmall.ec.main.sort.list;

import android.content.ClipData;
import android.graphics.Color;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.AppCompatTextView;
import android.view.View;

import com.frank.vmall.delegates.VmallDelegate;
import com.frank.vmall.ec.R;
import com.frank.vmall.ec.main.sort.SortDelegate;
import com.frank.vmall.ec.main.sort.content.ContentDelegate;
import com.frank.vmall.ui.recycler.ItemType;
import com.frank.vmall.ui.recycler.MallMultiFields;
import com.frank.vmall.ui.recycler.MallMultiItemEntity;
import com.frank.vmall.ui.recycler.adapter.MallMultiRecyclerAdapter;
import com.frank.vmall.ui.recycler.adapter.MallMultiViewHolder;

import java.sql.SQLTransactionRollbackException;
import java.util.List;

import me.yokeyword.fragmentation.SupportFragment;
import me.yokeyword.fragmentation.SupportHelper;

/**
 * Created by Administrator on 2017/9/2 0002.
 */

public class SortRecyclerAdapter extends MallMultiRecyclerAdapter {
    private final SortDelegate SORT_DELEGATE;
    private int mPrePosition = 0;

    protected SortRecyclerAdapter(List<MallMultiItemEntity> data,
                                  SortDelegate sortDelegate) {
        super(data);
        this.SORT_DELEGATE = sortDelegate;
        // 添加垂直菜单布局
        addItemType(ItemType.SORT_MENU_LIST, R.layout.item_sort_vertical_menu_list);
    }

    @Override
    protected void convert(final MallMultiViewHolder holder, final MallMultiItemEntity itemEntity) {
        super.convert(holder, itemEntity);
        switch (holder.getItemViewType()) {
            case ItemType.SORT_MENU_LIST:
                final String text = itemEntity.getField(MallMultiFields.TEXT);
                final boolean isClicked = itemEntity.getField(MallMultiFields.TAG);
                final AppCompatTextView nameTextView = holder.getView(R.id.tv_vertical_item_name);
                final View line = holder.getView(R.id.view_line);
                final View itemView = holder.itemView;
                itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        final int currentPosition = holder.getAdapterPosition();
                        if (mPrePosition != currentPosition) {
                            // 还原上一个
                            getData().get(mPrePosition).setField(MallMultiFields.TAG, false);
                            notifyItemChanged(mPrePosition);

                            // 更新选中的item
                            itemEntity.setField(MallMultiFields.TAG, true);
                            notifyItemChanged(currentPosition);
                            mPrePosition = currentPosition;

                            final int contentId = getData().get(currentPosition).getField(MallMultiFields.ID);
                            showContent(contentId);
                        }
                    }
                });
                if (!isClicked) {
                    line.setVisibility(View.INVISIBLE);
                    nameTextView.setTextColor(ContextCompat.getColor(mContext, R.color.wechat_black));
                    itemView.setBackgroundColor(ContextCompat.getColor(mContext, R.color.item_background));
                } else {
                    line.setVisibility(View.VISIBLE);
                    nameTextView.setTextColor(ContextCompat.getColor(mContext, R.color.app_main));
                    line.setBackgroundColor(ContextCompat.getColor(mContext, R.color.app_main));
                    itemView.setBackgroundColor(Color.WHITE);
                }
                holder.setText(R.id.tv_vertical_item_name, text);
                break;
            default:
                break;
        }
    }

    private void showContent(int contentId) {
        final ContentDelegate contentDelegate = ContentDelegate.newInstance(contentId);
        switchContent(contentDelegate);
    }

    private void switchContent(ContentDelegate delegate) {
        final VmallDelegate contentDelegate = SORT_DELEGATE.findChildFragment(ContentDelegate.class);
        if (contentDelegate != null) {
            contentDelegate.replaceFragment(delegate, false);
        }
    }
}
