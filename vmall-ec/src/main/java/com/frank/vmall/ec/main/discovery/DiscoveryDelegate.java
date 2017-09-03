package com.frank.vmall.ec.main.discovery;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.webkit.CookieManager;
import android.webkit.CookieSyncManager;
import android.webkit.WebView;

import com.frank.vmall.delegates.bottom.BottomItemDelegate;
import com.frank.vmall.delegates.web.WebDelegateImpl;
import com.frank.vmall.ec.R;

/**
 * Created by Administrator on 2017/9/2 0002.
 */

public class DiscoveryDelegate extends BottomItemDelegate {
    private WebDelegateImpl webDelegate;

    @Override
    public Object setLayout() {
        return R.layout.delegate_discovery;
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, View rootView) {

    }

    @Override
    public void onLazyInitView(@Nullable Bundle savedInstanceState) {
        super.onLazyInitView(savedInstanceState);
        final WebDelegateImpl webDelegate = WebDelegateImpl.create("index.html");
        webDelegate.setTopDelegate(this.getParentDelegate());
        loadRootFragment(R.id.web_discovery_container, webDelegate);
        this.webDelegate = webDelegate;
    }
}
