package com.wuba.mobile.middle.mis.protocol.router;

import android.content.Context;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.IBinder;
import android.os.Parcelable;
import android.os.PersistableBundle;
import android.support.annotation.RequiresApi;
import android.text.TextUtils;
import android.util.SparseArray;

import com.wuba.mobile.base.common.callback.IRequestCallBack;
import com.wuba.mobile.base.common.utils.Log4Utils;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * this is project PluginMiddleware
 *
 * @description 抽象路由类，继承或实现该类用于实现父路由或子节点路由
 * @create liqiuzuo
 * @data 2018/5/29
 */

public class RouterReqBuilder implements IRouteReqBuilder {

    protected RouteRequest mRequest;
    protected IRouteMatcher mMatcher;
    protected RouteTable mTable;
    protected IRouteInterceptor mInterceptor;

    public RouterReqBuilder(String url) {
        mRequest = new RouteRequest();
        mRequest.setUri(url);
    }

    @Override
    public IRouteReqBuilder setMatcher(IRouteMatcher matcher) {
        mMatcher = matcher;
        return this;
    }

    @Override
    public IRouteReqBuilder setTable(RouteTable table) {
        mTable = table;
        return this;
    }

    @Override
    public IRouteReqBuilder setInterceptor(IRouteInterceptor interceptor) {
        mInterceptor = interceptor;
        return this;
    }

    @Override
    public IRouteReqBuilder build(Uri uri) {
        mRequest = new RouteRequest();
        mRequest.setUri(uri.toString());
        return this;
    }

    @Override
    public IRouteReqBuilder build(String uri) {
        mRequest = new RouteRequest();
        mRequest.setUri(uri.toString());
        return this;
    }

    @Override
    public IRouteReqBuilder target(String target) {
        mRequest.setTarget(target);
        return this;
    }

    @Override
    public IRouteReqBuilder action(String action) {
        mRequest.setAction(action);
        return this;
    }

    @Override
    public IRouteReqBuilder requestCode(int requestCode) {
        mRequest.setRequestCode(requestCode);
        return this;
    }

    @Override
    public IRouteReqBuilder with(Bundle bundle) {
        if (bundle != null && !bundle.isEmpty()) {
            Bundle extras = mRequest.getExtra();
            if (extras == null) {
                extras = new Bundle();
            }
            extras.putAll(bundle);
            mRequest.setExtra(extras);
        }
        return this;
    }

    @RequiresApi(21)
    @Override
    public IRouteReqBuilder with(PersistableBundle bundle) {
        if (bundle != null && !bundle.isEmpty()) {
            Bundle extras = mRequest.getExtra();
            if (extras == null) {
                extras = new Bundle();
            }
            extras.putAll(bundle);
            mRequest.setExtra(extras);
        }
        return this;
    }

    @Override
    public IRouteReqBuilder with(String key, Object value) {
        if (value == null) {
            Log4Utils.d("Route", "Ignored: The extra value is null.");
            return this;
        }
        Bundle bundle = mRequest.getExtra();
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
                Log4Utils.e("Route", "putBinder() requires api 18.");
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
            Log4Utils.d("Route", "Unknown object type: " + value.getClass().getName());
        }
        mRequest.setExtra(bundle);
        return this;
    }

    @Override
    public boolean go(Context context) {
       return go(context, null);
    }

    @Override
    public boolean go(Context context, IRequestCallBack callBack) {
        mRequest.setCallback(callBack);
        String target = mRequest.getTarget();
        String url = mRequest.getUri();
        if (TextUtils.isEmpty(target)) {//没有指定target
            if (!TextUtils.isEmpty(url) && mMatcher != null) {//Url 和 matcher 不为空
                RouteRequest req = mMatcher.match(url);
                if (req != null) {
                    target = req.getTarget();
                    mRequest.setTarget(target)
                            .setExtra(req.getExtra())
                            .setAction(req.getAction());
                }
            }
        }
        if (TextUtils.isEmpty(target)) {
            Router.getRootRouterError().onError(RouterError.NO_TARGET, "no target!", mRequest);
            return true;
        }
        //拦截操作
        if (mInterceptor != null) {
            if (mInterceptor.intercept(mRequest)) {//拦截的话执行
                Router.getRootRouterError().onError(RouterError.REQ_INTERCEPTED, "intercept!", mRequest);
                return true;
            }
        }

        if (mTable != null) {
            IRouteNode subRouter = mTable.route(target);
            if (subRouter != null) {
                if (subRouter instanceof Router)//分发到子节点执行
                    ((Router)subRouter).build(this).go(context, callBack);
                if (subRouter instanceof RouteNode){//执行命令
                    subRouter.execute(context,mRequest);
                }
                return true;
            }
        }
        //子类未匹配上，交由父类处理
        Router.getRootRouterError().onError(RouterError.NO_MATCH_TARGET, "no match target!", mRequest);
        return false;
    }
}

