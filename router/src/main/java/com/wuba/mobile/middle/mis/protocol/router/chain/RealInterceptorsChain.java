package com.wuba.mobile.middle.mis.protocol.router.chain;

import android.app.Fragment;
import android.content.Context;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.wuba.mobile.middle.mis.protocol.router.RouteRequest;
import com.wuba.mobile.middle.mis.protocol.router.RouteResponse;
import com.wuba.mobile.middle.mis.protocol.router.RouteStatus;
import com.wuba.mobile.middle.mis.protocol.router.RouterInterceptor;

import java.util.List;

/**
 * author : wangqiang
 * date : 2018/8/16
 * desc :拦截器容器
 */
public class RealInterceptorsChain implements RouterInterceptor.Chain {

    private int index = 0;
    private final Object mSource;
    private final RouteRequest mRouteRequest;
    private final List<RouterInterceptor> mInterceptors;
    private Class<?> targetClzz; //Intent、Fragment class
    private Object targetObject; //Intent Fragment instance

    public RealInterceptorsChain(Object source, RouteRequest request, List<RouterInterceptor> interceptors) {
        this.mSource = source;
        this.mRouteRequest = request;
        this.mInterceptors = interceptors;
    }

    @Override
    public RouteResponse intercept() {
        return null;
    }

    @Override
    public Context getContext() {
        Context context = null;
        if (mSource instanceof Context) {
            context = (Context) mSource;
        } else if (mSource instanceof android.support.v4.app.Fragment) {
            context = ((android.support.v4.app.Fragment) mSource).getContext();
        } else if (mSource instanceof Fragment) {
            if (Build.VERSION.SDK_INT >= 23) {
                context = ((Fragment) mSource).getContext();
            } else {
                context = ((Fragment) mSource).getActivity();
            }
        }
        return context;
    }

    @Override
    public RouteRequest getRequest() {
        return mRouteRequest;
    }

    @Override
    public RouteResponse process() {
        if (index >= mInterceptors.size()) {
            RouteResponse response = RouteResponse.assemble(RouteStatus.SUCCED, null);
            if (targetObject != null) {
                response.setResult(targetObject);
            } else {
                response.setStatus(RouteStatus.FAILED);
            }
            return response;
        }
        RouterInterceptor interceptor = mInterceptors.get(index++);
        return interceptor.intercept(this);
    }

    @Override
    public Object getSource() {
        return mSource;
    }

    @Override
    public Fragment getFragment() {
        return (mSource instanceof Fragment) ? (Fragment) mSource : null;
    }

    @Override
    public android.support.v4.app.Fragment getFragmentV4() {
        return (mSource instanceof android.support.v4.app.Fragment) ? (android.support.v4.app.Fragment) mSource : null;
    }

    @NonNull
    public List<RouterInterceptor> getInterceptors() {
        return mInterceptors;
    }

    @Nullable
    public Class<?> getTargetClass() {
        return targetClzz;
    }

    public void setTargetClass(@Nullable Class<?> targetClass) {
        this.targetClzz = targetClass;
    }

    public void setTargetObject(@Nullable Object targetObject) {
        this.targetObject = targetObject;
    }


}
