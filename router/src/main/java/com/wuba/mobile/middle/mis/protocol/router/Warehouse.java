package com.wuba.mobile.middle.mis.protocol.router;

import java.util.HashMap;
import java.util.Map;

/**
 * author : wangqiang
 * date : 2018/8/15
 * desc :
 */
public final class Warehouse {

    // Cache route and metas
    public final static Map<String, Class<?>> routeTable = new HashMap<>();

    static void clear() {
        routeTable.clear();
    }

}
