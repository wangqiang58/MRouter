package com.wuba.mobile.middle.mis.protocol.router.chain;

import android.content.Intent;

import com.wuba.mobile.middle.mis.protocol.router.MatcherRegistry;
import com.wuba.mobile.middle.mis.protocol.router.RouteRequest;
import com.wuba.mobile.middle.mis.protocol.router.RouteResponse;
import com.wuba.mobile.middle.mis.protocol.router.RouteStatus;
import com.wuba.mobile.middle.mis.protocol.router.RouterInterceptor;
import com.wuba.mobile.middle.mis.protocol.router.Warehouse;
import com.wuba.mobile.middle.mis.protocol.router.matcher.AbsImplicitMatcher;
import com.wuba.mobile.middle.mis.protocol.router.matcher.AbsMatcher;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * author : wangqiang
 * date : 2018/8/16
 * desc :implicit、explicit intent——>activity
 */
public class IntentProcessor implements RouterInterceptor {

    @Override
    public RouteResponse intercept(Chain chain) {
        RouteResponse response = RouteResponse.assemble(RouteStatus.SUCCED, null);
        if (Warehouse.routeTable.isEmpty()) {
            response.setStatus(RouteStatus.FAILED);
            return response;
        }
        RealInterceptorsChain realChain = (RealInterceptorsChain) chain;
        List<AbsMatcher> matcherList = MatcherRegistry.getMatcher();
        Set<Map.Entry<String, Class<?>>> tables = Warehouse.routeTable.entrySet();


        Intent intent = null;
        FOUND:
        for (AbsMatcher matcher : matcherList) {
            boolean isImplicit = matcher instanceof AbsImplicitMatcher; //隐式Intent
            if (isImplicit) {
                if (matcher.match(chain.getContext(), chain.getRequest().getUri(), null, chain.getRequest())) {
                    realChain.setTargetClass(null);
                    Object result = matcher.generate(chain.getContext(), chain.getRequest().getUri(), null);
                    if (result instanceof Intent) {
                        intent = (Intent) result;
                        buildIntent(intent, realChain.getRequest());
                        realChain.setTargetObject(intent);
                    } else {
                        RouteResponse.assemble(RouteStatus.FAILED, String.format("The matcher can not geneater Intent for uri:%s", realChain.getRequest().getUri().toString()));
                    }
                    break;
                }
            } else { //显示Intent
                for (Map.Entry<String, Class<?>> table : tables) {
                    if (matcher.match(realChain.getContext(), realChain.getRequest().getUri(), table.getKey(), realChain.getRequest())) {
                        Object result = matcher.generate(chain.getContext(), realChain.getRequest().getUri(), table.getValue());
                        if (result instanceof Intent) {
                            intent = (Intent) result;
                            buildIntent(intent, realChain.getRequest());
                            realChain.setTargetObject(intent);
                        } else {
                            return RouteResponse.assemble(RouteStatus.FAILED, String.format(
                                    "The matcher can't generate an intent for uri: %s",
                                    realChain.getRequest().getUri().toString()));
                        }
                        break FOUND;
                    }
                }
            }
        }

        if (intent == null) {
            return RouteResponse.assemble(RouteStatus.NOT_FOUND, String.format("Can not find an activity for the given uri %s", realChain.getRequest().getUri()));
        }
        return chain.process();
    }


    private void buildIntent(Intent intent, RouteRequest request) {
        if (request.getExtra() != null && !request.getExtra().isEmpty()) {
            intent.putExtras(request.getExtra());
        }
        if (request.getFlags() != 0) {
            intent.addFlags(request.getFlags());
        }
        if (request.getData() != null) {
            intent.setData(request.getData());
        }
        if (request.getType() != null) {
            intent.setType(request.getType());
        }
        if (request.getAction() != null) {
            intent.setAction(request.getAction());
        }
    }
}
