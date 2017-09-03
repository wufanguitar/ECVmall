package com.frank.vmall.ui.refresh;

/**
 * Created by Administrator on 2017/9/1 0001.
 */

public final class PagingBean {
    // 当前是第几页
    private int mPageIndex;
    // 总数据条数
    private int mTotal;
    // 一页显示几条数据
    private int mPageSize;
    // 当前已经显示了几条数据
    private int mCurDataCount;
    // 加载延时
    private int mDelayed;

    public int getPageIndex() {
        return mPageIndex;
    }

    public int getTotal() {
        return mTotal;
    }

    public int getPageSize() {
        return mPageSize;
    }

    public int getCurDataCount() {
        return mCurDataCount;
    }

    public int getDelayed() {
        return mDelayed;
    }

    public PagingBean setPageIndex(int pageIndex) {
        this.mPageIndex = pageIndex;
        return this;
    }

    public PagingBean setTotal(int total) {
        this.mTotal = total;
        return this;
    }

    public PagingBean setPageSize(int pageSize) {
        this.mPageSize = pageSize;
        return this;
    }

    public PagingBean setCurDataCount(int curDataCount) {
        this.mCurDataCount = curDataCount;
        return this;
    }

    public PagingBean setDelayed(int delayed) {
        this.mDelayed = delayed;
        return this;
    }

    PagingBean addIndex() {
        mPageIndex++;
        return this;
    }
}
