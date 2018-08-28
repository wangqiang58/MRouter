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
public class SchemeMatcher extends AbsExplicitMatcher {

    @Override
    public boolean match(Context context, Uri uri, @Nullable String route, RouteRequest routeRequest) {
        if (isEmpty(route)) {
            return false;
        }
        Uri routeUri = Uri.parse(route);
        if (routeUri.isAbsolute() && uri.isAbsolute()) {
            if (!TextUtils.equals(uri.getScheme(), routeUri.getScheme())) {
                return false;
            }
        }
        if (isEmpty(uri.getAuthority()) && isEmpty(routeUri.getAuthority())) {
            return true;
        }
        if (TextUtils.equals(uri.getAuthority(), routeUri.getAuthority())) {
            if (!cutSlash(uri.getPath()).equals(cutSlash(routeUri.getPath()))) {
                return false;
            }

            // bundle parser
            if (uri.getQuery() != null) {
                parseParms(uri, routeRequest);
            }
            return true;
        }
        return false;
    }

    /**
     * 剔除path头部和尾部的斜杠/
     *
     * @param path 路径
     * @return 无/的路径
     */
    private String cutSlash(String path) {
        if (path.startsWith("/")) {
            return cutSlash(path.substring(1));
        }
        if (path.endsWith("/")) {
            return cutSlash(path.substring(0, path.length() - 1));
        }
        return path;
    }
}
