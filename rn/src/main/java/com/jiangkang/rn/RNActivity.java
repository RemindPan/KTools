package com.jiangkang.rn;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.facebook.react.ReactActivity;

import javax.annotation.Nullable;

public class RNActivity extends ReactActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rn);
    }


    @Nullable
    @Override
    protected String getMainComponentName() {
        return "navigation";
    }
}
