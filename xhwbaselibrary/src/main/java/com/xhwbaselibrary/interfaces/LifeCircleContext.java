package com.xhwbaselibrary.interfaces;

import android.content.Context;
import android.view.View;

import com.xhwbaselibrary.lifecircle.LifeCircleCallback;

/**
 * Created by xuhuawei on 2017/11/22.
 * 暂时线做一个 后续还会添加
 */

public interface LifeCircleContext {
    /**
     * 获取生命周期 监听的presenter
     * @return
     */
    public LifeCircleCallback getLifeCircleCallbackPresenter();
    public Context getContext();


    public View findViewById(int viewId);
    public void showLoadingView();
    public void dismissLoadingView();
    public void finish();
}
