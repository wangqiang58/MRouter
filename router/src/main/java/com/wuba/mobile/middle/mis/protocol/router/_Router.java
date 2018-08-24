package com.wuba.mobile.middle.mis.protocol.router;

import android.app.Activity;
import android.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;

import com.wuba.mobile.middle.mis.protocol.router.chain.AppInterceptorHandler;
import com.wuba.mobile.middle.mis.protocol.router.chain.ContextValidator;
import com.wuba.mobile.middle.mis.protocol.router.chain.IntentProcessor;
import com.wuba.mobile.middle.mis.protocol.router.chain.IntentValidator;
import com.wuba.mobile.middle.mis.protocol.router.chain.RealInterceptorsChain;
import com.wuba.mobile.middle.mis.protocol.router.util.RLog;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


/**
 * author : wangqiang
 * date : 2018/8/15
 * desc :
 */
public class _Router extends AbsRouter {

    private volatile static boolean debuggable = false;
    private static final String TAG = _Router.class.getSimpleName();
    private final ContextValidator mContextValidator = new ContextValidator();
    private final RouterInterceptor mRouterInterceptor = new IntentProcessor();
    private final AppInterceptorHandler mAppInterceptorHandler = new AppInterceptorHandler();
    private final IntentValidator mIntentValidator = new IntentValidator();
    private final IntentProcessor mIntentProcessor = new IntentProcessor();


    private _Router() {
    }

    protected static class Holder {
        static _Router instance = new _Router();
    }

    public static boolean debuggable() {
        return debuggable;
    }

    protected static synchronized void openDebug() {
        debuggable = true;
        RLog.i(TAG, "MRouer openLog");
    }

    protected static _Router getInstatnce() {
        return Holder.instance;
    }

    @Override
    public void go(Context context) {
        Intent intent = getIntent(context);
        if (intent != null) {
            Bundle options = mRouteRequest.getActivityOptionsBundle();
            if (context instanceof Activity) {
                ActivityCompat.startActivityForResult((Activity) context, intent, mRouteRequest.getRequestCode(), options);
                if (mRouteRequest.getEnterAnim() >= 0 && mRouteRequest.getExitAnim() >= 0) {
                    ((Activity) context).overridePendingTransition(mRouteRequest.getEnterAnim(), mRouteRequest.getExitAnim());
                }
            } else {
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                // The below api added in v4:25.1.0
                // ContextCompat.startActivity(context, intent, options);
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                    context.startActivity(intent, options);
                } else {
                    context.startActivity(intent);
                }
            }
        }
    }

    @Override
    public void go(Fragment fragment) {
        Intent intent = getIntent(fragment);
        if (intent != null) {
            Bundle options = mRouteRequest.getActivityOptionsBundle();
            if (mRouteRequest.getRequestCode() < 0) {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) { // 4.1
                    fragment.startActivity(intent, options);
                } else {
                    fragment.startActivity(intent);
                }
            } else {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) { // 4.1
                    fragment.startActivityForResult(intent, mRouteRequest.getRequestCode(), options);
                } else {
                    fragment.startActivityForResult(intent, mRouteRequest.getRequestCode());
                }
            }
            if (mRouteRequest.getEnterAnim() >= 0 && mRouteRequest.getExitAnim() >= 0
                    && fragment.getActivity() != null) {
                // Add transition animation.
                fragment.getActivity().overridePendingTransition(
                        mRouteRequest.getEnterAnim(), mRouteRequest.getExitAnim());
            }
        }
    }

    @Override
    public void go(android.support.v4.app.Fragment fragment) {
        Intent intent = getIntent(fragment);
        if (intent != null) {
            Bundle options = mRouteRequest.getActivityOptionsBundle();
            if (mRouteRequest.getRequestCode() < 0) {
                fragment.startActivity(intent, options);
            } else {
                fragment.startActivityForResult(intent, mRouteRequest.getRequestCode(), options);
            }
            if (mRouteRequest.getEnterAnim() >= 0 && mRouteRequest.getExitAnim() >= 0
                    && fragment.getActivity() != null) {
                // Add transition animation.
                fragment.getActivity().overridePendingTransition(
                        mRouteRequest.getEnterAnim(), mRouteRequest.getExitAnim());
            }
        }
    }

    @Override
    public Intent getIntent(@NonNull Object source) {
        List<RouterInterceptor> interceptors = new ArrayList<>();
        Collections.addAll(interceptors, mContextValidator, mIntentValidator, mIntentProcessor);
        RealInterceptorsChain chain = new RealInterceptorsChain(source, mRouteRequest, interceptors);
        RouteResponse response = chain.process();
        callback(response);
        return (Intent) response.getResult();
    }

    private void callback(RouteResponse response) {
        if (response.getStatus() != RouteStatus.SUCCED) {
            RLog.w(response.getMessage());
        }
        if (mRouteRequest.getCallBack() != null) {
            mRouteRequest.getCallBack().callback(
                    response.getStatus(), mRouteRequest.getUri(), response.getMessage());
        }
    }
}
