package com.wuba.mobile.middle.mis.protocol.router;

import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;

import com.wuba.mobile.base.common.callback.IProgressCallBack;
import com.wuba.mobile.base.common.callback.IRequestCallBack;

import java.util.HashMap;

/**
 * this is project PluginMiddleware
 *
 * @description 一个路由请求.
 * @create liqiuzuo
 * @data 2018/5/30
 */

public class RouteRequest implements IRequestCallBack, IProgressCallBack, Parcelable {

    public static final Parcelable.Creator<RouteRequest> CREATOR = new Parcelable.Creator<RouteRequest>() {
        @Override
        public RouteRequest createFromParcel(Parcel source) {
            return new RouteRequest(source);
        }

        @Override
        public RouteRequest[] newArray(int size) {
            return new RouteRequest[size];
        }
    };

    protected String uri;
    protected String host;
    protected String requestID;
    protected String target;
    protected String action;
    protected Bundle extra;
    protected int requestCode;
    protected IRequestCallBack mCallBack;
    protected IProgressCallBack mProgressCallback;

    public RouteRequest() {
    }

    protected RouteRequest(Parcel in) {
        this.uri = in.readString();
        this.host = in.readString();
        this.requestID = in.readString();
        this.target = in.readString();
        this.action = in.readString();
        this.extra = in.readBundle();
        this.requestCode = in.readInt();
        this.mCallBack = in.readParcelable(IRequestCallBack.class.getClassLoader());
        this.mProgressCallback = in.readParcelable(IProgressCallBack.class.getClassLoader());
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

    public String getRequestID() {
        return requestID;
    }

    public RouteRequest setRequestID(String requestID) {
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

    public IRequestCallBack getCallBack() {
        return mCallBack;
    }

    public RouteRequest setCallback(IRequestCallBack callback) {
        this.mCallBack = callback;
        return this;
    }

    public IProgressCallBack getProgressCallback() {
        return mProgressCallback;
    }

    public RouteRequest setProgressCallback(IProgressCallBack progressCallback) {
        this.mProgressCallback = progressCallback;
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

    @Override
    public void onProgress(String s, long l, long l1, boolean b) {
        if (mCallBack != null) {
            mProgressCallback.onProgress(s, l, l1, b);
        }
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.uri);
        dest.writeString(this.host);
        dest.writeString(this.requestID);
        dest.writeString(this.target);
        dest.writeString(this.action);
        dest.writeBundle(this.extra);
        dest.writeInt(this.requestCode);
        dest.writeParcelable((Parcelable) this.mCallBack, flags);
        dest.writeParcelable((Parcelable) this.mProgressCallback, flags);
    }
}
