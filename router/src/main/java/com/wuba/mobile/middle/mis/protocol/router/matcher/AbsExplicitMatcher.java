package com.wuba.mobile.middle.mis.protocol.router.matcher;

import android.app.Activity;
import android.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.annotation.Nullable;

/**
 * author : wangqiang
 * date : 2018/8/23
 * desc :显示跳转
 */
public abstract class AbsExplicitMatcher extends AbsMatcher {

    @Override
    public Object generate(Context context, Uri uri, @Nullable Class<?> target) {
        if (target == null) {
            return null;
        }
        Object result = null;

        if (Activity.class.isAssignableFrom(target)) {
            result = new Intent(context, target);
        } else if (Fragment.class.isAssignableFrom(target) || android.support.v4.app.Fragment.class.isAssignableFrom(target)) {
            try {
                result = target.newInstance();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
        return result;
    }
}
