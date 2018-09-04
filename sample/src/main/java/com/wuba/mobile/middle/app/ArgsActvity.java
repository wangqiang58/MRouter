package com.wuba.mobile.middle.app;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.Toast;

import com.wuba.mobile.annotation.Route;

/**
 * author : wangqiang
 * date : 2018/8/15
 * desc :
 */
@Route(path = "/im/first")
public class FirstActvity extends Activity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first);
        Intent intent = getIntent();
        String args = intent.getStringExtra("args");
        Toast.makeText(this, args, Toast.LENGTH_LONG).show();
    }

}
