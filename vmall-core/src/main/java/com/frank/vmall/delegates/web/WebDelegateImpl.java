package com.frank.vmall.delegates.web;

import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.frank.vmall.delegates.IPageLoadListener;
import com.frank.vmall.delegates.web.chromeclient.WebChromeClientImpl;
import com.frank.vmall.delegates.web.client.WebViewClientImpl;
import com.frank.vmall.delegates.web.route.RouteKeys;
import com.frank.vmall.delegates.web.route.Router;

/**
 * Created by Administrator on 2017/9/2 0002.
 */

public class WebDelegateImpl extends WebDelegate implements IWebViewInitializer {
    private IPageLoadListener mIPageLoadListener;

    public static WebDelegateImpl create(String url) {
        final Bundle args = new Bundle();
        args.putString(RouteKeys.URL.name(), url);
        final WebDelegateImpl webDelegate = new WebDelegateImpl();
        webDelegate.setArguments(args);
        return webDelegate;
    }

    public void setPageLoadListener(IPageLoadListener pageLoadListener) {
        this.mIPageLoadListener = pageLoadListener;
    }

    @Override
    public Object setLayout() {
        return getWebView();
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, View rootView) {
        String url = getUrl();
        if (url != null && !url.isEmpty()) {
            // 用原生的方式模拟Web跳转并进行页面加载
            Router.getInstance().loadPage(this, url);
        }
    }

    @Override
    public IWebViewInitializer setInitializer() {
        return this;
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    public WebView initWebView(WebView webView) {
        return new WebViewInitializer().createWebView(webView);
    }

    @Override
    public WebViewClient initWebViewClient() {
        final WebViewClientImpl webViewClient = new WebViewClientImpl(this);
        webViewClient.setPageLoadListener(mIPageLoadListener);
        return webViewClient;
    }

    @Override
    public WebChromeClient initWebChromeClient() {
        return new WebChromeClientImpl();
    }
}
