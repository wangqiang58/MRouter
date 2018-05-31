package com.wuba.mobile.middle.plugin.route;

import com.wuba.mobile.middle.mis.protocol.router.Router;

/**
 * this is project PluginMiddleware
 *
 * @description 测试节点A
 * @create liqiuzuo
 * @data 2018/5/30
 */

public class PluginARouter extends Router {
    private static PluginARouter mInstance;

    private PluginARouter() {

    }

    public static PluginARouter getInstance() {
        if (mInstance == null) {
            synchronized (PluginARouter.class) {
                if (mInstance == null) {
                    mInstance = new PluginARouter();
                }
            }
        }
        return mInstance;
    }

}
