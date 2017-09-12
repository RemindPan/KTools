package com.jiangkang.ktools;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class IOActivity extends AppCompatActivity {


    public static void launch(Context context, Bundle bundle) {
        Intent intent = new Intent(context, IOActivity.class);
        if (bundle != null) {
            intent.putExtras(bundle);
        }
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_io);
    }


}
