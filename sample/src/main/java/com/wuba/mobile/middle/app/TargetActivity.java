package com.wuba.mobile.middle.app;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;

import com.wuba.mobile.annotation.Route;

/**
 * author : wangqiang
 * date : 2018/8/27
 * desc :
 */
@Route(path = "router://mis.android/target")
public class TargetActivity extends Activity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_target);
    }
}
