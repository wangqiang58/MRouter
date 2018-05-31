package com.wuba.mobile.middle.mis.protocol.router.node;

import android.content.Context;

import com.wuba.mobile.middle.mis.protocol.router.RouteNode;
import com.wuba.mobile.middle.mis.protocol.router.RouteRequest;

/**
 * this is project PluginMiddleware
 *
 * @description 路由终点为启动一个页面，如果有回调参数，可以获取选中值.
 * @create liqiuzuo
 * @data 2018/5/30
 */

public class PageRouteNode extends RouteNode {

    @Override
    public void execute(Context context, RouteRequest routeRequest) {
        routeRequest.getRequestCode();

    }
}
