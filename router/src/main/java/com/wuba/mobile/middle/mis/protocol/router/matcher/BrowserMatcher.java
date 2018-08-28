package com.wuba.mobile.middle.mis.protocol.router.matcher;

import android.content.Context;
import android.net.Uri;
import android.support.annotation.Nullable;

import com.wuba.mobile.middle.mis.protocol.router.RouteRequest;

/**
 * author : wangqiang
 * date : 2018/8/23
 * desc :跳转web
 */
public class BrowserMatcher extends AbsImplicitMatcher {

    @Override
    public boolean match(Context context, Uri uri, @Nullable String route, RouteRequest routeRequest) {
        return (uri.toString().toLowerCase().startsWith("http://"))
                || uri.toString().toLowerCase().startsWith("https://");
    }
}
