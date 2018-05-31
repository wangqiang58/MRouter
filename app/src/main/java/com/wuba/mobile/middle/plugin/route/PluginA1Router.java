package com.wuba.mobile.middle.plugin.route;

import com.wuba.mobile.middle.mis.protocol.router.Router;

/**
 * this is project PluginMiddleware
 *
 * @description 测试节点 A1
 * @create liqiuzuo
 * @data 2018/5/30
 */

public class PluginA1Router extends Router {

    private static PluginA1Router mInstance;

    private PluginA1Router() {

    }

    public static PluginA1Router getInstance() {
        if (mInstance == null) {
            synchronized (PluginA1Router.class) {
                if (mInstance == null) {
                    mInstance = new PluginA1Router();
                }
            }
        }
        return mInstance;
    }
}
