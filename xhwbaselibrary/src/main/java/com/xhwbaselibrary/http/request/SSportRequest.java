package com.xhwbaselibrary.http.request;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.text.TextUtils;


import com.xhwbaselibrary.http.XhwRequestChecker;
import com.xhwbaselibrary.http.XhwHttp;
import com.xhwbaselibrary.http.executor.HttpExecutor;
import com.xhwbaselibrary.http.response.BaseSSResponse;
import com.xhwbaselibrary.interfaces.LifeCircleContext;
import com.xhwbaselibrary.lifecircle.LifeCircleCallback;
import com.xhwbaselibrary.lifecircle.Lifecycle;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * 网络请求
 * Created by xuhuawei on 2017/6/6 0006.
 */

public class SSportRequest {
    /**
     * 请求的方式
     */
    public enum RequestMethod {
        POST, GET, UPLOAD, DOWNLOAD
    }
    //------------------------------------
    /**
     * 默认是post请求方式
     */
    private RequestMethod requestType = RequestMethod.POST;
    /**
     * 请求回调代理类
     */
    private BaseSSResponse response;

    /**
     * 请求的Url
     */
    private String url;
    /**
     * 请求的标号，用于取消请求使用
     */
    protected String tag = "";


    /**
     * 适合在无Activity/Fragment的情况下使用
     */
    protected Context context;
    private LifeCircleContext lcContext;
    protected HashMap<String, String> paramMap = new HashMap<String, String>();
    protected HashMap<String, String> headerMap = new HashMap<String, String>();

    protected HashMap<String, ArrayList<File>> fileMap = new HashMap<String, ArrayList<File>>();

    public LifeCircleContext getLifeCircleContext() {
        return lcContext;
    }

    public Lifecycle getLifecycle() {
        return lifecycle;
    }

    /**
     * 请求的的文字描述
     */
    protected String requestDesc = "";

    /**
     * 文件名称
     */
    protected String downloadFileName = "";
    /**
     * 文件目录
     */
    protected String downloadFileDir = "";
    /**
     * 是否支持断点续传
     */
    private boolean supportResumeBreakPoint = false;

    /**
     * 生命周期回调
     */
    private Lifecycle lifecycle = new Lifecycle() {
        @Override
        public void onLifecycleChange(int status) {
            if (status == STATUS_DESTROY) {
                XhwHttp.cancelRequest(tag);
            }
        }
    };


    public SSportRequest() {
    }

    public SSportRequest(String tag) {
        this.tag = tag;
    }

    public String getTag() {
        return tag;
    }

    public Context getContext() {
        return context;
    }


    public String getRequestDesc() {
        return requestDesc;
    }


    public SSportRequest setRequestDesc(String requestDesc) {
        this.requestDesc = requestDesc;
        return this;
    }

    public SSportRequest setTag(String tag) {
        this.tag = tag;
        return this;
    }


    /**
     * 是否支持断点续传
     *
     * @return
     */
    public boolean isSupportResumeBreakPoint() {
        return supportResumeBreakPoint;
    }

    /**
     * 设置是否支持断点续传
     *
     * @param supportResumeBreakPoint
     * @return
     */
    public SSportRequest setSupportResumeBreakPoint(boolean supportResumeBreakPoint) {
        this.supportResumeBreakPoint = supportResumeBreakPoint;
        return (SSportRequest) this;
    }


    public String getUrl() {
        return url;
    }

    public SSportRequest setUrl(String url) {
        this.url = url;
        return this;
    }

    public SSportRequest bind(@NonNull Fragment fragment) {
        return setFragment(fragment);
    }

    public SSportRequest bind(@NonNull Activity activity) {
        return setActivity(activity);
    }

    public SSportRequest bind(@NonNull Context context) {
        return setAppContext(context);
    }

    public SSportRequest setActivity(@NonNull Activity activity) {
        this.context = activity;
        if (TextUtils.isEmpty(tag)) {
            tag = activity.getClass().getName();
        }
        if (activity instanceof LifeCircleContext) {

            LifeCircleContext activityContext = (LifeCircleContext) activity;
            lcContext = activityContext;
            LifeCircleCallback presenter = activityContext.getLifeCircleCallbackPresenter();
            presenter.addLifecycleListener(lifecycle);
        }
        return this;
    }

