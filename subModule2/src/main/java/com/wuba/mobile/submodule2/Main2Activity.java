package com.wuba.mobile.submodule2;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.wuba.mobile.annotation.Route;

@Route(path = "/crm/main2")
public class Main2Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
    }
}
