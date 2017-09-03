package com.frank.ecvmall;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.webkit.WebView;
import android.widget.Toast;

import com.frank.vmall.activities.ProxyActivity;
import com.frank.vmall.app.ConfigKeys;
import com.frank.vmall.app.Mall;
import com.frank.vmall.delegates.VmallDelegate;
import com.frank.vmall.ec.launcher.LauncherDelegate;
import com.frank.vmall.ec.main.EcBottomDelegate;
import com.frank.vmall.ec.sign.ISignListener;
import com.frank.vmall.ec.sign.SignInDelegate;
import com.frank.vmall.ec.sign.SignUpDelegate;
import com.frank.vmall.ui.launcher.ILauncherListener;
import com.frank.vmall.ui.launcher.OnLauncherFinishTag;

import qiu.niorgai.StatusBarCompat;

public class EcvmallActivity extends ProxyActivity implements
        ISignListener,
        ILauncherListener {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        final ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.hide();
        }
        Mall.getConfigurations().put(ConfigKeys.ACTIVITY, this);
        StatusBarCompat.translucentStatusBar(this, true);
    }

    @Override
    public VmallDelegate setRootDelegate() {
        return new LauncherDelegate();
    }

    @Override
    public void onSignInSuccess() {
        Toast.makeText(this, "登录成功", Toast.LENGTH_LONG).show();
    }

    @Override
    public void onSignUpSuccess() {
        Toast.makeText(this, "注册成功", Toast.LENGTH_LONG).show();
    }

    @Override
    public void onLauncherFinish(OnLauncherFinishTag tag) {
        switch (tag) {
            case SIGNED:
                Toast.makeText(this, "启动结束，用户已经登录", Toast.LENGTH_LONG).show();
                startWithPop(new EcBottomDelegate());
                break;
            case NOT_SIGNED:
                Toast.makeText(this, "启动结束，用户没有登录", Toast.LENGTH_LONG).show();
                startWithPop(new SignInDelegate());
                break;
            default:
                break;
        }
    }
}
