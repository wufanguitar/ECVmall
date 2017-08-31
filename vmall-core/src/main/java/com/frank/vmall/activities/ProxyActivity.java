package com.frank.vmall.activities;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.ContentFrameLayout;

import com.frank.vmall.R;
import com.frank.vmall.delegates.VmallDelegate;

import me.yokeyword.fragmentation.SupportActivity;

/**
 * Created by Administrator on 2017/8/28 0028.
 * 为了不让其他开发者直接使用该类，必然会有一个主Activity去继承该类，故声明为抽象类
 */

public abstract class ProxyActivity extends SupportActivity {

    // 返回根delegate
    public abstract VmallDelegate setRootDelegate();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initContainer(savedInstanceState);
    }

    private void initContainer(@Nullable Bundle savedInstanceState) {
        /*
           一般来说，容纳fragment的根容器都是FrameLayout
           这里我们使用v7包中的ContentFrameLayout
         */
        final ContentFrameLayout container = new ContentFrameLayout(this);
        // 会在R文件中生成一个独有的ID，绝对不会重复
        container.setId(R.id.delegate_container);
        setContentView(container);
        // 如果是第一次加载
        if (savedInstanceState == null) {
            loadRootFragment(R.id.delegate_container, setRootDelegate());
        }
    }

    /**
     * 由于我们是用单Activity架构，因此当应用退出时也就说明该Activity也销毁了
     * 那么，可以在onDestroy中进行垃圾回收，根据Java GC原理该操作不一定会立即执行
     */
    @Override
    protected void onDestroy() {
        super.onDestroy();
        System.gc();
        System.runFinalization();
    }
}
