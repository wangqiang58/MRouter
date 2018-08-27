package com.wuba.mobile.middle.mis.protocol.router.chain;

import com.wuba.mobile.middle.mis.protocol.router.MatcherRegistry;
import com.wuba.mobile.middle.mis.protocol.router.RouteResponse;
import com.wuba.mobile.middle.mis.protocol.router.RouteStatus;
import com.wuba.mobile.middle.mis.protocol.router.RouterInterceptor;
import com.wuba.mobile.middle.mis.protocol.router.matcher.AbsExplicitMatcher;

import java.util.List;

/**
 * author : wangqiang
 * date : 2018/8/24
 * desc :
 */
public class FragmentValidator implements RouterInterceptor {

    //fragment 只能通过显示调用创建
    @Override
    public RouteResponse intercept(Chain chain) {
        List<AbsExplicitMatcher> matcherList = MatcherRegistry.getExplicitMatcher();
        if (matcherList.isEmpty()) {
            RouteResponse.assemble(RouteStatus.NOT_FOUND,
                                   String.format("can not find available matcher for fragment,Uri path = %s", chain.getRequest().getUri()));
        }
        return chain.process();
    }
}
