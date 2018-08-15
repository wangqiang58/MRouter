package com.wuba.mobile.middle.mis.protocol.router.table;

import java.util.Map;

/**
 * author : wangqiang
 * date : 2018/8/15
 * desc :路由表
 */
public interface RouteTable {

    /**
     * Mapping between uri and target,the target maybe
     * Activity Fragment
     *
     * @param map
     */
    void handle(Map<String, Class<?>> map);
}
