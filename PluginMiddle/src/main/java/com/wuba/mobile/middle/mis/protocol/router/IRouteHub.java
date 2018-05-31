package com.wuba.mobile.middle.mis.protocol.router;

/**
 * this is project PluginMiddleware
 *
 * @description
 * 路由必备配置信息
 * 1.域名匹配器
 * 2.IP匹配表
 * 3.请求拦截器.
 * @create liqiuzuo
 * @data 2018/5/30
 */

interface IRouteHub<T> {
    /**
     * @param matcher 用于动态替换成自己的匹配器
     * */
    T setMatcher(IRouteMatcher matcher);
    /**
     * @param table 用于动态替换成自己的子类路由表
     * */
    T setTable(RouteTable table);
    /**
     * @param interceptor 用于动态替换成自己的拦截器，用于阻止或预处理
     * */
    T setInterceptor(IRouteInterceptor interceptor);
}
