package com.wuba.mobile.middle.app;

import android.app.Activity;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;

import com.wuba.mobile.middle.mis.protocol.router.RouteStatus;
import com.wuba.mobile.middle.mis.protocol.router.Router;
import com.wuba.mobile.middle.mis.protocol.router.RouterCallback;

/**
 * author : wangqiang
 * date : 2018/8/27
 * desc :提供js调用native统一入口
 */
public class SchemeFilterActivity extends Activity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Uri uri = getIntent().getData();
        Router.build(uri).callback(new RouterCallback() {
            @Override
            public void callback(RouteStatus status, Uri uri, String message) {
                finish();
            }
        }).go(this);
    }
}
