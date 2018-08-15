package com.wuba.mobile.middle.mis.protocol.router;

import android.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.AnimRes;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;

/**
 * this is project PluginMiddleware
 *
 * @description my router interface for all subRouters and their parent Router.
 * @create liqiuzuo
 * @data 2018/5/29
 */

public interface IRouter {

    IRouter build(Uri uri);

    IRouter build(RouteRequest routeRequest);

    IRouter callback(RouterCallback callback);

    IRouter requestCode(int requestCode);

    IRouter with(Bundle bundle);

    @RequiresApi(21)
    IRouter with(PersistableBundle bundle);

    IRouter with(String key, Object value);

    IRouter setType(String type);

    IRouter setAction(String action);

    IRouter anim(@AnimRes int enterAnim, @AnimRes int exitAnim);

    Intent getIntent(@NonNull Object source);

    void go(Context context, RouterCallback callback);

    void go(Context context);

    void go(Fragment fragment, RouterCallback callback);

    void go(Fragment fragment);

    void go(android.support.v4.app.Fragment fragment, RouterCallback callback);

    void go(android.support.v4.app.Fragment fragment);
}
