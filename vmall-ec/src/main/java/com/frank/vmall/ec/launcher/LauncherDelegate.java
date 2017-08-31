package com.frank.vmall.ec.launcher;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.AppCompatTextView;
import android.view.View;

import com.frank.vmall.delegates.VmallDelegate;
import com.frank.vmall.ec.R;
import com.frank.vmall.ec.R2;
import com.frank.vmall.ui.launcher.ScrollLauncherTag;
import com.frank.vmall.utils.storage.MallPreference;
import com.frank.vmall.utils.timer.BaseTimerTask;
import com.frank.vmall.utils.timer.ITimerListener;

import java.text.MessageFormat;
import java.util.Timer;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by Administrator on 2017/8/30 0030.
 */

public class LauncherDelegate extends VmallDelegate implements ITimerListener {

    @BindView(R2.id.tv_launcher_timer)
    AppCompatTextView mTvTimer;

    private Timer mTimer;
    private int mCount = 5;

    @OnClick(R2.id.tv_launcher_timer)
    void onClickTimerView() {
        if (mTimer != null) {
            mTimer.cancel();
            mTimer = null;
            checkIsShowScrollDelegate();
        }
    }

    private void intiTimer() {
        mTimer = new Timer();
        final BaseTimerTask task = new BaseTimerTask(this);
        mTimer.schedule(task, 0, 1000);
    }

    @Override
    public Object setLayout() {
        return R.layout.delegate_launcher;
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, View rootView) {
        intiTimer();
    }

    private void checkIsShowScrollDelegate() {
        if (!MallPreference.getAppFlag(ScrollLauncherTag.HAS_FIRST_LAUNCHER_APP.name())) {
            start(new LauncherScrollDelegate(), SINGLETASK);
        } else {
            // 检查用户是否登录了APP
        }
    }

    @Override
    public void onTimer() {
        getProxyActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                if (mTvTimer != null) {
                    mTvTimer.setText(MessageFormat.format("跳过\n{0}s", mCount));
                    mCount--;
                    if (mCount < 0 && mTimer != null) {
                        mTimer.cancel();
                        mTimer = null;
                        checkIsShowScrollDelegate();
                    }
                }
            }
        });
    }
}
