package com.wuba.mobile.middle.mis.protocol.router;

import android.net.Uri;

import java.io.Serializable;

/**
 * author : wangqiang
 * date : 2018/8/15
 * desc :路由回调
 */
public interface RouterCallback extends Serializable {

    void callback(RouteStatus status, Uri uri, String message);
}
