package com.wuba.mobile.middle.mis.protocol.router;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

/**
 * author : wangqiang
 * date : 2018/8/15
 * desc :
 */
final public class RouteResponse {

    @NonNull
    private RouteStatus status = RouteStatus.PROCESSING;
    private String message;
    @Nullable
    private Object result;

    private RouteResponse() {
    }

    public static RouteResponse assemble(@NonNull RouteStatus status, @Nullable String msg) {
        RouteResponse response = new RouteResponse();
        response.status = status;
        response.message = msg;
        return response;
    }

    @NonNull
    public RouteStatus getStatus() {
        return status;
    }

    public void setStatus(@NonNull RouteStatus status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    @Nullable
    public Object getResult() {
        return result;
    }

    public void setResult(@Nullable Object result) {
        this.result = result;
    }

}
