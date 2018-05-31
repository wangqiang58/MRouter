package com.wuba.mobile.middle.mis.protocol.router;

import android.net.Uri;

/**
 * this is project PluginMiddleware
 *
 * @description
 * 路由协议抽象类.
 * 1.配置路由转接协议
 * 2.生成路由请求的基本方法
 * @create liqiuzuo
 * @data 2018/5/30
 */

abstract class AbsRouter implements IRouter {

    protected IRouteMatcher mMatcher;
    protected RouteTable mTable;
    protected IRouteInterceptor mInterceptor;

    public IRouteReqBuilder build(IRouteReqBuilder builder) {
        builder.setInterceptor(mInterceptor)
                .setMatcher(mMatcher)
                .setTable(mTable);
        return builder;
    }

    @Override
    public IRouteReqBuilder build(String url) {
        return new RouterReqBuilder(url).setInterceptor(mInterceptor)
                .setMatcher(mMatcher)
                .setTable(mTable);
    }

    @Override
    public IRouteReqBuilder build(Uri url) {
        return new RouterReqBuilder(url.toString())
                .setInterceptor(mInterceptor)
                .setMatcher(mMatcher)
                .setTable(mTable);
    }

    @Override
    public IRouter setMatcher(IRouteMatcher matcher) {
        mMatcher = matcher;
        return this;
    }

    @Override
    public IRouter setTable(RouteTable table) {
        mTable = table;
        return this;
    }

    @Override
    public IRouter setInterceptor(IRouteInterceptor interceptor) {
        mInterceptor = interceptor;
        return this;
    }

}
