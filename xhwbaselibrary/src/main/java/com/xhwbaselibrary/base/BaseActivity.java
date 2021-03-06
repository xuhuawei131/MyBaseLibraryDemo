package com.xhwbaselibrary.base;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.widget.Toast;

import com.roger.catloadinglibrary.CatLoadingView;
import com.xhwbaselibrary.interfaces.LifeCircleContext;
import com.xhwbaselibrary.lifecircle.ActivityCallbackManager;
import com.xhwbaselibrary.lifecircle.LifeCircleCallback;
import com.xhwbaselibrary.lifecircle.Lifecycle;
import com.xhwbaselibrary.lifecircle.LifecycleManager;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by lingdian on 17/9/13.
 */

public abstract class BaseActivity extends AppCompatActivity implements LifeCircleCallback {
    private LifecycleManager lifecycleManager = new LifecycleManager();
    protected abstract void init();
    protected abstract int setContentView();
    protected abstract void findViewByIds();
    protected abstract void requestService();
    protected abstract void onMyDestory();
    private CatLoadingView mView;
    private List<Toast> arrayList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        init();
        arrayList = new ArrayList<>();
        int resLayout = setContentView();
        setContentView(resLayout);
        findViewByIds();
        requestService();
        lifecycleManager.onCreate();
    }

    public void showProgressDialog(String content) {
        if (TextUtils.isEmpty(content)) {
            content = "加载中...";
        }
        if (mView == null) {
            mView = new CatLoadingView();
        }
        if (!mView.isAdded()) {
            mView.show(getSupportFragmentManager(), content);
        }
    }

    public void showProgressDialog() {
        showProgressDialog(null);
    }

    public void disProgressDialog() {
        if (mView != null && mView.isAdded()) {
            mView.dismiss();
        }
    }

    public void showToast(String content) {
        Toast toast = Toast.makeText(getContext(), content, Toast.LENGTH_SHORT);
        toast.show();
        arrayList.add(toast);
    }

    public void showToast(int content) {
        Toast toast = Toast.makeText(getContext(), content, Toast.LENGTH_SHORT);
        toast.show();
        arrayList.add(toast);
    }

    @Override
    public Context getContext() {
        return this;
    }

    @Override
    protected void onResume() {
        super.onResume();
        lifecycleManager.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
        lifecycleManager.onPause();
    }

    @Override
    public void onStop() {
        super.onStop();
        lifecycleManager.onStop();
    }
    @Override
    public void onDestroy() {
        super.onDestroy();
        for (Toast toast : arrayList) {
            toast.cancel();
        }
        arrayList.clear();
        lifecycleManager.onDestroy();
        lifecycleManager = null;
        onMyDestory();
    }

    @Override
    public void addLifecycleListener(@NonNull Lifecycle lifecycle) {
        lifecycleManager.add(lifecycle);
    }
    @Override
    public void removeLifecycleListener(@NonNull Lifecycle lifecycle) {
        lifecycleManager.remove(lifecycle);
    }
    public void removeLifecycleListener(@NonNull String listenerId) {
        lifecycleManager.remove(listenerId);
    }
}
