package com.wuba.mobile.middle.app;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.Toast;

/**
 * author : wangqiang
 * date : 2018/8/27
 * desc :
 */
public class ImplicitActivity extends Activity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_implicit);
        String id = getIntent().getStringExtra("id");
        Toast.makeText(this, "id:" + id, Toast.LENGTH_LONG).show();
    }
}
