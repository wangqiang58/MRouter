package com.wuba.mobile.middle.mis.protocol.router;

import java.util.HashMap;

/**
 * this is project PluginMiddleware
 *
 * @description 路由表.
 * @create liqiuzuo
 * @data 2018/5/29
 */

public abstract class RouteTable {

    protected HashMap<String, IRouteNode> table;

    public RouteTable() {

    }

    public IRouteNode route(String target) {
        IRouteNode router = null;
        if (table != null) {
            router = table.get(target);
        }
        return router;
    }
}
