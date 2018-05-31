package com.wuba.mobile.middle.app.route;

import com.wuba.mobile.middle.mis.protocol.router.Router;

/**
 * this is project PluginMiddleware
 *
 * @description He is so lazy that he can't write anything.
 * @create liqiuzuo
 * @data 2018/5/30
 */

public class MisRouter extends Router {

    private static MisRouter mInstance;

    private MisRouter() {
        mTable = new MisRouterTable();
        mInterceptor = new MisRouterInterceptor();
        mMatcher = new MisRouterMatcher();
    }

    public static MisRouter getInstance() {
        if (mInstance == null) {
            synchronized (MisRouter.class) {
                if (mInstance == null) {
                    mInstance = new MisRouter();
                }
            }
        }
        return mInstance;
    }



}
