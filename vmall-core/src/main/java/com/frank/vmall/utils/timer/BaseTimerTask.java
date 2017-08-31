package com.frank.vmall.utils.timer;

import java.util.TimerTask;

/**
 * Created by Administrator on 2017/8/30 0030.
 */

public class BaseTimerTask extends TimerTask {
    private ITimerListener mTimerListener;

    public BaseTimerTask(ITimerListener timerListener) {
        this.mTimerListener = timerListener;
    }

    @Override
    public void run() {
        if (mTimerListener != null) {
            mTimerListener.onTimer();
        }
    }
}
