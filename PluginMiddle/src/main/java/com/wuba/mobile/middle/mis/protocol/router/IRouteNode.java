package com.wuba.mobile.middle.mis.protocol.router;

import android.content.Context;

/**
 * this is project PluginMiddleware
 *
 * @description 终端执行方法抽象类.
 * @create liqiuzuo
 * @data 2018/5/29
 */
interface IRouteNode {
    void execute(Context context, RouteRequest routeRequest);
}
