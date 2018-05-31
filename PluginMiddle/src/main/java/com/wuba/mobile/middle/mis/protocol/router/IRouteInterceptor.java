package com.wuba.mobile.middle.mis.protocol.router;

/**
 * this is project PluginMiddleware
 *
 * @description 路由拦截器，用于阻止跳转或在路由跳转前添加预处理.
 * @create liqiuzuo
 * @data 2018/5/29
 */

public interface IRouteInterceptor {
    boolean intercept(RouteRequest request);
}
