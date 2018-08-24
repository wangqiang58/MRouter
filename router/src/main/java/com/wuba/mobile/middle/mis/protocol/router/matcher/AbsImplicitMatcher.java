package com.wuba.mobile.middle.mis.protocol.router.matcher;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.annotation.Nullable;

/**
 * author : wangqiang
 * date : 2018/8/23
 * desc :隐式调用
 */
public abstract class AbsImplicitMatcher extends AbsMatcher {

    @Override
    public Object generate(Context context, Uri uri, @Nullable Class<?> target) {
        return new Intent(Intent.ACTION_VIEW, uri);
    }
}
