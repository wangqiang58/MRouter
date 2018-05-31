package com.wuba.mobile.middle.plugin.route;

import com.wuba.mobile.middle.mis.protocol.router.Router;

/**
 * this is project PluginMiddleware
 *
 * @description 测试节点B.
 * @create liqiuzuo
 * @data 2018/5/30
 */

public class PluginBRouter extends Router {

    private static PluginBRouter mInstance;

    private PluginBRouter() {

    }

    public static PluginBRouter getInstance() {
        if (mInstance == null) {
            synchronized (PluginBRouter.class) {
                if (mInstance == null) {
                    mInstance = new PluginBRouter();
                }
            }
        }
        return mInstance;
    }

}
