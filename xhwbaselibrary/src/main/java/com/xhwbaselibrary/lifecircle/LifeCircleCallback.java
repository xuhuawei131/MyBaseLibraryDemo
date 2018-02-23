package com.xhwbaselibrary.lifecircle;

import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.support.annotation.NonNull;

/**
 * 生命周期监听
 * Created by xuhuawei on 2017/6/5 0005.
 */

public interface LifeCircleCallback {
    public Context getContext();

    public void onCreate();
    public void onResume() ;
    public void onPause() ;
    public void onStop() ;
    public void onDestroy() ;

    /**
     * 添加生命周期监听
     * @param lifecycle
     */
    public void addLifecycleListener(@NonNull Lifecycle lifecycle) ;

    /**
     * 移除生命周期监听
     * @param lifecycle
     */
    public void removeLifecycleListener(@NonNull Lifecycle lifecycle) ;

    public void onActivityResult(int requestCode, int resultCode, Intent data) ;

    public void onConfigurationChanged(Configuration newConfig) ;

}
