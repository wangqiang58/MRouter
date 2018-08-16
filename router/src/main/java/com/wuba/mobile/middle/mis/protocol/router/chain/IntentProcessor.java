package com.wuba.mobile.middle.mis.protocol.router.chain;

import com.wuba.mobile.middle.mis.protocol.router.RouteResponse;
import com.wuba.mobile.middle.mis.protocol.router.RouterInterceptor;

/**
 * author : wangqiang
 * date : 2018/8/16
 * desc :
 */
public class IntentProcessor implements RouterInterceptor {

    @Override
    public RouteResponse intercept(Chain chain) {
        return chain.process();

    }
}
