package com.wuba.mobile.middle.mis.protocol.router.chain;

import com.wuba.mobile.middle.mis.protocol.router.MatcherRegistry;
import com.wuba.mobile.middle.mis.protocol.router.RouteResponse;
import com.wuba.mobile.middle.mis.protocol.router.RouteStatus;
import com.wuba.mobile.middle.mis.protocol.router.RouterInterceptor;

/**
 * author : wangqiang
 * date : 2018/8/23
 * desc :
 */
public class IntentValidator implements RouterInterceptor {

    @Override
    public RouteResponse intercept(Chain chain) {
        if (MatcherRegistry.getMatcher().size() == 0) {
            return RouteResponse.assemble(RouteStatus.FAILED, "The matcherRegister contain no matcher");
        }
        return chain.process();
    }
}
