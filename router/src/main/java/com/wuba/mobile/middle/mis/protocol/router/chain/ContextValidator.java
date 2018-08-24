package com.wuba.mobile.middle.mis.protocol.router.chain;

import android.app.Fragment;
import android.content.Context;
import android.os.Build;

import com.wuba.mobile.middle.mis.protocol.router.RouteRequest;
import com.wuba.mobile.middle.mis.protocol.router.RouteResponse;
import com.wuba.mobile.middle.mis.protocol.router.RouteStatus;
import com.wuba.mobile.middle.mis.protocol.router.RouterInterceptor;

/**
 * author : wangqiang
 * date : 2018/8/16
 * desc :校验 source是否是context类型
 */
public class ContextValidator implements RouterInterceptor {

    @Override
    public RouteResponse intercept(Chain chain) {
        RouteRequest request = chain.getRequest();
        if (request.getUri() == null) {
            return RouteResponse.assemble(RouteStatus.FAILED, "uri=null");
        }
        Context context = null;
        if (chain.getSource() instanceof Context) {
            context = (Context) chain.getSource();
        } else if (chain.getSource() instanceof Fragment) {
            if (Build.VERSION.SDK_INT >= 23) {
                context = ((Fragment) chain.getSource()).getContext();
            } else {
                context = ((Fragment) chain.getSource()).getActivity();
            }
        } else if (chain.getSource() instanceof android.support.v4.app.Fragment) {
            context = ((android.support.v4.app.Fragment) chain.getSource()).getContext();
        }
        if (context == null) {
            return RouteResponse.assemble(RouteStatus.FAILED, "Can't retrieve context from source.");
        }
        return chain.process();
    }
}
