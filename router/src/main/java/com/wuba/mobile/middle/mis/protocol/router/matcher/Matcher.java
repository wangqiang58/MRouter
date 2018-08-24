package com.wuba.mobile.middle.mis.protocol.router.matcher;

import android.content.Context;
import android.net.Uri;
import android.support.annotation.Nullable;

import com.wuba.mobile.middle.mis.protocol.router.RouteRequest;

/**
 * author : wangqiang
 * date : 2018/8/23
 * desc :
 */
public interface Matcher {

    boolean match(Context context, Uri uri, @Nullable String route, RouteRequest routeRequest);

    Object generate(Context context, Uri uri, @Nullable Class<?> target);

}
