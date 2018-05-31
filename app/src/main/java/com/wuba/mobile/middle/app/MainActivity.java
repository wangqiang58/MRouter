package com.wuba.mobile.middle.app;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.wuba.mobile.base.common.callback.IRequestCallBack;
import com.wuba.mobile.middle.app.route.MisRouter;

import java.util.HashMap;

/**
 * this is project PluginMiddleware
 *
 * @description He is so lazy that he can't write anything.
 * @create liqiuzuo
 * @data 2018/5/31
 */

public class MainActivity extends Activity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void onClick(View view){
        int id = view.getId();
        if (id == R.id.main_get_data_from_C){
            MisRouter.getInstance().build("http://mis.com/C/getData?content=1").go(this,new IRequestCallBack(){
                @Override
                public void onSuccess(String s, Object o, HashMap hashMap) {

                }

                @Override
                public void onFail(String s, String s1, String s2, HashMap hashMap) {

                }
            });
        }else if(id == R.id.main_jump_to_A){
            MisRouter.getInstance().build("http://mis.com/A/getData?").requestCode(100).go(this);
        }else if(id == R.id.main_jump_to_A1){
            MisRouter.getInstance().build("http://mis.com/A_A1/showPage?").go(this);
        }else if(id == R.id.main_jump_to_B){
            MisRouter.getInstance().build("").target("B").action("showPage").with("content","I am from Main to show myself!").go(this);
        }
    }
}
