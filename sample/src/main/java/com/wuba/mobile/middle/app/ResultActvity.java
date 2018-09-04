package com.wuba.mobile.middle.app;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Toast;

import com.wuba.mobile.annotation.Route;

/**
 * author : wangqiang
 * date : 2018/8/15
 * desc :
 */
@Route(path = "/im/result")
public class ResultActvity extends Activity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        Intent intent = getIntent();
        String args = intent.getStringExtra("args");
        Toast.makeText(this, args, Toast.LENGTH_LONG).show();

        this.findViewById(R.id.main_jump_to_A).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent data = new Intent();
                data.putExtra("result", "哈哈哈");
                setResult(RESULT_OK, data);
                finish();
            }
        });
    }
}
