package com.wuba.mobile.middle.mis.protocol.router.chain;

import com.wuba.mobile.middle.mis.protocol.router.RouteRequest;
import com.wuba.mobile.middle.mis.protocol.router.RouteResponse;
import com.wuba.mobile.middle.mis.protocol.router.RouterInterceptor;

/**
 * author : wangqiang
 * date : 2018/8/16
 * desc :
 */
public class AppInterceptorHandler implements RouterInterceptor {

    @Override
    public RouteResponse intercept(Chain chain) {
        RealInterceptorsChain realChain = (RealInterceptorsChain) chain;
        RouteRequest request = chain.getRequest();


        return null;
    }
}
