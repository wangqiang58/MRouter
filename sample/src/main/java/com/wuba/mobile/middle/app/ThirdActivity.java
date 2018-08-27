package com.wuba.mobile.middle.app;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.FrameLayout;

import com.wuba.mobile.annotation.Route;
import com.wuba.mobile.middle.app.fragment.FirstFragment;
import com.wuba.mobile.middle.mis.protocol.router.Router;

/**
 * author : wangqiang
 * date : 2018/8/24
 * desc :
 */
@Route(path = "/im/third")
public class ThirdActivity extends Activity {

    private FrameLayout mFrameLayout;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_third);
        FirstFragment fragment = (FirstFragment) Router.build("/im/fragment1").getFragment(this);
        FragmentManager fm = getFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        ft.add(R.id.framen_container, fragment);
        ft.commitAllowingStateLoss();

    }
}
