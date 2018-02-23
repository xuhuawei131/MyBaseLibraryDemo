package com.xhwbaselibrary.lifecircle;

import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.support.annotation.NonNull;

/**
 * 生命周期监听的presenter
 * Created by xuhuawei on 2017/6/6 0006.
 */

public class LifeCirclePresenter implements LifeCircleCallback {
    private LifecycleManager lifecycleManager = new LifecycleManager();
    private ActivityCallbackManager callbackManager = new ActivityCallbackManager();
    private Context context;

    public LifeCirclePresenter(Context context) {
        this.context = context;
    }

    @Override
    public Context getContext() {
        return context;
    }
    @Override
    public void onCreate() {
        lifecycleManager.onCreate();
    }

    public void onResume() {
        lifecycleManager.onResume();
    }

    public void onPause() {
        lifecycleManager.onPause();
    }

    public void onStop() {
        lifecycleManager.onStop();
    }

    public void onDestroy() {
        lifecycleManager.onDestroy();
        lifecycleManager = null;
    }

    public void addLifecycleListener(@NonNull Lifecycle lifecycle) {
        lifecycleManager.add(lifecycle);
    }

    public void removeLifecycleListener(@NonNull Lifecycle lifecycle) {
        lifecycleManager.remove(lifecycle);
    }

    public void removeLifecycleListener(@NonNull String listenerId) {
        lifecycleManager.remove(listenerId);
    }

    //--------------------------------------------------------
    public void addActivityCallback(ActivityCallback callback) {
        callbackManager.addCallback(callback);
    }

    public void removeActivityCallback(ActivityCallback callback) {
        callbackManager.removeCallback(callback);
    }

    public void removeActivityCallback(String callbackId) {
        callbackManager.removeCallback(callbackId);
    }


    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        callbackManager.onActivityResult(requestCode, resultCode, data);
    }

    public void onConfigurationChanged(Configuration newConfig) {
        callbackManager.onConfigurationChanged(newConfig);
    }

}
