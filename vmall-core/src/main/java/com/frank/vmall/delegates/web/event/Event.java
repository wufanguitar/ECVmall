package com.frank.vmall.delegates.web.event;

import android.content.Context;
import android.webkit.WebView;

import com.frank.vmall.delegates.VmallDelegate;
import com.frank.vmall.delegates.web.WebDelegate;

/**
 * Created by Administrator on 2017/9/3 0003.
 */

public abstract class Event implements IEvent{

    private Context mContext;
    private String mAction;
    private WebDelegate mDelegate;
    private String mUrl;
    private WebView mWebView;

    public Context getContext() {
        return mContext;
    }

    public void setContext(Context context) {
        this.mContext = context;
    }

    public String getAction() {
        return mAction;
    }

    public void setAction(String action) {
        this.mAction = action;
    }

    public WebDelegate getDelegate() {
        return mDelegate;
    }

    public void setDelegate(WebDelegate delegate) {
        this.mDelegate = delegate;
    }

    public String getUrl() {
        return mUrl;
    }

    public void setUrl(String url) {
        this.mUrl = url;
    }

    public WebView getWebView() {
        return mDelegate.getWebView();
    }
}
