package com.wuba.mobile.middle.mis.protocol.router;

import android.content.Context;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.IBinder;
import android.os.Parcelable;
import android.os.PersistableBundle;
import android.support.annotation.RequiresApi;
import android.support.v4.app.Fragment;
import android.util.SparseArray;

import com.wuba.mobile.middle.mis.protocol.router.util.RLog;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * this is project PluginMiddleware
 *
 * @description 路由协议抽象类.
 * 1.配置路由转接协议
 * 2.生成路由请求的基本方法
 * @create liqiuzuo
 * @data 2018/5/30
 */

abstract class AbsRouter implements IRouter {

    protected RouteRequest mRouteRequest;

    @Override
    public IRouter build(Uri uri) {
        mRouteRequest = new RouteRequest();
        mRouteRequest.setUri(uri.toString());
        Bundle bundle = new Bundle();
        bundle.putString(Router.RAW_URI, uri == null ? null : uri.toString());
        mRouteRequest.setExtra(bundle);
        return this;
    }

    @Override
    public IRouter build(RouteRequest routeRequest) {
        mRouteRequest = routeRequest;
        Bundle bundle = mRouteRequest.getExtra();
        if (bundle == null) {
            bundle = new Bundle();
        }
        bundle.putString(Router.RAW_URI, routeRequest.getUri().toString());
        mRouteRequest.setExtra(bundle);
        return this;
    }

    @Override
    public IRouter callback(RouterCallback callback) {
        mRouteRequest.callback(callback);
        return this;
    }

    @Override
    public IRouter requestCode(int requestCode) {
        mRouteRequest.setRequestCode(requestCode);
        return this;
    }

    @Override
    public IRouter with(Bundle bundle) {
        if (bundle != null && !bundle.isEmpty()) {
            Bundle extras = mRouteRequest.getExtra();
            if (extras == null) {
                extras = new Bundle();
            }
            extras.putAll(bundle);
            mRouteRequest.setExtra(extras);
        }
        return this;
    }


    @RequiresApi(21)
    @Override
    public IRouter with(PersistableBundle bundle) {
        if (bundle != null && !bundle.isEmpty()) {
            Bundle extras = mRouteRequest.getExtra();
            if (extras == null) {
                extras = new Bundle();
            }
            extras.putAll(bundle);
            mRouteRequest.setExtra(extras);
        }
        return this;
    }

    @SuppressWarnings("unchecked")
    @Override
    public IRouter with(String key, Object value) {
        if (value == null) {
            RLog.w("Ignored: The extra value is null.");
            return this;
        }
        Bundle bundle = mRouteRequest.getExtra();
        if (bundle == null) {
            bundle = new Bundle();
        }
        if (value instanceof Bundle) {
            bundle.putBundle(key, (Bundle) value);
        } else if (value instanceof Byte) {
            bundle.putByte(key, (byte) value);
        } else if (value instanceof Short) {
            bundle.putShort(key, (short) value);
        } else if (value instanceof Integer) {
            bundle.putInt(key, (int) value);
        } else if (value instanceof Long) {
            bundle.putLong(key, (long) value);
        } else if (value instanceof Character) {
            bundle.putChar(key, (char) value);
        } else if (value instanceof Boolean) {
            bundle.putBoolean(key, (boolean) value);
        } else if (value instanceof Float) {
            bundle.putFloat(key, (float) value);
        } else if (value instanceof Double) {
            bundle.putDouble(key, (double) value);
        } else if (value instanceof String) {
            bundle.putString(key, (String) value);
        } else if (value instanceof CharSequence) {
            bundle.putCharSequence(key, (CharSequence) value);
        } else if (value instanceof byte[]) {
            bundle.putByteArray(key, (byte[]) value);
        } else if (value instanceof short[]) {
            bundle.putShortArray(key, (short[]) value);
        } else if (value instanceof int[]) {
            bundle.putIntArray(key, (int[]) value);
        } else if (value instanceof long[]) {
            bundle.putLongArray(key, (long[]) value);
        } else if (value instanceof char[]) {
            bundle.putCharArray(key, (char[]) value);
        } else if (value instanceof boolean[]) {
            bundle.putBooleanArray(key, (boolean[]) value);
        } else if (value instanceof float[]) {
            bundle.putFloatArray(key, (float[]) value);
        } else if (value instanceof double[]) {
            bundle.putDoubleArray(key, (double[]) value);
        } else if (value instanceof String[]) {
            bundle.putStringArray(key, (String[]) value);
        } else if (value instanceof CharSequence[]) {
            bundle.putCharSequenceArray(key, (CharSequence[]) value);
        } else if (value instanceof IBinder) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR2) {
                bundle.putBinder(key, (IBinder) value);
            } else {
                RLog.e("putBinder() requires api 18.");
            }
        } else if (value instanceof ArrayList) {
            if (!((ArrayList) value).isEmpty()) {
                Object obj = ((ArrayList) value).get(0);
                if (obj instanceof Integer) {
                    bundle.putIntegerArrayList(key, (ArrayList<Integer>) value);
                } else if (obj instanceof String) {
                    bundle.putStringArrayList(key, (ArrayList<String>) value);
                } else if (obj instanceof CharSequence) {
                    bundle.putCharSequenceArrayList(key, (ArrayList<CharSequence>) value);
                } else if (obj instanceof Parcelable) {
                    bundle.putParcelableArrayList(key, (ArrayList<? extends Parcelable>) value);
                }
            }
        } else if (value instanceof SparseArray) {
            bundle.putSparseParcelableArray(key, (SparseArray<? extends Parcelable>) value);
        } else if (value instanceof Parcelable) {
            bundle.putParcelable(key, (Parcelable) value);
        } else if (value instanceof Parcelable[]) {
            bundle.putParcelableArray(key, (Parcelable[]) value);
        } else if (value instanceof Serializable) {
            bundle.putSerializable(key, (Serializable) value);
        } else {
            RLog.w("Unknown object type: " + value.getClass().getName());
        }
        mRouteRequest.setExtra(bundle);
        return this;
    }

    @Override
    public IRouter setAction(String action) {
        mRouteRequest.setAction(action);
        return this;
    }

    @Override
    public IRouter setType(String type) {
        mRouteRequest.setType(type);
        return this;
    }

    @Override
    public IRouter anim(int enterAnim, int exitAnim) {
        mRouteRequest.setEnterAnim(enterAnim);
        mRouteRequest.setExitAnim(exitAnim);
        return this;
    }

    @Override
    public void go(Fragment fragment, RouterCallback callback) {
        mRouteRequest.callback(callback);
        go(fragment);
    }

    @Override
    public void go(android.app.Fragment fragment, RouterCallback callback) {
        mRouteRequest.callback(callback);
        go(fragment);
    }

    @Override
    public void go(Context context, RouterCallback callback) {
        mRouteRequest.callback(callback);
        go(context);
    }
}
