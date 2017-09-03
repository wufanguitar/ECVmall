package com.frank.vmall.ec.main.index;

import android.content.Context;
import android.graphics.Color;
import android.support.design.widget.CoordinatorLayout;
import android.support.v7.widget.Toolbar;
import android.util.AttributeSet;
import android.view.View;

import com.frank.vmall.ec.R;
import com.frank.vmall.ui.recycler.RgbValue;

/**
 * Created by Administrator on 2017/9/2 0002.
 */

@SuppressWarnings("unused")
public class TranslucencyBehavior extends CoordinatorLayout.Behavior<Toolbar> {
    // 顶部距离
    private int mDistanceY = 0;
    // 颜色变化速度
    private static final int COLOR_CHANGE_SPEED = 3;
    // 定义变化的颜色
    private final RgbValue RGB_VALUE = RgbValue.create(255, 124, 2);


    @SuppressWarnings("SpellCheckingInspection")
    public TranslucencyBehavior() {
    }

    @SuppressWarnings("SpellCheckingInspection")
    public TranslucencyBehavior(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public boolean layoutDependsOn(CoordinatorLayout parent, Toolbar child, View dependency) {
        return dependency.getId() == R.id.rcv_index;
    }

    @Override
    public boolean onStartNestedScroll(CoordinatorLayout coordinatorLayout, Toolbar child, View directTargetChild, View target, int nestedScrollAxes) {
        return true;
    }

    @Override
    public void onNestedPreScroll(CoordinatorLayout coordinatorLayout, Toolbar child, View target, int dx, int dy, int[] consumed) {
        super.onNestedPreScroll(coordinatorLayout, child, target, dx, dy, consumed);
        // 增加滑动距离
        mDistanceY += dy;
        // 获取Toolbar的高度
        final int targetHeight = child.getBottom();

        // 滑动时，并且距离小于Toolbar高度的时候，调整渐变色
        if (mDistanceY > 0 && mDistanceY <= targetHeight) {
            final float scale = (float) mDistanceY / targetHeight;
            final float alpha = scale * 255;
            child.setBackgroundColor(Color.argb((int) alpha, RGB_VALUE.red(), RGB_VALUE.green(), RGB_VALUE.blue()));
        } else if (mDistanceY > targetHeight){
            child.setBackgroundColor(Color.rgb(RGB_VALUE.red(), RGB_VALUE.green(), RGB_VALUE.blue()));
        }
    }
}
