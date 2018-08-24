package com.wuba.mobile.middle.mis.protocol.router.matcher;

import android.net.Uri;
import android.os.Bundle;

import com.wuba.mobile.middle.mis.protocol.router.RouteRequest;

import java.util.Iterator;
import java.util.List;
import java.util.Set;

/**
 * author : wangqiang
 * date : 2018/8/23
 * desc :
 */
public abstract class AbsMatcher implements Matcher {

    protected void parseParms(Uri uri, RouteRequest request) {

        if (uri.getAuthority() != null) {
            Bundle bundle = request.getExtra();
            if (bundle == null) {
                bundle = new Bundle();
                request.setExtra(bundle);
            }
            Set<String> keys = uri.getQueryParameterNames();
            Iterator<String> iterable = keys.iterator();
            while (iterable.hasNext()) {
                String key = iterable.next();
                List<String> vals = uri.getQueryParameters(key);
                if (vals.size() > 1) {
                    bundle.putStringArray(key, vals.toArray(new String[0]));
                } else if (vals.size() == 1) {
                    bundle.putString(key, vals.get(0));
                }
            }
        }
    }

    protected boolean isEmpty(CharSequence str) {
        return str == null || str.length() == 0;
    }
}
