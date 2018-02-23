package com.xhwbaselibrary.base;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.widget.Toast;

import com.roger.catloadinglibrary.CatLoadingView;
import com.xhwbaselibrary.interfaces.LifeCircleContext;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by lingdian on 17/9/13.
 */

public abstract class BaseActivity extends AppCompatActivity implements LifeCircleContext {
    protected abstract void init();

    protected abstract int setContentView();

    protected abstract void findViewByIds();

    protected abstract void requestService();

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

        IntentFilter intentFilter = new IntentFilter();
//        intentFilter.addAction(ACTION_EXIT_ALL_LIFE);
        LocalBroadcastManager.getInstance(this).registerReceiver(broadcastReceiver, intentFilter);

    }

    private BroadcastReceiver broadcastReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            finish();
        }
    };

    protected void showProgressDialog(String content) {
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

    protected void showProgressDialog() {
        showProgressDialog(null);
    }

    protected void disProgressDialog() {
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

    protected abstract void onMyDestory();

    @Override
    protected void onDestroy() {
        super.onDestroy();
        for (Toast toast : arrayList) {
            toast.cancel();
        }
        arrayList.clear();

        onMyDestory();
        LocalBroadcastManager.getInstance(this).unregisterReceiver(broadcastReceiver);
    }
}
