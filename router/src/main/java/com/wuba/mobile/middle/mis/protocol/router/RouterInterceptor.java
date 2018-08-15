package com.wuba.mobile.middle.mis.protocol.router;

import android.app.Fragment;
import android.content.Context;

/**
 * author : wangqiang
 * date : 2018/8/15
 * desc :
 */
public interface RouterInterceptor {

    RouteResponse intercept(Chain chain);

    interface Chain {

        RouteRequest getRequest();

        Object getSource();

        Context getContext();

        Fragment getFragment();

        android.support.v4.app.Fragment getFragmentV4();

        RouteResponse process();

        RouteResponse intercept();
    }
}