    public SSportRequest setFragment(@NonNull Fragment fragment) {
        if (TextUtils.isEmpty(tag)) {
            tag = fragment.getClass().getName();
        }

        this.context = fragment.getContext();
        if (TextUtils.isEmpty(tag)) {
            tag = fragment.getClass().getName();
        }
        //如果fragment 实现了LifeCircleContext
        if (fragment instanceof LifeCircleContext) {
            LifeCircleContext fragmentContext = (LifeCircleContext) fragment;
            lcContext = fragmentContext;
            LifeCircleCallback presenter = fragmentContext.getLifeCircleCallbackPresenter();
            presenter.addLifecycleListener(lifecycle);
        }
        return this;
    }

    public Context getAppContext() {
        return context;
    }


    public SSportRequest setAppContext(@NonNull Context context) {
        this.context = context;
        if (TextUtils.isEmpty(tag)) {
            tag = context.getClass().getName();
        }
        return this;
    }

    public HashMap<String, ArrayList<File>> getFileMap() {
        return fileMap;
    }

    public SSportRequest setFileMap(HashMap<String, ArrayList<File>> fileMap) {
        this.fileMap = fileMap;
        return this;
    }

    public RequestMethod getRequestType() {
        return requestType;
    }

    public SSportRequest setRequestType(RequestMethod requestType) {
        this.requestType = requestType;
        return this;
    }

    public String getDownloadFileDir() {
        return downloadFileDir;
    }

    public SSportRequest setDownloadFileDir(String downloadFileDir) {
        this.downloadFileDir = downloadFileDir;
        return this;
    }

    public HashMap<String, String> getParamMap() {
        return paramMap;
    }

    public SSportRequest setParamMap(HashMap<String, String> paramMap) {
        this.paramMap = paramMap;
        return this;
    }

    public HashMap<String, String> getHeaderMap() {
        return headerMap;
    }

    public SSportRequest setHeaderMap(HashMap<String, String> headerMap) {
        this.headerMap = headerMap;
        return this;
    }

    public SSportRequest addParam(String key, String value) {
        paramMap.put(key, value);
        return this;
    }

    public SSportRequest removeParam(String key) {
        if (paramMap != null && paramMap.containsKey(key)) {
            paramMap.remove(key);
        }
        return this;
    }

    public SSportRequest clearParams() {
        paramMap.clear();
        return this;
    }

    public SSportRequest addHeaderParam(String key, String value) {
        headerMap.put(key, value);
        return this;
    }

    public SSportRequest removeHeaderParam(String key) {
        if (headerMap != null && headerMap.containsKey(key)) {
            headerMap.remove(key);
        }
        return this;
    }

    public SSportRequest clearHeaderParams() {
        headerMap.clear();
        return this;
    }

    public SSportRequest addFileParam(@NonNull String key, @NonNull File file) {

        if (fileMap.containsKey(key)) {
            ArrayList<File> fileList = fileMap.get(key);
            fileList.add(file);
        } else {
            ArrayList<File> fileList = new ArrayList<>();
            fileList.add(file);
            fileMap.put(key, fileList);
        }

        return this;
    }

    public SSportRequest removeFileParam(String key) {
        if (fileMap != null && fileMap.containsKey(key)) {
            fileMap.remove(key);
        }
        return this;
    }

    public SSportRequest clearFileParams() {
        fileMap.clear();
        return this;
    }

    public String getDownloadFileName() {
        return downloadFileName;
    }

    public SSportRequest setDownloadFileName(String downloadFileName) {
        this.downloadFileName = downloadFileName;
        return this;
    }

    public BaseSSResponse getResponseProxy() {
        return response;
    }

    public SSportRequest send() {
        return send(null);
    }

    public SSportRequest send(BaseSSResponse proxy) {

        try {

            if (proxy != null) {
                this.response = proxy;
                this.response.setRequest(this);
            }

            if (XhwRequestChecker.check(this)) {
                if (getRequestType() == RequestMethod.GET) {
                    HttpExecutor.getInstance().sendGet(this);
                } else if (getRequestType().equals(RequestMethod.POST)) {
                    HttpExecutor.getInstance().sendPost(this);
                } else if (getRequestType().equals(RequestMethod.DOWNLOAD)) {
                    HttpExecutor.getInstance().upload(this);
                } else if (getRequestType().equals(RequestMethod.DOWNLOAD)) {
                    HttpExecutor.getInstance().download(this);
                }
            } else {
                //发送请求前被检测出问题，MageRequestChecker会自行处理，所以这里不需要做任何事情
            }

        } catch (RuntimeException e) {
            e.printStackTrace();
        }
        return this;
    }

    public void retry() {
        send();
    }


}
