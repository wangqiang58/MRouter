package com.wuba.mobile.middle.mis.protocol.router;

import android.net.Uri;

/**
 * this is project PluginMiddleware
 *
 * @description my router interface for all subRouters and their parent Router.
 * @create liqiuzuo
 * @data 2018/5/29
 */

interface IRouter extends IRouteHub<IRouter> {
    IRouteReqBuilder build(IRouteReqBuilder builder);
    IRouteReqBuilder build(String url);
    IRouteReqBuilder build(Uri url);
}
