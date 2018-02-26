package com.xuhuawei.mybaselibrarydemo;


import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.Response;
import com.xhwbaselibrary.base.BaseActivity;

import okhttp3.Call;

public class NetRequestActivity extends BaseActivity {
    @Override
    protected void init() {

    }
    @Override
    protected int setContentView() {
        return R.layout.activity_net_request;
    }
    @Override
    protected void findViewByIds() {

    }
    @Override
    protected void requestService() {
        String url="http://www.522yw.life/article/46085.html";
//        OkGo.<String>post(url).tag(this).execute(new StringCallback() {
//
//            @Override
//            public void onSuccess(Response<String> response) {
//
//            }
//            @Override
//            public void onCacheSuccess(Response<String> response) {
//            }
//            @Override
//            public void onError(Response<String> response) {
//                int code = response.code();
//            }
//            @Override
//            public void onFinish() {
//            }
//        });
    }
    @Override
    protected void onMyDestory() {

    }
}
