package com.wuba.mobile.middle.mis.protocol.router;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.RequiresApi;

import com.wuba.mobile.base.common.callback.IRequestCallBack;

/**
 * this is project PluginMiddleware
 *
 * @description 美事路由请求构造者 - 行为抽象类.
 * @create liqiuzuo
 * @data 2018/5/29
 */

public interface IRouteReqBuilder extends IRouteHub<IRouteReqBuilder> {
    /**
     * Entrance.
     *
     * @param uri 通过Uri协议链接
     */
    IRouteReqBuilder build(Uri uri);

    /**
     * Entrance
     *
     * @param uri 通过Uri协议链接
     */
    IRouteReqBuilder build(String uri);

    /**
     * @param target 路由目标
     */
    IRouteReqBuilder target(String target);

    /**
     * @param action 路由行为
     */
    IRouteReqBuilder action(String action);

    /**
     * Call <code>startActivityForResult</code>.
     */
    IRouteReqBuilder requestCode(int requestCode);

    /**
     * @see Bundle#putAll(Bundle)
     */
    IRouteReqBuilder with(Bundle bundle);

    /**
     * @see Bundle#putAll(PersistableBundle)
     */
    @RequiresApi(21)
    IRouteReqBuilder with(PersistableBundle bundle);

    /**
     * bundle.putXXX(String key, XXX value).
     */
    IRouteReqBuilder with(String key, Object value);

    /**
     * @param context 发送路由的上下文
     */
    boolean go(Context context);

    /**
     * @param context  发送路由的上下文
     * @param callBack 请求回调
     */
    boolean go(Context context, IRequestCallBack callBack);
}
