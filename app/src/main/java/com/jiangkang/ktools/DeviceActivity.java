package com.jiangkang.ktools;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.jiangkang.tools.device.DeviceUtils;
import com.jiangkang.tools.utils.ToastUtils;

public class DeviceActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_device);
//        ToastUtils.showShortToast(DeviceUtils.getIpAddress());
    }
}
