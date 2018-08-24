package com.wuba.mobile.middle.mis.protocol.router;


import android.net.Uri;

import com.wuba.mobile.middle.mis.protocol.router.table.RouteTable;
import com.wuba.mobile.middle.mis.protocol.router.util.RLog;

/**
 * this is project PluginMiddleware
 *
 * @description 抽象路由类，继承或实现该类用于实现父路由或子节点路由
 * @create liqiuzuo
 * @data 2018/5/29
 * 优秀
 */

public class Router {
    public static final String RAW_URI = "raw_uri";

    public static synchronized void openLog() {
        _Router.getInstatnce().openDebug();
    }

    public static IRouter build(String path) {
        return build(path == null ? null : Uri.parse(path));
    }

    public static IRouter build(Uri uri) {
        return _Router.getInstatnce().build(uri);
    }

    public static IRouter build(RouteRequest request) {
        return _Router.getInstatnce().build(request);
    }

    /**
     * 动态添加路由表
     *
     * @param table
     */
    public static void handleRouteTable(RouteTable table) {
        table.handle(Warehouse.routeTable);
    }
}
