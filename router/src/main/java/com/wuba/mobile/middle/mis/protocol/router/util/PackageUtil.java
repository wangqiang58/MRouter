package com.wuba.mobile.middle.mis.protocol.router.util;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.text.TextUtils;

import static com.wuba.mobile.middle.mis.protocol.router.util.Consts.AROUTER_SP_CACHE_KEY;
import static com.wuba.mobile.middle.mis.protocol.router.util.Consts.LAST_VERSION_CODE;
import static com.wuba.mobile.middle.mis.protocol.router.util.Consts.LAST_VERSION_NAME;

/**
 * author : wangqiang
 * date : 2018/8/16
 * desc :
 */
public class PackageUtil {
    private static String NEW_VERSION_NAME;
    private static int NEW_VERSION_CODE;

    public static boolean isNewVersion(Context context) {
        PackageInfo packageInfo = getPackInfo(context);
        if (packageInfo != null) {
            String versionName = packageInfo.versionName;
            int versionCode = packageInfo.versionCode;
            SharedPreferences sp = context.getSharedPreferences(AROUTER_SP_CACHE_KEY, Context.MODE_PRIVATE);
            if (!TextUtils.equals(versionName, sp.getString(LAST_VERSION_NAME, null)) || versionCode != sp.getInt(LAST_VERSION_CODE, -1)) {
                NEW_VERSION_CODE = versionCode;
                NEW_VERSION_NAME = versionName;
                return true;
            } else {
                return false;
            }
        } else {
            return true;
        }
    }

    public static void updateVersion(Context context) {
        if (!TextUtils.isEmpty(NEW_VERSION_NAME) && NEW_VERSION_CODE != 0) {
            SharedPreferences sp = context.getSharedPreferences(AROUTER_SP_CACHE_KEY, Context.MODE_PRIVATE);
            sp.edit().putString(LAST_VERSION_NAME, NEW_VERSION_NAME).putInt(LAST_VERSION_CODE, NEW_VERSION_CODE).apply();
        }
    }

    private static PackageInfo getPackInfo(Context context) {
        PackageInfo packageInfo = null;
        try {
            packageInfo = context.getPackageManager().getPackageInfo(context.getPackageName(), PackageManager.GET_CONFIGURATIONS);
        } catch (Exception e) {
            RLog.e("Get package info erro");
        }
        return packageInfo;
    }
}
