package com.wuba.mobile.middle.mis.protocol.router;

import android.content.Context;

/**
 * this is project PluginMiddleware
 *
 * @description 抽象路由类，继承或实现该类用于实现父路由或子节点路由
 * @create liqiuzuo
 * @data 2018/5/29
 */

public class Router extends AbsRouter implements IRouteNode {

    public static Router rootRouter;
    public static RouterError rootRouterError;

    public static Router getRootRouter() {
        if (rootRouter == null) {
            rootRouter = new Router();
        }
        return rootRouter;
    }

    public static RouterError getRootRouterError() {
        if (rootRouterError == null) {
            rootRouterError = new RouterError();
        }
        return rootRouterError;
    }

    @Override
    public void execute(Context context, RouteRequest routeRequest) {
        Router.getRootRouterError().onError(RouterError.WRONG_WAY_FOR_EXECUTE,"wrong way for execute(),please use build() replace!",null);
    }
}
