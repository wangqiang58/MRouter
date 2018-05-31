package com.wuba.mobile.middle.mis.protocol.router;

/**
 * this is project PluginMiddleware
 *
 * @description 路由终端工厂.
 * @create liqiuzuo
 * @data 2018/5/30
 */

public class RouteNodeFactory{

    public static RouteNode creator(Class tClass) {
        RouteNode node = null;
        try {
            node = (RouteNode) tClass.newInstance();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return node;
    }
}
