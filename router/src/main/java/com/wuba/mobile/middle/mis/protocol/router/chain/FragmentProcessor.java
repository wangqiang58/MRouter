package com.wuba.mobile.middle.mis.protocol.router.chain;

import android.app.Fragment;
import android.os.Bundle;

import com.wuba.mobile.middle.mis.protocol.router.MatcherRegistry;
import com.wuba.mobile.middle.mis.protocol.router.RouteRequest;
import com.wuba.mobile.middle.mis.protocol.router.RouteResponse;
import com.wuba.mobile.middle.mis.protocol.router.RouteStatus;
import com.wuba.mobile.middle.mis.protocol.router.RouterInterceptor;
import com.wuba.mobile.middle.mis.protocol.router.Warehouse;
import com.wuba.mobile.middle.mis.protocol.router.matcher.AbsExplicitMatcher;
import com.wuba.mobile.middle.mis.protocol.router.util.RLog;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * author : wangqiang
 * date : 2018/8/24
 * desc :
 */
public class FragmentProcessor implements RouterInterceptor {

    private static final String TAG = FragmentProcessor.class.getSimpleName();

    @Override
    public RouteResponse intercept(Chain chain) {
        RealInterceptorsChain realChain = (RealInterceptorsChain) chain;
        List<AbsExplicitMatcher> matcherList = MatcherRegistry.getExplicitMatcher();
        RouteRequest request = realChain.getRequest();
        Set<Map.Entry<String, Class<?>>> routeTable = Warehouse.routeTable.entrySet();

        for (AbsExplicitMatcher matcher : matcherList) {
            for (Map.Entry<String, Class<?>> entry : routeTable) {
                if (matcher.match(chain.getContext(), request.getUri(), entry.getKey(), request)) {
                    RLog.i(TAG, String.format("found available fragment for uri = %s", entry.getKey()));
                    Object result = matcher.generate(chain.getContext(), request.getUri(), entry.getValue());
                    if (result instanceof Fragment) {
                        Fragment fragment = (Fragment) result;
                        Bundle bundle = request.getExtra();
                        if (bundle != null && !bundle.isEmpty()) {
                            fragment.setArguments(request.getExtra());
                        }
                        realChain.setTargetObject(fragment);
                    } else if (result instanceof android.support.v4.app.Fragment) {
                        android.support.v4.app.Fragment fragment = (android.support.v4.app.Fragment) result;
                        Bundle bundle = request.getExtra();
                        if (bundle != null && !bundle.isEmpty()) {
                            fragment.setArguments(request.getExtra());
                        }
                        realChain.setTargetObject(fragment);
                    } else {
                        return RouteResponse.assemble(RouteStatus.FAILED,
                                                      String.format("The matcher can not generate a fragment for Uri = %s", request.getUri()));
                    }
                    return chain.process();
                }
            }
        }

        return RouteResponse.assemble(RouteStatus.NOT_FOUND, String.format(
                "Can't find a fragment that matches the given uri: %s",
                chain.getRequest().getUri().toString()));
    }
}
