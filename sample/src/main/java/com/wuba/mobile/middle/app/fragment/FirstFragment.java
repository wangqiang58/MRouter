package com.wuba.mobile.middle.app.fragment;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.wuba.mobile.annotation.Route;
import com.wuba.mobile.middle.app.R;

/**
 * author : wangqiang
 * date : 2018/8/24
 * desc :
 */
@Route(path = "/im/fragment1")
public class FirstFragment extends Fragment {

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_first, container,false);
    }
}
