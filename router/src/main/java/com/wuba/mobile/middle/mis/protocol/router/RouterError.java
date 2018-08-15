package com.wuba.mobile.middle.mis.protocol.router;

/**
 * this is project PluginMiddleware
 *
 * @description 抽象路由类，继承或实现该类用于实现父路由或子节点路由
 * @create liqiuzuo
 * @data 2018/5/29
 */

public class RouterError {

    public static final int NO_TARGET = 0xff000001;
    public static final int NO_MATCH_TARGET = 0xff000002;
    public static final int REQ_INTERCEPTED = 0xff000003;
    public static final int WRONG_WAY_FOR_EXECUTE = 0xff000004;

    protected RouterError(){

    }

    public void onError(int code,String msg,RouteRequest request){

    }

}
