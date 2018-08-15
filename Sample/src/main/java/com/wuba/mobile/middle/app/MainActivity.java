package com.wuba.mobile.middle.app;

import android.app.Activity;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.wuba.mobile.annotation.Route;
import com.wuba.mobile.middle.mis.protocol.router.RouteStatus;
import com.wuba.mobile.middle.mis.protocol.router.Router;
import com.wuba.mobile.middle.mis.protocol.router.RouterCallback;
import com.wuba.mobile.middle.mis.protocol.router.table.RouteTable;

import java.util.Map;

/**
 * author : wangqiang
 * date : 2018/8/15
 * desc :
 */
@Route(path = "/im/main")
public class MainActivity extends Activity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //动态添加路由表
        Router.handleRouteTable(new RouteTable() {
            @Override
            public void handle(Map<String, Class<?>> map) {
                map.put("dynamic", FirstActvity.class);
            }
        });

        this.findViewById(R.id.main_jump_to_First).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Router.build("/im/first").callback(new RouterCallback() {
                    @Override
                    public void callback(RouteStatus status, Uri uri, String message) {

                    }
                }).go(MainActivity.this);
            }
        });
    }
}
