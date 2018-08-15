package com.wuba.mobile.middle.mis.protocol.router;

import android.os.Bundle;
import android.support.annotation.Nullable;

import java.io.Serializable;

/**
 * this is project PluginMiddleware
 *
 * @description 一个路由请求.
 * @create liqiuzuo
 * @data 2018/5/30
 */

public class RouteRequest implements Serializable {

    private static final int INVALID_CODE = -1;
    //Base
    protected String uri;
    protected String host;
    protected String target;
    protected String action;
    protected Bundle extra;
    protected int requestCode;
    protected int flags = -1; //Flag of route
    protected String type;
    @Nullable
    private Bundle activityOptionsBundle;
    private int enterAnim = INVALID_CODE;
    private int exitAnim = INVALID_CODE;
    protected int requestID = INVALID_CODE;

    //callback
    protected RouterCallback mCallBack;

    public RouteRequest() {
    }

    public String getUri() {
        return uri;
    }

    public RouteRequest setUri(String uri) {
        this.uri = uri;
        return this;
    }

    public String getHost() {
        return host;
    }

    public RouteRequest setHost(String host) {
        this.host = host;
        return this;
    }

    public int getRequestID() {
        return requestID;
    }

    public RouteRequest setRequestID(int requestID) {
        this.requestID = requestID;
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

    public void setType(String type) {
        this.type = type;
    }

    public RouterCallback getCallBack() {
        return mCallBack;
    }

    public void callback(RouterCallback callback) {
        this.mCallBack = callback;
    }

    public void setEnterAnim(int enterAnim) {
        this.enterAnim = enterAnim;
    }

    public int getEnterAnim() {
        return enterAnim;
    }

    public void setExitAnim(int exitAnim) {
        this.exitAnim = exitAnim;
    }

    public int getExitAnim() {
        return exitAnim;
    }

    @Nullable
    public Bundle getActivityOptionsBundle() {
        return activityOptionsBundle;
    }

    public void setActivityOptionsBundle(@Nullable Bundle activityOptionsBundle) {
        this.activityOptionsBundle = activityOptionsBundle;
    }


    @Override
    public String toString() {
        return "RouteRequest{" +
                "uri='" + uri + '\'' +
                ", host='" + host + '\'' +
                ", requestID='" + requestID + '\'' +
                ", target='" + target + '\'' +
                ", action='" + action + '\'' +
                ", extra=" + extra +
                ", requestCode=" + requestCode +
                ", mCallBack=" + mCallBack +
                '}';
    }
}
