package com.wuba.mobile.middle.mis.protocol.router;

/**
 * this is project PluginMiddleware
 *
 * @description He is so lazy that he can't write anything.
 * @create liqiuzuo
 * @data 2018/5/29
 */

public interface IRouteMatcher {
    /**
     * @param url 协议链接拆解成对应的key
     * @return 拆解为路由请求
     **/
    RouteRequest match(String url);
}

