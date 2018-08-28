package com.wuba.mobile.middle.app;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.webkit.WebView;

import com.wuba.mobile.annotation.Route;

/**
 * author : wangqiang
 * date : 2018/8/27
 * desc :
 */
@Route(path = "/im/webview")
public class WebViewActivity extends Activity {

    private WebView mWebView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_native);
        mWebView = (WebView) this.findViewById(R.id.id_webview);
        mWebView.loadUrl("file:///android_asset/scheme.html");

    }
}
