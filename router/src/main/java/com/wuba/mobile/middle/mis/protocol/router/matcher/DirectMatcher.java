package com.wuba.mobile.middle.mis.protocol.router.matcher;

import android.content.Context;
import android.net.Uri;
import android.support.annotation.Nullable;
import android.text.TextUtils;

import com.wuba.mobile.middle.mis.protocol.router.RouteRequest;

/**
 * author : wangqiang
 * date : 2018/8/23
 * desc :
 */
public class DirectMatcher extends AbsExplicitMatcher {

    @Override
    public boolean match(Context context, Uri uri, @Nullable String route, RouteRequest routeRequest) {
        return !isEmpty(route) && TextUtils.equals(uri.toString(), route);
    }
}
