package com.wuba.mobile.middle.mis.protocol.router;

import android.os.Bundle;

import com.wuba.mobile.base.common.callback.IRequestCallBack;

import java.util.HashMap;

/**
 * this is project PluginMiddleware
 *
 * @description 一个路由请求.
 * @create liqiuzuo
 * @data 2018/5/30
 */

public class RouteRequest implements IRequestCallBack {

    protected String uri;
    protected String target;
    protected String action;
    protected Bundle extra;
    protected int requestCode;

    protected IRequestCallBack mCallBack;

    public String getUri() {
        return uri;
    }

    public RouteRequest setUri(String uri) {
        this.uri = uri;
        return this;
    }

    public String getTarget() {
        return target;
    }

    public RouteRequest setTarget(String target) {
        this.target = target;
        return this;
    }

    public String getAction() {
        return action;
    }

    public RouteRequest setAction(String action) {
        this.action = action;
        return this;
    }

    public Bundle getExtra() {
        return extra;
    }

    public RouteRequest setExtra(Bundle extra) {
        this.extra = extra;
        return this;
    }

    public int getRequestCode() {
        return requestCode;
    }

    public RouteRequest setRequestCode(int requestCode) {
        this.requestCode = requestCode;
        return this;
    }

    public IRequestCallBack getCallBack() {
        return mCallBack;
    }

    public RouteRequest setCallback(IRequestCallBack callback) {
        this.mCallBack = callback;
        return this;
    }

    @Override
    public void onSuccess(String s, Object o, HashMap hashMap) {
        if (mCallBack != null) {
            mCallBack.onSuccess(s, o, hashMap);
        }
    }

    @Override
    public void onFail(String s, String s1, String s2, HashMap hashMap) {
        if (mCallBack != null) {
            mCallBack.onFail(s, s1, s2, hashMap);
        }
    }
}
