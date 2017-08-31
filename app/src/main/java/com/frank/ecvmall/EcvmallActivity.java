package com.frank.ecvmall;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.view.Window;

import com.frank.vmall.activities.ProxyActivity;
import com.frank.vmall.delegates.VmallDelegate;
import com.frank.vmall.ec.launcher.LauncherDelegate;
import com.frank.vmall.ec.launcher.LauncherScrollDelegate;
import com.frank.vmall.ec.sign.SignUpDelegate;

public class EcvmallActivity extends ProxyActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        final ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.hide();
        }
    }

    @Override
    public VmallDelegate setRootDelegate() {
        return new SignUpDelegate();
    }
}
