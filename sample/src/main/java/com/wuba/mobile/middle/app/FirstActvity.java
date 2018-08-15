package com.wuba.mobile.middle.app;

import android.app.Activity;

import com.wuba.mobile.annotation.Route;

/**
 * author : wangqiang
 * date : 2018/8/15
 * desc :
 */
@Route(path = "/im/first")
public class FirstActvity extends Activity {

    @Override
    public void setContentView(int layoutResID) {
        super.setContentView(layoutResID);
        setContentView(R.layout.activity_first);
    }


}
