package com.wuba.mobile.submodule1;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.wuba.mobile.annotation.Route;

@Route(path = "/submodule1/main1")
public class Main1Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main1);
    }
}
