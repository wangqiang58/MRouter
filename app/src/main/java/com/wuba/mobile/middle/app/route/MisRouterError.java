package com.wuba.mobile.middle.app.route;

import com.wuba.mobile.middle.mis.protocol.router.RouteRequest;
import com.wuba.mobile.middle.mis.protocol.router.RouterError;

/**
 * this is project PluginMiddleware
 *
 * @description He is so lazy that he can't write anything.
 * @create liqiuzuo
 * @data 2018/5/30
 */

public class MisRouterError extends RouterError {
    private static MisRouterError mInstance;

    private MisRouterError() {
    }

    public static MisRouterError getInstance() {
        if (mInstance == null) {
            synchronized (MisRouterError.class) {
                if (mInstance == null) {
                    mInstance = new MisRouterError();
                }
            }
        }
        return mInstance;
    }

    public void onError(int code, String msg, RouteRequest request) {
        if (code == RouterError.NO_TARGET) {//没有获取到目标

        } else if (code == RouterError.NO_MATCH_TARGET) {//未找到匹配项

        } else if (code == RouterError.REQ_INTERCEPTED) {//请求被拦截

        } else if (code == RouterError.WRONG_WAY_FOR_EXECUTE) {//调用方法错误

        } else {//

        }
    }
}
