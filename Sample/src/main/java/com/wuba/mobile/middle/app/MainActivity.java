package com.wuba.mobile.middle.app;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Toast;

import com.wuba.mis.router.apt.SampleRouteTable;
import com.wuba.mis.router.apt.Submodule1RouteTable;
import com.wuba.mis.router.apt.Submodule2RouteTable;
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
        Router.openLog();
        //动态添加路由表
        Router.handleRouteTable(new SampleRouteTable());
        Router.handleRouteTable(new Submodule1RouteTable());
        Router.handleRouteTable(new Submodule2RouteTable());

        this.findViewById(R.id.im_args).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Router.build("/im/args").with("args", "hello ,word!").go(MainActivity.this);
            }
        });

        this.findViewById(R.id.im_result).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Router.build("/im/result").requestCode(100).callback(new RouterCallback() {
                    @Override
                    public void callback(RouteStatus status, Uri uri, String message) {

                    }
                }).with("args", "hello ,word2!").go(MainActivity.this);
            }
        });

        this.findViewById(R.id.main_jump_to_third).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Router.build("/im/third").go(MainActivity.this);
            }
        });

        this.findViewById(R.id.main_get_data_from_webview).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Router.build("/im/webview").with("url", "file:///android_asset/scheme.html").go(MainActivity.this);
                }
        });

        this.findViewById(R.id.id_implicit_native).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Router.build("").go(MainActivity.this);
            }
        });

        this.findViewById(R.id.id_implicit_page).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Router.build("router://implicit?id=9527&status=success").go(MainActivity.this);
            }
        });

        this.findViewById(R.id.id_module1).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Router.build("/submodule1/main1").go(MainActivity.this);
            }
        });


    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == Activity.RESULT_OK && requestCode == 100) {
            Toast.makeText(this, data.getStringExtra("result"), Toast.LENGTH_LONG).show();
        }
    }
}
