package com.wuba.mobile.middle.mis.protocol.router.matcher;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.net.Uri;
import android.support.annotation.Nullable;

import com.wuba.mobile.middle.mis.protocol.router.RouteRequest;

/**
 * author : wangqiang
 * date : 2018/8/23
 * desc :
 */
public class ImplicitMatcher extends AbsImplicitMatcher {

    @Override
    public boolean match(Context context, Uri uri, @Nullable String route, RouteRequest routeRequest) {
        if (uri.toString().toLowerCase().startsWith("http://") || uri.toString().toLowerCase().startsWith("https://"))
            return false;
        ResolveInfo resolveInfo = context.getPackageManager().resolveActivity(new Intent(Intent.ACTION_VIEW, uri), PackageManager.MATCH_DEFAULT_ONLY);
        if (resolveInfo != null) {
            if (uri.getQuery() != null) {
                parseParms(uri, routeRequest);
            }
            return true;
        }
        return false;
    }
}
