package com.wuba.mobile.middle.mis.protocol.router;

import android.content.Context;

import com.wuba.mobile.middle.mis.protocol.router.util.ClassUtil;
import com.wuba.mobile.middle.mis.protocol.router.util.PackageUtil;
import com.wuba.mobile.middle.mis.protocol.router.util.RLog;

import java.util.Set;

/**
 * author : wangqiang
 * date : 2018/8/16
 * desc :
 * 1. create instance when it first used
 * 2.handler Multi-Module re
 */
public class LogisticsCenter {

    private static final String TAG = LogisticsCenter.class.getSimpleName();
    private static boolean registerByPlugin;


    public synchronized static void init(Context context) {
        long startInit = System.currentTimeMillis();
        if (registerByPlugin) {
            RLog.i(TAG, "Load router map by arouter-auto-register plugin.");
        } else {
            Set<String> routeMap;
            if (_Router.debuggable() || PackageUtil.isNewVersion(context)) {
            //  routeMap = ClassUtil.getFileNameByPackageName(context,context.getPackageName())
            }
        }
    }
}
