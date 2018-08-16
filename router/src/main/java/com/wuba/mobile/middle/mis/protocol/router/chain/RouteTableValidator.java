package com.wuba.mobile.middle.mis.protocol.router.chain;

import com.wuba.mobile.middle.mis.protocol.router.RouteRequest;
import com.wuba.mobile.middle.mis.protocol.router.RouteResponse;
import com.wuba.mobile.middle.mis.protocol.router.RouteStatus;
import com.wuba.mobile.middle.mis.protocol.router.RouterInterceptor;
import com.wuba.mobile.middle.mis.protocol.router.Warehouse;

/**
 * author : wangqiang
 * date : 2018/8/16
 * desc :检测路由表是否为空
 */
public class RouteTableValidator implements RouterInterceptor {

    @Override
    public RouteResponse intercept(Chain chain) {
        RouteResponse response = RouteResponse.assemble(RouteStatus.SUCCED, null);
        if (Warehouse.routeTable.isEmpty()) {
            response.setStatus(RouteStatus.FAIL);
            return response;
        }
        RouteRequest request = chain.getRequest();
        if (Warehouse.routeTable.containsKey(request.getUri())) {
            Class clzz = Warehouse.routeTable.get(request.getUri());
            try {
                response.setResult(clzz.newInstance());
            } catch (Exception ex) {
                ex.printStackTrace();
            }
            return response;
        } else {
            return RouteResponse.assemble(RouteStatus.FAIL, String.format("target page : path = %s Not Found!", request.getUri()));
        }
    }
}
