package com.wuba.mobile.middle.app.route;

import com.wuba.mobile.middle.mis.protocol.router.RouteRequest;
import com.wuba.mobile.middle.mis.protocol.router.IRouteInterceptor;

/**
 * this is project PluginMiddleware
 *
 * @description 美事路由表.
 * @create liqiuzuo
 * @data 2018/5/30
 */

public class MisRouterInterceptor implements IRouteInterceptor {

    @Override
    public boolean intercept(RouteRequest request) {

        return false;
    }
}
