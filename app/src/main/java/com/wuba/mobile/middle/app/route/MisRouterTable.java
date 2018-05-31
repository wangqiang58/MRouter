package com.wuba.mobile.middle.app.route;

import com.wuba.mobile.middle.app.route.node.IMRouteNode;
import com.wuba.mobile.middle.mis.protocol.router.RouteNodeFactory;
import com.wuba.mobile.middle.mis.protocol.router.RouteTable;
import com.wuba.mobile.middle.plugin.route.PluginARouter;
import com.wuba.mobile.middle.plugin.route.PluginBRouter;

import java.util.HashMap;

/**
 * this is project PluginMiddleware
 *
 * @description 美事路由表.
 * @create liqiuzuo
 * @data 2018/5/30
 */

public class MisRouterTable extends RouteTable {

    public MisRouterTable(){
       table = new HashMap<>();
       table.put("A",PluginARouter.getInstance());
       table.put("B",PluginBRouter.getInstance());
       table.put("c", RouteNodeFactory.creator(IMRouteNode.class));
    }

}
