package com.frank.vmall.delegates.web.client;

import android.graphics.Bitmap;
import android.net.http.SslError;
import android.os.Build;
import android.os.Handler;
import android.util.Log;
import android.webkit.SslErrorHandler;
import android.webkit.WebResourceError;
import android.webkit.WebResourceRequest;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.frank.vmall.app.Mall;
import com.frank.vmall.delegates.IPageLoadListener;
import com.frank.vmall.delegates.web.WebDelegate;
import com.frank.vmall.delegates.web.route.RouteKeys;
import com.frank.vmall.delegates.web.route.Router;
import com.frank.vmall.ui.loader.MallLoader;
import com.frank.vmall.utils.log.MallLogger;

import retrofit2.http.Url;

/**
 * Created by Administrator on 2017/9/2 0002.
 */

public class WebViewClientImpl extends WebViewClient {

    private final WebDelegate WEB_DELEGATE;
    private IPageLoadListener mIPageLoadListener;
    private static final Handler HANDLER = Mall.getHandler();

    public void setPageLoadListener(IPageLoadListener pageLoadListener) {
        this.mIPageLoadListener = pageLoadListener;
    }

    public WebViewClientImpl(WebDelegate webDelegate) {
        this.WEB_DELEGATE = webDelegate;
    }

    @Override
    public boolean shouldOverrideUrlLoading(WebView view, String url) {
        MallLogger.d("shouldOverrideUrlLoading", url);
        return Router.getInstance().handleWebUrl(WEB_DELEGATE, url);
    }

    @Override
    public void onPageStarted(WebView view, String url, Bitmap favicon) {
        super.onPageStarted(view, url, favicon);
        if (mIPageLoadListener != null) {
            mIPageLoadListener.onPageLoadStart();
        }
        MallLoader.showLoading(view.getContext());
    }

    @Override
    public void onPageFinished(WebView view, String url) {
        super.onPageFinished(view, url);
        if (mIPageLoadListener != null) {
            mIPageLoadListener.onPageLoadFinish();
        }
        HANDLER.postDelayed(new Runnable() {
            @Override
            public void run() {
                MallLoader.stopLoading();
            }
        }, 1000);

    }
}
