package com.frank.vmall.delegates.web.route;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v4.content.ContextCompat;
import android.webkit.URLUtil;
import android.webkit.WebView;

import com.frank.vmall.delegates.VmallDelegate;
import com.frank.vmall.delegates.web.WebDelegate;
import com.frank.vmall.delegates.web.WebDelegateImpl;

/**
 * Created by Administrator on 2017/9/2 0002.
 */

public class Router {
    private Router() {

    }

    private static class Holder {
        private static final Router INSTANCE = new Router();
    }

    public static Router getInstance() {
        return Holder.INSTANCE;
    }

    public final boolean handleWebUrl(WebDelegate webDelegate, String url) {
        // 如果是电话协议
        if (url.contains("tel:")) {
            callPhone(webDelegate.getContext(), url);
            return true;
        }

        final VmallDelegate topDelegate = webDelegate.getTopDelegate();
        final WebDelegateImpl webDelegateImpl = WebDelegateImpl.create(url);
        topDelegate.start(webDelegateImpl);
        return true;
    }

    private void loadWebPage(WebView webView, String url) {
        if (webView != null) {
            webView.loadUrl(url);
        } else {
            throw new NullPointerException("WebView is null");
        }
    }

    private void loadLocalPage(WebView webView, String url) {
        loadWebPage(webView, "file:///android_asset/" + url);
    }

    private final void loadPage(WebView webView, String url) {
        if (URLUtil.isNetworkUrl(url) || URLUtil.isAssetUrl(url)) {
            loadWebPage(webView, url);
        } else {
            loadLocalPage(webView, url);
        }
    }

    public final void loadPage(WebDelegate webDelegate, String url) {
        loadPage(webDelegate.getWebView(), url);
    }

    private void callPhone(Context context, String uri) {
        final Intent intent = new Intent(Intent.ACTION_DIAL);
        final Uri data = Uri.parse(uri);
        intent.setData(data);
        ContextCompat.startActivity(context, intent, null);
    }
}
